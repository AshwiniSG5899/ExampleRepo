package createContactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import fileUtility.PropertiesFileUtility;
import genericJavaUtility.JavaUtility;
import genericWebDriverUtility.WebDriverUtility;

public class CreateContact {

	@Test
	public void createContactWithCampaignTest() throws IOException, InterruptedException {
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
		        Sheet orgSheet = wb.getSheet("Org");
		        Row row = orgSheet.getRow(1);
		        String organization=row.getCell(2).getStringCellValue()+randomInt;
		        String title = row.getCell(3).getStringCellValue();
		        String contactName = row.getCell(4).getStringCellValue()+randomInt;
		        String mobile = row.getCell(5).getStringCellValue();
		        
		        Sheet compSheet = wb.getSheet("Compaign");
		        Row row1 = compSheet.getRow(1);
		        String campName = row1.getCell(2).getStringCellValue()+randomInt;
		        String targetSize = row1.getCell(3).getStringCellValue();
		        
		        
		        wb.close();
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
				WebElement contactLink = driver.findElement(By.linkText("Contacts"));
				WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
				wait.until(ExpectedConditions.elementToBeClickable(contactLink));
				contactLink.click();
				Thread.sleep(5000);
				WebElement createContactLink = driver.findElement(By.xpath("//span[text()='Create Contact']"));
				WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(20));
				wait.until(ExpectedConditions.visibilityOf(createContactLink));
				createContactLink.click();
				driver.findElement(By.name("organizationName")).sendKeys(organization);
				driver.findElement(By.name("title")).sendKeys(title);
				driver.findElement(By.name("contactName")).sendKeys(contactName);
				driver.findElement(By.name("mobile")).sendKeys(mobile);
				driver.findElement(By.xpath("//button[@type='button' and contains(@style,'white-space')]")).click();
				WebDriverUtility WUtil=new WebDriverUtility();
				WUtil.switchToNewWindow(driver, "selectCampaign");
				
		        WebElement selectTypeDD = driver.findElement(By.id("search-criteria"));
		        Select select1=new Select(selectTypeDD);
		        select1.selectByValue("campaignName");
		        driver.findElement(By.id("search-input")).sendKeys(campName);
		        driver.findElement(By.xpath("//button[@class='select-btn']")).click();
		        WUtil.switchToNewWindow(driver, "create-contact");
		        
		       driver.findElement(By.xpath("//button[text()='Create Contact']")).click();
		       Thread.sleep(3000);
		       String ConfirmationMsg = driver.findElement(By.xpath("//div[text()='Contact "+contactName+" Successfully Added']")).getText();
		       System.out.println(ConfirmationMsg);
		        if(ConfirmationMsg.contains(contactName))
		        {
		        	System.out.println("Contact added Successfully");
		        }
		        else
		        {
		        	System.out.println("Contact not added");
		        }
		       Thread.sleep(5000);
		       driver.findElement(By.xpath("//*[name()='svg' and @role=\"img\"]")).click();
		       WebElement logout = driver.findElement(By.xpath("//div[text()='Logout ']"));
		       Actions action=new Actions(driver);
		       action.moveToElement(logout).click().perform();
		       driver.quit();
		      
		      

	}

}
