package com.mycompany.ecommerce.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import com.mycompany.ecommerce.dao.UserDao;
import com.mycompany.ecommerce.entities.User;
import com.mycompany.ecommerce.helper.FactoryProvider;
import com.mycompany.ecommerce.helper.PasswordUtil;

/**
 * Servlet handling login authentication.
 */
@WebServlet("/LoginServlet")  // Maps the servlet to the "/LoginServlet" URL pattern
public class LoginServlet extends HttpServlet {

    /**
     * Handles HTTP GET requests.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Try-with-resources for auto-closing PrintWriter
        try (PrintWriter out = response.getWriter()) {
            // Handle the GET request if necessary
            out.println("<html><body><h2>GET request received (although it's not used here).</h2></body></html>");
        }
    }

    /**
     * Handles HTTP POST requests.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve email and password parameters from the form
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Hash the password for secure comparison
        String hashPassword = PasswordUtil.hashPassword(password);

        // Authenticate the user using the DAO
        UserDao userDao = new UserDao(FactoryProvider.getFactory());
        User user = userDao.getUserByEmailAndPassword(email, hashPassword);

        // Create a session object to hold user data
        HttpSession httpSession = request.getSession();

        // Check if the user is found or not
        if (user == null) {
            // If authentication fails, set an error message in the session and redirect to the login page
            httpSession.setAttribute("message", "Invalid email address or password!!");
            response.sendRedirect("login.jsp");
        } else {
            // If authentication is successful, store the user in the session
            httpSession.setAttribute("currentUser", user);

            // Redirect based on user type (admin or normal)
            switch (user.getUserType()) {
                case "admin":
                    response.sendRedirect("admin.jsp");  // Redirect to admin page
                    break;
                case "normal":
                    response.sendRedirect("normal.jsp");  // Redirect to normal user page
                    break;
                default:
                    try (PrintWriter out = response.getWriter()) {
                        out.println("We have not identified user type");
                    }
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     */
    @Override
    public String getServletInfo() {
        return "Handles login authentication for users.";
    }
}
