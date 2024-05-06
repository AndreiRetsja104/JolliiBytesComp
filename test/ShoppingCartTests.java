package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import andrei.Product;
import andrei.ShoppingCart;

public class ShoppingCartTests {

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
        assertEquals(65696.77, roundedTotal4, 0.01); // Using delta for floating point comparison
    }
    
}