package amrutha.SeleniumFrameaworkDesign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class JDBCConnection 
{	
	public static void main(String[] args) throws SQLException
	{	
		//Driver Class Should come from Com.mysql.jdbc.driver
		Driver dbd = new Driver();
		//registering mysql datareference object with 
		DriverManager.registerDriver(dbd);
		Connection conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects","root@%","root");
		System.out.println("Connection established");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("Select * from project");
		while(rs.next())
		{	
			System.out.println(rs.getString(1)+"  "+rs.getString(1)+"  "+rs.getString(3));			
		
		}
		
		
		
		
	}

}
