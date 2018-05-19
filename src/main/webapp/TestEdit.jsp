<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <% String title = "Test edit"; %>
    <%@ include file="resources/templates/headers.html" %>
    <script ty pe="text/javascript" src="resources/js/EditEntity.js"></script>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <div class="input-group">
        <p><h2><input type="text" name="name" id="name" placeholder="Test name" autofocus required value="${entity.name}" pattern="[a-zA-ZА-Яа-я_0-9 ]{2,}"></h2></p>
    </div>
    <p><h2>Questions</h2></p>
    <table border="solid 1px black" id="questions">
        <tr>
            <th class="deletecheckbox"></th>
            <th>Name</th>
        </tr>
        <c:forEach var="question" items="${entity.testQuestionsByIdTest}">
            <tr>
                <td class="deletecheckbox"><input type="checkbox" value="${question.idTestQuestion}"></td>
                <td><a href="/QuestionEdit?id=${question.idTestQuestion}">${question.question}</a></td>
            </tr>
        </c:forEach>
    </table><br>
    <div class="input-group">
        <h2><button type="button" id="deleteQuestions" name="deleteQuestions" onclick="DeleteEntities('/QuestionEdit','questions')">Delete selected questions</button></h2>
        <h4><a class="design" id="addNewQuestion" name="addNewQuestion" href="/QuestionEdit?add=true&parentId=${entity.idTest}">Add new question</a></h4><br>
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
        return "/TestEdit?parentId=${entity.course}&id=${entity.idTest}";
    }
</script>
