package test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import andrei.LoginServlet;
import andrei.OrderServlet;
import andrei.Product;
import andrei.ShoppingCart;

public class All_Tests_IN1 {

	@Test
	void testTotalPrice() {
	    ShoppingCart cart = new ShoppingCart();
	    cart.addProduct(new Product(1, "GPU", "NVIDIA, RTX 3080, 10GB GDDR6X, 1.44 GHz - 1.71 GHz", 699.99f, 1, "img_product/rtx-3080.jpg"));
	    cart.addProduct(new Product(2, "GPU", "AMD, Radeon RX 6800 XT, 16GB GDDR6, 1.82 GHz - 2.25 GHz", 649.99f, 1, "img_product/radeon-rx-5xt.png"));
	    double total = cart.getTotalPrice();
	    double roundedTotal = Math.round(total * 100.0) / 100.0;
	    System.out.println("Testing total price calculation: Total is " + total);
	    assertEquals(1349.98f, roundedTotal, 0.01); // Allowing a tolerance of 0.01 for rounding errors
	}
	
    @Test
    void testTotalPrice1() {
        // Create a new shopping cart
        ShoppingCart cart = new ShoppingCart();
        
        // Add products to the shopping cart with two products 
        cart.addProduct(new Product(1, "GPU", "NVIDIA, RTX 3080, 10GB GDDR6X, 1.44 GHz - 1.71 GHz", 699.99f, 1, "img_product/rtx-3080.jpg"));
        cart.addProduct(new Product(2, "GPU", "AMD, Radeon RX 6800 XT, 16GB GDDR6, 1.82 GHz - 2.25 GHz", 649.99f, 1, "img_product/radeon-rx-5xt.png"));
        
        // Calculate the total price
        double total1 = cart.getTotalPrice();
        
        // Round the total price to two decimal places
        double roundedTotal1 = Math.round(total1 * 100.0) / 100.0;
        
        // Print the total price for debugging
        System.out.println("Testing total price calculation: Total is " + roundedTotal1);
        
        // Assert that the total price matches the expected value
        assertEquals(1349.98, roundedTotal1, 0.01); // Using delta for floating point comparison
    }
    
    @Test
    void testTotalPrice2() {
        // Create a new shopping cart with eight products
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Product(1, "GPU", "NVIDIA, RTX 3080, 10GB GDDR6X, 1.44 GHz - 1.71 GHz", 699.99f, 1, "img_product/rtx-3080.jpg"));
        cart.addProduct(new Product(2, "GPU", "AMD, Radeon RX 6800 XT, 16GB GDDR6, 1.82 GHz - 2.25 GHz", 649.99f, 1, "img_product/radeon-rx-5xt.png"));
        cart.addProduct(new Product(3, "GPU", "NVIDIA, RTX 3070, 8GB GDDR6, 1.50 GHz - 1.73 GHz", 499.99f, 1, "img_product/rtx-3070.png"));
        cart.addProduct(new Product(4, "GPU", "AMD, Radeon RX 6700 XT, 12GB GDDR6, 2.42 GHz - 2.58 GHz", 479.99f, 1, "img_product/radeonSO.png"));
        cart.addProduct(new Product(5, "GPU", "NVIDIA, RTX 3090 Ti, 24GB GDDR6X, 1.67 GHz - 1.86 GHz", 1970f, 1, "img_product/rtx-3090-ti.png"));
        cart.addProduct(new Product(6, "GPU", "NVIDIA, RTX 3090, 24GB GDDR6X, 1.40 GHz - 1.70 GHz", 1739.00f, 1, "img_product/rtx-3090.png"));
        cart.addProduct(new Product(7, "GPU", "NVIDIA, RTX 3080 Ti, 10GB GDDR6X, 1.44 GHz - 1.71 GHz", 1269.00f, 1, "img_product/rtx-3080.jpg"));
        cart.addProduct(new Product(8, "GPU", "NVIDIA, RTX 3070 Ti, 8GB GDDR6, 1.58 GHz - 1.77 GHz", 699.99f, 1, "img_product/rtx-3070.png"));
        
        // Calculate the total price
        double total2 = cart.getTotalPrice();
        
        // Round the total price to two decimal places
        double roundedTotal2 = Math.round(total2 * 100.0) / 100.0;
        
        // Print the total price for debugging
        System.out.println("Testing total price calculation: Total is " + roundedTotal2);
        
