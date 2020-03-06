package runnerfiles;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ImmutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.google.common.collect.ImmutableMap;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExtentReport;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

//, tags = { "@Menu" }

@CucumberOptions(features = "src/test/java/featurefiles/", glue = { "stepdef" })
public class TestRunner {
	public static WebDriver driver;
	String DriverPath;
	ITestResult result;
	Properties conf;
	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

	@DataProvider
	public Object[][] features() {
		return testNGCucumberRunner.provideFeatures();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
		testNGCucumberRunner.finish();
	}

	@BeforeSuite
	public void setup() {
		ExtentReport.startReport();

	}

	@BeforeTest
	public void driverSetup() {
		
		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
		
		FirefoxOptions options = new FirefoxOptions();
		options.addCapabilities(new ImmutableCapabilities(
				ImmutableMap.of(CapabilityType.ACCEPT_SSL_CERTS, true, CapabilityType.ACCEPT_INSECURE_CERTS, true)));
		driver = new FirefoxDriver(options);
		ExtentReport.startTest("Red Bus Test Suite");
		ExtentReport.logger.log(LogStatus.INFO, "Test Case execution started in Firefox browser");
		driver.get("https://www.redbus.in/");
		ExtentReport.logger.log(LogStatus.INFO, "Browser launched successfully for redbus site");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void testclosure(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {

			ExtentReport.logger.log(LogStatus.FAIL, "Test Case Failed in " + result.getName());
			ExtentReport.logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
			String screenshotPath = ExtentReport.getScreenshot(driver, result.getName());
			ExtentReport.logger.log(LogStatus.FAIL, ExtentReport.logger.addScreenCapture(screenshotPath));
			System.out.println(screenshotPath);
		}

		else if (result.getStatus() == ITestResult.SKIP)
			ExtentReport.logger.log(LogStatus.SKIP, "Test Case Skipped in " + result.getName());
		else {
			ExtentReport.logger.log(LogStatus.PASS, "Test Case Passed in " + result.getName());
		}
		ExtentReport.endTest();

	}

	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

	@AfterSuite
	public void teardown() {

		ExtentReport.endReport();
		driver.quit();
	}
}
