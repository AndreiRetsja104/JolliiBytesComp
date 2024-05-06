package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import andrei.ShoppingCart;
import andrei.Product;
import andrei.OrderServlet;

public class TotalPriceCalculationTest {

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
}