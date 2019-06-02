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

public class Topic_11_Popup_Iframe_Windows {
    
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
  
  
  public void TC_03() throws Exception {
	  driver.get("http://www.hdfcbank.com/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  String parentID = driver.getWindowHandle();
	  
	  List <WebElement> popBanner = driver.findElements(By.xpath("//img[@class='popupbanner at-element-click-tracking']")); 
	  
	  if (popBanner.size() >= 1 && popBanner.get(0).isDisplayed()) {
		  driver.findElement(By.xpath("//img[@class='popupCloseButton']")).click();
		  Assert.assertFalse(popBanner.get(0).isDisplayed());
	  } else if (popBanner.size() >=1 && !popBanner.get(0).isDisplayed()) {
		  System.out.println("Size >= 1: Present, co trong DOM khong hien thi tren site");
	  }
	  
	  driver.findElement(By.xpath("//a[text()='Agri']")).click();
	  switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
	  Thread.sleep(3000);
	  
	  driver.findElement(By.xpath("//img[@src='images/thumb/3.png']/following-sibling::div//p[text()='Account Details']")).click();
	  switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
	  Thread.sleep(3000);
	  
	  WebElement iframe = driver.findElement(By.xpath("//frame[@name='footer']"));
	  driver.switchTo().frame(iframe);
	  driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
	  switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
	  Thread.sleep(3000);
	  
	  driver.findElement(By.xpath("//a[@title='Corporate Social Responsibility']")).click();
	  
	  closeAllWindowsWithoutParent(parentID);
	  switchToWindowByTitle("HDFC Bank: Personal Banking Services");

  }
  
  @Test
  public void TC_04() throws Exception {
	  driver.get("http://live.guru99.com/index.php/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  driver.findElement(By.xpath("//a[text()='Mobile']")).click();
	  Thread.sleep(3000);
	  
	  String parentID = driver.getWindowHandle();
	  
	  driver.findElement(By.xpath("(//ul[@class='add-to-links']//a[text()='Add to Compare'])[1]")).click();
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Sony Xperia has been added to comparison list.");
	  
	  driver.findElement(By.xpath("(//ul[@class='add-to-links']//a[text()='Add to Compare'])[3]")).click();
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "The product Samsung Galaxy has been added to comparison list.");
	  
	  driver.findElement(By.xpath("//button[@title='Compare']")).click();
	  
	  switchToWindowByTitle("Products Comparison List - Magento Commerce");
	  
	  Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
	  
	  closeAllWindowsWithoutParent(parentID);
	  
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
