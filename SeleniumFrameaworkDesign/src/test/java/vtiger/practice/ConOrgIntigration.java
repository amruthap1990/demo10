package vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.swing.text.DateFormatter;

import org.apache.poi.hpsf.Date;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConOrgIntigration
{	
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\Amrutha Perumal\\Desktop\\codeTry\\VTiger\\CommonData.properties");
		Properties p = new Properties();
		p.load(fis);
		
		String BROWSER = p.getProperty("browser");
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		
		WebDriver driver = null;
		//Reading Data from Excel File
		
		FileInputStream fisExcel = new FileInputStream("C:\\Users\\Amrutha Perumal\\Desktop\\codeTry\\VTiger\\VTiger_TSData.xlsx");
		Workbook tsWorkbook = WorkbookFactory.create(fisExcel);
		Sheet sheetCon = tsWorkbook.getSheet("contact");
		Sheet sheetOrg = tsWorkbook.getSheet("org");
		
		String OrgNameExcel = sheetOrg.getRow(4).getCell(2).toString();
		String lastName = sheetCon.getRow(2).getCell(2).toString();
		Random r = new Random();
		int randomNum= r.nextInt(250);
		String orgName =OrgNameExcel+randomNum;
		
		if(BROWSER.equals("Chrome") )
		{ driver = new ChromeDriver();}
		else if(BROWSER.equals("firefox"))
		{new FirefoxDriver();}
		else if(BROWSER.equals("edge"))
		{new EdgeDriver();}
		else
			new ChromeDriver();
			
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Create organization
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']/ancestor::a")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		
		//Navigate to Contacts Page
		Thread.sleep(3000);
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		
		WebDriverWait wait =new  WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='firstname']"))));
		
		
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		Set<String> setwindoes = driver.getWindowHandles();
		//String conactionWindow =null;
		Iterator<String> it = setwindoes.iterator();
		while(it.hasNext())
		{
			String window = it.next();
			String currentUrl = driver.switchTo().window(window).getCurrentUrl();
			if(currentUrl.contains("module=Accounts&action"))
							break;
			
		}
		//wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		//driver.switchTo().window(conactionWindow);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("search_txt"))));
		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		//Support Start Date
		
		//driver.findElement(By.id("jscal_field_support_start_date")).clear();
		//Date d = new Date();
		
		
		
		
		
		//driver.findElement(By.xpath("//img[@title='Create Contact...']/ancestor::a")).click();
		
		
		
		
		
		
	}

}
