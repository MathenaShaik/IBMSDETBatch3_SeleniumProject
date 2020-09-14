package projectActivities;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

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

/**
 * 
 * @author MATHENASHAIK
 *
 */
public class Activity5 {
	WebDriver driver;
	
	public static final String TAB_MYINFO = "//*[@id='menu_pim_viewMyDetails']";
	public static final String BTN_EDIT = "//*[@value='Edit']";
	public static final String INPUT_FIRSTNAME_EDIT = "//*[@id='personal_txtEmpFirstName']";
	public static final String INPUT_GENDERFEMALE = "//*[@id='personal_optGender_2']";
	public static final String INPUT_GENDERMALE = "//*[@id='personal_optGender_1']";
	public static final String INPUT_NATIONALITY = "//*[@id='personal_cmbNation']";
	public static final String INPUT_DOB = "//*[@id='personal_DOB']";
	public static final String BTN_SAVE_EDIT = "//*[@id='btnSave']";
	
	String editedFirstName = "Test123";
	String editedDOB = "1986-04-23";
	
  @Test
  public void EditUserInfo() throws InterruptedException {
	  
	  //Open the OrangeHRM page and login with credentials provided
	  testLogin();
	  
	  //Find the “My Info” menu item and click it. 
	  //driver.findElement(By.xpath("//b[text()='My Info']")).click();
	  WebElement element = driver.findElement(By.id("menu_pim_viewMyDetails"));
	  JavascriptExecutor executor = (JavascriptExecutor)driver;
	  executor.executeScript("arguments[0].click();", element);
	  
	  WebDriverWait wait = new WebDriverWait(driver,80);	 
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INPUT_FIRSTNAME_EDIT)));
	  
	  //On the new page, click the Edit button
	  driver.findElement(By.xpath(BTN_EDIT)).click();	 
	  //Fill in the Name, Gender, Nationality, and the DOB fields
	  driver.findElement(By.xpath(INPUT_FIRSTNAME_EDIT)).clear();
	  driver.findElement(By.xpath(INPUT_FIRSTNAME_EDIT)).sendKeys(editedFirstName);
	  Thread.sleep(1000);
	  if(driver.findElement(By.xpath(INPUT_GENDERFEMALE)).isSelected()) {
	  driver.findElement(By.xpath(INPUT_GENDERMALE)).click();
	  }else {
		  driver.findElement(By.xpath(INPUT_GENDERFEMALE)).click();
	  }
	  
	  Select nationality = new Select(driver.findElement(By.xpath(INPUT_NATIONALITY)));
	  nationality.selectByIndex(4);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath(INPUT_DOB)).clear();	
	  driver.findElement(By.xpath(INPUT_DOB)).sendKeys(editedDOB);	  
	  
	  //Click Save
	  driver.findElement(By.xpath(BTN_SAVE_EDIT)).click();	  
	  
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
