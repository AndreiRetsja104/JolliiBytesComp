package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


/**
 * Servlet implementation class CreateDatabase
 */
@WebServlet("/CreateDatabase")
public class CreateDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
       // static final String URL = "jdbc:mysql://localhost:3306/JolliiBytesComp";
       // static  String user = "root";
       // static  String pass = "";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateDatabase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		try{
                    
                   //  Connection connection = DriverManager.getConnection(URL,user,pass);
                    // Statement statement = connection.createStatement();
                     String createStatement = "CREATE DATABASE IF NOT EXISTS JolliiBytesComp";
                     statement.executeUpdate(createStatement);
                     System.out.println("Database Created");
            
                     connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/JolliiBytesComp","" ,"" );
                     System.out.println("Connected to new database");
                    
                    
                    
                    
			out.println("Registering  JDBC driver");
			//STEP 1: Register JDBC driver. This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			out.println("Seting up the connection with the DB");
			//STEP 2: Open a connection. Setup the connection with the DB
			//connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/JolliiBytesComp","" ,"" );
                       // System.out.println("Connected to new database");
                        
            // STEP 3: Statements allow us to  issue SQL queries to the database
			statement = connect.createStatement();
			//STEP 4: declare a query
			String sql = "CREATE DATABASE IF NOT EXISTS JolliiBytesComp";  //eg 
			//STEP 5: execute a query
			statement.executeUpdate(sql);
			out.println("Database created.....");
			
		}catch (Exception e){
			
		}// Exception
		
	}// end of doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
