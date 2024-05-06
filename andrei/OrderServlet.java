package andrei;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet result = null;
    private int quantity;
    
    // Method to calculate TotalPrice 
    private float calculateTotalPrice(List<Product> products) {
        float totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice() * product.getQuantity();
        }
        return totalPrice;
    }
 // Method to calculate discount based on total price
    private float calculateDiscount(float totalPrice) {
        float discount = 0f;
        if (totalPrice >= 100) { // Apply a 10% discount for orders over 100 euros
            discount = totalPrice * 0.1f;
        } else if (totalPrice >= 50) { // Apply a 5% discount for orders over 50 euros
            discount = totalPrice * 0.05f;
        }
        return discount;
    }
    
    public OrderServlet() {
    super();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] ids = request.getParameterValues("id");
        if (ids == null || ids.length == 0) {
            response.sendRedirect("WelcomeServlet");
            return; // Early return
        }
        PrintWriter out = response.getWriter();
        List<Product> products = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/jolli_bytes_com", "root", "");
            statement = connect.createStatement();

            RequestDispatcher rd = request.getRequestDispatcher("ShopDisplay.html");
            rd.include(request, response);

            out.println("<!DOCTYPE html><html><head><link rel=\"icon\" href=\"img/Logo-Bike-96x96.png\" type=\"image/png\" /><link rel='stylesheet' href='css/style1.css'/><head><body>");
            out.println("<div id='wrapper'><header></header><nav><ul><li><a href='index.html'>Home</a></li></ul></nav><div id ='mainsection'><div id='scr'></div><div id='main'><div id='main-wrapper'><form action='LoginServlet' method='get' class='form'>");

            out.println("<div class='container'>");
            if (ids != null) {
                out.println("<h1 class='montblack'>Checkout</h1>");
                out.println("<table class=\"table table-borderless m-auto\">\r\n" + "  <thead>\r\n" + "    <tr>\r\n" + "      <th scope=\"col\">&nbsp;</th>\r\n" + "      <th scope=\"col\">Product Name</th>\r\n" + "      <th scope=\"col\">Price</th>\r\n"
                        + "      <th scope=\"col\">Quantity</th>\r\n" + "      <th scope=\"col\">&nbsp;</th>\r\n" + "    </tr>");

                float totalPrice = 0f;
                for (String id : ids) {
                    String sql = "SELECT * FROM products WHERE id = " + id;
                    result = statement.executeQuery(sql);
                    result.next();
                    String product = result.getString("producttype");
                    String brand = result.getString("productname");
                    String img = result.getString("img");
                    float price = result.getFloat("price");
                    int qtyOrdered = Integer.parseInt(request.getParameter("qty" + id));
                    sql = "UPDATE products SET qty = qty - " + qtyOrdered + " WHERE id = " + id;
                    statement.executeUpdate(sql);
                    
                    out.println("<tr><td>Total Price: <h3 class='montblack'>Euro " + totalPrice + "</h3></td><td class='text-center align-center'></tr>");
                    out.println("<tr>");
                    out.println("<td class='align-middle'>" + product + "</td>");
                    out.println("<td class='align-middle'>" + brand + "</td>");
                    out.println("<td class='align-middle'> Euro - " + price + " </td>");
                    out.println("<td class='align-middle'> " + qtyOrdered + " </td>");
                    out.println("<td class='align-middle text-center'><img class='product' style='height: auto; width: 150px;'src='" + img + "'></td>");
                    out.println("</tr>");
                    totalPrice += price * qtyOrdered;

                    // Create Product object and add to the list
                    Product productObj = new Product();
                    productObj.setId(Integer.parseInt(id));
                    productObj.setProductType(product);
                    productObj.setProductName(brand);
                    productObj.setPrice(price);
                    productObj.setQuantity(qtyOrdered);
                    products.add(productObj);
                }
                
                // Calculate total price
                float discount = calculateDiscount(totalPrice);

                // Subtract the discount from the total price
                totalPrice -= discount;

                // Set the discount attribute in the request
                request.setAttribute("totalPrice", totalPrice);
                request.setAttribute("discount", discount);

                out.println("<tr><td>Total Price: <h3 class='montblack'>Euro " + totalPrice + "</h3></td><td class='text-center align-center'></tr>");
                out.println("</table>");

                out.println("<h2 class='text-center montblack'>Thank You! </h2>");
                out.println("<p class='text-center montlight'>Your order will be shipped in 2 days<br/><b><br><br><br>");
                out.println("<a class='btn btn-outline-success m-auto text-center' href='WelcomeServlet'>Return To Home Page</a>");
                out.println("<a class='btn btn-outline-success m-auto text-center' href='DeliveryServlet'>Proceed to Delivery Details </a>");
                out.println("</div>");

                // Format total price to display only two decimal places
                HttpSession session = request.getSession();
                session.setAttribute("products", products);
                session.setAttribute("totalPrice", totalPrice);
                String formattedTotalPrice = String.format("%.2f", totalPrice);
                RequestDispatcher dispatcher = request.getRequestDispatcher("delivery_form.jsp");
                dispatcher.forward(request, response);
            }
            out.println("</form></div></div><p></p></div></div></body></html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean setQuantity(int quantity) {
        if (quantity >= 1 && quantity <= 100) {
            this.quantity = quantity;
            System.out.println("Quantity set to: " + this.quantity);
            return true;
        } else {
            System.out.println("Invalid quantity: " + quantity);
            return false;
        }
    }
}