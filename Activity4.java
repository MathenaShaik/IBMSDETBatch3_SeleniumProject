package projectActivities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Activity4{
	
	WebDriver driver;
	public static final String TAB_PIM = "//a[@id='menu_pim_viewPimModule']";
	public static final String TAB_ADDEMPLOYEE = "//a[@id='menu_pim_addEmployee']";
	public static final String INPUT_EMPLOYEENAME_SEARCH = "//input[@id='empsearch_employee_name_empName']";
	public static final String INPUT_EMPLOYEEID_SEARCH = "//input[@id='empsearch_id']";
	public static final String INPUT_EMPLOYEEID_ADD = "//input[@id='employeeId']";
	public static final String INPUT_FIRSTNAME_ADD = "//input[@id='firstName']";
	public static final String INPUT_LASTNAME_ADD = "//input[@id='lastName']";
	public static final String INPUT_BTN_SAVE = "//input[@id='btnSave']";
	public static final String IMG_PROFILE = "//img[@id='empPic']";	
	
	public static String FirstName = "SDUser1";
	public static String LastName = "SkillTest";	
	
  @Test
  public void addEmployee() throws InterruptedException {
	  
	  //login to application with credentials provided
	  testLogin();
	  
	  //Find the PIM option in the menu and click it
	  driver.findElement(By.xpath(TAB_PIM)).click();
	  
	  //Click the Add button to add a new Employee
	  driver.findElement(By.xpath(TAB_ADDEMPLOYEE)).click();
	  
	  WebDriverWait wait = new WebDriverWait(driver,80);	 
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(INPUT_FIRSTNAME_ADD)));
	  	 
	  //Fill in the required fields and click Save
	  driver.findElement(By.xpath(INPUT_FIRSTNAME_ADD)).sendKeys(FirstName);
	  Thread.sleep(1000);
	  driver.findElement(By.xpath(INPUT_LASTNAME_ADD)).sendKeys(LastName);
	  Thread.sleep(1000);
	  String EmployeeID = driver.findElement(By.xpath(INPUT_EMPLOYEEID_ADD)).getAttribute("value");
	  Thread.sleep(1000);
	  System.out.println("Employee ID of new employee added :" + EmployeeID);
	  
	  driver.findElement(By.xpath(INPUT_BTN_SAVE)).click();
	  	  
	  //Navigate to the PIM Page and verify the creation of your employee
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(IMG_PROFILE)));
	  if(driver.findElements(By.xpath(IMG_PROFILE)).size() != 0){
		  System.out.println("Employee created");
		  }else{
		  System.out.println("Employye not created");
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
