package createProduct;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import fileUtility.PropertiesFileUtility;
import genericJavaUtility.JavaUtility;

public class CreateProduct {

	@Test
	public void createProductTest() throws IOException, InterruptedException {
		PropertiesFileUtility pro=new PropertiesFileUtility();
		String BROWSER = pro.readDataFromPropFile("browser");
		String URL = pro.readDataFromPropFile("url");
		String UN = pro.readDataFromPropFile("uname");
		String PSW = pro.readDataFromPropFile("pwd");
			
			FileInputStream fis1=new FileInputStream("C:\\Users\\QSP-Ashtra\\OneDrive\\Desktop\\Selenium\\TestScriptData_1.xlsx");
	        Workbook wb = WorkbookFactory.create(fis1);
	        JavaUtility JUtil=new JavaUtility();
	        int randomInt = JUtil.getRandomNumber(1000);
	        Sheet compSheet = wb.getSheet("Product");
	        Row row1 = compSheet.getRow(1);
	        String productName = row1.getCell(2).getStringCellValue()+randomInt;
	        String Quantity = row1.getCell(3).getStringCellValue();
	        String price = row1.getCell(4).getStringCellValue();
			
            
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
			//creating product
			driver.findElement(By.linkText("Products")).click();
   	        driver.findElement(By.xpath("//span[text()='Add Product']")).click();
   	        driver.findElement(By.name("productName")).sendKeys(productName);
   	        WebElement quantityTF=driver.findElement(By.name("quantity"));
   	        quantityTF.clear();
   	        quantityTF.sendKeys(Quantity);
   	        WebElement priceTF = driver.findElement(By.name("price"));
   	        priceTF.clear();
   	        priceTF.sendKeys(price);
   	        WebElement category = driver.findElement(By.name("productCategory"));
   	        Select obj=new Select(category);
   	        obj.selectByValue("Electronics");
   	        
   	        WebElement vendor = driver.findElement(By.name("vendorId"));
   	        Select obj1=new Select(vendor);
   	        obj1.selectByVisibleText("Intel - (Vender-2)");
   	        driver.findElement(By.xpath("//button[text()='Add']")).click();
   	        Thread.sleep(2000);
   	     String ConfirmationMsg = driver.findElement(By.xpath("//div[text()='Product "+productName+" Successfully Added']")).getText();
	       System.out.println(ConfirmationMsg);
	        if(ConfirmationMsg.contains(productName))
	        {
	        	System.out.println("Product added Successfully");
	        }
	        else
	        {
	        	System.out.println("Product not added");
	        }
	        Thread.sleep(2000);
   	        Actions action=new Actions(driver);
   	        WebElement logout = driver.findElement(By.xpath("//*[name()='svg' and @role='img']"));
   	        action.click(logout).perform();
   	        driver.quit();
   	        }

}
