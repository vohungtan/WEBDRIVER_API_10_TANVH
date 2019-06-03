package selenium;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_13_Javascript {
	
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
	  
	  System.setProperty("webdriver.chrome.driver", "/Users/tanvohung/Documents/Online_Class_10/Software/chromedriver");
	  WebDriver driver = new ChromeDriver(); 
	  //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
  }
  
  public void TC_01_JavascriptExecutor() {
	  
	  //Step 1
	  driver.get("http://live.guru99.com/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  //Step 2
	  JavascriptExecutor js = (JavascriptExecutor)driver;
	  String domain = (String) js.executeScript("return document.domain");
	  System.out.println("Domain is: " + domain);
	  Assert.assertEquals(domain, "live.guru99.com");
	  
	  //Step 3
	  String url = (String) js.executeScript("return document.URL");
	  System.out.println("URL is: " + url);
	  Assert.assertEquals(url, "http://live.guru99.com/");
	  
	  //Step 4
	  WebElement mobileTab = driver.findElement(By.xpath("//a[text()='Mobile']"));
	  js.executeScript("arguments[0].click();", mobileTab);
	  
	  //Step 5
	  WebElement addSamSungGalaxyToCart = driver.findElement(By.xpath("//h2[@class='product-name']//a[@title='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//button[@title='Add to Cart']"));
	  js.executeScript("arguments[0].click();", addSamSungGalaxyToCart);
	  
	  //Step 6
	  verifyTextInInnerText("Samsung Galaxy was added to your shopping cart.");
	  
	  //Step 7
	  WebElement privacyPolicyLink = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
	  //js.executeScript("arguments[0].scrollIntoView(true;)", privacyPolicyLink);
	  js.executeScript("arguments[0].click();", privacyPolicyLink);
	  
	  String titlePage = (String) js.executeScript("return document.title");
	  System.out.println("URL is: " + titlePage);
	  Assert.assertEquals("Privacy Policy", titlePage);
	  
	  //Step 8
	  js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	  
	  //Step 9
	  WebElement wishListRow = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
	  Assert.assertEquals("The number of items in your Wishlist.", wishListRow.getText());
	  
	  //Step 10
	  js.executeScript("window.location='http://demo.guru99.com/v4/'");
	  String newDomain = (String) js.executeScript("return document.domain");
	  System.out.println("Domain is: " + newDomain);
	  Assert.assertEquals(newDomain, "live.guru99.com");
	  
  }
  
  @Test
  public void TC_02_RemoveAttribute() {
	  driver.get("http://demo.guru99.com/v4");
	 
	  //Step 2
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr26593");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("abc@123");
	  driver.findElement(By.xpath("//input[@value='LOGIN']")).click();
	  
	  //Step 3
	  driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	
	  //Step 4
	  driver.findElement(By.xpath("//input[@name='name']")).clear();
	  driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Selenium Online");
	  driver.findElement(By.xpath("//input[@value='f']")).click();
	  
	  removeAttributeInDOM(driver.findElement(By.id("dod")), "type");
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
	  driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("abc1122@gmail.com");
	  
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
	  Assert.assertEquals(email, "abc1122@gmail.com");
	  
	  //Step 7
	  driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
	  driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
	  driver.findElement(By.xpath("//input[@value='Submit']")).click();
	  
	  //Step 8
	  Assert.assertEquals(customerName, "Selenium Online");
	  Assert.assertEquals(address, "123 Address");
  }
  
  public Object removeAttributeInDOM(WebElement element, String attributeRemove) {
	  JavascriptExecutor js = (JavascriptExecutor)driver;
	  return js.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
  }
  
  public boolean verifyTextInInnerText(String expectedText) {
	  JavascriptExecutor js = (JavascriptExecutor)driver;
	  String actualText = (String) js.executeScript("return document.documentElement.innerText.match('" + expectedText + "')[0]");
	  System.out.println("Text actual: " + actualText);
	  return actualText.equals(expectedText);
  }
  
  @AfterClass
  public void afterClass() {
	  //driver.quit();
  }

}
