package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


import java.time.Duration;
import java.util.Iterator;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClass.BaseClass;

//Page class for the Home page of the website
public class HomePage extends BaseClass
{
	
	//Webpage elements of the current page used for running the test
	@FindBy(id="user-name")
	WebElement userName;
	@FindBy(className="job-title")
	WebElement jobTitle;
	@FindBy(id="searchbox")
	WebElement searchBox;
	@FindBy(xpath = "//div[@class='modern-search-center__left']//ul/li[4]/button")
	WebElement appsAndTools;
	@FindBy(xpath="//*[@class='ng-binding ng-scope' and text()='TruTime']")
	WebElement truTime;
	@FindBy(id="search-button")
	WebElement searchButton;
	
	//Variables used to switch window handles
	Iterator<String> iter;
	String mainTab;
	String truTimeTab;
	
	//Constructor to instantiate the Page Object
	public HomePage() 
	{
		PageFactory.initElements(driver, this);
	}
	
	//Method to get the Username and Job title of the user
	public void getUserdetails()
	{
		System.out.println("_______________________________________________________________________________________________________");
		System.out.println(userName.getText());
		System.out.println(jobTitle.getText());
		System.out.println("_______________________________________________________________________________________________________");
	}
	
	//Method to validate the presence of the search box in the web page
	public void validateSearchBox()
	{
		assertTrue(searchBox.isDisplayed());
		searchBox.click();
	}
	
	//Method to search for TruTime in search bar
	public void searchTruTime()
	{
		searchBox.sendKeys("TruTime");
	}
	
	//Method to click the search button
	public void clickSearchButton()
	{
		searchButton.click();
	}
	
	//Method to go to the Apps and Tools section after search
	public void clickAppsAnsTools()
	{
		appsAndTools.click();
	}
	
	//Method to click on The TruTime and navigate to the next page
	public void clickTruTime()
	{
		fWait.withTimeout(Duration.ofSeconds(10))
		.pollingEvery(Duration.ofSeconds(2))
		.ignoring(StaleElementReferenceException.class)
		.until(driver -> {
			truTime.click();
			return driver.getWindowHandles().size() > 1;
		});

		iter = driver.getWindowHandles().iterator();
		mainTab = iter.next();
		truTimeTab = iter.next();
		driver.switchTo().window(truTimeTab);
	}
	
	//Method to verify the current page Title
	public void verifyPage()
	{
		check = driver.getTitle();
		assertEquals(check, "Be.Cognizant");
	}
}
