package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.LogStatus;

import config.OR;
import utilities.ExtentReport;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id = OR.txtSourceLocation)
	static WebElement SourceLocation;

	@FindBy(css = OR.selFirstSelectionLocation)
	static WebElement FirstSelectionLocation;

	@FindBy(id = OR.txtDestinationLocation)
	static WebElement DestinationLocation;

	@FindBy(linkText = OR.menuBusHire)
	static WebElement menuBusHire;

	@FindBy(linkText = OR.menuBusTicket)
	static WebElement menuBusTicket;

	@FindBy(linkText = OR.menuPilgrimages)
	static WebElement menuPilgrimages;

	public void inputData(String sourceLocation, String destinationLocation) {

		SourceLocation.clear();
		SourceLocation.sendKeys(sourceLocation);
		FirstSelectionLocation.click();
		DestinationLocation.clear();
		DestinationLocation.sendKeys(destinationLocation);
		FirstSelectionLocation.click();

		ExtentReport.logger.log(LogStatus.INFO,
				"User searched for route - " + sourceLocation + " to " + destinationLocation);
		

	}

	public BusHirePage navigateToBusHirePage() {
		
		menuBusHire.click();
		ExtentReport.logger.log(LogStatus.PASS,
				"Clicked on " + OR.menuBusHire + " successfully ");
		return PageFactory.initElements(driver, BusHirePage.class);
	}

	public BusTicketPage navigateToBusTicketPage() {
		menuBusTicket.click();
		ExtentReport.logger.log(LogStatus.PASS,
				"Clicked on " + OR.menuBusTicket + " successfully ");
		return PageFactory.initElements(driver, BusTicketPage.class);
	}

	public PilgrimagePage navigateToPilgrimagePage() {
		menuPilgrimages.click();
		ExtentReport.logger.log(LogStatus.PASS,
				"Clicked on " + OR.menuPilgrimages + " successfully ");
		return PageFactory.initElements(driver, PilgrimagePage.class);
	}

}
