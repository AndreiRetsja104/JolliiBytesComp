package andrei;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the contact page
        request.getRequestDispatcher("/contact.html").forward(request, response);
    }

    @SuppressWarnings("resource")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("Address");
        String county = request.getParameter("County");
        String phone = request.getParameter("Phone");
        String subject = request.getParameter("Subject");
        String message = request.getParameter("message");

        // Validate form inputs
        if (name == null || name.isEmpty() ||
            email == null || email.isEmpty() ||
            address == null || address.isEmpty() ||
            county == null || county.isEmpty() ||
            phone == null || phone.isEmpty() ||
            subject == null || subject.isEmpty() ||
            message == null || message.isEmpty()) {
            // Handle invalid input
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Error</title></head><body>");
            out.println("<h1>Invalid form input</h1>");
            out.println("<p>Please fill out all fields</p>");
            out.println("</body></html>");
            return;
        }

        // Continue with database operations if form input is valid

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Establish database connection
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/jolli_bytes_com", "root", "");

            // Create the table if it doesn't exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS contacts ("
                    + "id INT(11) NOT NULL AUTO_INCREMENT,"
                    + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + "name VARCHAR(255),"
                    + "email VARCHAR(255),"
                    + "address VARCHAR(255),"
                    + "county VARCHAR(255),"
                    + "phone VARCHAR(255),"
                    + "subject VARCHAR(255),"
                    + "message TEXT,"
                    + "PRIMARY KEY (id)"
                    + ")";
            pstmt = conn.prepareStatement(createTableSQL);
            pstmt.executeUpdate();

            // Insert contact information into the database
            String insertContactSQL = "INSERT INTO contacts (name, email, address, county, phone, subject, message) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(insertContactSQL);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, address);
            pstmt.setString(4, county);
            pstmt.setString(5, phone);
            pstmt.setString(6, subject);
            pstmt.setString(7, message);
            pstmt.executeUpdate();


            // Send a response to the user
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<html><head><title>Thank You!</title>");
            out.println("<link rel=\"icon\" href=\"img/Logo_JolliBytesComp_96x96.png\" type=\"image/png\">");
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
            out.println("<h1>Thank you for used our Contact Page.</h1>");
            out.println("<h1> We will reply to your Email as soon as possible !!!</h1>");
            out.println("<a href=\"index.html\" class=\"btn btn-send\">Go back to the main page</a>");
            out.println("</div>");
            out.println("</body></html>");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle database-related exceptions
        } finally {
            // Close resources
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}