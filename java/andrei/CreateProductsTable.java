package andrei;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateTable
 */
@WebServlet("/CreateTable")
public class CreateProductsTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Statement statement = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateProductsTable() {
		super();
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<body>");
		Connection conn = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			// STEP 3: Open a connection
			out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/jolli_bytes_com", "root", "");
			
			
			String sql = "CREATE TABLE IF NOT EXISTS products(id INTEGER NOT NULL, producttype VARCHAR(255), productname VARCHAR(255), price FLOAT, qty INTEGER, img VARCHAR(255), PRIMARY KEY (id))";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			out.println("Created table in given database...");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		out.println("</body>");
		out.println("</html>");
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
		// TODO Auto-generated method stub
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

}
