package LoginTestcase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import GenericBaseClassUtility.BaseClass;
import GenericFileUtility.PropertiesFileUtility;
import ObjectRepository.LoginPage;
import ObjectRepository.dashboardPage;

public class LoginTest extends BaseClass{

	
	@Test(groups= {"SmokeTest"})
	public void loginTest() throws IOException, InterruptedException 
	{
	PropertiesFileUtility propUtil=new PropertiesFileUtility();
	String ExpectedURL="http://49.249.28.218:8098/dashboard";
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	
    Thread.sleep(2000);
	String actualURL=driver.getCurrentUrl();
	Assert.assertEquals(actualURL, ExpectedURL);
	Thread.sleep(2000);
	
	}

}
