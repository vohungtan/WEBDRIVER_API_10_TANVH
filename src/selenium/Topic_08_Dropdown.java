package selenium;

import org.testng.annotations.Test;

import com.google.common.base.Predicate;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_08_Dropdown {
  WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
  }
  
  //@Test
  public void TC_01_HandleHTMLDropdown() throws InterruptedException {
	  //Step 1
	  driver.get("https://daominhdam.github.io/basic-form/index.html");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  	  
	  WebElement jobRoleDropdownList = driver.findElement(By.xpath("//select[@id='job1']"));
	  Select select = new Select(jobRoleDropdownList);
	  
	  //Step 2
	  Assert.assertFalse(select.isMultiple());
	  
	  //Step 3
	  select.selectByVisibleText("Automation Tester");
	  
	  //Step 4
	  Assert.assertEquals(select.getFirstSelectedOption().getText(), "Automation Tester");
	  Thread.sleep(3);
	  
	  //Step 5
	  select.selectByValue("manual");
	  
	  //Step 6
	  Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");
	  
	  //Step 7
	  select.selectByIndex(3);
	  
	  //Step 8
	  Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");
	  
	  //Step 9
	  Assert.assertEquals(select.getOptions().size(), 5);
	  
  }
  
  //@Test
  public void TC_02_HandleCustomDropdown1() throws Exception {
	//Step 1
	  driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	//Step 2
	  selectItemInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "19");
	  Thread.sleep(3000);
	  
	//Step 3
	  Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")).getText(), "19");
	  
  }
  
  //@Test
  public void TC_03_HandleCustomDropdown2() throws Exception {
	//Step 1
	  driver.get("https://material.angular.io/components/select/examples");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  selectItemInDropdown("//mat-label[text()='State']/ancestor::span/preceding-sibling::mat-select//div[@class='mat-select-arrow-wrapper']", "//mat-option/span", "Alaska");
	  Thread.sleep(3000);
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//mat-select[@id='mat-select-5']//span[text()='Alaska']")).isDisplayed());
	  
  }
  
  
  public void selectItemInDropdown(String parentLocator, String allItemsInDropdown, String expectedText) {
	  WebElement parentElement = driver.findElement(By.xpath(parentLocator));
	  ((JavascriptExecutor) driver).executeScript("arguments[0].click();", parentElement);
	  
	  WebDriverWait wait = new WebDriverWait(driver, 30);
	  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsInDropdown)));
	  
	  List <WebElement> allItemsElement = driver.findElements(By.xpath(allItemsInDropdown));
	  
	  for (int i = 0; i < allItemsElement.size(); i++) {
		  String itemText = allItemsElement.get(i).getText();
		  if (itemText.equals(expectedText)) {
			  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allItemsElement.get(i));
			  allItemsElement.get(i).click();
			  break;
		  }
	  }
	  
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
