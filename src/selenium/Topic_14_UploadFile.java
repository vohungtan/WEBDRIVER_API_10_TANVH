package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_14_UploadFile {
	
  WebDriver driver;
  String root_Folder_Path, image_01_Path, image_02_Path, image_03_Path, upload_File_Path;
  String image_01_Name = "Image01.png";
  String image_02_Name = "Image02.png";
  String image_03_Name = "Image03.png";
  String upload_File_Name = "UploadFile.png";
  
  @BeforeClass
  public void beforeClass() {
	  root_Folder_Path = System.getProperty("user.dir");
	  System.setProperty("webdriver.chrome.driver", root_Folder_Path + "/lib/chromedriver");
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  
	  image_01_Path = root_Folder_Path + "/uploadFiles/" + image_01_Name;
	  image_02_Path = root_Folder_Path + "/uploadFiles/" + image_02_Name;
	  image_03_Path = root_Folder_Path + "/uploadFiles/" + image_03_Name;
	  upload_File_Path = root_Folder_Path + "/uploadFiles/" + upload_File_Name;
	  
	  System.out.println(image_01_Path);
	  System.out.println(image_02_Path);
	  System.out.println(image_03_Path);
	  System.out.println(upload_File_Path);
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
	  //Step 1
	  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  //Click on 'Add files' to open file dialog
	  WebElement uploadChrome = driver.findElement(By.cssSelector(".fileinput-button"));
	  uploadChrome.click();
	  Thread.sleep(1000);
	  
	  StringSelection select = new StringSelection(image_01_Path);
	  
	  //Copy to clipboard
	  Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
	  
	  if(driver.toString().contains("chrome") || driver.toString().contains("firefox")) {
		  WebElement uploadFile = driver.findElement(By.cssSelector(".fileinput-button"));
		  uploadFile.click();
		  Thread.sleep(1000);
	  } else {
		  System.out.println("Go to IE");
		  WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		  clickToElementByJS(uploadFile);
	  }
	  
	  Robot robot = new Robot();
	  Thread.sleep(1000);
	  
	  //Nhan phim Enter
	  robot.keyPress(KeyEvent.VK_ENTER);
	  robot.keyRelease(KeyEvent.VK_ENTER);
	  
	  //Nhan xuong Ctrl + V
	  robot.keyPress(KeyEvent.VK_CONTROL);
	  robot.keyPress(KeyEvent.VK_V);
	  
	  //Nha phim Ctrl + V
	  robot.keyRelease(KeyEvent.VK_CONTROL);
	  robot.keyRelease(KeyEvent.VK_V);
	  Thread.sleep(1000);
	  
	  //Nhan phim Enter lan nua
	  robot.keyPress(KeyEvent.VK_ENTER);
	  robot.keyRelease(KeyEvent.VK_ENTER);
	  
	  Thread.sleep(3000);
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//tbody[@class='files']//p[@class='name' and text()='Image01.png']")).isDisplayed());
	  
	  
  }
  
  public Object clickToElementByJS(WebElement element) {
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  return js.executeScript("arguments[0].click();", element);
  }
  
  @Test
  public void TC_04() throws Exception {
	//Step 1
	  driver.get("https://encodable.com/uploaddemo/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	//Step 2
	  WebElement browserButton = driver.findElement(By.xpath("//input[@name='uploadname1']"));
	  browserButton.sendKeys(upload_File_Path);
	  
	//Step 3
	  WebElement uploadFolderDropdown = driver.findElement(By.xpath("//select[@name='subdir1']"));
	  Select select = new Select(uploadFolderDropdown);
	  select.selectByVisibleText("/uploaddemo/files/");
	  
	  //Step 4
	  Random rd = new Random();
	  int s = rd.nextInt(999); 
	  String newFolderName = "tanvh" + s;
	  driver.findElement(By.xpath("//input[@id='newsubdir1']")).sendKeys(newFolderName);
	  
	  //Step 5
	  WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='formfield-email_address']"));
	  emailTextbox.sendKeys("dam@gmail.com");
	  WebElement firstNameTextbox = driver.findElement(By.xpath("//input[@id='formfield-first_name']"));
	  firstNameTextbox.sendKeys("DAM DAO");
	  
	  //Step 6
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	  WebElement uploadButton = driver.findElement(By.xpath("//input[@id='uploadbutton']"));
	  uploadButton.click();
	  Thread.sleep(3000);
	  
	  //Step 7
	  Assert.assertEquals(driver.findElement(By.xpath("//*[text()[contains(.,'Email Address: dam@gmail.com')]]")).getText(), "Email Address: dam@gmail.com");
	  Assert.assertEquals(driver.findElement(By.xpath("//*[text()[contains(.,'First Name: DAM DAO')]]")).getText(), "First Name: DAM DAO");
	  Assert.assertEquals(driver.findElement(By.xpath("//*[text()[contains(.,'UploadFile.png')]]")).getText(), "UploadFile.png");
	  
	  //Step 8
	  WebElement viewUploadedFilesLink = driver.findElement(By.xpath("//div[@id='fcfooter-inner']//a[text()='View Uploaded Files']"));
	  viewUploadedFilesLink.click();
	  Thread.sleep(3000);
	  
	  //Step 9	  
	  WebElement randomFolder = driver.findElement(By.xpath("//table[@id='filelist']//a[contains(text(),'tanvh')]"));
	  randomFolder.click();
	  Thread.sleep(3000);
	  
	  //Step 10
	  Assert.assertEquals(driver.findElement(By.xpath("//table[@id='filelist']//a[text()='UploadFile.png']")).getText(), "UploadFile.png");
	
	  
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
