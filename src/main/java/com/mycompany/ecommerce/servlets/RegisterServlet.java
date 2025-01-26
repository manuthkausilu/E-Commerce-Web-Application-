package com.mycompany.ecommerce.servlets;

import com.mycompany.ecommerce.entities.User;
import com.mycompany.ecommerce.helper.FactoryProvider;
import com.mycompany.ecommerce.helper.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.Date;

/**
 * Servlet for handling user registration
 */
@WebServlet(value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession httpSession = request.getSession();
        Session hibernateSession = null;
        Transaction tx = null;

        try {
            // Initialize Hibernate session
            hibernateSession = FactoryProvider.getFactory().openSession();
            tx = hibernateSession.beginTransaction();

            // Retrieve user input from the request
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            // Hash the password before storing
            String hashPassword = PasswordUtil.hashPassword(password);

            // Create a new user object
            User user = new User(username, email, hashPassword, phone, null, address, new Date(), "normal");

            // Persist user object to the database
            hibernateSession.persist(user);

            // Commit the transaction
            tx.commit();

            // Set success message in the session
            httpSession.setAttribute("message", "Registration Successful!!");
            response.sendRedirect("register.jsp");

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback(); // Rollback the transaction on exception
            }
            httpSession.setAttribute("message", "Email must be unique!!");
            response.sendRedirect("register.jsp");
            e.printStackTrace();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); // Rollback on unexpected exception
            }
            httpSession.setAttribute("message", "Something went wrong! Please try again.");
            response.sendRedirect("register.jsp");
            e.printStackTrace();

        } finally {
            // Close the Hibernate session
            if (hibernateSession != null && hibernateSession.isOpen()) {
                hibernateSession.close();
            }
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect GET requests to the registration page
        response.sendRedirect("register.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Servlet for handling user registration";
    }
}
