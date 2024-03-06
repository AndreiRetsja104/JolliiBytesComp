package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;



/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private PreparedStatement pstatement = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// get dispatcher
		RequestDispatcher rd = request.getRequestDispatcher("welcome.html");
		rd.include(request, response);

		try {
			// STEP 1: Register JDBC driver. This will load the MySQL driver,
			// each DB has its own driver
			//Class.forName("com.mysql.jdbc.Driver");
			// STEP 2: Open a connection. Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/JolliiBytesComp","" ,"" );
                        System.out.println("Connected to new database");
			// STEP 3: Prepare Statements allow us to issue SQL queries to the
			// database
			
			pstatement = connect.prepareStatement("insert into userTable values(?,?,?,?)");
			// set strings into the database
			pstatement.setString(1, request.getParameter("name"));
			pstatement.setString(2, request.getParameter("pass"));
			pstatement.setString(3, request.getParameter("email"));
			pstatement.setString(4, request.getParameter("PostalRegion"));
			
			pstatement.executeUpdate();
		} catch (Exception e) {

		}// Exception

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}