        // Assert that the total price matches the expected value
        assertEquals(8007.95, roundedTotal2, 0.01); // Using delta for floating point comparison
    }
    
    @Test
    void testTotalPrice3() {
        // Create a new shopping cart with varying quantities of products
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Product(1, "GPU", "NVIDIA, RTX 3080, 10GB GDDR6X, 1.44 GHz - 1.71 GHz", 699.99f, 2, "img_product/rtx-3080.jpg"));
        cart.addProduct(new Product(2, "GPU", "AMD, Radeon RX 6800 XT, 16GB GDDR6, 1.82 GHz - 2.25 GHz", 649.99f, 4, "img_product/radeon-rx-5xt.png"));
        cart.addProduct(new Product(3, "GPU", "NVIDIA, RTX 3070, 8GB GDDR6, 1.50 GHz - 1.73 GHz", 499.99f, 8, "img_product/rtx-3070.png"));
        cart.addProduct(new Product(4, "GPU", "AMD, Radeon RX 6700 XT, 12GB GDDR6, 2.42 GHz - 2.58 GHz", 479.99f, 1, "img_product/radeonSO.png"));
        cart.addProduct(new Product(5, "GPU", "NVIDIA, RTX 3090 Ti, 24GB GDDR6X, 1.67 GHz - 1.86 GHz", 1970f, 9, "img_product/rtx-3090-ti.png"));
        cart.addProduct(new Product(6, "GPU", "NVIDIA, RTX 3090, 24GB GDDR6X, 1.40 GHz - 1.70 GHz", 1739.00f, 10, "img_product/rtx-3090.png"));
        cart.addProduct(new Product(7, "GPU", "NVIDIA, RTX 3080 Ti, 10GB GDDR6X, 1.44 GHz - 1.71 GHz", 1269.00f, 13, "img_product/rtx-3080.jpg"));
        cart.addProduct(new Product(8, "GPU", "NVIDIA, RTX 3070 Ti, 8GB GDDR6, 1.58 GHz - 1.77 GHz", 699.99f, 8, "img_product/rtx-3070.png"));
        
        // Calculate the total price
        double total3 = cart.getTotalPrice();
        
        // Round the total price to two decimal places
        double roundedTotal3 = Math.round(total3 * 100.0) / 100.0;
        
        // Print the total price for debugging
        System.out.println("Testing total price calculation: Total is " + roundedTotal3);
        
        // Assert that the total price matches the expected value
        assertEquals(65696.77, roundedTotal3, 0.01); // Using delta for floating point comparison
    }
    
    @Test
    void testTotalPrice4() {
        // Create a new shopping cart with varying quantities of products
    	// This Test is get not right Calculation of Total Price ,just want to show the difference between the other Cases
    	// If you change the row assertEquals TotalPrice from 61696.77 to 65696.77 , the all test will by passed throw .
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Product(1, "GPU", "NVIDIA, RTX 3080, 10GB GDDR6X, 1.44 GHz - 1.71 GHz", 699.99f, 2, "img_product/rtx-3080.jpg"));
        cart.addProduct(new Product(2, "GPU", "AMD, Radeon RX 6800 XT, 16GB GDDR6, 1.82 GHz - 2.25 GHz", 649.99f, 4, "img_product/radeon-rx-5xt.png"));
        cart.addProduct(new Product(3, "GPU", "NVIDIA, RTX 3070, 8GB GDDR6, 1.50 GHz - 1.73 GHz", 499.99f, 8, "img_product/rtx-3070.png"));
        cart.addProduct(new Product(4, "GPU", "AMD, Radeon RX 6700 XT, 12GB GDDR6, 2.42 GHz - 2.58 GHz", 479.99f, 1, "img_product/radeonSO.png"));
        cart.addProduct(new Product(5, "GPU", "NVIDIA, RTX 3090 Ti, 24GB GDDR6X, 1.67 GHz - 1.86 GHz", 1970f, 9, "img_product/rtx-3090-ti.png"));
        cart.addProduct(new Product(6, "GPU", "NVIDIA, RTX 3090, 24GB GDDR6X, 1.40 GHz - 1.70 GHz", 1739.00f, 10, "img_product/rtx-3090.png"));
        cart.addProduct(new Product(7, "GPU", "NVIDIA, RTX 3080 Ti, 10GB GDDR6X, 1.44 GHz - 1.71 GHz", 1269.00f, 13, "img_product/rtx-3080.jpg"));
        cart.addProduct(new Product(8, "GPU", "NVIDIA, RTX 3070 Ti, 8GB GDDR6, 1.58 GHz - 1.77 GHz", 699.99f, 8, "img_product/rtx-3070.png"));
        
        // Calculate the total price
        double total4 = cart.getTotalPrice();
        
        // Round the total price to two decimal places
        double roundedTotal4 = Math.round(total4 * 100.0) / 100.0;
        
        // Print the total price for debugging
        System.out.println("Here is not right Calculation of Total Price ,just want to show the difference: " + roundedTotal4);
        
        // Assert that the total price matches the expected value
        assertEquals(61696.77, roundedTotal4, 0.01); // Using delta for floating point comparison
    }
	
	@Test
	void testDoGet_ValidOrder() throws Exception {
	    HttpServletRequest request = mock(HttpServletRequest.class);
	    HttpServletResponse response = mock(HttpServletResponse.class);
	    RequestDispatcher dispatcher = mock(RequestDispatcher.class);

	    when(request.getParameterValues("id")).thenReturn(new String[]{"1", "2"});
	    when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

	    OrderServlet servlet = new OrderServlet();
	    servlet.doGet(request, response);

	    verify(dispatcher).include(request, response); // Verify include is called
	    verify(dispatcher, never()).forward(request, response); // Ensure forward is not called
		}

    @Test
    void testDoGet_InvalidOrder() throws Exception {
        // Create mocks for HttpServletRequest, HttpServletResponse, and RequestDispatcher
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        // Stubbing request parameters
        when(request.getParameterValues("id")).thenReturn(null);

        // Creating test instance and invoking doGet method
        OrderServlet servlet = new OrderServlet();
        servlet.doGet(request, response);

        // Verifying redirect to WelcomeServlet
        verify(response).sendRedirect("WelcomeServlet");
    }
    
    @Test
    public void testDiscountCalculation() throws ServletException, IOException {
        // Create mocks for request and response
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        // Create a mock shopping cart and add items
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(new Product(1, "GPU", "NVIDIA, RTX 3080, 10GB GDDR6X, 1.44 GHz - 1.71 GHz", 699.99f, 1, "img_product/rtx-3080.jpg"));
        shoppingCart.addProduct(new Product(2, "GPU", "AMD, Radeon RX 6800 XT, 16GB GDDR6, 1.82 GHz - 2.25 GHz", 649.99f, 2, "img_product/radeon-rx-5xt.png"));

        // Mock the session to return the shopping cart
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("shoppingCart")).thenReturn(shoppingCart);

        // Create an instance of the OrderServlet
        OrderServlet orderServlet = new OrderServlet();

        // Call the doGet method
        orderServlet.doGet(request, response);

        // Verify that the discount is calculated correctly
        float expectedDiscount = 10f;
        // Set the discount attribute in the request
        float actualDiscount = (float) request.getAttribute("discount");
        assertEquals(expectedDiscount, actualDiscount, 0.01); // Assuming a precision of 2 decimal places

        // Verify that the "discount" attribute is set in the request
        verify(request).setAttribute(eq("discount"), anyFloat());
    }
    
    @Test
    public void doGet_ValidUser_ForwardsToWelcomeServlet() throws Exception {
        try (PrintWriter writer = new PrintWriter("somefile.txt")) {
            when(request.getParameter("name")).thenReturn("validUser");
            when(request.getParameter("pass")).thenReturn("validPass");
            when(request.getRequestDispatcher("WelcomeServlet")).thenReturn(dispatcher);
            when(response.getWriter()).thenReturn(writer);

            new LoginServlet().doGet(request, response);

            // Verify that the forward method is called with the correct arguments
            verify(dispatcher).forward(request, response);

            // Optionally, verify that no other methods are called on the mocks
            verifyNoMoreInteractions(request, response, dispatcher);
        }
    }

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    RequestDispatcher dispatcher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void doGet_InvalidUser_IncludesLoginHtml() throws Exception {
        try (PrintWriter writer = new PrintWriter("somefile.txt")) {
            when(request.getParameter("name")).thenReturn("invalidUser");
            when(request.getParameter("pass")).thenReturn("wrongPass");
            when(request.getRequestDispatcher("login.html")).thenReturn(dispatcher);
            when(response.getWriter()).thenReturn(writer);

            new LoginServlet().doGet(request, response);

            verify(dispatcher).include(request, response);
        }
    }
    
    @Test
    void testValidQuantity() {
    	OrderServlet checkout = new OrderServlet();
        boolean validLow = checkout.setQuantity(1);
        boolean validHigh = checkout.setQuantity(100);
        System.out.println("Testing lower valid quantity: " + validLow);
        System.out.println("Testing upper valid quantity: " + validHigh);
        assertTrue(validLow && validHigh);
    }

    @Test
    void testInvalidQuantity() {
    	OrderServlet checkout = new OrderServlet();
        boolean invalidLow = checkout.setQuantity(0);
        boolean invalidHigh = checkout.setQuantity(101);
        System.out.println("Testing lower invalid quantity: " + invalidLow);
        System.out.println("Testing upper invalid quantity: " + invalidHigh);
        assertFalse(invalidLow && invalidHigh);
    }
}
