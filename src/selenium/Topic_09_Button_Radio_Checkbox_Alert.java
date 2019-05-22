package selenium;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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
  
  @Test
  public void TC_04_AcceptAlert() throws Exception {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  WebElement jsAlertButton = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
	  
	  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", jsAlertButton);
	  Thread.sleep(2000);
	  
	  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", jsAlertButton);
	  //jsAlertButton.click();
	  Thread.sleep(2000);
	  
	  Alert alert = driver.switchTo().alert();
	  
	  String textOnAlert = alert.getText();
	  
	  Assert.assertEquals(textOnAlert, "I am a JS Alert");
	  
	  alert.accept();
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//p[text()='You clicked an alert successfully ']")).getText(), "You clicked an alert successfully");
	  
  }
  
  
  public void TC_05_ConfirmAlert() throws Exception {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  WebElement jsConfirmButton = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
	  
	  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", jsConfirmButton);
	  Thread.sleep(2000);
	  
	  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", jsConfirmButton);
	  //jsConfirmButton.click();
	  Thread.sleep(2000);
	  
	  Alert alert = driver.switchTo().alert();
	  
	  String textOnAlert = alert.getText();
	  
	  Assert.assertEquals(textOnAlert, "I am a JS Confirm");
	  
	  alert.dismiss();
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//p[text()='You clicked: Cancel']")).getText(), "You clicked: Cancel");
	  
  }
  
  @AfterClass
  public void afterClass() {
	  //driver.quit();
  }

}
