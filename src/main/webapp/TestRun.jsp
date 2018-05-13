<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Courses"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <h2>${test.name}</h2>
    <c:forEach var="question" items="${test.testQuestionsByIdTest}">
        <h4>${question.question}</h4>
        <c:forEach var="answer" items="${question.testAnswersByIdTestQuestion}">
            <h6><input name="${question.question}" type="radio" value="id">${answer.text}</h6>
        </c:forEach>
    </c:forEach>
    <input type="button" name="sendTest" id="sendTest" value="Send">
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>