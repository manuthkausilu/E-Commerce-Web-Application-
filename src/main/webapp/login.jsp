
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
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

        <main class="container-fluid background-img align-items-center" style="height:92vh">

            <div class="row">
                <div class="col-md-4 offset-md-4">

                    <div class="card mt-5">

                        <%@include file="components/message.jsp" %>

                        <div class="card-header primary-background text-white text-center">
                            <span class="fa fa-user-circle fa-3x"></span>
                            <br>
                            <p><h3 class="text-center my-3"><b>Login here</b></h3></p>
                        </div>

                        <div class="card-body px-5 login-background">

                            <form action="LoginServlet" method="post">

                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input name="email" required type="email" class="form-control" id="Email" aria-describedby="emailHelp" placeholder="Enter here">
                                </div>

                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input name="password" required type="password" class="form-control" id="password" placeholder="Password">
                                </div>

                                <a href="register.jsp"><small>If not registered click here</small></a>
                                <div class="container text-center mt-2">
                                    <button type="submit" class="btn btn-outline-nav">Login</button>
                                    <button type="reset" class="btn btn-primary" href="#">Reset</button>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>
