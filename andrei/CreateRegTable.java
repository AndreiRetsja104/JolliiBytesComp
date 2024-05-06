package andrei;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateTable
 */
@WebServlet("/CreateRegTable")
public class CreateRegTable extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateRegTable() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Prints formatted representations of objects to a text-output stream
		PrintWriter out = response.getWriter();

		try {
			out.println("Registering JDBC driver");
			// STEP 1: Register JDBC driver. This will load the MySQL driver,
			// each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			out.println("Setup the connection with the DB");
			// STEP 2: Open a connection. Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/jolli_bytes_com", "root", "");
			// STEP 3: Statements allow us to issue SQL queries to the database
			statement = connect.createStatement();
			// STEP 4: declare a query
			String sql = "CREATE TABLE userTable (userName VARCHAR(50), userPass VARCHAR(50), userEmail VARCHAR(50), userPostalRegion VARCHAR(50))";
			// STEP 5: execute a query
			statement.executeUpdate(sql);
			out.println("Database Created...");
		} catch (Exception e) {

		} // Exception
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
