<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="entity" scope="request" type="hibernate.UserEntity"/>

<html>
<% String title = "User"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<%@ include file="resources/templates/header.jsp" %>
<div class="container main">
    <div class="form-group">
        Name: ${entity.name}
    </div>
    <div class="form-group">
        Role: ${entity.admin() ? "Admin" : "User"}
    </div>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>
