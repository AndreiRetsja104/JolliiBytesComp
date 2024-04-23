package andrei;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/chartForJason")
public class ChartForJasonServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("loggedIn") != null && (Boolean) session.getAttribute("loggedIn")) {
            // User is logged in, proceed with rendering the chartForJason page
            // Here you can render the chart or whatever content you have for the chartForJason page
            response.setContentType("text/html");
            response.getWriter().println("<html><head><title>Chart For Jason</title></head><body>");
            response.getWriter().println("<h1>Welcome to the Chart For Jason page</h1>");
            response.getWriter().println("<p>Here you can render your chart or any other content</p>");
            response.getWriter().println("</body></html>");
        } else {
            // User is not logged in, redirect to the login page or handle accordingly
            response.sendRedirect("login.html");
        }
    }
}
