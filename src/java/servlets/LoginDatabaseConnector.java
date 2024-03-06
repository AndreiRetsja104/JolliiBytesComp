package servlets;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class LoginDatabaseConnector {
	public static boolean validate(String name,String pass){  
		boolean status=false;
		 Connection connect = null;
		 PreparedStatement pstatement = null;
		
		try{
			//STEP 1: Register JDBC driver. This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			//STEP 2: Open a connection. Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/JolliiBytesComp","" ,"" );
                        System.out.println("Connected to new database");
            // STEP 3:  Prepare Statements allow us to  issue SQL queries to the database
			
			pstatement=connect.prepareStatement(  "select * from userTable where UserName=? and UserPass=?");
			
			pstatement.setString(1,name);  
			pstatement.setString(2,pass);  
			      
			ResultSet rs=pstatement.executeQuery();  
			status=rs.next(); 
			
		}catch (Exception e){
			
		}// Exception
		return status;
		
		
	}

}//end of class
