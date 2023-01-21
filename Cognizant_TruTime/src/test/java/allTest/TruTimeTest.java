package allTest;


import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.google.common.io.Files;

import baseClass.BaseClass;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;
import pages.TruTimePage;

//Step definitions class of the TruTime feature
public class TruTimeTest extends BaseClass
{
	//Defining the variables of all Page Object Class required by the current test
	public static LoginPage loginPage = new LoginPage();
	public static HomePage homePage = new HomePage();
	public static TruTimePage trutimePage = new TruTimePage();
	int count=1;
	
	//Secanario 1: Step 1
	@Given("User is on the Login page")
	public void validateLoginPage()
	{
		loginPage.verifyPage();
	}
	
	//Secanario 1: Step 2
	@When("User enters the Email ID")
	public void emailID()
	{
		loginPage.validateEmailTextbox();
		loginPage.enterEmail();
		
	}
	
	//Secanario 1: Step 3,5
	@And("User clicks on Next button")
	public void nextButton()
	{
		loginPage.nextButton();
		
	}
	
	//Secanario 1: Step 4
	@And("User enters the Password")
	public void enterPassword()
	{
		loginPage.validatePasswordTextbox();
		loginPage.enterPassword();
		
	}
	
	//Secanario 1: Step 6
	@And("User selects the required login options")
	public void loginOptions()
	{
		loginPage.selectLoginOption();
	}
	
	//Secanario 1: Step 7
	@And("User clicks No for Remember sign in")
	public void rememberOption()
	{
		loginPage.rememberSignIn();
	}
	
	//Secanario 1: Step 8
	//Secanario 2: Step 1
	@Then("User is redirected to Home page")
	@Given("User is on Home Page")
	public void verify()
	{
		homePage.verifyPage();
	}
	
	//Secanario 2: Step 2
	@When("User clicks on Search bar")
	public void searchBar()
	{
		homePage.getUserdetails();
		homePage.validateSearchBox();
	}
	
	//Secanario 2: Step 3
	@And("User enters TruTime")
	public void enterTruTime()
	{
		homePage.searchTruTime();
	}
	
	//Secanario 2: Step 4
	@And("User clicks on Search button")
	public void clickSearchButton()
	{
		homePage.clickSearchButton();
	}
	
	//Secanario 2: Step 5
	@And("User clicks on Apps & Tools")
	public void clickAppsAndTools()
	{
		homePage.clickAppsAnsTools();
	}
	
	//Secanario 2: Step 6
	@And("User clicks on TruTime")
	public void clickTruTime()
	{
		homePage.clickTruTime();
	}
	
	//Secanario 2: Step 7
	//Secanario 3: Step 1
	@Then("User is redirected to TruTime webpage")
	@Given("User is on TruTime Page")
	public void Verify()
	{
		trutimePage.verifyPage();
	}
	
	//Secanario 3: Step 2
	@Then("The dates for the current week are correctly displayed")
	public void verifyAndDisplayCurentWeekDates()
	{
		trutimePage.displayedDates();
		trutimePage.verifyDisplayedDates();
	}
	
	//Secanario 3: Step 3
	@And("Today's date is selected")
	public void currentDate()
	{
		trutimePage.verifyPageCurrentDate();
	}
	
	//Takes the Screenshot after every failed step
	@AfterStep
	public void tearDown(Scenario scenario) throws Exception
	{
		//Screensot is captured and saved as File
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				
		//Screenshot is saved in the required directory
		Files.copy(f,new File("./Screenshots/" + scenario.getName() + "_" + count + ".png"));
		count++;
		if(scenario.isFailed())
		{
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, "");
			scenario.attach(screenshot, "image/png","Screenshot");
		}
	}
	
	
	
}
