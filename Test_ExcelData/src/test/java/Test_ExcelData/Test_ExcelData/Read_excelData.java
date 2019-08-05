package Test_ExcelData.Test_ExcelData;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Read_excelData 
{
	WebDriver driver;
	String RpPath=System.getProperty("user.dir");
	@Test
	public void readData() throws Exception
	{
		try
		{
			System.setProperty("webdriver.chrome.driver", RpPath+"\\webdrivers\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.get("https://accounts.google.com/signin/v2/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//input[@name='identifier']")).click();
			System.out.println("Fetching User DATA from properties file");
			System.out.println(Getdata.getTestData().get("UserName"));
			driver.findElement(By.xpath("//input[@name='identifier']")).sendKeys(Getdata.getTestData().get("UserName"));
			driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@name='password']")).click();
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Getdata.getTestData().get("password"));
			driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
			Thread.sleep(10000);

			//driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys(arg0);

			String path = RpPath+"\\Read_data.xlsx";

			FileInputStream fis = new FileInputStream(path);

			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);

			int lastRow = sheet.getLastRowNum();
			
			System.out.println("Excel Sheet loaded successfully");
			for(int i=0; i<=lastRow; i++)
			{

				Row row = sheet.getRow(i);
				Cell cell = row.getCell(0);
				String value = cell.getStringCellValue();
			

				driver.findElement(By.xpath("//div[contains(text(),'Compose')]")).click();

				Thread.sleep(3000);
				System.out.println("mail names: "+value);
				driver.findElement(By.xpath("//textarea[@name='to']")).click();
				driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys(value);
				Thread.sleep(3000);
				driver.findElement(By.xpath("//div/input[@name='subjectbox']")).click();
				driver.findElement(By.xpath("//div/input[@name='subjectbox']")).sendKeys("Test Assignment");
				Thread.sleep(3000);
				driver.findElement(By.xpath("//div[@role='textbox']")).click();
				driver.findElement(By.xpath("//div[@role='textbox']")).sendKeys("This is Datatest sample email");
				Thread.sleep(3000);
				driver.findElement(By.xpath("//div[@class='dC']/div[contains(text(),'Send')]")).click();
				Thread.sleep(5000);

			}
			
			driver.quit();

		}

		catch(Exception e)
		{
			throw e;
		}
	}

}
