<%-- 
    Document   : register
    Created on : Aug 14, 2024, 9:24:02 PM
    Author     : rishan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <%@include file="components/common_css_js.jsp" %>

        <style>
            .background-img{
                background:url(img/shop.jpg);
                background-size: cover;
                background-attachment: fixed;
                min-height: 100%;
                padding-top: 20px; /* Adjust to match the height of your navbar */
                box-sizing: border-box;
            }
        </style>
    </head>

    <body>
        <%@include file="components/navbar.jsp" %>
        
        <main class="container-fluid background-img">
            
            <div class="row">
                <div class="col-md-4 offset-md-4">
                    
                    <div class="card">
                        
                        <%@include file="components/message.jsp" %>
                        
                        <div class="card-header primary-background text-white text-center">
                            <span class="fa fa-user-circle fa-3x"></span>
                            <br>
                            <p><h3 class="text-center my-3"><b>Sign Up here</b></h3></p>
                        </div>
                        
                        <div class="card-body px-5 login-background">

                            <form action="RegisterServlet" method="post">

                                <div class="form-group">
                                    <label for="name">Username</label>
                                    <input name="username"required type="text" class="form-control" id="name" aria-describedby="emailHelp" placeholder="Enter here">
                                </div>

                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input name="email" required type="email" class="form-control" id="Email" aria-describedby="emailHelp" placeholder="Enter here">
                                </div>

                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input name="password" required type="password" class="form-control" id="password" placeholder="Password">
                                </div>

                                <div class="form-group">
                                    <label for="phone">Phone Number</label>
                                    <input name="phone" required type="number" class="form-control" id="phone" aria-describedby="emailHelp" placeholder="Enter your phone number">
                                </div>

                                <div class="form-group">
                                    <label for="address">Address</label>
                                    <textarea name="address"required style="height: 120px;" class="form-control" id="address" aria-describedby="emailHelp" placeholder="Enter your address here">
                                    </textarea>
                                </div>

                                <div class="container text-center">
                                    <button type="submit" class="btn btn-outline-success">Register</button>
                                    <button type="reset" class="btn btn-primary">Reset</button>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>

    </body>
</html>
