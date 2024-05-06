package test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import andrei.OrderServlet;
import andrei.ShoppingCart;
import andrei.Product;

class OrderServlet_Test {

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
    
    
}
    
 
    
