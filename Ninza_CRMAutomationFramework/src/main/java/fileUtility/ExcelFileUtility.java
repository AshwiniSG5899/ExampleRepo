package fileUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {

	public String readDataFromExcelFile(String shName,int row,int cell) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\QSP-Ashtra\\eclipse-workspace\\Ninza_CRMAutomationFramework\\src\\test\\resources\\TestScriptData_1.xlsx");
	    Workbook wb = WorkbookFactory.create(fis);
	    String data = wb.getSheet(shName).getRow(row).getCell(cell).getStringCellValue();
	    return data;
	}
	public int getLastRowCount(String shName) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\QSP-Ashtra\\eclipse-workspace\\Ninza_CRMAutomationFramework\\src\\test\\resources\\TestScriptData_1.xlsx");
	    Workbook wb = WorkbookFactory.create(fis);
	    int lastRowNum = wb.getSheet(shName).getLastRowNum();
	    return lastRowNum;
	}
	public void writeDataBackToExcel(String ShName,int rowNum,int cellNum,String data) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\QSP-Ashtra\\eclipse-workspace\\Ninza_CRMAutomationFramework\\src\\test\\resources\\TestScriptData_1.xlsx");
	    Workbook wb = WorkbookFactory.create(fis);
	    wb.createSheet(ShName).createRow(rowNum).createCell(cellNum).setCellValue(data);
	    FileOutputStream fos=new FileOutputStream("C:\\Users\\QSP-Ashtra\\eclipse-workspace\\Ninza_CRMAutomationFramework\\src\\test\\resources\\TestScriptData_1.xlsx");
	    wb.write(fos);
	    wb.close();
	
	}
}
