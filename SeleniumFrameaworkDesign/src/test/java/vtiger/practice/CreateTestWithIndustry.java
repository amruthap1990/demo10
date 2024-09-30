package vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateTestWithIndustry 
{	
	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{	
		//Read the data from property file
				FileInputStream fis = new FileInputStream("C:\\Users\\Amrutha Perumal\\Desktop\\codeTry\\VTiger\\CommonData.properties");
				Properties p = new Properties();
				p.load(fis);
				
				String URL = p.getProperty("url");
				String USERNAME = p.getProperty("username");
				String PASSWORD = p.getProperty("password");
				String BROWSER = p.getProperty("browser");
				
				//Reading Data from the Excel File
				FileInputStream fisExcel= new FileInputStream("C:\\Users\\Amrutha Perumal\\Desktop\\codeTry\\VTiger\\VTiger_TSData.xlsx");
				
				Workbook workbook = WorkbookFactory.create(fisExcel);
				Sheet sheetOrg = workbook.getSheetAt(0);
				String orgName = sheetOrg.getRow(2).getCell(2).getStringCellValue();
				String indVal= sheetOrg.getRow(2).getCell(3).getStringCellValue();
				String PhoneNo = sheetOrg.getRow(3).getCell(4).getStringCellValue();
				
				
				
				//create the browser according to the data read by the property file
				WebDriver driver = null;
				if(BROWSER.equals("Chrome")) {
					driver = new ChromeDriver();}
				else if(BROWSER.equals("firefox")) {
					driver = new FirefoxDriver();}
				else if(BROWSER.equals("edge")) {
					driver = new EdgeDriver();	}
				else { driver = new ChromeDriver();}
				
				driver.get(URL);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				
				//Step1 : Login to Application
				
				driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
				// Step2: navigate to organization module		
				driver.findElement(By.linkText("Organizations")).click();
				
				//Step3 : Click on Create Organization Button
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
				//Step4: Enter all details and create new organization.
				
				Random r = new Random();
				int rn=r.nextInt(250);
				orgName = orgName+rn;
				driver.findElement(By.name("accountname")).sendKeys(orgName);
				
				
				//select Industry Drop Down
				Select s = new Select(driver.findElement(By.name("industry")));
				s.selectByValue(indVal);
				
				Select s2 = new Select(driver.findElement(By.name("accounttype")));
				s2.selectByIndex(2);
				
				//Add Phone number to the field 
				driver.findElement(By.id("phone")).sendKeys(PhoneNo);
				
				//Click on save
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//VerifyHeader dropdown industry drop down and type value
				
				String actualIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
				if(actualIndustry.equals(indVal))
				{
					System.out.println("Actual Industry value is the same");
				}
				else
					System.out.println("TEST CASE FAIL");
					
				
				
				
				
				

		
	}

}
