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
import java.sql.ResultSet;
import java.sql.Statement;



/**
 * Servlet implementation class ShopOrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet result = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		// Prints formatted representations of objects to a text-output stream
		String[] ids = request.getParameterValues("id"); // Possibly more than
															// one values
		try {

			// STEP 1: Register JDBC driver. This will load the MySQL driver,
			// each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 2: Open a connection. Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/JolliiBytesComp","" ,"" );
                        System.out.println("Connected to new database");
			// STEP 3: Statements allow us to issue SQL queries to the database
			statement = connect.createStatement();
			// Print html with CSS style sheet

  			RequestDispatcher rd = request.getRequestDispatcher("ShopDisplay.html");
  			rd.include(request, response);
			out.println("<!DOCTYPE html><html><head><link rel=\"icon\" href=\"img/Logo-Bike-96x96.png\" type=\"image/png\" /><link rel='stylesheet' href='css/style1.css'/><head><body>");
			out.println("<div id='wrapper'> <!-- This wrapper centers the page--><header> <!-- This is HEADER--></header><nav><!-- This is the Navigation bar--> <ul><li><a href='index.html'>Home</a></li></ul></nav><div id ='mainsection'> <!-- This is the main content bar--><div id='scr'></div><div id='main'><!-- This is the  form block--><div id='main-wrapper'<form action='LoginServlet' method='get' class='form'>  <!-- Start of form-->");
			// Create table
			out.println("<div class='container'>");
			if(ids != null) {
            out.println("<h1 class='montblack' style='margin-top: 15vh !important; text-transform: uppercase; text-align: center;'>Checkout</h1>");
               out.println("<table class=\"table table-borderless m-auto\">\r\n"
               		+ "  <thead>\r\n"
               		+ "    <tr>\r\n"
               		+ "      <th scope=\"col\">&nbsp;</th>\r\n"
               		+ "      <th scope=\"col\">Product Name</th>\r\n"
               		+ "      <th scope=\"col\">Price</th>\r\n"
               		+ "      <th scope=\"col\">Quantity</th>\r\n"
               		+ "      <th scope=\"col\">&nbsp;</th>\r\n"
               		+ "    </tr>");

			float totalPrice = 0f;
			for (String id : ids) {
				String sql = "SELECT * FROM products WHERE id = " + id;

				result = statement.executeQuery(sql);

				// Expect only one row in ResultSet
				result.next();
				// use following strings
				String product = result.getString("producttype");
				String brand = result.getString("productname");
				String img = result.getString("img");
				float price = result.getFloat("price");

				int qtyOrdered = Integer.parseInt(request.getParameter("qty"
						+ id));
				sql = "UPDATE products SET qty = qty - " + qtyOrdered
						+ " WHERE id = " + id;

				statement.executeUpdate(sql);
				// Display this book ordered
                out.println("<tr>");
                out.println("<td class='align-middle'>" +  product + "</td>");
                out.println("<td class='align-middle'>" +  brand + "</td>");
                out.println("<td class='align-middle'> $" + price + " </td>");
                out.println("<td class='align-middle'> " + qtyOrdered + " </td>");
                out.println("<td class='align-middle text-center'><img class='product' style='height: auto; width: 150px;'src='" + img + "'></td>");
                out.println("</tr>");
				totalPrice += price * qtyOrdered;
			}

			out.println("<tr><td>Total Price: <h3 class='montblack'>$");
			out.printf("%.2f</h3></td><td class='text-center align-center'></tr>", totalPrice);
			out.println("</table>");

			
				// print the following strings and text
			out.println("");
				out.println("<h2 class='text-center montblack'>Thank You! </h2>");
				out.println("<p class='text-center montlight'>Your order will be shipped in 2 days<br/><b><br><br><br>");
				out.println("<a class='btn btn-outline-success m-auto text-center' href='WelcomeServlet'>Return To Home Page</a>");
                out.println("</div>");
			

			out.println("</form> <!-- end of form--></div></div><!-- end of main--><p></p></div> <!-- end of mainsection --> </div> <!-- end of wrapper --></body></html>");
			// end of form
			} else {
				response.sendRedirect("WelcomeServlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
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