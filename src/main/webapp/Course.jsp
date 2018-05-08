<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Course"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <p><h2>${course.name}</h2></p>
    <p>Duration: ${course.duration}</p>
    <p>Price: ${course.price}</p>
    <p>Level: ${course.level}</p>
    <p><h2>Lections</h2></p>
    <table border="solid 1px black">
        <tr>
            <th>Name</th>
        </tr>
        <c:forEach var="lection" items="${course.lectionsByIdCourse()}">
            <tr>
                <td><a href="Lection?id=${lection.idLection}">${lection.name}</a></td>
            </tr>
        </c:forEach>
    </table>
    <p><h2>Tests</h2></p>
    <table border="solid 1px black">
        <tr>
            <th>Name</th>
        </tr>
        <c:forEach var="test" items="${course.testsByIdCourse()}">
            <tr>
                <td><a href="Test?id=${test.idTest}">${test.name}</a></td>
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