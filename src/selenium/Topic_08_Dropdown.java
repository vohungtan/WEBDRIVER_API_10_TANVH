package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_08_Dropdown {
	
  WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
  }
  
  @Test
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
	  select.deselectByValue("manual");
	  
	  //Step 6
	  Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");
	  
	  //Step 7
	  select.deselectByIndex(3);
	  
	  //Step 8
	  Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");
	  
	  //Step 9
	  Assert.assertEquals(select.getOptions().size(), 5);
	  
  }
  
  @Test
  public void TC_02_HandleCustomDropdown() {
	//Step 1
	  driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
