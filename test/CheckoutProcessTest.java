package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import andrei.OrderServlet;

class CheckoutProcessTest {

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