package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_04_DataProvider {
	WebDriver driver;
	String root_Folder_Path;
  
  @Parameters("browser")	
  @BeforeClass
  public void initData(String browserName) {
	  if(browserName.equals("firefox")) {
		  driver = new FirefoxDriver();
	  } else if(browserName.equals("chrome")) {
		  root_Folder_Path = System.getProperty("user.dir");
		  System.setProperty("webdriver.chrome.driver", root_Folder_Path + "/lib/chromedriver");
		  driver = new ChromeDriver();
	  } else if(browserName.equals("headlesschrome")) {
		  ChromeOptions options = new ChromeOptions();
		  options.addArguments("headless");
		  options.addArguments("window-size=1280x800");
		  driver = new ChromeDriver(options);
	  } else {
		  System.out.println("Please input correct browser name");
	  }
	  	
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  @Test(dataProvider = "UserAndPassword")
  public void TC_01_LoginWithManyUsers(String username, String password) {
	  System.out.println("Run TESTCASE 01");
	  driver.get("http://live.guru99.com");
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  
	  driver.findElement(By.id("email")).sendKeys(username);
	  driver.findElement(By.id("pass")).sendKeys(password);
	  driver.findElement(By.id("send2")).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
	  driver.findElement(By.xpath("//div[@class='page-header-container']//span[text()='Account']")).click();
	  driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
  }
  
  @DataProvider(name = "UserAndPassword")
  public Object[][] UserInfor(){
	  return new Object[][] {
		  {"auto_test_01@gmail.com", "Pass111"},
		  {"auto_test_05@gmail.com", "123123"},
		  {"auto_test_07@gmail.com", "123123"}
	  };
  }
  
  @AfterClass()
  public void cleanData() {
	  driver.quit();
  }
  
}
