package test;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import andrei.InsertData;

public class InsertDataTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    ServletContext servletContext;

    @InjectMocks
    InsertData insertDataServlet;

    @SuppressWarnings("deprecation")
	@Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("connection")).thenReturn(mock(Connection.class));
    }

    @Test
    public void testDoGet() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        insertDataServlet.doGet(request, response);

        verify(response).setContentType("text/html"); // Verify content type is set
        verify(response).getWriter(); // Verify getWriter is called
        writer.flush();

        // Now we should check for the correct content in the HTML output
        String htmlContent = stringWriter.toString();
        assertTrue("The HTML should contain a success message.", htmlContent.contains("Data inserted successfully"));
        assertTrue("The HTML should contain a product image.", htmlContent.contains("<img src='img_product/cpu1.npg' alt='Product Image'>"));
        // Add more assertions for other expected content if needed
    }

}