package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_14_UploadFile {
	
  WebDriver driver;
  String root_Folder_Path, image_01_Path, image_02_Path, image_03_Path;
  String image_01_Name = "Image01.png";
  String image_02_Name = "Image02.png";
  String image_03_Name = "Image03.png";
  
  @BeforeClass
  public void beforeClass() {
	  root_Folder_Path = System.getProperty("user.dir");
	  System.setProperty("webdriver.chrome.driver", root_Folder_Path + "/lib/chromedriver");
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  
	  image_01_Path = root_Folder_Path + "/uploadFiles/" + image_01_Name;
	  image_02_Path = root_Folder_Path + "/uploadFiles/" + image_02_Name;
	  image_03_Path = root_Folder_Path + "/uploadFiles/" + image_03_Name;
	  
	  System.out.println(image_01_Path);
	  System.out.println(image_02_Path);
	  System.out.println(image_03_Path);
  }
  
  @Test
  public void TC_01_UploadFileBySendKeys() throws Exception {
	  //Step 1
	  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  //Handle upload multiple files
	  WebElement uploadFile = driver.findElement(By.xpath("//input[@name='files[]']"));
	  uploadFile.sendKeys(image_01_Path + "\n" + image_02_Path + "\n" + image_03_Path);
	  
	  //Handle wait for 3 files load success
	  
	  List <WebElement> fileUploaded = driver.findElements(By.xpath("//tbody[@class='files']//p[@class='name']"));
	  
	  for(WebElement file : fileUploaded) {
		  System.out.println("File name: " + file.getText());
		  Assert.assertTrue(file.isDisplayed());
	  }
	  
	  //Click start to upload each file  
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("window.scrollBy(0,document.body.scrollHeight)");  
	  clickStartUploadButton("//p[text()='Image01.png']/parent::td/following-sibling::td//button[@class='btn btn-primary start']");
	  clickStartUploadButton("//p[text()='Image02.png']/parent::td/following-sibling::td//button[@class='btn btn-primary start']");
	  clickStartUploadButton("//p[text()='Image03.png']/parent::td/following-sibling::td//button[@class='btn btn-primary start']");
	  
	  
	  //Check 3 files upload success
	  Assert.assertTrue(driver.findElement(By.xpath("//tbody[@class='files']//p[@class='name']/a[@title='Image01.png']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//tbody[@class='files']//p[@class='name']/a[@title='Image02.png']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//tbody[@class='files']//p[@class='name']/a[@title='Image03.png']")).isDisplayed());
  }
  
  public void clickStartUploadButton(String element) throws Exception {
	  WebElement startButton = driver.findElement(By.xpath(element));
	  startButton.click();
	  Thread.sleep(2000);
  }
  
  @Test
  public void TC_03_Robot() throws Exception {
	  //Click on 'Add files' to open file dialog
	  WebElement uploadChrome = driver.findElement(By.cssSelector(".fileinput-button"));
	  uploadChrome.click();
	  Thread.sleep(1000);
	  
	  StringSelection select = new StringSelection(image_01_Path);
	  
	  //Copy to clipboard
	  Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
	  
  }
  
  @AfterClass
  public void afterClass() {
	  //driver.quit();
  }

}
