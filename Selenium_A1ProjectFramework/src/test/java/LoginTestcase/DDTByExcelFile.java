package LoginTestcase;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DDTByExcelFile {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\QSP-Ashtra\\practice\\Selenium_A1ProjectFramework\\src\\test\\resources\\Data2.xlsx");
        Workbook wb = WorkbookFactory.create(fis);
        String data1 = wb.getSheet("DDT").getRow(0).getCell(0).getStringCellValue();
        String data2 = wb.getSheet("DDT").getRow(1).getCell(1).getStringCellValue();
        String data3 = wb.getSheet("DDT").getRow(2).getCell(2).getStringCellValue();
        System.out.println(data1);
        System.out.println(data2);
        System.out.println(data3);
	}

}
