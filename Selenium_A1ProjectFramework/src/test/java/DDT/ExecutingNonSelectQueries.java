package DDT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ExecutingNonSelectQueries {

	public static void main(String[] args) throws SQLException {
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "rmgy@9999");

		Statement stmt = conn.createStatement();
		
		int result = stmt.executeUpdate("insert into actor values(203,'Ashwini','Gulannanavar','2006-02-15 04:34:33')");
		
		System.out.println(result);
		if(result!=0)
		{
			System.out.println("Operation is successFully");
		}
		else
		{
			System.out.println("Operation is failed");
		}
		
		conn.close();

	}

}
