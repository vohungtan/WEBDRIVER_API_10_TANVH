package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_02_Xpath_Css_Part_I {
	
  WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  driver.get("http://live.guru99.com/");
  }
  
  @Test
  public void TC_01_LoginEmpty() {
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.id("send2")).click();
	  Assert.assertEquals("This is a required field.", driver.findElement(By.id("advice-required-entry-email")).getText());
	  Assert.assertEquals("This is a required field.", driver.findElement(By.id("advice-required-entry-pass")).getText());
  }
  
  @Test
  public void TC_02_LoginWithEmailInvalid() {
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("1234534313@123412.123412");
	  driver.findElement(By.id("send2")).click();
	  Assert.assertEquals("Please enter a valid email address. For example johndoe@domain.com.", driver.findElement(By.id("advice-validate-email-email")).getText());
  }
  
  @Test
  public void TC_03_LoginWithPasswordLessThan6Characters() {
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123");
	  driver.findElement(By.id("send2")).click();
	  Assert.assertEquals("Please enter 6 or more characters without leading or trailing spaces.", driver.findElement(By.id("advice-validate-password-pass")).getText());
  }
  
  @Test
  public void TC_04_LoginWithPasswordIncorrect() {
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123123123");
	  driver.findElement(By.id("send2")).click();
	  Assert.assertEquals("Invalid login or password.", driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText());
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
