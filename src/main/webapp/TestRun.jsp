<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <% String title = "Courses"; %>
    <%@ include file="resources/templates/headers.html" %>
    <script ty pe="text/javascript" src="resources/js/EditEntity.js"></script>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <h2>${entity.name}</h2>
    <c:forEach var="question" items="${entity.testQuestionsByIdTest}">
        <h4>${question.question}</h4>
        <c:forEach var="answer" items="${question.testAnswersByIdTestQuestion}">
            <h6><input type="checkbox" value="${answer.idTestAnswer}">${answer.text}</h6>
        </c:forEach>
    </c:forEach>
    <input type="button" name="sendTest" id="sendTest" value="Send" onclick="Create()">
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>
<script>
    function GetData() {
        var answers = new Array();

        $('input:checked').each(function () {
            answers.push($(this).val());
        });

        return{
            answersCorrect: answers
        };
    }

    function GetEditUrl(){
        return "/TestRun?id=${entity.idTest}";
    }
</script>