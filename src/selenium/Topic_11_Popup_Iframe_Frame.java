package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_11_Popup_Iframe_Frame {
	
  WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();	  
	  driver.manage().window().maximize();
  }
  
  @Test
  public void TC_01_LoginEmpty() { 
	  driver.get("http://www.hdfcbank.com/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  WebElement iframeElement = driver.findElement(By.xpath("//iframe[starts-with(@id,'viz_iframe')]"));
	  driver.switchTo().frame(iframeElement);
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//span[text()='What are you looking for?']")).getText(), "What are you looking for?");
	  
	  driver.switchTo().defaultContent();
	  
	  List <WebElement> rightBannerImages = driver.findElements(By.xpath("//div[@id='rightbanner']//div[@class='owl-stage']/div[not(@class='owl-item cloned')]//img"));
	  
	  Assert.assertEquals(rightBannerImages.size(), 5);
	  
	  List <WebElement> flipBanner = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
	  
	  Assert.assertEquals(flipBanner.size(), 8);
	  
	  for(WebElement image: flipBanner) {
		  Assert.assertTrue(image.isDisplayed());
	  }
  }
  
  
  @AfterClass
  public void afterClass() {
	  //driver.quit();
  }

}
