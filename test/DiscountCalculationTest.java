package test;

import org.junit.jupiter.api.Test;

import andrei.OrderServlet;
import andrei.Product;
import andrei.ShoppingCart;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import andrei.DiscountCalculator;

class DiscountCalculationTest {

	@Test
	void testMemberDiscount() {
	    DiscountCalculator calculator = new DiscountCalculator();
	    float discount = calculator.calculateDiscount(500f, true); // Pass the total price and specify that the customer is a member
	    System.out.println("Testing member discount for Euro 500 purchase: " + discount);
	    assertEquals(50f, discount, 0.01); // Assuming a precision of 2 decimal places
	}

	@Test
	void testNonMemberDiscount() {
	    DiscountCalculator calculator = new DiscountCalculator();
	    float discount = calculator.calculateDiscount(500f, false); // Pass the total price and specify that the customer is not a member
	    System.out.println("Testing non-member discount for Euro 500 purchase: " + discount);
	    assertEquals(0.0f, discount, 0.01); // Assuming a precision of 2 decimal places
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
    float actualDiscount = (float) request.getAttribute("discount");
    assertEquals(expectedDiscount, actualDiscount, 0.01); // Assuming a precision of 2 decimal places

    // Verify that the "discount" attribute is set in the request
    verify(request).setAttribute(eq("discount"), anyFloat());
	}
}

