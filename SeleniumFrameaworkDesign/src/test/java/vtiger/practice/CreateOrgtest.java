package vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateOrgtest 
{
	public static void main(String[] args) throws IOException 
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
		String orgName = sheetOrg.getRow(1).getCell(2).getStringCellValue();
		
		
		
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
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step5 Verify expected Result
		//VerifyHeader orgName info Expected result
		
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerInfo.contains(orgName))
		{	
			System.out.println(orgName+ "Is created ======== PASS");
			
		}
		
		// Verify Header organization name
		String actualOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		
		if(actualOrgName.equals(orgName))
		{
			System.out.println("ORGANAZATION NAME IS SAME AS EXPECTED ======= PASS");
		}
		else
			System.out.println("===============FAIL===========");
		driver.close();
		
		
		
		
	}

}
