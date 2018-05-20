<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="entity" scope="request" type="hibernate.TestEntity"/>

<html>
    <% String title = "Test run"; %>
    <%@ include file="resources/templates/headers.html" %>
    <script type="text/javascript" src="resources/js/EditEntity.js"></script>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <h2>${entity.name}</h2>
    <c:forEach var="question" items="${entity.questions}">
        <h4>${question.question}</h4>
        <c:forEach var="answer" items="${question.testAnswersByIdTestQuestion}">
            <h6><input type="checkbox" id="${answer.idTestAnswer}" value="${answer.idTestAnswer}"><span id="label${answer.idTestAnswer}">${answer.text}</span></h6>
        </c:forEach>
    </c:forEach>
    <span id="message" class="message"></span><br>
    <input class="design" type="button" name="sendTest" id="sendTest" value="Send" onclick="Create()">
    <a class="design" id="ok" name="ok" href="/Tests?parentId=${entity.course}" style="display: none">Ok</a>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>
<script>
    function GetData() {
        var answers = [];

        $('input:checked').each(function () {
            answers.push($(this).val());
        });

        return{
            answersCorrect: answers
        };
    }

    function GetEditUrl(){
        return "/TestRun?id=${entity.id}";
    }

    function SuccessChange(data) {
        var parseData = JSON.parse(data);
        parseData.answers.forEach(function (answer) {
            if (answer.isCorrect == 1){
                $("#label" + answer.idAnswer).addClass('seccussAnswer').removeClass('errorAnswer');
            }else{
                $("#label" + answer.idAnswer).addClass('errorAnswer').removeClass('seccussAnswer');
            }
            $("#" + answer.idAnswer).prop('disabled',true);
        });
        $("#ok").css("display", "inline-block");
        $("#sendTest").css("display", "none");
        $("#message").text("Correct " + parseData.numberOfCorrectQuestions + " questions form " + parseData.numberOfQuestions);
    }
</script>