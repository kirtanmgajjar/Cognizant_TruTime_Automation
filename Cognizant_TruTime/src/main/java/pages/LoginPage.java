package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import baseClass.BaseClass;

//Page class for the Login page of the website
public class LoginPage extends BaseClass 
{

	//Webpage elements of the current page used for running the test
	@FindBy(id="i0116")
	WebElement signinInputBox;
	@FindBy(id="idSIButton9")
	WebElement nextButton;
	@FindBy(id="i0118")
	WebElement passwordInputBox;
	@FindBy(id="idBtn_Back")
	WebElement option;
	@FindBy(xpath="//*[@data-value='PhoneAppNotification']")
	WebElement choice;
	
	//Constructor to instantiate the Page Object
	public LoginPage() 
	{
		PageFactory.initElements(driver, this);
	}

	//Method to validate the presence of the email textbox
	public void validateEmailTextbox() 
	{
		eWait.until(ExpectedConditions.visibilityOf(signinInputBox));
		assertTrue(signinInputBox.isEnabled());
	}

	//Method to enter the email address
	public void enterEmail() 
	{
		signinInputBox.sendKeys(username);
	}

	//Method to click on the next button to go the next step
	public void nextButton() 
	{
		nextButton.click();
	}

	//Method to validate the password textbox
	public void validatePasswordTextbox() 
	{

		eWait.until(ExpectedConditions.visibilityOf(passwordInputBox));
		assertTrue(passwordInputBox.isEnabled());
	}

	//Method to enter the password
	public void enterPassword() 
	{
		passwordInputBox.sendKeys(password);

	}

	//Method to select the option to verify identity 
	//Using the request on Microsoft Authenticator App
	public void selectLoginOption() 
	{
		choice.click();
		eWait.until(ExpectedConditions.visibilityOf(option));
	}

	//Method to choose option No for Stay signed in
	public void rememberSignIn()
	{
		option.click();
	}

	//Method to verify the page title
	public void verifyPage() 
	{
		check = driver.getTitle();
		assertEquals(check, "Sign in to your account");
	}
}
