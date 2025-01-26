<%@page import="com.mycompany.ecommerce.entities.User"%>
<%
    User userN = (User) session.getAttribute("currentUser");


%>


<nav class="navbar navbar-expand-lg navbar-dark bg-light nav-color">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">ShopNest</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">

            <ul class="navbar-nav ml-auto">

                <li class="nav-item active">
                    <a class="nav-link" href="#" data-toggle="modal" data-target="#cart"><i class="fa fa-cart-plus"style="font-size: 20px;"> (<span class="cart-items">  </span>) </i></a>
                </li>

                <%          if (userN == null) {

                %>

                <li class="nav-item active">
                    <a class="nav-link" href="login.jsp">Login</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="register.jsp">Register</a>
                </li>

                <%                } else if (userN.getUserType().equals("admin")) {
                %>

                <li class="nav-item active">
                    <a class="nav-link" href="admin.jsp"><%= userN.getUserName()%></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="LogoutServlet">Logout</a>
                </li>
                <%                 } else {

                %>
                <li class="nav-item active">
                    <a class="nav-link" href="normal.jsp"><%= userN.getUserName()%></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="LogoutServlet">Logout</a>
                </li>
                <%
                    }
                %>





            </ul>


        </div>
    </div>
</nav>