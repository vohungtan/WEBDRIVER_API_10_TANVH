package selenium;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.Random;
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

		System.setProperty("webdriver.chrome.driver",
				"/Users/tanvohung/Documents/Online_Class_10/Software/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_JavascriptExecutor() {

		// Step 1
		driver.get("http://live.guru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Step 2
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String domain = (String) js.executeScript("return document.domain");
		System.out.println("Domain is: " + domain);
		Assert.assertEquals(domain, "live.guru99.com");

		// Step 3
		String url = (String) js.executeScript("return document.URL");
		System.out.println("URL is: " + url);
		Assert.assertEquals(url, "http://live.guru99.com/");

		// Step 4
		WebElement mobileTab = driver.findElement(By.xpath("//a[text()='Mobile']"));
		js.executeScript("arguments[0].click();", mobileTab);

		// Step 5
		WebElement addSamSungGalaxyToCart = driver.findElement(By.xpath(
				"//h2[@class='product-name']//a[@title='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//button[@title='Add to Cart']"));
		js.executeScript("arguments[0].click();", addSamSungGalaxyToCart);

		// Step 6
		verifyTextInInnerText("Samsung Galaxy was added to your shopping cart.");

		// Step 7
		WebElement privacyPolicyLink = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		// js.executeScript("arguments[0].scrollIntoView(true;)", privacyPolicyLink);
		js.executeScript("arguments[0].click();", privacyPolicyLink);

		String titlePage = (String) js.executeScript("return document.title");
		System.out.println("URL is: " + titlePage);
		Assert.assertEquals("Privacy Policy", titlePage);

		// Step 8
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

		// Step 9
		WebElement wishListRow = driver.findElement(By.xpath(
				"//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
		Assert.assertEquals("The number of items in your Wishlist.", wishListRow.getText());

		// Step 10
		js.executeScript("window.location='http://demo.guru99.com/v4/'");
		String newDomain = (String) js.executeScript("return document.domain");
		System.out.println("Domain is: " + newDomain);
		Assert.assertEquals(newDomain, "live.guru99.com");

	}

	@Test
	public void TC_02_RemoveAttribute() {

		// Step 1
		driver.get("http://demo.guru99.com/v4");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Step 2
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr26593");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("abc@123");
		driver.findElement(By.xpath("//input[@value='LOGIN']")).click();

		// Step 3
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// Step 4
		inputText("//input[@name='name']", "Selenium Online");
		driver.findElement(By.xpath("//input[@value='f']")).click();

		WebElement dateOfBirthPicker = driver.findElement(By.xpath("//input[@name='dob']"));
		removeAttributeInDOM(dateOfBirthPicker, "type");
		driver.findElement(By.xpath("//input[@name='dob']")).sendKeys("10/01/2000");

		inputText("//textarea[@name='addr']", "123 Address");
		inputText("//input[@name='city']", "Ho Chi Minh");
		inputText("//input[@name='state']", "Thu Duc");
		inputText("//input[@name='pinno']", "123456");
		inputText("//input[@name='telephoneno']", "0123456987");
		inputText("//input[@name='emailid']", "abc1126@gmail.com");
		inputText("//input[@name='password']", "123123");

		driver.findElement(By.xpath("//input[@value='Submit']")).click();

		// Step 6
		assertText("//td[text()='Customer Name']/following-sibling::td", "Selenium Online");
		assertText("//td[text()='Gender']/following-sibling::td", "female");
		assertText("//td[text()='Birthdate']/following-sibling::td", "2000-10-01");
		assertText("//td[text()='Address']/following-sibling::td", "123 Address");
		assertText("//td[text()='City']/following-sibling::td", "Ho Chi Minh");
		assertText("//td[text()='State']/following-sibling::td", "Thu Duc");
		assertText("//td[text()='Pin']/following-sibling::td", "123456");
		assertText("//td[text()='Mobile No.']/following-sibling::td", "0123456987");
		assertText("//td[text()='Email']/following-sibling::td", "abc1126@gmail.com");

	}

	public void assertText(String xpathElement, String expectedText) {
		Assert.assertEquals(driver.findElement(By.xpath(xpathElement)).getText(), expectedText);
	}

	// @Test
	public void TC_03_CreateAccount() throws Exception {
		// Step 1
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.location='http://live.guru99.com/'");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Step 2
		WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
		js.executeScript("arguments[0].click();", myAccountLink);

		// Step 3
		WebElement createAccountButton = driver.findElement(By.xpath(
				"//div[@class='col-1 new-users']//a[@href='http://live.guru99.com/index.php/customer/account/create/']"));
		js.executeScript("arguments[0].click();", createAccountButton);

		// Step 4
		Random rd = new Random();
		int a = rd.nextInt(999);
		String email = "Demo" + a + "@gmail.com";

		// Step 5
		sendkeyToElementByJS(driver.findElement(By.id("firstname")), "Tan");
		sendkeyToElementByJS(driver.findElement(By.id("middlename")), "Hung");
		sendkeyToElementByJS(driver.findElement(By.id("lastname")), "Vo");
		sendkeyToElementByJS(driver.findElement(By.id("email_address")), email);
		sendkeyToElementByJS(driver.findElement(By.id("password")), "123456");
		sendkeyToElementByJS(driver.findElement(By.id("confirmation")), "123456");
		WebElement registerButton = driver.findElement(By.xpath("//button[@title='Register']"));
		js.executeScript("arguments[0].click();", registerButton);
		verifyTextInInnerText("Thank you for registering with Main Website Store.");

		// Step 6
		WebElement logoutButton = driver.findElement(By.xpath("//a[text()='Log Out']"));
		js.executeScript("arguments[0].click();", logoutButton);
		Thread.sleep(10000);

		// Step 7
		verifyTextInInnerText("This is demo site for");

	}

	public void inputText(String xpathElement, String inputText) {
		driver.findElement(By.xpath(xpathElement)).clear();
		driver.findElement(By.xpath(xpathElement)).sendKeys(inputText);
	}

	public Object sendkeyToElementByJS(WebElement element, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public Object removeAttributeInDOM(WebElement element, String attributeRemove) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public boolean verifyTextInInnerText(String expectedText) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String actualText = (String) js
				.executeScript("return document.documentElement.innerText.match('" + expectedText + "')[0]");
		System.out.println("Text actual: " + actualText);
		return actualText.equals(expectedText);
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
