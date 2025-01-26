<%@page import="java.util.Map"%>
<%@page import="com.mycompany.ecommerce.helper.Helper"%>
<%@page import="com.mycompany.ecommerce.entities.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.ecommerce.helper.FactoryProvider"%>
<%@page import="com.mycompany.ecommerce.dao.CategoryDao"%>
<%@page import="com.mycompany.ecommerce.entities.User"%>
<%
    User user = (User) session.getAttribute("currentUser");
    if (user == null) {
        session.setAttribute("message", "You are not logged in !! Login first");
        response.sendRedirect("login.jsp");
        return;
    } else {
        if (user.getUserType().equals("normal")) {
            session.setAttribute("message", "You are not admin !! Can't access");
            response.sendRedirect("login.jsp");
            return;
        }
    }
    CategoryDao categoryDao = new CategoryDao(FactoryProvider.getFactory());
    List<Category> categories = categoryDao.getCategories();

    //getting count
    Map<String,Integer> map = Helper.getCounts( FactoryProvider.getFactory());
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Panel</title>
        <%@include file="components/common_css_js.jsp" %>
    </head>
    <body>
        <%@include file="components/navbar.jsp" %>

        <div class="container admin">

            <%@include file="components/message.jsp" %>

            <!--first row-->
            <div class="row mt-3">

                <!--first column-->
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body text-center">
                            <div class="container">
                                <img class="fluid" style="max-width: 100px;" src="img/team.png" alt="user_icon"/>
                            </div>
                            <h1><%= map.get("userCount") %></h1>
                            <h1 class="text-uppercase text-muted">Users</h1>
                        </div>
                    </div>
                </div>

                <!--second column-->
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body text-center">
                            <div class="container">
                                <img class="fluid" style="max-width: 100px;" src="img/window.png" alt="user_icon"/>
                            </div>
                            <h1><%= categories.size() %></h1>
                            <h1 class="text-uppercase text-muted">Categories</h1>
                        </div>
                    </div>
                </div>

                <!--third column-->
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body text-center">
                            <div class="container">
                                <img class="fluid" style="max-width: 100px;" src="img/delivery.png" alt="user_icon"/>
                            </div>
                            <h1><%= map.get("productCount") %></h1>
                            <h1 class="text-uppercase text-muted">Products</h1>
                        </div>
                    </div>
                </div>
            </div>


            <!--second row-->
            <div class="row mt-3">

                <!--first-column-->
                <div class="col-md-6">
                    <div class="card" data-toggle="modal" data-target="#add-category-modal">



                        <div class="card-body text-center">
                            <div class="container">
                                <img class="fluid" style="max-width: 100px;" src="img/category.png" alt="category_icon"/>
                            </div>
                            <small><p class="mt-2">Click here to add new category</p></small>
                            <div class="text-uppercase text-muted">Add Category</div>
                        </div>
                    </div>
                </div>

                <!--second-column-->
                <div class="col-md-6">
                    <div class="card"data-toggle="modal" data-target="#add-product-modal">
                        <div class="card-body text-center">
                            <div class="container">
                                <img class="fluid" style="max-width: 100px;" src="img/box.png" alt="category_icon"/>
                            </div>
                            <small><p class="mt-2">Click here to add new Product</p></small>
                            <div class="text-uppercase text-muted">Add Product</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--modal add category-->

        <!-- Modal -->

        <div class="modal fade" id="add-category-modal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header nav-color text-white">
                        <h5 class="modal-title" id="staticBackdropLabel">Fill Category Details</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">

                        <form action="ProductOperationServlet" method="post">

                            <input type="hidden" name="operation" value="addCategory">

                            <div class="form-group">
                                <input type="text" name="catTitle" class="form-control" placeholder="Enter category title"required>
                            </div>

                            <div class="form-group">
                                <textarea style="height: 200px" class="form-control" name="catDescription" placeholder="Enter category description" required></textarea>
                            </div>

                            <div class="container text-center">
                                <button type="submit" class="btn btn-outline-success">Add Category</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>

                        </form>

                    </div>
                </div>
            </div>
        </div>
        <!--end of add category modal-->

        <!----------------------------------------------------------------------------------------------------------------------------------->
        <!--modal add product-->

        <!-- Modal -->

        <div class="modal fade" id="add-product-modal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header nav-color text-white">
                        <h5 class="modal-title" id="staticBackdropLabel">Fill Product Details</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">

                        <form action="ProductOperationServlet" method="post"enctype="multipart/form-data">

                            <input type="hidden" name="operation" value="addProduct">

                            <div class="form-group">
                                <input type="text" name="productName" class="form-control" placeholder="Enter product name"required>
                            </div>

                            <!--product-category-drop-down-->

                            <div class="form-group">

                                <select name="catId" class="form-control form-control-sm">

                                    <option value="first" disabled selected>select a category</option>

                                    <%                                        
                                        for (Category category : categories) {
                                    %>

                                    <option value="<%= category.getCategoryId()%>"><%=category.getCategoryTitle()%></option>

                                    <%
                                        }
                                    %>

                                </select>
                            </div>


                            <div class="form-group">
                                <textarea style="height: 150px" class="form-control" name="ProductDescription" placeholder="Enter product description" required></textarea>
                            </div>

                            <div class="form-group">
                                <label for="productPic">Select Picture of  the Product</label>
                                <br>
                                <input type="file" name="productPic" id="productPic" required>
                            </div>

                            <div class="form-group">
                                <input type="number" name="productPrice" class="form-control" placeholder="Enter product price"required>
                            </div>

                            <div class="form-group">
                                <input type="number" name="productDiscount" class="form-control" placeholder="Enter product discount"required>
                            </div>

                            <div class="form-group">
                                <input type="number" name="productQuantity" class="form-control" placeholder="Enter product quantity"required>
                            </div>

                            <div class="container text-center">
                                <button type="submit" class="btn btn-outline-success">Add Product</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>

                        </form>

                    </div>
                </div>
            </div>
        </div>
        <!--end of add product modal-->

    </body>
</html>
