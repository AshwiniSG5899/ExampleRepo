package fileUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtility {
public String readDataFromPropFile(String key) throws IOException
{
	FileInputStream fis=new FileInputStream("C:\\Users\\QSP-Ashtra\\eclipse-workspace\\Ninza_CRMAutomationFramework\\src\\test\\resources\\Commondata_1.properties");
    Properties prop=new Properties();
    prop.load(fis);
    String data = prop.getProperty(key);
    return data;

}
}
