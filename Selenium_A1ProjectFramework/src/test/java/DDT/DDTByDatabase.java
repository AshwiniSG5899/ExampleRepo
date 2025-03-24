package DDT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DDTByDatabase {

	public static void main(String[] args) throws SQLException {
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", "root", "rmgy@9999");

		Statement stmt = conn.createStatement();
		
		ResultSet result = stmt.executeQuery("select * from actor where actor_id=5");
		
		while(result.next())
		{
			int actorId = result.getInt(1);
			String firstName = result.getString(2);
			String lastName = result.getString(3);
			System.out.print(actorId+" ");
			System.out.print(firstName+" ");
			System.out.println(lastName);
			
		}
		conn.close();
	}

}
