package andrei;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeliveryServlet")
public class DeliveryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle GET requests here
        // For example, you can forward to a JSP page to display a form for entering delivery information
        // Or you can simply redirect to the order review page if you don't have a separate delivery form
        response.sendRedirect("delivery_form.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String address1 = request.getParameter("address1");
        if (address1 == null || address1.isEmpty()) {
        // Handle the case where address1 is null or empty, such as setting a default value or displaying an error message
        // For example:
        out.println("Address1 cannot be null or empty");
        return; // Stop further processing
        }
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        
       
        // Get the current date
        java.util.Date currentDate = new java.util.Date();
        java.sql.Date orderDate = new java.sql.Date(currentDate.getTime());

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/jolli_bytes_com", "root", "");
            PreparedStatement ps = conn.prepareStatement(
            	    "INSERT INTO deliveryTable (name, address, address1, city, phone, email, order_date) VALUES (?, ?, ?, ?, ?, ?, ?)");
            
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, address1);
            ps.setString(4, city);
            ps.setString(5, phone);
            ps.setString(6, email);
            ps.setDate(7, orderDate); // Set the order date

            // Store the user details as request attributes
            request.setAttribute("name", name);
            request.setAttribute("address", address);
            request.setAttribute("address1", address1);
            request.setAttribute("city", city);
            request.setAttribute("phone", phone);
            request.setAttribute("email", email);

            // Forward the request to the order review page
            request.getRequestDispatcher("order_review_page.jsp").forward(request, response);
            
            
            int i = ps.executeUpdate();
            if (i > 0) {
                // If insertion is successful, redirect the user to the order review page
                response.sendRedirect("order_review_page.jsp");
            } else {
                // If insertion fails, display an error message
                out.print("Failed to submit delivery information!");
            }

        } catch (Exception e) {
            out.print(e);
        } finally {
            out.close();
        }
    }
}