package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
  
  //@Test
  public void TC_01_UploadFileBySendKeys() {
	  //Step 1
	  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  //Step 2
	  WebElement uploadFile = driver.findElement(By.xpath("//input[@name='files[]']"));
	  uploadFile.sendKeys("/Users/tanvohung/Documents/Online_Class_10/WEBDRIVER_API_10_TANVH/uploadFiles/Image01.png");
  }
  
  @Test
  public void TC_02_CheckTitle() {
	  
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
