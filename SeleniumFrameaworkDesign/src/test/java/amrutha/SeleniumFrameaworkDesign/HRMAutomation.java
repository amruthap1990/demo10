package amrutha.SeleniumFrameaworkDesign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

public class HRMAutomation 
{	
	public static void main(String[] args) throws SQLException 
	{	
		String ProjectName = "MyNew123";
		WebDriver driver = new ChromeDriver();
		driver.get("http://106.51.90.215:8084/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[.='Sign in']")).click();
		
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//span[.='Create Project']")).click();
		
		driver.findElement(By.name("projectName")).sendKeys(ProjectName);
		driver.findElement(By.name("createdBy")).sendKeys("TeamLead70");
		
		Select s = new Select(driver.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select[@name='status']")));
		s.selectByIndex(1);
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		
		//verify weather it is Addedto  the DataBase.
		
		Driver DBdriver = new Driver();
	  DriverManager.registerDriver(DBdriver);
	  Connection connection = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects","root@%","root");
	  Statement statement = connection.createStatement();
	  String querry="select * from project where project_name='"+ProjectName+"';";
	 // statement.executeQuery(querry);
	  ResultSet resultSet = statement.executeQuery(querry);
	int size = resultSet.getFetchSize();
	  //  boolean last = resultSet.last();
	  //System.out.println("============="+last+"====================");
	   System.out.println(size);
		connection.close();
		
	}

}
