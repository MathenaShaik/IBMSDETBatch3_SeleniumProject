package projectActivities;

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
public class Activity6 {
	WebDriver driver;
	public static final String TAB_DIRECTORY = "//a[@id='menu_directory_viewDirectory']";
	public static final String PAGE_SEARCHFORM = "//div[@class='box searchForm toggableForm']";
	public static final String MENU_NAV = "//div[@class='menu']";
	
  @Test
  public void directoryMenuTest() throws InterruptedException {
	  
	  //Open the OrangeHRM page and login with credentials provided
	  testLogin();
	  
	  //Locate the navigation menu
	  driver.findElement(By.xpath(MENU_NAV));
	  
	  //Verify that the “Directory” menu item is visible and clickable
	  if(driver.findElement(By.xpath(TAB_DIRECTORY)).isDisplayed()) {
		  WebDriverWait wait = new WebDriverWait(driver, 5);
		    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TAB_DIRECTORY)));
	  }	  
	  
	  // If clickable, click on the menu item
	  WebElement element = driver.findElement(By.xpath(TAB_DIRECTORY));
	  JavascriptExecutor executor = (JavascriptExecutor)driver;
	  executor.executeScript("arguments[0].click();", element);
	  
	  
	  //Verify that the heading of the page matches “Search Directory”. 
	  WebDriverWait wait = new WebDriverWait(driver, 5);
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(PAGE_SEARCHFORM)));
	    
	    WebElement searchForm = driver.findElement(By.xpath(PAGE_SEARCHFORM));

	    String heading = searchForm.findElement(By.tagName("h1")).getText();
	    
	    if(heading.equalsIgnoreCase("Search Directory")) {
	    	System.out.println("Heading displayed: "+ heading);
	    }else {
	    	System.out.println("Heading not displayed as expected");
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
