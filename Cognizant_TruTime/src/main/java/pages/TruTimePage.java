package pages;

import static java.util.stream.Collectors.toList;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import baseClass.BaseClass;

//Page class for the TruTme page of the website
public class TruTimePage extends BaseClass 
{
	//Webpage elements of the current page used for running the test
	@FindBy(xpath="//*[contains(@class,'dayHeadr active')]")
	WebElement currentDate;
	@FindBy(xpath="//*[contains(@class,'dayHeadr')]")
	List<WebElement> listOfDates;
	@FindBy(id="appFrame")
	WebElement iframe;
	@FindBy(linkText = "1C")
	WebElement menuOptions;
	
	//Variables defined to get the system time in the required format
	public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, dd MMM");  
	public static LocalDate now = LocalDate.now(); 
	
	//Variable declared to get the dates from the website
	List<String> dateList;
	
	//Constructor to instantiate the Page Object
	public TruTimePage() 
	{
		PageFactory.initElements(driver, this);
	}
	
	//Method to get the dispayed dates on the TruTime Page
	public void displayedDates()
	{
		
		eWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
		eWait.until(ExpectedConditions.visibilityOfAllElements(listOfDates));
		dateList = listOfDates.stream().map(WebElement::getText).collect(toList());
		System.out.println("_______________________________________________________________________________________________________");
		dateList.forEach(System.out::println);
		System.out.println("_______________________________________________________________________________________________________");
	}
	
	//Method to check the displayed dates against the current system dates
	public void verifyDisplayedDates()
	{
		List<String> list = Arrays.asList(DayOfWeek.values()).stream()
								  .map(k->dtf.format(now.with(k).minusDays(1)))
								  .collect(toList());
		assertTrue(dateList.containsAll(list));
	}
	
	//Method to check the active date is of today
	public void verifyPageCurrentDate()
	{
		String presentPageDate = currentDate.getText();
		assertEquals(presentPageDate, dtf.format(now),"Current Date is different");
	}
	
	//Method to verify the page title
	public void verifyPage()
	{
		eWait.until(ExpectedConditions.visibilityOf(menuOptions));
		check = driver.getTitle();
		assertEquals(check, "OneCognizant");
	}
	
}
