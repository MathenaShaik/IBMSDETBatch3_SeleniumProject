package projectActivities;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

/**
 * 
 * @author MATHENASHAIK
 *
 */
public class Activity3 {
	
	WebDriver driver;
	public static final String INPUT_USERNAME = "//input[@id='txtUsername']";
	public static final String INPUT_PASSWORD = "//input[@id='txtPassword']";
	public static final String BTN_LOGIN = "//input[@id='btnLogin']";
	public static final String username = "orange";
	public static final String password = "orangepassword123";
	
	//WebDriverWait wait = new WebDriverWait(driver,60);
	
  @Test
  public void testLogin() throws InterruptedException {
	  
	  String expectedTitle = "OrangeHRM";	  
	  //Enter Username	  
	  driver.findElement(By.xpath(INPUT_USERNAME)).sendKeys(username);
	  
	  //Enter Password
	  driver.findElement(By.xpath(INPUT_PASSWORD)).sendKeys(password);
	  
	  //click Login button
	  driver.findElement(By.xpath(BTN_LOGIN)).click();	  	    
	  
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
