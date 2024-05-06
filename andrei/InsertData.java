package andrei;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertData
 */
@WebServlet("/InsertData")
public class InsertData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertData() {
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
		response.setContentType("text/html");
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();

		try {
			out.println("Registering  JDBC driver");
			// STEP 1: Register JDBC driver. This will load the MySQL driver, each DB has
			// its own driver
			Class.forName("com.mysql.jdbc.Driver");
			out.println("Seting up the connection with the DB");
			// STEP 2: Open a connection. Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/jolli_bytes_com", "root", "");
			// STEP 3: Statements allow us to issue SQL queries to the database
			statement = connect.createStatement();
			// STEP 4: declare a query
			String sql = "INSERT INTO products (id, producttype, productname, price, qty, img) VALUES" +
					 "(001, 'GPU', 'NVIDIA, RTX 3080, 10GB GDDR6X, 1.44 GHz - 1.71 GHz', 699.99, 10, 'img_product/rtx-3080.jpg')," +
					 "(002, 'GPU', 'AMD, Radeon RX 6800 XT, 16GB GDDR6, 1.82 GHz - 2.25 GHz', 649.99, 20, 'img_product/radeon-rx-5xt.png')," +
					 "(003, 'GPU', 'NVIDIA, RTX 3070, 8GB GDDR6, 1.50 GHz - 1.73 GHz', 499.99, 15, 'img_product/rtx-3070.png')," +			 
					 "(004, 'GPU', 'AMD, Radeon RX 6700 XT, 12GB GDDR6, 2.42 GHz - 2.58 GHz', 479.99, 25, 'img_product/radeonSO.png'),"+		 
					 "(005, 'GPU', 'NVIDIA, RTX 3090 Ti, 24GB GDDR6X, 1.67 GHz - 1.86 GHz', 1970, 5, 'img_product/rtx-3090-ti.png')," +					 
					 "(006, 'GPU', 'NVIDIA, RTX 3090, 24GB GDDR6X, 1.40 GHz - 1.70 GHz', 1739.00, 8, 'img_product/rtx-3090.png')," +					 
					 "(007, 'GPU', 'NVIDIA, RTX 3080 Ti, 10GB GDDR6X, 1.44 GHz - 1.71 GHz', 1269.00, 12, 'img_product/rtx-3080.jpg')," +			 
					 "(008, 'GPU', 'NVIDIA, RTX 3070 Ti, 8GB GDDR6, 1.58 GHz - 1.77 GHz', 699.99, 20, 'img_product/rtx-3070.png')," +		 
					 "(009, 'GPU', 'NVIDIA, RTX 3060, 8GB GDDR6, 1.32 GHz - 1.78 GHz', 349.00, 30, 'img_product/rtx-3060-ti.png')," +			 
					 "(010, 'GPU', 'NVIDIA, RTX 3060 Ti, 8GB GDDR6, 1.41 GHz - 1.67 GHz', 579.90, 15, 'img_product/rtx-3060-ti.png')," +		 
					 "(011, 'GPU', 'NVIDIA, RTX 4090, 24GB GDDR6X, 2.2 GHz - 2.52 GHz', 1629.99, 7, 'img_product/rtx-3060-ti.png')," +			 
					 "(012, 'GPU', 'NVIDIA, RTX 4080, 16GB GDDR6X, 2.2 GHz - 2.52 GHz', 1199.99, 10, 'img_product/rtx-3060-ti.png')," +			 
					 "(013, 'GPU', 'NVIDIA, RTX 4070 Ti, 12GB GDDR6X, 2.3 GHz - 2.62 GHz', 799.99, 8, 'img_product/rtx-3060-ti.png')," +			 
					 "(014, 'GPU', 'NVIDIA, RTX 4060 Ti, 16GB GDDR6, 2.6 GHz - 2.3 GHz', 482, 20, 'img_product/rtx-3060-ti.png')," +			 
					 "(015, 'GPU', 'NVIDIA, RTX 2080 Ti, 11GB GDDR6, 1.35 GHz - 1.66 GHz', 588.06, 5, 'img_product/rtx-3060-ti.png')," +
					 "(016, 'GPU', 'NVIDIA, RTX 2080 Super, 8GB GDDR6, 1.65 GHz - 1.83 GHz', 394.02, 10, 'img_product/rtx-2070-super.png')," +
					 "(017, 'GPU', 'NVIDIA, RTX 2070 Super, 8GB GDDR6, 1.60 GHz - 1.80 GHz', 358, 15, 'img_product/rtx-2070-super.png')," +
					 "(018, 'GPU', 'NVIDIA, GTX 1080 Ti, 11GB GDDR5X, 1.56 GHz - 1.63 GHz', 429.99, 7, 'img_product/rtx-3080.png')," +
					 "(019, 'GPU', 'NVIDIA, GTX 1080, 8GB GDDR5X, 1.68 GHz - 1.82 GHz', 245, 25, 'img_product/rtx-3060-ti.png')," +				 
					 "(020, 'GPU', 'NVIDIA, GTX 1070 Ti, 8GB GDDR5, 1.60 GHz - 1.68 GHz', 333, 18, 'img_product/rtx-3060-ti.png')," +				 
					 "(021, 'GPU', 'NVIDIA, GTX 1060, 3GB GDDR5, 1.6 GHz - 1.83 GHz', 399.00, 20, 'img_product/rtx-3060-ti.png'),"+				 
					 "(022, 'GPU', 'AMD, Radeon RX 6900 XT, 16GB GDDR6, 1.9 GHz - 2.3 GHz', 629.99, 12, 'img_product/radeon-rx-6800-xt.png')," +				 
					 "(023, 'GPU', 'AMD, Radeon RX 6600 XT, 8GB GDDR6, 1.96 GHz - 2.6 GHz', 399.00, 15, 'img_product/radeon-rx-6800-xt.png')," +			 
					 "(024, 'GPU', 'AMD, Radeon RX 7700 XT, 12GB GDDR6, 1.7 GHz - 2.5 GHz', 449.99, 10, 'img_product/radeon-rx7tx.png')," +
					 "(025, 'GPU', 'AMD, Radeon RX 7800 XT, 16GB GDDR6, 1.4 GHz - 2.4 GHz', 509.99, 8, 'img_product/radeon-rx7tx.png')," +
					 "(026, 'GPU', 'AMD, Radeon RX 7900 XT, 20GB GDDR6, 2.0 GHz - 2.5 GHz', 839.99, 5, 'img_product/radeon-rx7tx.png')," +	 
					 "(027, 'GPU', 'AMD, Radeon RX 7900 XTX, 24GB GDDR6, 2.3 GHz - 2.6 GHz', 1029.99, 3, 'img_product/radeon-rx7tx.png')," +
					 "(028, 'GPU', 'AMD, Radeon RX 550, 4GB GDDR5, 1.2 GHz - 1.5 GHz', 1029.99, 20, 'img_product/radeon-rx.png')," +
					 "(029, 'GPU', 'AMD, Radeon RX 5550 XT, 8GB GDDR6, 1.6 GHz - 1.8 GHz', 398.68, 15, 'img_product/radeon-rx-5xt.png')," +
					 "(030, 'GPU', 'AMD, Radeon RX 5550 XT, 8GB GDDR6, 1.6 GHz - 1.8 GHz', 398.68, 25, 'img_product/radeon-rx-5xt.png')," +			 
					 "(031, 'GPU', 'AMD, Radeon RX 560, 4GB GDDR5, 1.2 GHz - None', 289.00, 20, 'img_product/radeon-rx.png')," +			 
					 "(032, 'GPU', 'AMD, Radeon RX 5600 XT, 6GB GDDR6, 1.2 GHz - 1.7 GHz', 250.00, 18, 'img_product/radeon-rx-5xt.png')," +			 
					 "(033, 'GPU', 'AMD, Radeon RX 570, 8GB GDDR5, 1.1 GHz - 1.2 GHz', 339.95, 10, 'img_product/radeon-rx.png')," +				 
					 "(034, 'GPU', 'AMD, Radeon RX 5700 XT, 8GB GDDR6, 1.8 GHz - 2.0 GHz', 389.00, 15, 'img_product/radeon-rx-5xt.png')," +		 
					 "(101, 'CPU', 'Intel, Core i9-11900K, 3.5 GHz - 5.3 GHz, LGA 1200, 125W', 549.00, 10, 'img_product/core-i9-11900k.png')," +				 
					 "(102, 'CPU', 'AMD, Ryzen 9 5900X, 3.7 GHz - 4.8 GHz, AM4, 105W', 549.00, 15, 'img_product/Ryzen.png')," +			 
					 "(103, 'CPU', 'AMD, Ryzen 5 7600, 3.7 GHz - 4.8 GHz, AM4, 65W', 219.00, 20, 'img_product/Ryzen5.png')," +				 
					 "(104, 'CPU', 'AMD, Ryzen 7 5800X, 4.7 GHz - 3.8 GHz, AM4, 105W', 220.70, 12, 'img_product/Ryzen7.png')," +				 
					 "(105, 'CPU', 'AMD, Ryzen 5 5600X, 3.7 GHz - 4.6 GHz, AM4, 65W', 172.03, 18, 'img_product/Ryzen5.png')," +			 
					 "(106, 'CPU', 'Intel, Core i5-12400F, 2.5 GHz - 3.5 GHz, LGA 1700, 65W', 137.62, 25, 'img_product/Intel_i5.png')," +					 
					 "(107, 'CPU', 'AMD, Ryzen 5 4600G, 3.7 GHz - 4.3 GHz, AM4, 65W', 98.11, 20, 'img_product/Ryzen5.png')," +		 
					 "(108, 'CPU', 'Intel, Core i7-14700KF, 5.6 GHz - 6.6 GHz, LGA 1700, 125W', 429.70, 15, 'img_product/Intel_i7.png')," +				 
					 "(109, 'CPU', 'Intel, Core i7-12700KF, 3.6 GHz - 5.0 GHz, LGA 1700, 125W', 275.55, 10, 'img_product/Intel_i7.png')," +				 
					 "(110, 'CPU', 'AMD, Ryzen 7 5800X3D, 3.4 GHz - 4.5 GHz, AM4, 105W', 338.61, 8, 'img_product/Ryzen7.png')," +					 
					 "(111, 'CPU', 'Intel, Core i5-13400F, 4.6 GHz - 5.6 GHz, LGA 1700, 65W', 227.68, 20, 'img_product/Intel_i5.png')," +					 
					 "(112, 'CPU', 'Intel, Core i3-12100F, 3.3 GHz - 4.3 GHz, LGA 1700, 58W', 96.91, 30, 'img_product/Intel_i3.png')," +					
					 "(113, 'CPU', 'Intel, Core i5-13600K, 3.5 GHz - 5.10 GHz, LGA 1356, 125W', 319.00, 25, 'img_product/Intel_i5.png')," +	 
					 "(114, 'CPU', 'Intel, Core i7-12700K, 3.6 GHz - 4.6 GHz, LGA 1700, 125W', 249.97, 20, 'img_product/Intel_i7.png')," +				 
					 "(115, 'CPU', 'Intel, Core i7-13700KF, 3.4 GHz - 5.4 GHz, LGA 1700, 125W', 386.49, 15, 'img_product/Intel_i7.png')," +				 
					 "(116, 'CPU', 'Intel, Core i7-14700K, 5.6 GHz - 6.6 GHz, LGA 1700, 125W', 453.04, 18, 'img_product/Intel_i7.png')," +					 
					 "(117, 'CPU', 'AMD, Ryzen 3 3200G, 3.6 GHz - 4.0 GHz, AM4, 65W', 78.95, 10, 'img_product/Ryzen.png')," +					 
					 "(118, 'CPU', 'AMD, Ryzen 7 8700G, 4.2 GHz - 4.9 GHz, Ryzen 7, 65W', 360.82, 8, 'img_product/Ryzen7.png')," +
					 "(119, 'CPU', 'Intel, Core i712700, 2.1 GHz - 4.9 GHz, LGA 1700, 65W', 294.24, 12, 'img_product/Intel_i7.png')," +
					 "(120, 'CPU', 'Intel, Core i5-12400, 2.5 GHz - 3.5 GHz, LGA 1700, 65W', 179.53, 15, 'img_product/Intel_i5.png')," +
					 "(121, 'CPU', 'Intel, i9-14900K, 6 GHz - 7 GHz, LGA 1700, 125W', 635.22, 20, 'img_product/core-i9-11900k.png')," +
					 "(201, 'SSD', 'Samsung 970 EVO Plus 1TB NVMe PCIe M.2 SSD', 179.99, 10, 'img_product/Samsung 970.png')," +
					 "(202, 'SSD', 'Crucial MX500 1TB 3D NAND SATA 2.5 Inch Internal SSD', 109.99, 15, 'img_product/Crucial.png')," +
					 "(203, 'SSD', 'WD Blue SN550 1TB NVMe Internal SSD', 104.99, 20, 'img_product/wd-blue-sn550.png')," +
					 "(204, 'SSD', 'Samsung 870 QVO SATA III 2.5 Inch SSD 1TB', 99.99, 12, 'img_product/Samsung.png')," +
					 "(205, 'SSD', 'ADATA SU635 240GB SATA III 3D NAND Internal SSD', 32.99, 18, 'img_product/adata-swordfish.png')," +
					 "(206, 'SSD', 'Crucial BX500 480GB 3D NAND SATA 2.5-Inch Internal SSD', 49.99, 25, 'img_product/Crucial.png')," +
					 "(207, 'SSD', 'Sabrent Rocket Q 1TB NVMe PCIe M.2 Internal SSD', 129.99, 20, 'img_product/Samsung.png')," +
					 "(208, 'SSD', 'ADATA Swordfish 1TB PCIe Gen3x4 M.2 2280 SSD', 109.99, 15, 'img_product/adata-swordfish.png')," + 
					 "(209, 'SSD', 'Samsung 870 EVO SATA III 2.5 Internal SSD 1TB', 119.99, 30, 'img_product/Samsung.png')," +
					 "(210, 'SSD', 'SK hynix Gold P31 PCIe NVMe Gen3 M.2 2280 Internal SSD 1TB', 134.99, 15, 'img_product/SK.png')," +
					 "(301, 'RAM', 'Corsair Vengeance RGB CMW16GX4M2C3200C16 memory module 16 GB 2 x 8 GB DDR4 3200 MHz', 109.99, 15, 'img_product/Corsair Vengeance.png')," +
					 "(302, 'RAM', 'G.SKILL Trident Z RGB (For AMD) 16GB (2 x 8GB) 288-Pin DDR4 SDRAM DDR4 3600 (PC4 28800) Desktop Memory Model F4-3600C18D-16GTZRX', 99.99, 20, 'img_product/G.SKILL.png')," +					 
					 "(303, 'RAM', 'Crucial Ballistix RGB 3600 MHz DDR4 DRAM Desktop Gaming Memory Kit 32GB (16GBx2) CL16 BL2K16G36C16U4BL (Black)', 189.99, 18, 'img_product/CrucialRAM.png')," +
					 "(304, 'RAM', 'Team T-Force Delta RGB DDR4 16GB (2x8GB) 3200MHz (PC4-25600) CL16 Desktop Memory Module ram TF4D416G3200HC16CDC01 - Black', 79.99, 25, 'img_product/RAM.png')," +
					 "(305, 'RAM', 'ADATA XPG GAMMIX D10 DDR4 16GB (2x8GB) 3200MHz CL16 Black (AX4U320038G16A-DT10)', 84.99, 22, 'img_product/RAM.png')," +			 
					 "(306, 'RAM', 'Corsair Vengeance LPX 16GB (2x8GB) DDR4 DRAM 3200MHz C16 Desktop Memory Kit - Black', 94.99, 17, 'img_product/Corsair VengeanceRAM.png')," +
					 "(307, 'RAM', 'Patriot Viper Steel DDR4 16GB (2 x 8GB) 3733MHz Kit w/Gunmetal Grey Heatshield', 119.99, 14, 'img_product/RAM.png')," +
					 "(308, 'RAM', 'HyperX Fury 16GB (2 x 8GB) DDR4 3200MHz C16 Desktop Memory - Black', 89.99, 19, 'img_product/HyperX Fury.png')," +
					 "(309, 'RAM', 'Crucial Ballistix RGB 3600 MHz DDR4 DRAM Desktop Gaming Memory Kit 16GB (8GBx2) CL16 BL2K8G36C16U4BL (Black)', 99.99, 21, 'img_product/CrucialRAM.png')," +
					 "(310, 'RAM', 'TEAMGROUP T-Force Delta RGB DDR4 32GB (2x16GB) 3200MHz (PC4-25600) CL16 Desktop Gaming Memory Module Ram TF3D432G3200HC16CDC01 - Black', 159.99, 16, 'img_product/RAM.png')," +
					 "(311, 'RAM', 'Corsair Vengeance LPX 32GB (2x16GB) DDR4 DRAM 3200MHz C16 Desktop Memory Kit - Black', 229.99, 24, 'img_product/Corsair_VengeanceRAM.png')," +
					 "(312, 'RAM', 'G.SKILL Trident Z RGB (For AMD) 32GB (2 x 16GB) 288-Pin DDR4 SDRAM DDR4 3600 (PC4 28800) Desktop Memory Model F4-3600C18D-32GTZRX', 179.99, 18, 'img_product/G.SKILLRAM.png')," +
					 "(313, 'RAM', 'Team T-FORCE VULCAN Z 32GB (2 x 16GB) 288-Pin DDR4 SDRAM DDR4 3200 (PC4 25600) Desktop Memory Model TLZGD432G3200HC16CDC01 - Black', 169.99, 20, 'img_product/RAM.png')," +
					 "(314, 'RAM', 'Crucial Ballistix MAX RGB 32GB (2 x 16GB) 288-Pin DDR4 SDRAM DDR4 4000 (PC4 32000) Desktop Memory Model BL2K16G40C18U4BL (Black)', 249.99, 15, 'img_product/CrucialRAM.png')," +
					 "(315, 'RAM', 'Patriot Viper Steel Series DDR4 32GB (2 x 16GB) 3733MHz Performance Memory Kit - PVS432G373C7K', 209.99, 17, 'img_product/RAM.png')";
			// STEP 5: execute a query
			statement.executeUpdate(sql);
			out.println("Data insert.....");

			 // Generate HTML response with image tags
            out.println("<html><head><title>Inserted Data</title></head><body>");
            out.println("<h1>Data inserted successfully</h1>");

            // Display images for each product
            out.println("<h2>Product Images</h2>");
            out.println("<ul>");

            out.println("</ul>");
            out.println("</body></html>");

        } catch (Exception e) {
            // Handle exceptions properly
            e.printStackTrace();
            out.println("Error occurred: " + e.getMessage());
        } finally {
            // Close resources properly
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle POST requests if needed
    }
}