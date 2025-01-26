package com.mycompany.ecommerce.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet to handle user logout.
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    /**
     * Handles HTTP GET requests.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set content type for response
        response.setContentType("text/html;charset=UTF-8");

        // Get session and invalidate the current user session
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("currentUser");  // Remove the current user from session

        // Optionally, invalidate the session
        httpSession.invalidate();

        // Redirect to login page after logging out
        response.sendRedirect("login.jsp");
    }

    /**
     * Handles HTTP POST requests.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward POST request to doGet() method for logout processing
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Handles user logout functionality.";
    }
}
