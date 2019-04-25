package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_01_CheckEnvironments {
	
  WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.get("http://live.guru99.com/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void TC_01_CheckUrl() {
	  String homePageUrl = driver.getCurrentUrl();
	  Assert.assertEquals("http://live.guru99.com/", homePageUrl);
  }
  
  @Test
  public void TC_02_CheckTitle() {
	  String homePageTitle = driver.getTitle();
	  Assert.assertEquals("Home page", homePageTitle);
  }
  
  @Test
  public void TC_03_CheckPanel() {
	  String homePageTitle = driver.getTitle();
	  Assert.assertEquals("Home page", homePageTitle);
  }
  
  @Test
  public void TC_04_CheckPanel() {
	  String homePageTitle = driver.getTitle();
	  Assert.assertEquals("Home page", homePageTitle);
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
