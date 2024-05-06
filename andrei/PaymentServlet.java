package andrei;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve total price from request attribute
        Float totalPrice = (Float) request.getAttribute("totalPrice");
        if (totalPrice != null) {
            // Perform operations on totalPrice
            float value = totalPrice.floatValue();
        } else {
            // Handle the case when totalPrice is null
        }
        
        // Retrieve payment details from request parameters
        String cardNumber = request.getParameter("card_number");
        String cardHolder = request.getParameter("card_holder");
        String expirationDate = request.getParameter("expiration_date");
        String cvv = request.getParameter("cvv");
        String maskedCardNumber = "xxxx xxxx xxxx " + cardNumber.substring(cardNumber.length() - 4);
        
        // Perform payment processing logic here
        // For demonstration purposes, let's assume payment is successful if all fields are provided

        if (cardNumber != null && !cardNumber.isEmpty() && cardHolder != null && !cardHolder.isEmpty()
                && expirationDate != null && !expirationDate.isEmpty() && cvv != null && !cvv.isEmpty()) {
            // Payment successful
        	out.println("<!DOCTYPE html>");
        	out.println("<html>");
        	out.println("<head>");
        	out.println("<title>Thank You!</title>");
        	out.println("<link rel=\"icon\" href=\"img/Logo_JolliBytesComp_96x96.png\" type=\"image/png\">");
        	out.println("<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
        	out.println("<link rel=\"stylesheet\" href=\"css/style.css\">");
        	out.println("<style>");
        	out.println("body {");
        	out.println("    background: url('img/test.png');");
        	out.println("    display: flex;");
        	out.println("    justify-content: center;");
        	out.println("    align-items: center;");
        	out.println("    height: 100vh;");
        	out.println("}");
        	out.println(".content {");
        	out.println("    text-align: center;");
        	out.println("}");
        	out.println(".btn-send {");
        	out.println("    display: inline-block;");
        	out.println("    padding: 10px 20px;");
        	out.println("    font-size: 16px;");
        	out.println("    color: #fff;");
        	out.println("    background-color: #007bff;");
        	out.println("    border: none;");
        	out.println("    border-radius: 5px;");
        	out.println("    cursor: pointer;");
        	out.println("    text-decoration: none;");
        	out.println("}");
        	out.println(".btn-send:hover {");
        	out.println("    background-color: #0056b3;");
        	out.println("}");
        	out.println("</style>");
        	out.println("</head>");
        	out.println("<body class=\"bg-background\">");
        	out.println("<div class=\"content\">");
        	out.println("<h1> Thank you for visiting JolliBytesCom </h1>");
        	out.println("<h2>Payment Successful</h2>");
        	out.println("<p> We will ship your order as soon as possible </p>");
        	out.println("<table class=\"table\">");
        	out.println("<tbody>");
        	out.println("<tr><td>Card Number:</td><td>" + maskedCardNumber + "</td></tr>");
        	out.println("<tr><td>Card Holder:</td><td>" + cardHolder + "</td></tr>");
        	out.println("<tr><td>Expiration Date:</td><td>" + expirationDate + "</td></tr>");
        	out.println("</tbody>");
        	out.println("</table>");
        	out.println("<a href=\"index.html\" class=\"btn btn-send\"> back to main Page  </a>");
        	out.println("</div>");
        	out.println("</body>");
        	out.println("</html>");
   
        } else {
            // Payment failed due to missing or invalid payment details
            out.println("<h2>Payment Failed</h2>");
            out.println("<p>Payment details are missing or invalid. Please try again.</p>");
        }

        out.close();
    }
}