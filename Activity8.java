package projectActivities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author MATHENASHAIK
 *
 */

public class Activity8 {
	
	WebDriver driver;
	public static final String IMG_APPLYLEAVE= "//span[text()='Apply Leave']";
	public static final String DROPDWN_LEAVETYPE="//select[@id='applyleave_txtLeaveType']";
	public static final String INPUT_FROMDATE_LEAVE= "//input[@id='applyleave_txtFromDate']";
	public static final String INPUT_TODATE_LEAVE= "//input[@id='applyleave_txtToDate']";
	public static final String INPUT_COMMENTS_LEAVE= "//*[@id='applyleave_txtComment']";
	public static final String BTN_APPLY_LEAVE="//*[@id='applyBtn']";
	public static final String MENU_MY_LEAVE = "//*[@id='menu_leave_viewMyLeaveList']";
	public static final String INPUT_FROMDATE_MYLEAVE = "//*[@id='calFromDate']";
	public static final String INPUT_TODATE_MYLEAVE = "//*[@id='calToDate']";
	public static final String BTN_SEARCH = "//*[@id='btnSearch']";
	public static final String TABLE_SEARCHRESULTS = "//*[@id='resultTable']";
	public static final String TAB_MYLEAVE = "//*[@id='menu_leave_viewMyLeaveList']";
	
	String FromDate="2020-12-15";
	String ToDate="2020-12-15";
	String Comments="Apply Leave for Test by MS";
	WebDriverWait wait;
	
  @Test
  public void applyLeave() throws InterruptedException {
	  
	  //Open the OrangeHRM page and login with credentials provided
	   testLogin();
	  
	  //Navigate to the Dashboard page and click on the Apply Leave option. 
	   driver.findElement(By.xpath(IMG_APPLYLEAVE)).click();
	     
	   wait = new WebDriverWait(driver,80);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DROPDWN_LEAVETYPE)));
	  
	  //Select leave type and duration of the leave
	   Select leaveType = new Select(driver.findElement(By.xpath(DROPDWN_LEAVETYPE)));
	   leaveType.selectByIndex(1);
	   
	   driver.findElement(By.xpath(INPUT_FROMDATE_LEAVE)).clear();
	   driver.findElement(By.xpath(INPUT_FROMDATE_LEAVE)).sendKeys(FromDate);
	   Thread.sleep(1000);
	   driver.findElement(By.xpath(INPUT_TODATE_LEAVE)).clear();
	   driver.findElement(By.xpath(INPUT_TODATE_LEAVE)).sendKeys(ToDate);
	   Thread.sleep(1000);
	   driver.findElement(By.xpath(INPUT_COMMENTS_LEAVE)).sendKeys(Comments);
	   Thread.sleep(1000);
	  
	  //Click Apply	   
		/*
		 * WebElement button = driver.findElement(By.xpath(BTN_APPLY_LEAVE));
		 * JavascriptExecutor executor = (JavascriptExecutor)driver;
		 * executor.executeScript("arguments[0].click();", button);
		 */  
	   
	   WebElement leaveApplyForm = driver.findElement(By.id("frmLeaveApply"));
	   WebElement button = leaveApplyForm.findElement(By.id("applyBtn"));
	   button.click();
	   
	  //Navigate to the My Leave page to check the status of leave application
	  //MyLeave Page
	   driver.findElement(By.xpath(TAB_MYLEAVE)).click();
	   Thread.sleep(5000);
	   driver.findElement(By.xpath(INPUT_FROMDATE_MYLEAVE)).clear();
	   driver.findElement(By.xpath(INPUT_FROMDATE_MYLEAVE)).sendKeys(FromDate);
	   Thread.sleep(1000);
	   driver.findElement(By.xpath(INPUT_TODATE_MYLEAVE)).clear();
	   driver.findElement(By.xpath(INPUT_TODATE_MYLEAVE)).sendKeys(ToDate);
	   Thread.sleep(1000);
	   driver.findElement(By.xpath(BTN_SEARCH)).click();
	   
	   WebDriverWait wait = new WebDriverWait(driver,80);	 
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TABLE_SEARCHRESULTS)));
	   
	   WebElement resultTable = driver.findElement(By.xpath(TABLE_SEARCHRESULTS));
	   List<WebElement> rows = resultTable.findElements(By.tagName("tr"));
	   List<WebElement> statusOfLeave = rows.get(1).findElements(By.tagName("td"));
	   String leaveStatus = statusOfLeave.get(5).getText().toString();
	   System.out.println("Status of Leave applied = " +leaveStatus);  
  }
  
public void testLogin() throws InterruptedException {
	  
	  String expectedTitle = "OrangeHRM";	  
	  //Enter Username	  
	  driver.findElement(By.xpath(Activity3.INPUT_USERNAME)).sendKeys(Activity3.username);
	  
	  //Enter Password
	  driver.findElement(By.xpath(Activity3.INPUT_PASSWORD)).sendKeys(Activity3.password);
	  
	  //click Login button
	  driver.findElement(By.xpath(Activity3.BTN_LOGIN)).click();	  	    
	  	  	 
	  wait = new WebDriverWait(driver,80);
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
