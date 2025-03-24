package HelperAttributePractice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import GenericFileUtility.ExcelFileUtility;

public class TestNGPractice1 {

	
	@Test(dataProvider = "getData")
	public void testcase1(String firstname,String lastname)
	{
		System.out.println("firstname"+firstname+",Lastname"+lastname);
	}
	
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
		ExcelFileUtility exUtil=new ExcelFileUtility();
		Object[][] objArr=new Object[3][2];
		objArr[0][0]=exUtil.getDataFromExcel("DataProvider", 0, 0);
		objArr[0][1]=exUtil.getDataFromExcel("DataProvider", 0, 1);
		objArr[1][0]=exUtil.getDataFromExcel("DataProvider", 1, 0);
		objArr[1][1]=exUtil.getDataFromExcel("DataProvider", 1, 1);
		objArr[2][0]=exUtil.getDataFromExcel("DataProvider", 2, 0);
		objArr[2][1]=exUtil.getDataFromExcel("DataProvider", 2, 1);
		return objArr;
	}
	
}
