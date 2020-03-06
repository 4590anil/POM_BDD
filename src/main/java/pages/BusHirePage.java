package pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.LogStatus;

import config.OR;
import utilities.ExtentReport;

public class BusHirePage {

	WebDriver driver;

	public BusHirePage(WebDriver driver) {
		this.driver = driver;
	}

	public void validateURL(String URL) {

		String currURL = driver.getCurrentUrl();
		assertEquals(currURL, URL);
		ExtentReport.logger.log(LogStatus.PASS,
				"The url validation passed - " + currURL + " matches with expected URL - " + URL);
	}

}
