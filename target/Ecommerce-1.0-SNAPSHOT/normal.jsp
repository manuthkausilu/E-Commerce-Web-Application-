<%@page import="com.mycompany.ecommerce.entities.User"%>
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
        <title>User Panel</title>
        <%@include file="components/common_css_js.jsp" %>
    </head>
    <body>
        <%@include file="components/navbar.jsp" %>
        <h1>This is User Panel</h1>
    </body>
    <%@include file="components/common_modals.jsp" %>
</html>
