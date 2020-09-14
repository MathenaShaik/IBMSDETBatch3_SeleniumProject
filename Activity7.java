package projectActivities;

import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
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
public class Activity7 {
	WebDriver driver;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	public static final String MENU_QUALIFICATION = "//a[text='Qualifications']";
	public static final String BTN_ADD_QUALIFICATION = "//input[@id='addWorkExperience']";
	public static final String INPUT_COMPANY = "//input[@id='experience_employer']";
	public static final String INPUT_JOBTITLE = "//input[@id='experience_jobtitle']";
	public static final String INPUT_FROMDATE = "//input[@id='experience_from_date']";
	public static final String INPUT_TODATE = "//input[@id='experience_to_date']";
	public static final String INPUT_COMMENTS = "//*[@id='experience_comments']";
	public static final String BTN_SAVE_QUALIFICATION = "//*[@id='btnWorkExpSave']";
	public static final String FORM_QUALIFICATION = "//*[@id='frmDelWorkExperience']";
	
  @Test
  public void addQualification() throws InterruptedException {
	  
	  //Open the OrangeHRM page and login with credentials provided
	  testLogin();
	  
	  //Find the “My Info” menu item and click it
	  WebElement element = driver.findElement(By.id("menu_pim_viewMyDetails"));
	  JavascriptExecutor executor = (JavascriptExecutor)driver;
	  executor.executeScript("arguments[0].click();", element);
	  
	  WebDriverWait wait = new WebDriverWait(driver,80);	 
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Activity5.INPUT_FIRSTNAME_EDIT)));
	  	  
	  //On the new page, find the Qualification option on the left side menu and click it.
	  	  
	  WebElement leftNav = driver.findElement(By.xpath("//*[@id='sidenav']"));
	  List<WebElement> leftNavOptions = leftNav.findElements(By.tagName("li"));
	  
	  for(int i= 0; i< leftNavOptions.size() ; i++) {
		  System.out.println("Menu Items text value: "+leftNavOptions.get(i).getText());
		  if(leftNavOptions.get(i).getText().equalsIgnoreCase("Qualifications")) {
			  leftNavOptions.get(i).click();
			  break;
		  }		  
	  }	  
      
	  Date date = new Date();
	  
	  /**
	   * Add Work Experience and click Save
	   */    
	  
	  //Click Add button
	  driver.findElement(By.xpath(BTN_ADD_QUALIFICATION)).click();
	  
	  //Company
	  String CompanyName= "IBMSDET"+date.getTime();
	  driver.findElement(By.xpath(INPUT_COMPANY)).sendKeys(CompanyName);	
	  Thread.sleep(1000);
	  //JobTitle
	  driver.findElement(By.xpath(INPUT_JOBTITLE)).sendKeys("TESTAUTENG");
	  Thread.sleep(1000);
	  //FromDate
	  driver.findElement(By.xpath(INPUT_FROMDATE)).clear();
	  driver.findElement(By.xpath(INPUT_FROMDATE)).sendKeys("2007-07-27");
	  Thread.sleep(1000);
	  //ToDate
	  driver.findElement(By.xpath(INPUT_TODATE)).clear();
	  driver.findElement(By.xpath(INPUT_TODATE)).sendKeys("2020-10-31");
	  Thread.sleep(1000);
	  //Comment
	  driver.findElement(By.xpath(INPUT_COMMENTS)).sendKeys("Adding New Qualification by MS");
	  Thread.sleep(1000);
	  //Save Button
	  driver.findElement(By.xpath(BTN_SAVE_QUALIFICATION)).click();		 
	 	 
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FORM_QUALIFICATION)));	  
	  
	  //Verify Save successful
	  if(driver.findElement(By.xpath(FORM_QUALIFICATION)).getText().toString().contains(CompanyName)) {
		  System.out.println("Qualification Saved successful");
	  }else {
		  System.out.println("Qualification is not saved properly");
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
