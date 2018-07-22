package Selenium_API;

import org.testng.annotations.Test;

import junit.framework.Assert;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Test_02_XPath_CSS_Location {
	WebDriver driver;
	
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
  }

  @Test
  public void TC_02_Login_empty() {
	  driver.get("http://live.guru99.com/index.php/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//div[@class='footer']//a[text() = 'My Account']")).click();
	  driver.findElement(By.id("send2")).click();
	  String usernameEmptyMessage = driver.findElement(By.id("advice-required-entry-email")).getText();
	  Assert.assertEquals(usernameEmptyMessage,"This is a required field.");
	  String passwordEmptyMessage = driver.findElement(By.id("advice-required-entry-pass")).getText();
	  Assert.assertEquals(passwordEmptyMessage,"This is a required field.");	  
    }
    
  @Test
  public void TC_03_Login_Email_Invalid() {
	  driver.get("http://live.guru99.com/index.php/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//div[@class='footer']//a[text() = 'My Account']")).click();
	  driver.findElement(By.cssSelector("#email")).sendKeys("1111@11.111.22");
	  driver.findElement(By.id("send2")).click();
	  String usernameInvalidMessage = driver.findElement(By.id("advice-validate-email-email")).getText();
	  Assert.assertEquals(usernameInvalidMessage,"Please enter a valid email address. For example johndoe@domain.com.");  	  
    }
  
  @Test
  public void TC_04_Login_Password_Incorrect() {
	  driver.get("http://live.guru99.com/index.php/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//div[@class='footer']//a[text() = 'My Account']")).click();
	  driver.findElement(By.cssSelector("#email")).sendKeys("tienhuynhminh93@gmail.com");
	  driver.findElement(By.cssSelector("#pass")).sendKeys("tien");
	  driver.findElement(By.id("send2")).click();
	  String passwordInvalidMessage = driver.findElement(By.id("advice-validate-password-pass")).getText();
	  Assert.assertEquals(passwordInvalidMessage,"Please enter 6 or more characters without leading or trailing spaces.");
    }
  
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
