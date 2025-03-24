package GenericFileUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {

	public String getDataFromExcel(String sheet,int row,int cell) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\QSP-Ashtra\\practice\\Selenium_A1ProjectFramework\\src\\test\\resources\\Data2.xlsx");
	    Workbook wb = WorkbookFactory.create(fis);
	    String data = wb.getSheet(sheet).getRow(row).getCell(cell).getStringCellValue();
	   return data;
	}
	public int getRowCount(String sheet) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\QSP-Ashtra\\practice\\Selenium_A1ProjectFramework\\src\\test\\resources\\Data2.xlsx");
	    Workbook wb = WorkbookFactory.create(fis);
	    int row = wb.getSheet(sheet).getLastRowNum();
	    return row;
	}
}
