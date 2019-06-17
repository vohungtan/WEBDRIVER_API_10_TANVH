package selenium;

import org.testng.annotations.Test;

import com.google.common.base.Function;

import org.testng.annotations.BeforeMethod;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Topic_16_WebDriverWait_II {
	
  WebDriver driver;
  WebDriverWait waitExplicit;
  
  By startButton = By.xpath("//div[@id='start']//button");
  By loadingIcon = By.xpath("//div[@id='loading']/img");
  By helloworldText = By.xpath("//div[@id='finish']/h4");
  
  @BeforeMethod
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
  }
  
  //@Test
  public void ImplicitWait() {
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  
	  WebElement startButton = driver.findElement(By.xpath("//div[@id='start']//button"));
	  startButton.click();
	  
	  WebElement helloworldText = driver.findElement(By.xpath("//div[@id='finish']/h4"));
	  
	  //wait for hello world displays
	  Assert.assertTrue(helloworldText.isDisplayed());
	  
	  Assert.assertEquals(helloworldText.getText(), "Hello World!");
  }
  
  //@Test
  public void TC_02_ExplicitWait_Invisible_3() {
	  
	  waitExplicit = new WebDriverWait(driver, 3);
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	 
	  waitExplicit.until(ExpectedConditions.elementToBeClickable(startButton));
	  driver.findElement(startButton).click();
	  
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	  
	  Assert.assertFalse(driver.findElement(helloworldText).isDisplayed());
	  
	  //Assert.assertEquals(driver.findElement(helloworldText).getText(), "Hello World!");
	  
  }
  
//@Test
  public void TC_02_ExplicitWait_Invisible_5() {
	  
	  waitExplicit = new WebDriverWait(driver, 3);
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	 
	  waitExplicit.until(ExpectedConditions.elementToBeClickable(startButton));
	  driver.findElement(startButton).click();
	  
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	  
	  Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
	  
	  Assert.assertEquals(driver.findElement(helloworldText).getText(), "Hello World!");
	  
  }
  
  //@Test
  public void TC_03_ExplicitWait_Visible_3() {
	  
	  waitExplicit = new WebDriverWait(driver, 3);
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	 
	  waitExplicit.until(ExpectedConditions.elementToBeClickable(startButton));
	  driver.findElement(startButton).click();
	  
	  waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloworldText));
	  
	  Assert.assertFalse(driver.findElement(helloworldText).isDisplayed());
	  
	  //Assert.assertEquals(driver.findElement(helloworldText).getText(), "Hello World!");
  }
  
//@Test
  public void TC_03_ExplicitWait_Visible_5() {
	  
	  waitExplicit = new WebDriverWait(driver, 5);
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	 
	  waitExplicit.until(ExpectedConditions.elementToBeClickable(startButton));
	  driver.findElement(startButton).click();
	  
	  waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloworldText));
	  
	  Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());
	  
	  Assert.assertEquals(driver.findElement(helloworldText).getText(), "Hello World!");
  }
  
  //@Test
  public void TC_04_ExplicitWait_ImplicitWait() {
	  
	  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  
	  //Hello world invisible
	  System.out.println("Start time Hello world: " + getDateTimeSecond());
	  By helloworldText = By.xpath("//div[@id='finish']/h4");
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(helloworldText));
	  System.out.println("End time Hello world: " + getDateTimeSecond());
	  
	  //Loading icon invisible
	  System.out.println("Start time Loading icon: " + getDateTimeSecond());
	  By loadingIcon = By.xpath("//div[@id='loading']/img"); 
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	  System.out.println("End time Loading icon: " + getDateTimeSecond());
	  
	  //Click Start Button
	  System.out.println("Start time Click Start Button: " + getDateTimeSecond());
	  By startButton = By.xpath("//div[@id='start']//button");
	  driver.findElement(startButton).click();
	  System.out.println("End time Click Start Button: " + getDateTimeSecond());
	  
	  //Loading icon invisible
	  System.out.println("Start time Loading icon: " + getDateTimeSecond());
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
	  System.out.println("End time Loading icon: " + getDateTimeSecond());
	  
	  //Start button invisible
	  System.out.println("Start time Start button: " + getDateTimeSecond());
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(startButton));
	  System.out.println("End time Start button: " + getDateTimeSecond());
  }
  
  public Date getDateTimeSecond() {
	  Date date = new Date();
	  date = new Timestamp(date.getTime());
	  return date;
  }
  
  //@Test
  public void TC_05_ExplicitWait() {
	  waitExplicit = new WebDriverWait(driver, 5);
	  driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	  
	  By dateTimePicker = By.xpath("//div[@class='RadAjaxPanel inlinePanel']");
	  waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(dateTimePicker));
	  
	  System.out.println("Selected date is: " + driver.findElement(By.xpath("//span[text()='No Selected Dates to display.']")).getText());
	  driver.findElement(By.xpath("//table[@class='rcMainTable']//td[@title='Monday, June 17, 2019']")).click();
	  
	  By loaderAjax = By.xpath("//div[@class='raDiv']");
	  waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loaderAjax));
	  
	  By selectedDate = By.xpath("//*[contains(@class,'rcSelected')]//a[text()='17']");
	  waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(selectedDate));
	  
	  Assert.assertTrue(driver.findElement(selectedDate).isDisplayed());
	  Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Monday, June 17, 2019']")).getText(), "Monday, June 17, 2019");
  }
  
  @Test
  public void TC_06_FluentWait() {
	  waitExplicit = new WebDriverWait(driver, 5);
	  driver.get("https://daominhdam.github.io/fluent-wait/");
	  
	  WebElement countDownTime = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
	  waitExplicit.until(ExpectedConditions.visibilityOf(countDownTime));
	  
	  new FluentWait<WebElement>(countDownTime)
	  .withTimeout(15, TimeUnit.SECONDS)
	  .pollingEvery(1, TimeUnit.SECONDS)
	  .ignoring(NoSuchElementException.class)
	  .until(new Function<WebElement, Boolean>(){
		  public Boolean apply(WebElement element) {
			  boolean flag = element.getText().endsWith("00");
			  System.out.println("Time = " + element.getText());
			  return flag;
		  }
	  });
	  
  }
  
  @AfterMethod
  public void afterClass() {
	  driver.quit();
  }

}
