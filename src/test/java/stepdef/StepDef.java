package stepdef;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ImmutableCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;

import com.google.common.collect.ImmutableMap;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import runnerfiles.TestRunner;

public class StepDef {

	WebDriver driver = TestRunner.driver;
	HomePage hp = PageFactory.initElements(driver, HomePage.class);
	String menuItem;

	@Given("^user is on redbus homepage$")
	public void user_is_on_redbus_homepage() throws Throwable {

	}

	@When("^user inputs \"([^\"]*)\" and \"([^\"]*)\" destination$")
	public void user_inputs_and_destination(String source, String destination) throws Throwable {

		hp.inputData(source, destination);

	}

	@When("^user clicks on \"([^\"]*)\"$")
	public void user_clicks_on(String menuItem) throws Throwable {

		this.menuItem = menuItem;
		if (menuItem.equals("BUS TICKETS"))
			hp.navigateToBusTicketPage();
		if (menuItem.equals("BUS HIRE"))
			hp.navigateToBusHirePage();
		if (menuItem.equals("PILGRIMAGES"))
			hp.navigateToPilgrimagePage();

	}

	@Then("^validate the url of the page \"([^\"]*)\"$")
	public void validate_the_url(String URL) throws Throwable {

		if (menuItem.equals("BUS TICKETS"))
			hp.navigateToBusTicketPage().validateURL(URL);
		if (menuItem.equals("BUS HIRE"))
			hp.navigateToBusHirePage().validateURL(URL);
		if (menuItem.equals("PILGRIMAGES"))
			hp.navigateToPilgrimagePage().validateURL(URL);
	}

}
