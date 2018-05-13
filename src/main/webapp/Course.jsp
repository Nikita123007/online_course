<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Course"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <p><h2>${entity.name}</h2></p>
    <p style="margin-bottom: 0px">Duration: ${entity.duration}</p>
    <p style="margin-bottom: 0px">Level: ${entity.level}</p>
    <p>Category: ${course.categoryByCategory.name}</p>
    <p><h2>Lections</h2></p>
    <table border="solid 1px black">
        <tr>
            <th>Name</th>
        </tr>
        <c:forEach var="lection" items="${entity.lectionsByIdCourse}">
            <tr>
                <td><a href="Lection?id=${lection.idLection}">${lection.name}</a></td>
            </tr>
        </c:forEach>
    </table>
    <p><h2>Tests</h2></p>
    <table border="solid 1px black">
        <tr>
            <th>Name</th>
            <th>Status</th>
        </tr>
        <c:forEach var="test" items="${tests}">
            <tr>
                <td><a href="TestRun?id=${test.idTest}">${test.name}</a></td>
                <td><c:if test="${test.completed == 1}">Completed</c:if><c:if test="${test.completed == 0}">Available</c:if></td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${courseCompleted==true}">
        <input type="button" id="diploma" name="diploma" value="diploma">
    </c:if>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>