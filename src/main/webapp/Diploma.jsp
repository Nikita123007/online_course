<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="entity" scope="request" type="hibernate.DiplomaEntity"/>
<jsp:useBean id="user" scope="request" type="hibernate.UserEntity"/>
<html>
<% String title = "Diploma"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<%@ include file="resources/templates/header.jsp" %>
<div class="container main">
    <h1>Diploma</h1>
    <h2>${entity.user.name}</h2>
    <h2>${entity.course.name}</h2>
    <h2>Mark: ${entity.mark}</h2>
    <h4>Date: ${entity.date}</h4>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>