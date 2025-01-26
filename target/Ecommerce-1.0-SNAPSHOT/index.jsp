<%@page import="com.mycompany.ecommerce.helper.Helper"%>
<%@page import="com.mycompany.ecommerce.entities.Category"%>
<%@page import="com.mycompany.ecommerce.dao.CategoryDao"%>
<%@page import="com.mycompany.ecommerce.entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.ecommerce.dao.ProductDao"%>
<%@page import="com.mycompany.ecommerce.helper.FactoryProvider"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>

        <%@include file="components/common_css_js.jsp" %>

    </head>
    <body>
        <%@include file="components/navbar.jsp" %>
        <div class="container-fluid">

            <div class="row mt-3 mx-2">

                <!--show categories-->
                <%                    String cId = request.getParameter("category");

                    ProductDao productDao = new ProductDao(FactoryProvider.getFactory());
                    List<Product> products = null;
                    String action ="";
                    if (cId == null || cId.trim().equals("all")) {
                        products = productDao.getAllProducts();
                    } else {
                        int cid = Integer.parseInt(cId.trim());
                        products = productDao.getProductsByCategoryId(cid);
                    }

                    CategoryDao categoryDao = new CategoryDao(FactoryProvider.getFactory());
                    List<Category> categories = categoryDao.getCategories();
                %>

                <div class="col-md-2">
                    <div class="list-group mt-4">
                        <a href="index.jsp?category=all" class="list-group-item list-group-item-action active">All Categories</a>
                        <%for (Category category : categories) {
                        %>

                        <a href="index.jsp?category=<%= category.getCategoryId()%>" class="list-group-item list-group-item-action <%= action %>"><%=category.getCategoryTitle()%></a>
                        <%
                            }
                        %>
                    </div>
                </div>

                <!--show products-->
                <%
                %>
                <div class="col-md-10">

                    <!--row-->
                    <div class="row mt-4">
                        <div class="col-md-12">
                            <div class="card-columns">
                                <%                                    for (Product product : products) {
                                %>

                                <div class="card product-card">
                                    <img class="card-img-top" src="img/products/<%= product.getProductPic()%>" alt="Card image cap">
                                    <div class="card-body">
                                        <h5 class="card-title"><%= product.getpName()%></h5>
                                        <p class="card-text"><%= Helper.get10Words(product.getpDescription())%></p>
                                    </div>
                                    <div class="card-footer text-center">
                                        <button class="btn btn-outline-nav"onclick="add_to_cart(<%= product.getpId() %>,'<%= product.getpName()%>',<%= product.getPriceAfterDiscount() %>)">Add to Cart</button>
                                        <button class="btn btn-outline-success"> &#2547 <%= product.getPriceAfterDiscount() %>/- <span class="text-scondary discount-label">&#2547 <%= product.getpPrice() %>  <%= product.getpDiscount() %> % off</span></button>
                                    </div>   
                                </div>

                                <%
                                    }
                                    if (products.size() == 0) {
                                        out.println("<h2>No item in this category !</h2>");
                                    }
                                %>
                            </div>
                        </div>

                    </div>

                </div>
            </div>
        </div>

                                <%@include file="components/common_modals.jsp" %>
                            
    </body>
</html>
