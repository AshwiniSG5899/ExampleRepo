package GenericBaseClassUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import GenericFileUtility.ExcelFileUtility;
import GenericFileUtility.PropertiesFileUtility;
import GenericJavaUtility.JavaUtility;
import GenericWebDriverUtility.UtilityClassObject;
import GenericWebDriverUtility.WebDriverUtility;
import ObjectRepository.LoginPage;
import ObjectRepository.dashboardPage;

public class BaseClass {
	
	public PropertiesFileUtility fLib=new PropertiesFileUtility();
	public JavaUtility jLib=new JavaUtility();
	public WebDriverUtility Wlib=new WebDriverUtility();
	public ExcelFileUtility exl=new ExcelFileUtility();
	
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	
	
	@BeforeSuite
	public void beforeSuite()
	{
		System.out.println("Established the databaseConnection");
	}
	@BeforeTest
	public void beforeTest()
	{
		System.out.println("Configuration setup started");
	}
	//@Parameters("browser")
	@BeforeClass
	public void beforeClass() throws IOException
	{
		String Browser=fLib.getDataFromPropFile("browser");
		System.out.println("Launch the browser");
		if(Browser.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(Browser.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(Browser.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
		
		
	}
	@BeforeMethod
	public void beforeMethod() throws IOException
	{
		System.out.println("Login");
		String URL = fLib.getDataFromPropFile("url");
		String UN = fLib.getDataFromPropFile("uname");
		String PWD = fLib.getDataFromPropFile("pwd");
		driver.get(URL);
		LoginPage lp=new LoginPage(driver);
		lp.login(UN, PWD);
	}
	@AfterSuite
	public void afterSuite()
	{
		System.out.println("Closed DB connection");
	}
	@AfterTest
	public void afterTest()
	{
		System.out.println("Configuration set up ended");
	}
	@AfterClass
	public void afterClass()
	{
		sdriver.quit();
	}
	@AfterMethod
	public void afterMethod()
	{
		dashboardPage dp=new dashboardPage(driver);
		dp.logout();
	}
	
}
