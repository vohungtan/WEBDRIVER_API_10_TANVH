package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_07_TextBox_TextArea {
	
  WebDriver driver;
  String customerID;
  String customerName;
  String gender;
  String dateOfBirth;
  String address;
  String city;
  String state;
  String pin;
  String mobileNumber;
  String email;
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
  }
  
  @Test
  public void TC_01_HandleTextboxTextArea() {
	  
	  //Step 1
	  driver.get("http://demo.guru99.com/v4/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  //Step 2
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr26593");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("abc@123");
	  driver.findElement(By.xpath("//input[@value='LOGIN']")).click();
	  String homePageURL = driver.getCurrentUrl();
	  Assert.assertEquals(homePageURL, "http://demo.guru99.com/v4/manager/Managerhomepage.php");
	  
	  //Step 3
	  driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	  
	  //Step 4
	  driver.findElement(By.xpath("//input[@name='name']")).clear();
	  driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Selenium Online");
	  
	  driver.findElement(By.xpath("//input[@value='f']")).click();
	  
	  driver.findElement(By.id("dob")).clear();
	  driver.findElement(By.id("dob")).sendKeys("10/01/2000");
	  
	  driver.findElement(By.xpath("//textarea[@name='addr']")).clear();
	  driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("123 Address");
	  
	  driver.findElement(By.xpath("//input[@name='city']")).clear();
	  driver.findElement(By.xpath("//input[@name='city']")).sendKeys("Ho Chi Minh");
	  
	  driver.findElement(By.xpath("//input[@name='state']")).clear();
	  driver.findElement(By.xpath("//input[@name='state']")).sendKeys("Thu Duc");
	  
	  driver.findElement(By.xpath("//input[@name='pinno']")).clear();
	  driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("123456");
	  
	  driver.findElement(By.xpath("//input[@name='telephoneno']")).clear();
	  driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys("0123456987");
	  
	  driver.findElement(By.xpath("//input[@name='emailid']")).clear();
	  driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("abc12345@gmail.com");
	  
	  driver.findElement(By.xpath("//input[@name='password']")).clear();
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("123123");
	  
	  driver.findElement(By.xpath("//input[@value='Submit']")).click();
	  
	  //Step 5
	  customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	  System.out.println("Customer ID is: " + customerID);
	  
	  //Step 6
	  customerName = driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText();
	  Assert.assertEquals(customerName, "Selenium Online");
	  
	  gender = driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText();
	  Assert.assertEquals(gender, "female");
	  
	  dateOfBirth = driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText();
	  Assert.assertEquals(dateOfBirth, "2000-10-01");
	  
	  address = driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText();
	  Assert.assertEquals(address, "123 Address");
	  
	  city = driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText();
	  Assert.assertEquals(city, "Ho Chi Minh");
	  
	  state = driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText();
	  Assert.assertEquals(state, "Thu Duc");
	  
	  pin = driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText();
	  Assert.assertEquals(pin, "123456");
	  
	  mobileNumber = driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText();
	  Assert.assertEquals(mobileNumber, "0123456987");
	  
	  email = driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText();
	  Assert.assertEquals(email, "abc12345@gmail.com");
	  
	  //Step 7
	  driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
	  driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
	  driver.findElement(By.xpath("//input[@value='Submit']")).click();
	  
	  //Step 8
	  Assert.assertEquals(customerName, "Selenium Online");
	  Assert.assertEquals(address, "123 Address");
  }
  
  
  @AfterClass
  public void afterClass() {
	  //driver.quit();
  }

}
