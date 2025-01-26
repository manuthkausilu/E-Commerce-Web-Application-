<%
    User user = (User) session.getAttribute("currentUser");
    if (user == null) {
        session.setAttribute("message", "You are not logged in !! Login first");
        response.sendRedirect("login.jsp");
        return;
    } else {
        if (user.getUserType().equals("admin")) {
            session.setAttribute("message", "You are not user !! Can't access");
            response.sendRedirect("login.jsp");
            return;
        }
    }


%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CheckOut</title>
        <%@include file="components/common_css_js.jsp" %>
    </head>
    <body>
        <%@include file="components/navbar.jsp" %>

        <div class="container"style="height:92vh">
            <div class="row">

                <div class="col-md-6">
                    <!--card-->

                    <div class="card  mt-5">
                        <div class="card-body text-center">
                            <h3>Your Selected Items</h3>
                            <div class="cart-body">

                            </div>
                        </div>
                    </div>

                </div>

                <div class="col-md-6">
                    <!--form-details-->

                    <div class="card  mt-5">
                        <div class="card-body">
                            <h3 class="text-center mb-5">Your Details for Order</h3>
                            <form>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Email address</label>
                                    <input value="<%= user.getUserEmail() %>" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Your Name</label>
                                    <input value="<%= user.getUserName() %>" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter your name">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Your Phone Number</label>
                                    <input value="<%= user.getUserPhone() %>" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter your phone number">
                                </div>
                                <div class="form-group">
                                    <label for="address">Address</label>
                                    <textarea value="<%= user.getUserAddress() %>" name="address"required style="height: 80px;" class="form-control" id="address" aria-describedby="emailHelp" placeholder="Enter your shipping address here">
                                    </textarea>
                                </div>
                                
                                <div class="container text-center">
                                    <button type="submit" class="btn btn-outline-success">Order Now</button>
                                    <button type="reset" class="btn btn-primary">Reset</button>
                                </div>

                            </form>

                        </div>
                    </div>

                </div>

            </div>
        </div>

        <%@include file="components/common_modals.jsp" %>
    </body>
</html>
