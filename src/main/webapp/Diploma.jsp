<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Courses"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <p><h1>Diploma</h1></p>
    <h2>${diploma.userByUser.name}</h2>
    <h2>${diploma.courseByCourse.name}</h2>
    <h2>Mark: ${diploma.mark}</h2>
    <h4>Date: ${diploma.date}</h4>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>