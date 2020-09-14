package projectActivities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/**
 * 
 * @author MATHENASHAIK
 *
 */

public class Activity9 {
	public static final String MENU_LEFTHAND = "//*[@id='sidenav']";
	public static final String MENU_EMERCONTACTS = "//a[text()='Emergency Contacts']";
	public static final String TABLE_EMERCONTACTS = "//table[@id='emgcontact_list']";
	
	WebDriver driver;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	
  @Test
  public void printingEmergencycontacts() throws InterruptedException {
	  
	//Open the OrangeHRM page and login with credentials provided
	  testLogin();
	 
	 //Navigate to the “My Info” page
	  WebElement element = driver.findElement(By.id("menu_pim_viewMyDetails"));
	  JavascriptExecutor executor = (JavascriptExecutor)driver;
	  executor.executeScript("arguments[0].click();", element);
	 Thread.sleep(2000);
	 //Locate the left hand menu	  
	 //Click on the “Emergency Contacts” menu item
	  if(driver.findElement(By.xpath(MENU_LEFTHAND)).isDisplayed()) {
		  //js.executeScript("scroll(0, 250);");	
		//  ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", By.xpath(MENU_LEFTHAND));
		  driver.findElement(By.xpath(MENU_EMERCONTACTS)).click();		 
	  }
	 
	  WebDriverWait wait = new WebDriverWait(driver,80);	 
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TABLE_EMERCONTACTS)));
	 
	  WebElement tableContacts = driver.findElement(By.xpath(TABLE_EMERCONTACTS));
	  //Retrieve table rows & columns to get text values
	  List<WebElement> rows = tableContacts.findElements(By.tagName("tr"));
	  System.out.println(rows.size());
	  
	 //Retrieve information about all the contacts listed in the table
	  for(int i=1 ; i<rows.size();i++) {
		  List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
		//Print all the information to the console
		  //Name of Emergency Contacts - Print
		  System.out.println("Name of Emergency Contact "+i +"=" + columns.get(1).getText());
		  
		  //Relationship - Print
		  System.out.println("Relationship of Emergency Contact "+i +"=" + columns.get(2).getText());
		  
		  //Mobile - Print
		  System.out.println("Mobile of Emergency Contact "+ i + "=" + columns.get(4).getText());
		  
	  }
	 
	 
	  
  }
  
public void testLogin() throws InterruptedException {
	  
	  String expectedTitle = "OrangeHRM";	  
	  //Enter Username	  
	  driver.findElement(By.xpath(Activity3.INPUT_USERNAME)).sendKeys(Activity3.username);
	  
	  //Enter Password
	  driver.findElement(By.xpath(Activity3.INPUT_PASSWORD)).sendKeys(Activity3.password);
	  
	  //click Login button
	  driver.findElement(By.xpath(Activity3.BTN_LOGIN)).click();	  	    
	  
	  WebDriverWait wait = new WebDriverWait(driver,80);	 
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='OrangeHRM']")));
	  
	  //Verify homepage is opened
	  if(driver.getTitle() != null && driver.getTitle().contains(expectedTitle)){
		  System.out.println("Web page is opened");
		}
		else{
		  System.out.println("Web page could not open.");
		}	  
  }    
    
  @BeforeMethod
  public void beforeMethod() throws InterruptedException {
	  //Create a new instance of the Firefox driver
      driver = new FirefoxDriver();     

      //Open browser
      driver.get("http://alchemy.hguy.co/orangehrm");
      Thread.sleep(10000);
      //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INPUT_USERNAME)));
  }

  @AfterMethod
  public void afterMethod() {
	  //close the browser
	  driver.quit();
  }
}
