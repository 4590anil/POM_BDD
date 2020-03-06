package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReport {

	static ExtentReports extent;
	public static ExtentTest logger;
	public static String ScreenshotPath;

	 public static ExtentReports getInstance() {	
	        return extent;
	    }
	
	public static void startReport() {

		extent = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReport\\TestOutputReport.html", true);
		extent.addSystemInfo("Host Name", "RedBus").addSystemInfo("Environment", "Automation Test Environment")
				.addSystemInfo("User Name", "Anil Rawat");

		extent.loadConfig(new File(System.getProperty("user.dir") + "\\src\\main\\java\\config\\extentConfig.xml"));

		ScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\";

	}

	public static void startTest(String TestName) {

		logger = extent.startTest(TestName);
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);

		System.out.println(destination);

		return destination;
	}

	public static void endTest() {
		
		extent.endTest(logger);
	}

	public static void endReport() {
		extent.flush();

		extent.close();
	}

}
