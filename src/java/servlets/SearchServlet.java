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
import java.sql.PreparedStatement;
import java.sql.ResultSet;



/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PreparedStatement st = null;
	private static Connection con = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		String search = request.getParameter("search");
		String sql = "SELECT * FROM products WHERE productname LIKE '%" + search + "%'";
		switch(search.toLowerCase()) {
		case "Mountan Bikes": {
			sql = "SELECT * FROM products WHERE producttype = 'Mountan Bikes'";
			break;
		}
		case "Hiking Clothes": {
			sql = "SELECT * FROM products WHERE producttype = 'Hiking Clothes'";
			break;
		}
		case "Hiking Boots": {
			sql = "SELECT * FROM products WHERE producttype = 'Hiking Boots'";
			break;
		}
		case "Hiking Tours": {
			sql = "SELECT * FROM products WHERE producttype = 'Hiking Tours'";
			break;
		}
		default: {
			sql = "SELECT * FROM products WHERE productname LIKE '%" + search + "%'";
			break;
		}
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/JolliiBytesComp","" ,"" );
                        System.out.println("Connected to new database");
  			RequestDispatcher rd = request.getRequestDispatcher("ShopDisplay.html");
  			
  			rd.include(request, response);
			st = con.prepareStatement(sql);
  			ResultSet result = st.executeQuery(sql);
  			out.println(result);
            out.println("<!DOCTYPE html><html><head><link rel=\"icon\" href=\"img/Logo-Bike-96x96.png\" type=\"image/png\" /><link rel='stylesheet' href='css/style1.css'/><head><body>");
            //out.println("<script>$(\"#shop\").css(\"display\", \"none\")</script>");
            // Print the result in an HTML form inside a table
            out.println("<div class='container pb-5' style='margin-top: 15vh !important;'>");
            out.println("<p class='montlight text-center'>Searching For... </p><h1 class='montblack' style='text-transform: uppercase; text-align: center;'>"+ search +"</h1>");
            if(!result.next() || search.equalsIgnoreCase("NULL")) {
            	out.println("<h3 class='montlight text-center'>No matched items, please try again</h3>");
            } else {
            out.println("<form method='get' action='OrderServlet'>");
            out.println("<table class=\"table table-borderless m-auto\">\r\n"
            		+ "  <thead>\r\n"
            		+ "    <tr>\r\n"
            		+ "      <th scope=\"col\">&nbsp;</th>\r\n"
            		+ "      <th scope=\"col\">Product Name</th>\r\n"
            		+ "      <th scope=\"col\">Price</th>\r\n"
            		+ "      <th scope=\"col\">&nbsp;</th>\r\n"
            		+ "    </tr>");
			
         // ResultSet's cursor now pointing at first row
            do {
               // Print each row with a checkbox identified by book's id 
               out.println("<tr>");
               out.println("<td class='align-middle'><a class='btn btn-warning montlight' href='QueryServlet?producttype=" +  result.getString("producttype") + "#qty"+result.getString("id")+"'>VIEW</a></td>");
               out.println("<td class='align-middle' id='"+result.getString("id")+"'>" +  result.getString("productname") + "</td>");
               out.println("<td class='align-middle'>$" +  result.getString("price") + "</td>");
               out.println("<td class='align-middle text-center'><img class='product' style='height: auto; width: 150px;'src='" + result.getString("img") +  "'></td>");
               out.println("</tr>");
            } while ( result.next());
            out.println("</table><br />");
            
            }
               
            out.println("</div>");
	           out.println("</body></html>");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
