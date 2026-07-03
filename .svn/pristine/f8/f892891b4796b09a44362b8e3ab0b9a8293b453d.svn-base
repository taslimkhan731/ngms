package common;

import java.io.InputStreamReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnect {
Connection conn;
	public Connection getConnection() { 
		 try {
		     String driverName = "org.postgresql.Driver";
		     Class.forName(driverName).newInstance(); 
		     Properties properties = new Properties();
		     InputStreamReader  isr = null;
		     try{
		    	 isr = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("conf/D6.properties"));
		         properties.load(isr);
		     	}
		     catch (Exception e) {
		        e.printStackTrace();
			 	} 
		     finally {
				         {
				             try
				             {
				            	 isr.close();
				             }
				             catch (Exception e)
				             {
				                 e.printStackTrace();
				             }
				         }
			    	}
		  
		     String serverName = properties.getProperty("serverName");
		     String portNumber = properties.getProperty("portNumber");
		     String dbname = properties.getProperty("dbname");
		     String url = "jdbc:postgresql://" + serverName + ":" + portNumber + "/" + dbname;
		     String username = properties.getProperty("username");
		     String password = properties.getProperty("password");
		     conn=DriverManager.getConnection(url, username, password);
		 } 
		 catch (ClassNotFoundException e) {e.printStackTrace();
		     System.out.println("db Error1");
		 } 
		 catch (SQLException e) {
			 e.printStackTrace();
		     System.out.println("DB Error 2");
		 }
		 catch (Exception e) {
			
		}
		 return conn;
	}
	public void closeConnection(Connection connection) {
		try{
		connection.close();
		}
		catch (Exception e) {
			
		}
		
	}
	

}
