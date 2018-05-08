<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Courses"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <h2><input type="text" name="nameTest" id="nameTest" value="${test.name}"></h2>
    <c:forEach var="question" items="${test.questions}">
        <h4><input type="text" name="nameQuestion" id="nameQuestion" value="${question.name}"></h4>
        <c:forEach var="answer" items="${question.answers}">
            <h6><input name="${question.name}" type="radio" value="id"><input type="text" name="${question.name}" id="${question.name}" value="${answer.description}"></h6>
        </c:forEach>
    </c:forEach>
    <input type="button" name="editTest" id="editTest" value="Edit">
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>
