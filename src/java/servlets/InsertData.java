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
 * Servlet implementation class InsertData
 */
@WebServlet("/InsertData")
public class InsertData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertData() {
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
			out.println( "Registering  JDBC driver");
			//STEP 1: Register JDBC driver. This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			out.println( "Seting up the connection with the DB");
			//STEP 2: Open a connection. Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/JolliiBytesComp","" ,"" );
                        System.out.println("Connected to new database");
            // STEP 3: Statements allow us to  issue SQL queries to the database
			statement = connect.createStatement();
			//STEP 4: declare a query
			String sql = "INSERT INTO products (id, producttype, productname, price, qty, img) VALUES" +
					"(101,'Mountain Bikes', 'lind constellation hoodie', 60, 1000, '/img/lind-hoodie.png'),"+
		    		"(102,'Hiking Clothes', 'lind constellation tee i', 35, 1000, '/img/lind-t-i.png'),"+
		    		"(103,'Hiking Boots', 'lind constellation tee ii', 35, 1000, '/img/lind-t-ii.png'),"+
		    		"(104,'Hiking Tours', 'Love Is Not Dying Poster', 25, 1000, '/img/lind-poster.png')," +
		    		"(105,'Hiking Clothes', 'l<3ve tee i', 30, 1000, '/img/love-t-i.png'),"+
		    		"(106,'Hiking Tours', 'l<3ve tee ii', 30, 1000, '/img/love-t-ii.png')," +
		    		"(107, 'Hiking Tours', 'JEREMY ZUCKER X THE M JEWELERS CHARM NECKLACE', 195, 1000, 'img/lind-necklace.png');";
			//STEP 5: execute a query
			statement.executeUpdate(sql);
			out.println( "Data insert.....");
			
		}catch (Exception e){
			
		}// Exception
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
