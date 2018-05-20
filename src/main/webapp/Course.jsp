<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="entity" scope="request" type="hibernate.CourseEntity"/>
<jsp:useBean id="courseCompleted" scope="request" type="java.lang.Boolean"/>

<html>
<% String title = "Course"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <h2>${entity.name}</h2>
    <p style="margin-bottom: 0px">Duration: ${entity.duration}</p>
    <p style="margin-bottom: 0px">Level: ${entity.level}</p>
    <p>Category: ${entity.category.name}</p>
    <h2><a href="Lections?parentId=${entity.id}">Lections</a></h2>
    <h2><a href="Tests?parentId=${entity.id}">Tests</a></h2>
    <c:if test="${courseCompleted}">
        <input type="button" id="diploma" name="diploma" value="diploma">
    </c:if>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>