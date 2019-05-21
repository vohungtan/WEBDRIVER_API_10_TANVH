package selenium;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_09_Button_Radio_Checkbox_Alert {
	
  WebDriver driver;
  JavascriptExecutor executor;
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();	  
	  driver.manage().window().maximize();
	  
	  executor = (JavascriptExecutor) driver;
  }
  
  @Test
  public void TC_01_JavascriptExecutor() throws Exception {
	  
	  driver.get("http://live.guru99.com/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
	  
	  
	  executor.executeScript("arguments[0].click();", myAccountLink);	  	  
	  Thread.sleep(3000);
	  Assert.assertEquals("http://live.guru99.com/index.php/customer/account/login/", driver.getCurrentUrl());
	  
	  WebElement createNewAccountButton = driver.findElement(By.xpath("//a[@title='Create an Account']"));
	  
	  Thread.sleep(3000);
	  executor.executeScript("arguments[0].click();", createNewAccountButton);
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
  }
  
  @Test
  public void TC_02_Checkbox() throws Exception {
	  driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  WebElement dualZoneCheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
	  
	  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dualZoneCheckbox);
	  Thread.sleep(2000);
	  
	  Assert.assertTrue(dualZoneCheckbox.isSelected());
	  
	  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dualZoneCheckbox);
	  Thread.sleep(2000);
	  
	  Assert.assertFalse(dualZoneCheckbox.isSelected());
	  
  }  
  
  @Test
  public void TC_03_Radiobutton() throws Exception {
	  driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  WebElement carEngine = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']//preceding-sibling::input"));
	  
	  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", carEngine);
	  Thread.sleep(2000);
	  
	  if(!carEngine.isSelected()) {
		  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", carEngine);
		  Thread.sleep(2000);
	  }
	  Assert.assertTrue(carEngine.isSelected());
	  
  } 
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
