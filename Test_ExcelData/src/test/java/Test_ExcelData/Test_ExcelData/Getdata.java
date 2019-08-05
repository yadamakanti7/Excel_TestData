package Test_ExcelData.Test_ExcelData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class Getdata 
{
	static String RpPath=System.getProperty("user.dir");
	public static HashMap<String, String> getTestData()
	{
        Properties prop = new Properties();
        HashMap<String, String> propvals = new HashMap<String, String>();
        try {
               FileInputStream fis = new FileInputStream(new File(RpPath+"\\TestData.properties"));   
               prop.load(fis);
               Set<String> propertyNames = prop.stringPropertyNames();
               for (String Property : propertyNames) {
                      propvals.put(Property, prop.getProperty(Property));
               }
        } 
        catch (FileNotFoundException e) 
        {
               e.printStackTrace();
        } 
        catch (IOException e) 
        {
               e.printStackTrace();
        } 
        catch (Exception e) 
        {
               e.printStackTrace();
        }
        return propvals;
  }

}
