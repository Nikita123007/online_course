<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <% String title = "Question edit"; %>
    <%@ include file="resources/templates/headers.html" %>
    <script ty pe="text/javascript" src="resources/js/EditEntity.js"></script>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <label for="name">Question</label>
    <div class="input-group">
        <p><h2><input type="text" name="name" id="name" placeholder="Question" autofocus required value="${entity.question}" pattern="[a-zA-ZА-Яа-я_0-9 ]{2,}"></h2></p>
    </div>
    <p><h2>Answers</h2></p>
    <table border="solid 1px black" id="answers">
        <tr>
            <th class="deletecheckbox"></th>
            <th>Correct</th>
            <th>Name</th>
        </tr>
        <c:forEach var="answer" items="${entity.testAnswersByIdTestQuestion}">
            <tr>
                <td class="deletecheckbox"><input type="checkbox" value="${answer.idTestAnswer}" style="margin-left: 20px;"></td>
                <td><c:if test="${answer.isCorrect == 1}">True</c:if><c:if test="${answer.isCorrect == 0}">False</c:if></td>
                <td><a href="/AnswerEdit?id=${answer.idTestAnswer}">${answer.text}</a></td>
            </tr>
        </c:forEach>
    </table><br>
    <div class="input-group">
        <h2><button type="button" id="deleteAnswers" name="deleteAnswers" onclick="DeleteEntities('/AnswerEdit','answers')">Delete selected questions</button></h2>
        <h4><a class="design" id="addNewAnswer" name="addNewAnswer" href="/AnswerEdit?add=true&parentId=${entity.idTestQuestion}">Add new answer</a></h4><br>
    </div><hr>
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
            name: $('#name').val()
        };
    }

    function GetEditUrl(){
        return "/QuestionEdit?parentId=${entity.test}&id=${entity.idTestQuestion}";
    }
</script>