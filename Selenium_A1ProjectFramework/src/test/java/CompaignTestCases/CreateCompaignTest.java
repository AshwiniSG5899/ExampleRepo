package CompaignTestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import GenericBaseClassUtility.BaseClass;
import GenericFileUtility.ExcelFileUtility;
import GenericFileUtility.PropertiesFileUtility;
import GenericJavaUtility.JavaUtility;
import ObjectRepository.CampaignPage;
import ObjectRepository.LoginPage;
import ObjectRepository.createCampaignPage;
import ObjectRepository.dashboardPage;

public class CreateCompaignTest extends BaseClass{
	
	
	@Test(groups={"SmokeTest"})
	public void createCampaignWithoutDate() throws IOException, InterruptedException 
	{
		
		PropertiesFileUtility propUtil=new PropertiesFileUtility();
		JavaUtility jutil=new JavaUtility();
		int randomNum = jutil.getRandomNum(2000);
		
		ExcelFileUtility exUtil=new ExcelFileUtility();
		String campaignName = exUtil.getDataFromExcel("Campaign", 1, 2)+randomNum;
		String targetSize = exUtil.getDataFromExcel("Campaign", 1, 3);
		
		String ExpectedURL="http://49.249.28.218:8098/dashboard";
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
		String confMsg = driver.findElement(By.xpath("//div[text()='Campaign "+campaignName+" Successfully Added']")).getText();
      boolean status = confMsg.contains(campaignName);
      Assert.assertTrue(status);
       Thread.sleep(5000);
      
		
	}
	@Test(groups={"RegressionTest"})
	public void createCampaignWithCloseDate() throws IOException, InterruptedException
	{
		PropertiesFileUtility propUtil=new PropertiesFileUtility();
		JavaUtility jutil=new JavaUtility();
		int randomNum = jutil.getRandomNum(2000);
		String date = jutil.getSystemDate();
		String closeDate = jutil.getRequiredDate(20);
		
		ExcelFileUtility exUtil=new ExcelFileUtility();
		String campaignName = exUtil.getDataFromExcel("Campaign", 1, 2)+randomNum;
		String targetSize = exUtil.getDataFromExcel("Campaign", 1, 3);
        
        driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Thread.sleep(2000);
		dashboardPage dp=new dashboardPage(driver);
		dp.getCampaignsLink().click();
		CampaignPage cp=new CampaignPage(driver);
		cp.getCreateCampaignBtn().click();
		createCampaignPage ccp=new createCampaignPage(driver);
		ccp.createCampaignWithCloseDate(campaignName, targetSize, closeDate);
		Thread.sleep(4000);
		String confMsg = driver.findElement(By.xpath("//div[text()='Campaign "+campaignName+" Successfully Added']")).getText();
		boolean status = confMsg.contains(campaignName);
	      Assert.assertTrue(status);
        Thread.sleep(5000);
        
        

	}

}
