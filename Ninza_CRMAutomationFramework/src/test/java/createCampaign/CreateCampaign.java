package createCampaign;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import fileUtility.PropertiesFileUtility;
import genericJavaUtility.JavaUtility;

public class CreateCampaign {

	@Test
	public void createCampaignWithoutDate() throws EncryptedDocumentException, IOException, InterruptedException {
		PropertiesFileUtility pro=new PropertiesFileUtility();
		String BROWSER = pro.readDataFromPropFile("browser");
		String URL = pro.readDataFromPropFile("url");
		String UN = pro.readDataFromPropFile("uname");
		String PSW = pro.readDataFromPropFile("pwd");
			
			//reading data from excel sheet
			FileInputStream fis1=new FileInputStream("C:\\Users\\QSP-Ashtra\\OneDrive\\Desktop\\Selenium\\TestScriptData_1.xlsx");
	        Workbook wb = WorkbookFactory.create(fis1);
	        JavaUtility JUtil=new JavaUtility();
	        int randomInt = JUtil.getRandomNumber(1000);
	        Sheet compSheet = wb.getSheet("Compaign");
	        Row row1 = compSheet.getRow(2);
	        String campName = row1.getCell(2).getStringCellValue()+randomInt;
	        String targetSize = row1.getCell(3).getStringCellValue();
			
            
			WebDriver driver=null;
			if(BROWSER.equalsIgnoreCase("chrome"))
			{
				driver=new ChromeDriver();
			}
			else if(BROWSER.equalsIgnoreCase("firefox"))
			{
				driver=new FirefoxDriver();
			}
			else if(BROWSER.equalsIgnoreCase("edge"))
			{
				driver=new EdgeDriver();
			}
			else
			{
				driver=new ChromeDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.get(URL);
			driver.findElement(By.id("username")).sendKeys(UN);
			driver.findElement(By.id("inputPassword")).sendKeys(PSW);
			driver.findElement(By.xpath("//button[text()='Sign In']")).click();
			
			//creating comapign
			driver.findElement(By.linkText("Campaigns")).click();
			driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
			driver.findElement(By.name("campaignName")).sendKeys(campName);
			driver.findElement(By.name("targetSize")).clear();
			driver.findElement(By.name("targetSize")).sendKeys(targetSize);
			driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
			Thread.sleep(2000);
			String ConfirmationMsg = driver.findElement(By.xpath("//div[text()='Campaign "+campName+" Successfully Added']")).getText();
		       System.out.println(ConfirmationMsg);
		        if(ConfirmationMsg.contains(campName))
		        {
		        	System.out.println("Campaign added Successfully");
		        }
		        else
		        {
		        	System.out.println("Campaign not added");
		        }
		        Thread.sleep(3000);
			   driver.findElement(By.xpath("//*[name()='svg' and @role=\"img\"]")).click();
		       WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
		       Actions action=new Actions(driver);
		       action.moveToElement(logout).click().perform();
			   driver.quit();
	}
	
	@Test
	public void createCampaignWithDate() throws IOException, InterruptedException
	{
		PropertiesFileUtility pro=new PropertiesFileUtility();
		String BROWSER = pro.readDataFromPropFile("browser");
		String URL = pro.readDataFromPropFile("url");
		String UN = pro.readDataFromPropFile("uname");
		String PSW = pro.readDataFromPropFile("pwd");
			
			//reading data from excel sheet
			FileInputStream fis1=new FileInputStream("C:\\Users\\QSP-Ashtra\\OneDrive\\Desktop\\Selenium\\TestScriptData_1.xlsx");
	        Workbook wb = WorkbookFactory.create(fis1);
	        JavaUtility JUtil=new JavaUtility();
	        int randomInt = JUtil.getRandomNumber(1000);
	        Sheet compSheet = wb.getSheet("Compaign");
	        Row row1 = compSheet.getRow(2);
	        String campName = row1.getCell(2).getStringCellValue()+randomInt;
	        String targetSize = row1.getCell(3).getStringCellValue();
	        
	        JavaUtility Jutil=new JavaUtility();
	        String EndDate = Jutil.getRequiredDate(30);
	        System.out.println(EndDate);
			
	        WebDriver driver=null;
			if(BROWSER.equalsIgnoreCase("chrome"))
			{
				driver=new ChromeDriver();
			}
			else if(BROWSER.equalsIgnoreCase("firefox"))
			{
				driver=new FirefoxDriver();
			}
			else if(BROWSER.equalsIgnoreCase("edge"))
			{
				driver=new EdgeDriver();
			}
			else
			{
				driver=new ChromeDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.get(URL);
			driver.findElement(By.id("username")).sendKeys(UN);
			driver.findElement(By.id("inputPassword")).sendKeys(PSW);
			driver.findElement(By.xpath("//button[text()='Sign In']")).click();
			
			//creating comapign
			driver.findElement(By.linkText("Campaigns")).click();
			driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
			driver.findElement(By.name("campaignName")).sendKeys(campName);
			driver.findElement(By.name("targetSize")).clear();
			driver.findElement(By.name("targetSize")).sendKeys(targetSize);
			driver.findElement(By.name("expectedCloseDate")).sendKeys(EndDate);
			driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
			Thread.sleep(2000);
			String ConfirmationMsg = driver.findElement(By.xpath("//div[text()='Campaign "+campName+" Successfully Added']")).getText();
		       System.out.println(ConfirmationMsg);
		        if(ConfirmationMsg.contains(campName))
		        {
		        	System.out.println("Campaign added Successfully");
		        }
		        else
		        {
		        	System.out.println("Campaign not added");
		        }
		        Thread.sleep(3000);
			   driver.findElement(By.xpath("//*[name()='svg' and @role=\"img\"]")).click();
		       WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
		       Actions action=new Actions(driver);
		       action.moveToElement(logout).click().perform();
			driver.quit();
	}
	

}
