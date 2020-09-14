package projectActivities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author MATHENASHAIK
 *
 */

public class Activity1 {
	
	WebDriver driver;
	
	@Test
	  public void verifyPageTitle() {
		// Check the title of the page
	      String title = driver.getTitle();          

	      //Print the title of the page
	      System.out.println("Page title is: " + title);         

	          //Assertion for page title
	      Assert.assertEquals(title, "OrangeHRM");      
		  
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
