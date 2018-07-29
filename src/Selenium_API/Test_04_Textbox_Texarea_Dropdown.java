package Selenium_API;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Test_04_Textbox_Texarea_Dropdown {
	WebDriver driver;

  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();  
  }
  
  @Test
  public void Test_01() {
	  driver.get("http://daominhdam.890m.com/");
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  Select jobRole = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
	  Assert.assertTrue(!jobRole.isMultiple());
	  jobRole.selectByVisibleText("Automation Tester");
	  Assert.assertEquals(jobRole.getFirstSelectedOption().getText(),"Automation Tester");
	  jobRole.selectByValue("manual");
	  Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Manual Tester");
	  jobRole.selectByIndex(3);Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Mobile Tester");
	  int jobItems = jobRole.getOptions().size();
	  Assert.assertEquals(jobItems, "5");	    
  }
  
  
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
