package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
  

  public void TC_01() { 
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
  
  
  @Test
  public void TC_02() throws Exception { 
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  String parentID = driver.getWindowHandle();
	  
	  WebElement googleLink = driver.findElement(By.xpath("//a[@href='https://google.com.vn']"));
	  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", googleLink);
	  googleLink.click();
	  
	  switchToWindowByTitle("Google");
	  Assert.assertEquals(driver.getTitle(), "Google");
	  
	  switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
	  Thread.sleep(3000);
	  
	  WebElement tikiLink = driver.findElement(By.xpath("//a[@href='https://tiki.vn']"));
	  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tikiLink);
	  tikiLink.click();	  
	  switchToWindowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
	  Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
	  
	  closeAllWindowsWithoutParent(parentID);
	  
	  Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
  }
  
  @Test
  public void TC_03() {
	  driver.get("http://www.hdfcbank.com/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  
  public void switchToWindowByTitle(String title) {
	  Set<String> allWindows = driver.getWindowHandles();
	  for(String runWindow:allWindows) {	  
		  driver.switchTo().window(runWindow);
		  String currentWin = driver.getTitle();
		  if(currentWin.equals(title)) {
			  break;
		  }
	  }
  }
  
  public boolean closeAllWindowsWithoutParent(String parentID) {
	  Set<String> allWindows = driver.getWindowHandles();
	  for(String runWindow:allWindows) {
		  if(!runWindow.equals(parentID)) {
			  driver.switchTo().window(runWindow);
			  driver.close();
		  }
	  }
	  driver.switchTo().window(parentID);
	  if(driver.getWindowHandles().size() == 1) {
		  return true;
	  }
	  else
		  return false;
  }
  
  @AfterClass
  public void afterClass() {
	  //driver.quit();
  }

}
