<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Cources"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <p><h1>${lection.name}</h1></p>
    <h3>${lection.description}</h3>
    <h6>${lection.text}</h6>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>

