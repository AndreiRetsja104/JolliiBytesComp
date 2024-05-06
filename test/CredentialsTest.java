package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import static org.mockito.Mockito.*;
import andrei.LoginServlet;

public class CredentialsTest {

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
    void testDoGet_ForwardToWelcomeServlet() throws ServletException, IOException {
        // Set up any necessary behavior on the mocks
        when(request.getParameter("name")).thenReturn("username");
        when(request.getParameter("pass")).thenReturn("password");
        when(request.getRequestDispatcher("WelcomeServlet")).thenReturn(dispatcher);

        new LoginServlet().doGet(request, response);

        // Verify that the forward method is called with the correct arguments
        verify(dispatcher).forward(request, response);
        
        // Optionally, verify that no other methods are called on the mocks
        verifyNoMoreInteractions(request, response, dispatcher);
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
}