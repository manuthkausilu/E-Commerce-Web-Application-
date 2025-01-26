<%
    String message = (String) session.getAttribute("message");
    String catTitle = (String) session.getAttribute("catTitle");
    String productName = (String) session.getAttribute("productName");

    if (message != null) {
        String alertType = "";
        String alertMessage = "";

        switch (message) {
            case "Registration Successful!!":
                alertType = "success";
                alertMessage = message;
                break;
            case "Email must be unique!!":
            case "Invalid email address or password!!":
            case "Category Title Must be Unique":
                alertType = "warning";
                alertMessage = message;
                break;
            case "You are not logged in !! Login first":
            case "You are not admin !! Can't access":
            case "You are not user !! Can't access":
                alertType = "danger";
                alertMessage = message;
                break;
            case "New Category Added":
                alertType = "success";
                alertMessage = "New Category " + catTitle + " Added";
                break;
            case "Could not be added-category":
                alertType = "warning";
                alertMessage = catTitle + " Could not be added";
                break;
            case "New Product Added":
                alertType = "success";
                alertMessage = "New Product " + productName + " Added";
                break;
            case "Could not be added":
                alertType = "warning";
                alertMessage = productName + " Could not be added";
                break;
            case "Uploaded file is not a valid image.":
                alertType = "warning";
                alertMessage = "Uploaded file is not a valid image !! "+productName + " Could not be added";
                break;
        }

        if (!alertType.isEmpty() && !alertMessage.isEmpty()) {
%>
<div class="alert alert-<%= alertType %> alert-dismissible fade show" role="alert">
    <strong><%= alertMessage %></strong> 
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<%
            session.removeAttribute("message");
            if (message.startsWith("New Category") || message.startsWith("Category Title Must be Unique")) {
                session.removeAttribute("catTitle");
            } else if (message.startsWith("New Product")) {
                session.removeAttribute("productName");
            }
        }
    }
%>
