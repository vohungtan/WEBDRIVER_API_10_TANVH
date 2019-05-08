package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_06_WebBrowser_WebElement {
	
  WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void TC_01_KiemTraPhanTuDisplayed() {
	  
	  if(driver.findElement(By.id("mail")).isDisplayed()) {
		  driver.findElement(By.id("mail")).sendKeys("Automation Testing");
	  }
	  
	  if(driver.findElement(By.id("under_18")).isDisplayed()) {
		  driver.findElement(By.id("under_18")).click();
	  }
	  
	  if(driver.findElement(By.id("edu")).isDisplayed()) {
		  driver.findElement(By.id("edu")).sendKeys("Automation Testing");
	  }
	  
  }
  
  public void TC_02_CheckTitle() {
	  
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
