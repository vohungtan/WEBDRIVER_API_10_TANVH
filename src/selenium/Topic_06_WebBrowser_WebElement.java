package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_06_WebBrowser_WebElement {
	
  WebDriver driver;
  
  By emailTextBox = By.id("mail");
  By ageUnder18Radio = By.id("under_18");
  By eduTextBox = By.id("edu");
  By jobRole1 = By.id("job1");
  By interestsDevelopmentRadio = By.id("development");
  By slider01 = By.id("slider-1");
  By buttonIsEnabled = By.id("button-enabled");
  By passwordTextBox = By.id("password");
  By ageRadioIsDisabled = By.id("radio-disabled");
  By bioTextBox = By.id("bio");
  By jobRole2 = By.id("job2");
  By interestsCheckboxDisabled = By.id("check-disbaled");
  By slider02 = By.id("slider-2");
  By buttonIsDisabled = By.id("button-disabled");
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void TC_01_KiemTraPhanTuDisplayed() throws InterruptedException {
	  
	  if(isElementDisplayed(emailTextBox)) {
		  sendKeyToElement(emailTextBox, "Automation Testing");
	  }
	  
	  if(isElementDisplayed(ageUnder18Radio)) {
		  clickToElement(ageUnder18Radio);
		  
	  }
	  
	  if(isElementDisplayed(eduTextBox)) {
		  sendKeyToElement(eduTextBox, "Automation Testing");
	  }
	  
	  Thread.sleep(5000);
  }
  
  @Test
  public void TC_02_KiemTraPhanTu() {
	  
	  if(isElementEnabled(emailTextBox)) {
		  
	  }
	  
  }
  
  public boolean isElementDisplayed(By by) {
	WebElement element = driver.findElement(by);
	return element.isDisplayed();
	  
  }
  
  public boolean isElementEnabled(By by) {
	  WebElement element = driver.findElement(by);
	  return element.isEnabled();
  }
  
  public void clickToElement(By by) {
	  WebElement element = driver.findElement(by);
	  element.click();
  }
  
  public void sendKeyToElement(By by, String value) {
	  WebElement element = driver.findElement(by);
	  element.sendKeys(value);
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
