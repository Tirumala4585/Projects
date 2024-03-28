package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
       public class DatabaseConnection {
		public static Connection getConnection() {
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/bank";
			String userName="root";
			String password="radha";
			return DriverManager.getConnection(url,userName,password);
		}
		catch(Exception ie) {
			ie.printStackTrace();
		}
		return null;	
	  }	
	  public static void closeConnection(Connection con)	{
		  if(con!=null) {
			  try {
				con.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			  
		  }
	  }

	}
 