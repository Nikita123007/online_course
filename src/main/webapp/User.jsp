<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Cources"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <div class="form-group">
        Name: ${user.firstName}
    </div>
    <div class="form-group">
        Suname: ${user.secondName}
    </div>
    <div class="form-group">
        Age: ${user.age}
    </div>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>
