<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "User"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <div class="form-group">
        Name: ${user.name}
    </div>
    <div class="form-group">
        Role: ${user.admin() ? "Admin" : "User"}
    </div>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>
