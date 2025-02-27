package selenium;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_10_UserInteractions {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_MoveMouseToElement() throws Exception {
		driver.get("http://www.myntra.com/");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement profileMenu = driver.findElement(By.xpath("//span[text()='Profile']"));

		Actions action = new Actions(driver);

		action.moveToElement(profileMenu).perform();
		Thread.sleep(2000);

		WebElement loginButton = driver.findElement(By.xpath("//a[text()='log in']"));

		Assert.assertTrue(loginButton.isDisplayed());

		loginButton.click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());

	}

	@Test
	public void TC_02_ClickAndHoldElement() throws Exception {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		List<WebElement> numberItem = driver.findElements(By.xpath("//ol[@id='selectable']//li"));

		System.out.println("Tong so number: " + numberItem.size());

		Actions action = new Actions(driver);

		action.clickAndHold(numberItem.get(0)).moveToElement(numberItem.get(3)).release().perform();

		List<WebElement> numberSelectedItem = driver
				.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));

		Assert.assertEquals(numberSelectedItem.size(), 4);
	}

	@Test
	public void TC_03_SelectRandomItem() throws Exception {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		List<WebElement> numberItem = driver.findElements(By.xpath("//ol[@id='selectable']//li"));

		System.out.println("Tong so number: " + numberItem.size());

		Actions action = new Actions(driver);

		action.keyDown(Keys.CONTROL).perform();

		action.click(numberItem.get(0));
		action.click(numberItem.get(2));
		action.click(numberItem.get(3));
		action.click(numberItem.get(10));

		action.keyUp(Keys.CONTROL).perform();
		Thread.sleep(2000);

		List<WebElement> numberSelectedItem = driver
				.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));

		Assert.assertEquals(numberSelectedItem.size(), 4);
	}

	@Test
	public void TC_04_DoubleClick() throws Exception {
		driver.get("http://www.seleniumlearn.com/double-click");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement doubleClickMe = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", doubleClickMe);

		Actions action = new Actions(driver);

		action.doubleClick(doubleClickMe).perform();
		Thread.sleep(2000);

		Alert alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "The Button was double-clicked.");

		alert.accept();

	}

	@Test
	public void TC_05_RightClick() throws Exception {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement rightClickMe = driver.findElement(By.xpath("//span[text()='right click me']"));

		Actions action = new Actions(driver);
		action.contextClick(rightClickMe);

		WebElement quitButton = driver
				.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]/span[text()='Quit']"));
		action.moveToElement(quitButton);

		Assert.assertTrue(driver.findElement(By.xpath(
				"//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit'])"))
				.isDisplayed());

		quitButton.click();

		Alert alert = driver.switchTo().alert();

		alert.accept();

	}

	@Test
	public void TC_06_DragAndDrop() throws Exception {
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement sourceItem = driver.findElement(By.xpath("//div[@id='draggable']"));

		WebElement targetItem = driver.findElement(By.xpath("//div[@id='droptarget']"));

		Actions action = new Actions(driver);

		action.dragAndDrop(sourceItem, targetItem).build().perform();
		Thread.sleep(2000);

		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='You did great!']")).getText(), "You did great!");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
