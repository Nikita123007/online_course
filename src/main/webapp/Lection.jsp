<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="entity" scope="request" type="hibernate.LectionEntity"/>

<html>
<% String title = "Lection"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<%@ include file="resources/templates/header.jsp" %>
<div class="container main">
    <h1>${entity.name}</h1>
    <h6>${entity.text}</h6>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>

