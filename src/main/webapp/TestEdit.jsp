<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Courses"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <h2><input type="text" name="nameTest" id="nameTest" value="${entity.name}"></h2>
    <c:forEach var="question" items="${entity.testQuestionsByIdTest}">
        <h4><input type="text" name="nameQuestion" id="nameQuestion" value="${question.question}"></h4>
        <c:forEach var="answer" items="${question.testAnswersByIdTestQuestion}">
            <h6><input name="${question.question}" type="radio" value="${question.idTestQuestion}" <c:if test="${answer.isCorrect == 1}">checked</c:if>><input type="text" name="answer${answer.idTestAnswer}" id="answer${answer.idTestAnswer}" value="${answer.text}"></h6>
        </c:forEach>
        <input type="button" name="editTest" id="editTest" value="Edit">
    </c:forEach>
    <input type="button" name="editTest" id="editTest" value="Edit">not work!!!
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>
