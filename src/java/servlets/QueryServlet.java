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
 * Servlet implementation class QueryServlet
 */
@WebServlet("/QueryServlet")
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet result= null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryServlet() {
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
			
			//STEP 1: Register JDBC driver. This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//STEP 2: Open a connection. Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/JolliiBytesComp","" ,"" );
                        System.out.println("Connected to new database");
                         // STEP 3: Statements allow us to  issue SQL queries to the database
			statement = connect.createStatement();
			// Retrieve and process request parameter: "product"
			String product = request.getParameter("producttype");
			
			
			out.println("<html><head><body>");
			
			
			String sql = "SELECT * FROM products WHERE qty > 0 AND ( producttype = '"+ product+"') ORDER BY id";
			 result = statement.executeQuery(sql);
              
               result.next();

      			RequestDispatcher rd = request.getRequestDispatcher("ShopDisplay.html");
      			rd.include(request, response);
               out.println("<!DOCTYPE html><html><head><link rel=\"icon\" href=\"img/Logo-Bike-96x96.png\" type=\"image/png\" /><link rel='stylesheet' href='css/style1.css'/><head><body>");
               // Print the result in an HTML form inside a table
               out.println("<div class='container pb-5'>");
               out.println("<h1 class='montblack' style='margin-top: 15vh !important; text-transform: uppercase; text-align: center;'>"+product+"</h1>");
               out.println("<form method='get' action='OrderServlet'>");
               out.println("<table class=\"table table-borderless m-auto\">"
               		+ "  <thead>\r\n"
               		+ "    <tr>\r\n"
               		+ "      <th scope=\"col\">&nbsp;</th>\r\n"
               		+ "      <th scope=\"col\">Product Name</th>\r\n"
               		+ "      <th scope=\"col\">Price</th>\r\n"
               		+ "      <th scope=\"col\">Quantity</th>\r\n"
               		+ "      <th scope=\"col\">&nbsp;</th>\r\n"
               		+ "    </tr>");
			
            // ResultSet's cursor now pointing at first row
               do {
                  // Print each row with a checkbox identified by book's id
                  String id =  result.getString("id");
                  out.println("<tr>");
                  out.println("<td class='align-middle'><input style='height: 25px; width: 25px;' type='checkbox' name='id' value='" + id +   "' /></td>");
                  out.println("<td class='align-middle'>" +  result.getString("productname") + "</td>");
                  out.println("<td class='align-middle'>$" +  result.getString("price") + "</td>");
                  out.println("<td class='align-middle'>"
                  		+ "<div class=\"input-group\">"
                  		+ "<div class=\"input-group-prepend\">"
                  		+ "<input type=\"button\" class=\"incdec" + id + " decrement btn btn-outline-secondary\" value=\"-\" />"
                  		+ "</div>"
                  		+ "<input type='text' size='3' value='1' class='form-control col-lg-3 text-center' name='qty"  + id +   "' id='qty"   + id +   "'/>"
                        + "<div class=\"input-group-append\"><input type=\"button\" class=\"incdec" + id +  " increment btn btn-outline-secondary\" value=\"+\" /></td>"
                        + "</div>"
          				+ "<script>"
          				+ "var $input" + id + " = $(\"#qty" + id + "\");"
          				+ "$input" + id + ".val(1);"
          				+ "$(\".incdec" + id + "\").click(function(){"
          				+ "if ($(this).hasClass('increment'))"
          				+ "$input" + id + ".val(parseInt($input" + id + ".val())+1);"
          				+ "else if ($input"+ id +".val()>=1)"
          				+ "$input" + id + ".val(parseInt($input"+ id +".val())-1);"
          				+ "});"
          				+ "</script>");
                  out.println("<td class='align-middle text-center'><img class='product' style='height: auto; width: 150px;'src='" + result.getString("img") +  "'></td>");
                  out.println("</tr>");
               } while ( result.next());
               out.println("</table><br />");
               
               out.println("<input type='submit' value='ORDER' class='ProductSans btn btn-success' />");
               out.println("<input type='reset' value='CLEAR' class='ProductSans btn btn-secondary'/></form>");
 
                  
               out.println("</div>");
  	           out.println("</body></html>");
			
        }catch (Exception e){
    		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}