package andrei;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List; // Import List interface
import andrei.Product; // Import the Product class

@WebServlet("/OrderReviewServlet")
public class OrderReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve order details from request parameters
        String[] productNames = request.getParameterValues("productName");
        String[] quantities = request.getParameterValues("quantity");
        String[] prices = request.getParameterValues("price");
        String deliveryAddress = request.getParameter("deliveryAddress");
        String deliveryCity = request.getParameter("deliveryCity");
        String deliveryPhone = request.getParameter("deliveryPhone");

        // Display order review
        out.println("<h2>Order Review</h2>");
        out.println("<h3>Products:</h3>");
        out.println("<ul>");
        for (int i = 0; i < productNames.length; i++) {
            out.println("<li>" + productNames[i] + " - Quantity: " + quantities[i] + " - Price: $" + prices[i] + "</li>");
        }
        out.println("</ul>");
        out.println("<h3>Delivery Details:</h3>");
        out.println("<p>Address: " + deliveryAddress + "</p>");
        out.println("<p>City: " + deliveryCity + "</p>");
        out.println("<p>Phone: " + deliveryPhone + "</p>");
        out.println("<h3>Payment Status:</h3>");
        out.println("<p>Payment Approved</p>");
        
        // Retrieve productList from the servlet context or session
        List<Product> productList = (List<Product>) getServletContext().getAttribute("productList");

        // Display product images
        out.println("<h2>Product Images</h2>");
        out.println("<ul>");
        for (Product product : productList) {
            out.println("<li><img src='img_product/" + product.getImageURL() + "' alt='Product Image'></li>");
        }
        out.println("</ul>");

        out.close();
    }
}