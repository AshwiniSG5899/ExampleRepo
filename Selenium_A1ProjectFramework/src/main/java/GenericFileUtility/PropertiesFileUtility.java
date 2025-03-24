package GenericFileUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtility {

	public String getDataFromPropFile(String key) throws IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\QSP-Ashtra\\practice\\Selenium_A1ProjectFramework\\src\\test\\resources\\CommonData_A1.properties");
	   Properties prop=new Properties();
	   prop.load(fis);
	   String data = prop.getProperty(key);
	   return data;
	}
}
