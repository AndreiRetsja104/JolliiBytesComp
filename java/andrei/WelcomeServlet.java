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
	 * @param request
	 * @param response
	 * @throws jakarta.servlet.ServletException
	 * @throws java.io.IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		if (request.getParameter("name") != null && !request.getParameter("name").isEmpty()) {
			out.print("<div class='container' style='margin-top: 15vh;'>" + "<div class='col-md-4 m-auto'>"
					+ "<div class=\"alert alert-success\" role=\"alert\"><br>"
					+ "<p class=\"montlight m-auto\" style=\"text-align: center;\">Welcome "
					+ request.getParameter("name") + "</p><br>\r\n" + "</div></div></div>");
		}
		RequestDispatcher rd = request.getRequestDispatcher("EshopDisplayServlet");
		rd.include(request, response);
	}

	/**
	 * @param request
	 * @param response
	 * @throws jakarta.servlet.ServletException
	 * @throws java.io.IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
