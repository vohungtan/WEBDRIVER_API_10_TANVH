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
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();	  
	  driver.manage().window().maximize();
  }
  
  @Test
  public void TC_01_JavascriptExecutor() {
	  
	  driver.get("http://live.guru99.com/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  clickElementByJavascript(driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")));
	  
	  Assert.assertEquals("http://live.guru99.com/index.php/customer/account/login/", driver.getCurrentUrl());
	  
	  clickElementByJavascript(driver.findElement(By.xpath("//div[@class='buttons-set']//a")));
	  
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
  }
  
  public void clickElementByJavascript(WebElement element) {
	  JavascriptExecutor je = (JavascriptExecutor) driver;
	  je.executeScript("agruments[0].click();", element);
  }
  
  @Test
  public void TC_02_Checkbox() {
	  driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
