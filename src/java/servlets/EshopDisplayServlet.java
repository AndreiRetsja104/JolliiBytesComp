package servlets;

import jakarta.servlet.RequestDispatcher;
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
import java.sql.ResultSet;



/**
 * Servlet implementation class EshopDisplayServlet
 */
@WebServlet("/EshopDisplayServlet")
public class EshopDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet result = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EshopDisplayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
		
        protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    		// TODO Auto-generated method stub
    		// Prints formatted representations of objects to a text-output stream
    		PrintWriter out = response.getWriter();

    		try {

    			// STEP 1: Register JDBC driver. This will load the MySQL driver,
    			// each DB has its own driver
    			Class.forName("com.mysql.jdbc.Driver");

    			// STEP 2: Open a connection. Setup the connection with the DB
    			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/JolliiBytesComp","" ,"" );
                        System.out.println("Connected to new database");
    			// STEP 3: Statements allow us to issue SQL queries to the database
    			statement = connect.createStatement();
    			// STEP 4: declare a query
    			String sql = "SELECT DISTINCT producttype FROM products WHERE qty > 0";
    			// STEP 5: execute a query
    			result = statement.executeQuery(sql);
    			if(request.getParameter("name")!=null && !request.getParameter("name").isEmpty()) {
    			out.println("\r\n"
    					+ "<!-- Modal -->\r\n"
    					+ "<div class=\"modal fade\" id=\"loginSuccess\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\r\n"
    					+ "  <div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\r\n"
    					+ "    <div class=\"modal-content\">\r\n"
    					+ "      <div class=\"modal-body\">\r\n"
    					+ "        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\r\n"
    					+ "          <span aria-hidden=\"true\">&times;</span>\r\n"
    					+ "          </button>\r\n"
    					+ "          <br><br>\r\n"
    					+ "        <p class=\"montlight\" style=\"text-align: center;\">Login Successful</p><br>\r\n"
    					+ "      </div>\r\n"
    					+ "    </div>\r\n"
    					+ "  </div>\r\n"
    					+ "</div>"
    					+ "\r\n"
    					+ "<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>"
    					+ "<script>"
    					+ "$(document).ready(function(){"
    					+ "$('#loginSuccess').modal('show')"
    					+ "});"
    					+ "</script>");
    			}
    			RequestDispatcher rd = request.getRequestDispatcher("ShopDisplay.html");
    			rd.include(request, response);
    			RequestDispatcher rd2 = request.getRequestDispatcher("home.html");
    			rd2.include(request, response);

    		} catch (Exception e) {

    		}
    	}

    	/**
    	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    	 *      response)
    	 */
    	protected void doPost(HttpServletRequest request,
    			HttpServletResponse response) throws ServletException, IOException {
    		// TODO Auto-generated method stub
    	}

    }

	