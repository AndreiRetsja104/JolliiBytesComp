package andrei;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    PrintWriter out = response.getWriter();
	    if (LoginDatabaseConnector.validate(request.getParameter("name"), request.getParameter("pass"))) {
	        forwardToWelcome(request, response); // Call the forwardToWelcome method
	    } else {
	        includeLoginPage(request, response);

	        // Print login failure message
	        out.println("\r\n" + "<!-- Modal -->\r\n"
	                + "<div class=\"modal fade\" id=\"loginFail\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">"
	                + "  <div class=\"modal-dialog modal-dialog-centered\" role=\"document\">"
	                + "    <div class=\"modal-content\">" + "      <div class=\"modal-body\">" + "          <br>"
	                + "        <p class=\"montlight\" style=\"text-align: center;\">Login Failed, Please Try Again!</p><br>"
	                + "<p style=\"text-align: center;\"><a href=\"login.html\"><button class=\"btn btn-secondary ProductSans\">BACK</button></a></p>"
	                + "      </div>\r\n" + "    </div>\r\n" + "  </div>\r\n" + "</div>" + "\r\n" + "\r\n"
	                + "<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n"
	                + "<!-- Bootstrap tooltips -->\r\n"
	                + "<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js\"></script>\r\n"
	                + "<script src=\"js/bootstrap.bundle.js\"></script>" + "<script>" + "$(document).ready(function(){"
	                + "$('#loginFail').modal('show')" + "});" + "</script>");
	    }
	    out.close();
	}

	 // Method to forward to the WelcomeServlet
    protected void forwardToWelcome(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("WelcomeServlet");
        rd.forward(request, response);
    }

    // Method to include the login page
    protected void includeLoginPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("login.html");
        if (rd != null) {
            rd.include(request, response);
        } else {
            // Handle the case where rd is null
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found: login.html");
        }
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
