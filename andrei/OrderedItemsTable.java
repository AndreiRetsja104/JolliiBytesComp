package andrei;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/OrderedItemsTable")
public class OrderedItemsTable extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        Connection connection = null;
        Statement statement = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            out.println("JDBC driver registered successfully.");

            // Setup the connection with the DB
            connection = DriverManager.getConnection("jdbc:mysql://localhost/jolli_bytes_com", "root", "");
            out.println("Connection to the database established successfully.");

            // Statements allow us to issue SQL queries to the database
            statement = connection.createStatement();

            // Declare a query to create the deliveryTable if not exists
            String sql = "CREATE TABLE IF NOT EXISTS ordered_items_table ( order_id INT AUTO_INCREMENT PRIMARY KEY,"
            		+ "    delivery_id INT,"
            		+ "    product_id INT,"
            		+ "    quantity INT,"
            		+ "    FOREIGN KEY (delivery_id) REFERENCES deliveryTable(id),"
            		+ "    FOREIGN KEY (product_id) REFERENCES products(id)"
            		+ ")";
            // Execute the query
            int rowsAffected = statement.executeUpdate(sql);

            if (rowsAffected > 0) {
                out.println("Database table 'deliveryTable' created successfully.");
            } else {
                out.println("Database table 'deliveryTable' already exists.");
            }

        } catch (ClassNotFoundException e) {
            out.println("Error: JDBC driver not found - " + e.getMessage());
        } catch (SQLException e) {
            out.println("Error executing SQL query - " + e.getMessage());
        } finally {
            // Close resources in a finally block to ensure they are always closed
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                out.println("Error closing database resources - " + e.getMessage());
            }
        }
    }
}