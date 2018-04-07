<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Cources"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <p><h2>${cource.name}</h2></p>
    <p>No description</p>
    <p><h2>Lections</h2></p>
    <table border="solid 1px black">
        <tr>
            <th>Name</th>
            <th>Hours</th>
            <th>Descroption</th>
        </tr>
        <c:forEach var="lection" items="${cource.getLectionsByIdCourse()}">
            <tr>
                <td><a href="Lection">${lection.name}</a></td>
                <td>${lection.hours}</td>
                <td>${lection.description}</td>
            </tr>
        </c:forEach>
    </table>
    <p><h2>Tests</h2></p>
    <table border="solid 1px black">
        <tr>
            <th>Name</th>
            <th>Status</th>
        </tr>
        <c:forEach var="test" items="${cource.getTestsByIdCourse()}">
            <tr>
                <td><a href="Test">${test.name}</a></td>
                <td><a href="TestStatus">${test.status}</a></td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${courceCompleted==true}">
        <input type="button" id="diploma" name="diploma" value="diploma">
    </c:if>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>