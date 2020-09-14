package projectActivities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author MATHENASHAIK
 *
 */
public class Activity2 {
	
	WebDriver driver;
			
  @Test
  public void testActivity2() throws InterruptedException {
	  var someimage = driver.findElement(By.xpath("//div[@id='divLogo']"));
	  var myimg = someimage.findElements(By.tagName("img"));
	  System.out.println(myimg.size());
	  
	  //Print the URL to the console
	  System.out.println(myimg.get(0).getAttribute("src"));
  }
  
  @BeforeMethod
  public void beforeMethod() throws InterruptedException {
	  //Create a new instance of the Firefox driver
      driver = new FirefoxDriver();     

      //Open browser
      driver.get("http://alchemy.hguy.co/orangehrm");
      Thread.sleep(10000);
      
  }

  @AfterMethod
  public void afterMethod() {
	  //close the browser
	  driver.quit();
  }
}
