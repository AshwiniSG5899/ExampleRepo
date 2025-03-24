package contactTestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import GenericBaseClassUtility.BaseClass;
import GenericFileUtility.ExcelFileUtility;
import GenericFileUtility.PropertiesFileUtility;
import GenericJavaUtility.JavaUtility;
import GenericWebDriverUtility.WebDriverUtility;
import ObjectRepository.CampaignPage;
import ObjectRepository.ContactPage;
import ObjectRepository.LoginPage;
import ObjectRepository.createCampaignPage;
import ObjectRepository.createContactPage;
import ObjectRepository.dashboardPage;

public class createContactWithCampaign extends BaseClass{


	
	@Test(groups= {"RegressionTest"})
	public void createContactWithCam()throws InterruptedException, IOException 
	{
		PropertiesFileUtility propUtil=new PropertiesFileUtility();
		
		JavaUtility jutil=new JavaUtility();
		int randomNum = jutil.getRandomNum(2000);
		
		ExcelFileUtility exUtil=new ExcelFileUtility();
		String campaignName = exUtil.getDataFromExcel("Campaign", 1, 2)+randomNum;
		String targetSize = exUtil.getDataFromExcel("Campaign", 1, 3);
		String organization = exUtil.getDataFromExcel("Contact", 1, 2)+randomNum;
		String contactName = exUtil.getDataFromExcel("Contact", 1, 3)+randomNum;
		String title = exUtil.getDataFromExcel("Contact", 1, 4);
		String mobile = exUtil.getDataFromExcel("Contact", 1, 5);
        driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(2000);
		dashboardPage dp=new dashboardPage(driver);
		dp.getCampaignsLink().click();
		CampaignPage cp=new CampaignPage(driver);
		cp.getCreateCampaignBtn().click();
		createCampaignPage ccp=new createCampaignPage(driver);
		ccp.createCampaign(campaignName, targetSize);
		Thread.sleep(5000);
		dp.getContactsLink().click();
		Thread.sleep(1000);
		ContactPage ctp=new ContactPage(driver);
		ctp.getCreateContactBtn().click();
		createContactPage ctcp=new createContactPage(driver);
		ctcp.createContactWithCampaign(organization, title, contactName, mobile, "selectCampaign", "create-contact", campaignName);
          Thread.sleep(5000);
       String ConfirmationMsg = driver.findElement(By.xpath("//div[text()='Contact "+contactName+" Successfully Added']")).getText();
       boolean status = ConfirmationMsg.contains(contactName);
       Assert.assertEquals(status, true);
       Thread.sleep(5000);
       
       
//      

	}

}
