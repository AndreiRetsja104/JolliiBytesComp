package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



/**
 * Servlet implementation class WelcomeServlet
 */
@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

        if(request.getParameter("name")!=null && !request.getParameter("name").isEmpty()) {
    		out.print("<div class='container' style='margin-top: 15vh;'>"
    				+ "<div class='col-md-4 m-auto'>"
    				+ "<div class=\"alert alert-success\" role=\"alert\"><br>"
    				+ "<p class=\"montlight m-auto\" style=\"text-align: center;\">Welcome " + request.getParameter("name") + "</p><br>\r\n"
    				+ "</div></div></div>");
        }
		RequestDispatcher rd=request.getRequestDispatcher("EshopDisplayServlet");  
        rd.include(request,response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
