package Selenium_API;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Test_07_Iframe_Handle_Frame {
	WebDriver driver;
	WebDriverWait wait;

  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  wait = new WebDriverWait(driver, 10);
	  driver.manage().window().maximize();
  }

  @Test
  public void Test_case_01() {
	  driver.get("https://www.hdfcbank.com/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  
	  //Switch to iframe (cotains image close)
	  //findElement
	  	// nếu findelement mà tìm thấy - sử dụng cái element đầu tiên để tương tác
	  	// nếu không tìm thấy element - throw ra lỗi: No such element -> stop test case (failed)
	  //findElements
	  	// nếu findelement tìm thấy - nó return tất cả các element match vs dk
	  	// nếu findelement k tìm thấy - return empty list - run tiếp test case mà k báo lỗi
	  
	  List <WebElement> notificationIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
	  if(notificationIframe.size() > 0) {
		  driver.switchTo().frame(notificationIframe.get(0));
		  WebElement closeIcon = driver.findElement(By.xpath("//div[@id='div-close']"));
		  
		  //Javascript click to element
		  JavascriptExecutor jse = (JavascriptExecutor) driver;
		  jse.executeScript("arguments[0].click();", closeIcon);
		  System.out.println("Close pop-up");
		  
		  //Iss: Switch between 2 iframe (switch to default content)
		  //Switch to Top Windows
		 driver.switchTo().defaultContent();
	  }
	  
	  //Handle dynamic iframe (id: random whenpage reload)
	  WebElement lookingIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe']"));
	  driver.switchTo().frame(lookingIframe);
	  
	  String lookingText = driver.findElement(By.xpath("//span[@id='messageText']")).getText();
	  Assert.assertEquals(lookingText, "What are you looking for?");
	  driver.switchTo().defaultContent();
	  
	  WebElement banner = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
	  driver.switchTo().frame(banner);
	  
	  By bannerImageXpath = By.xpath("//div[@id='productcontainer']//img");
	  List <WebElement> bannerImage = driver.findElements(By.xpath("//div[@id='productcontainer']//img"));
	  int bannerImageso = bannerImage.size();
	  Assert.assertEquals(bannerImageso, 6);
	  
	  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(bannerImageXpath));
	  driver.switchTo().defaultContent();
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='flipBanner']")).isDisplayed(), true);
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='flipBanner']")).isDisplayed());
	  
	  List <WebElement> flipbanner = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
	  int flipbannerso = flipbanner.size();
	  Assert.assertEquals(flipbannerso, 8);
	  int i=0;
	  
	  for(WebElement image: flipbanner) {
		  Assert.assertTrue(image.isDisplayed());
		  i++;
		  System.out.println("Image [" + i + "] displayed!");
	  }
	  
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
