package com.mycompany.ecommerce.servlets;

import com.mycompany.ecommerce.dao.CategoryDao;
import com.mycompany.ecommerce.dao.ProductDao;
import com.mycompany.ecommerce.entities.Category;
import com.mycompany.ecommerce.entities.Product;
import com.mycompany.ecommerce.helper.FactoryProvider;
import com.mycompany.ecommerce.helper.FileHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.File;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

@MultipartConfig
@WebServlet(value = "/ProductOperationServlet")
public class ProductOperationServlet extends HttpServlet {

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
        // Get the operation parameter
        String operation = request.getParameter("operation");

        // Determine the operation type and call the respective method
        if (operation != null) {
            if (operation.trim().equals("addCategory")) {
                addCategory(request, response);
            } else if (operation.trim().equals("addProduct")) {
                addProduct(request, response);
            }
        }
    }

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
        // Forward the POST request to the GET handler for further processing
        doGet(request, response);
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession httpSession = request.getSession();
        String productName = "";

        try {
            // Fetching product data
            productName = request.getParameter("productName");
            String productDescription = request.getParameter("ProductDescription");

            // Get the product picture
            Part part = request.getPart("productPic");
            String productPicName = part.getSubmittedFileName().replaceAll("[^a-zA-Z0-9\\.\\-]", "_");

            // Validate if the uploaded file is an image
            String fileType = part.getContentType();
            String fileExtension = productPicName.substring(productPicName.lastIndexOf(".") + 1).toLowerCase();

            if (!FileHandler.isImage(fileType, fileExtension)) {
                httpSession.setAttribute("message", "Uploaded file is not a valid image.");
                httpSession.setAttribute("productName", productName);
                response.sendRedirect("admin.jsp");
                return;
            }

            // Product image upload file path
            String contextPath = request.getSession().getServletContext().getRealPath("img");
            String filePath = contextPath + File.separator + "products" + File.separator + productPicName;
            InputStream inputStream = part.getInputStream();
            FileHandler.saveFile(inputStream, filePath);

            // Fetch other product details
            int productPrice = Integer.parseInt(request.getParameter("productPrice"));
            int productDiscount = Integer.parseInt(request.getParameter("productDiscount"));
            int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
            int catId = Integer.parseInt(request.getParameter("catId"));

            // Fetch category details
            Category category = new CategoryDao(FactoryProvider.getFactory()).getCategoryByCatId(catId);
            Product product = new Product(productName, productDescription, productPicName, productPrice, productDiscount, productQuantity, new Date(), category);

            // Save the product to the database
            ProductDao productDao = new ProductDao(FactoryProvider.getFactory());
            productDao.saveProduct(product);

            httpSession.setAttribute("message", "New Product Added");
            httpSession.setAttribute("productName", productName);

        } catch (HibernateException e) {
            httpSession.setAttribute("message", productName + " Could not be added");
            httpSession.setAttribute("productName", productName);
            e.printStackTrace();
        } catch (Exception e) {
            httpSession.setAttribute("message", "Could not be added");
            httpSession.setAttribute("productName", productName);
            e.printStackTrace();
        }

        response.sendRedirect("admin.jsp");
    }

    private void addCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Fetch category data
        String catTitle = request.getParameter("catTitle").toUpperCase();
        String catDescription = request.getParameter("catDescription");

        Category category = new Category();
        category.setCategoryTitle(catTitle);
        category.setCategoryDecription(catDescription);

        // Save category to database
        CategoryDao categoryDao = new CategoryDao(FactoryProvider.getFactory());
        HttpSession httpSession = request.getSession();

        try {
            categoryDao.saveCategory(category);
            httpSession.setAttribute("message", "New Category Added");
            httpSession.setAttribute("catTitle", catTitle);
        } catch (ConstraintViolationException e) {
            httpSession.setAttribute("message", "Category Title Must be Unique");
            httpSession.setAttribute("catTitle", catTitle);
            e.printStackTrace();
        } catch (HibernateException e) {
            httpSession.setAttribute("message", catTitle + " Could not be added-category");
            httpSession.setAttribute("catTitle", catTitle);
            e.printStackTrace();
        } catch (Exception e) {
            httpSession.setAttribute("message", "Could not be added-category");
            httpSession.setAttribute("catTitle", catTitle);
            e.printStackTrace();
        }

        response.sendRedirect("admin.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet that handles product and category operations like adding a product or category";
    }
}
