<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <% String title = "Answer edit"; %>
    <%@ include file="resources/templates/headers.html" %>
    <script ty pe="text/javascript" src="resources/js/EditEntity.js"></script>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <div class="input-group">
        <p><h2><input type="text" name="name" id="name" placeholder="Answer name" autofocus required value="${entity.text}" pattern="[a-zA-ZА-Яа-я_0-9 ]{2,}"></h2></p>
    </div><br>
    <div class="input-group">
        <h4><input name="answer" id="correct" value="true" type="radio" <c:if test="${entity.isCorrect == 1}">checked</c:if>><label for="correct">Correct</label></h4>
        <h4><input name="answer" id="wrong" value="false" type="radio" <c:if test="${entity.isCorrect == 0}">checked</c:if> style="margin-left: 20px;"><label for="wrong">Wrong</label></h4>
    </div><br>
    <div class="input-group">
        <c:if test="${!add}">
            <h2><button type="button" id="save" name="save" onclick="Save()">Save</button></h2>
            <h2><button type="button" id="delete" name="delete" onclick="Delete()">Delete</button></h2>
        </c:if>
        <c:if test="${add}">
            <h2><button type="button" id="create" name="create" onclick="Create()">Create</button></h2>
        </c:if>
    </div>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>
<script>
    function GetData() {
        return{
            name: $('#name').val(),
            correct: $('input:checked').val()
        };
    }

    function GetEditUrl(){
        return "/AnswerEdit?parentId=${entity.testQuestion}&id=${entity.idTestAnswer}";
    }
</script>