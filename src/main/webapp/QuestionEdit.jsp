<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="entity" scope="request" type="hibernate.QuestionEntity"/>
<jsp:useBean id="add" scope="request" type="java.lang.Boolean"/>

<html>
    <% String title = "Question edit"; %>
    <%@ include file="resources/templates/headers.html" %>
    <script type="text/javascript" src="resources/js/EditEntity.js"></script>
<body>
<%@ include file="resources/templates/header.jsp" %>
<div class="container main">
    <label for="name">Question</label>
    <div class="input-group">
        <h2><input type="text" name="name" id="name" placeholder="Question" autofocus required value="${entity.question}" pattern=".{2,}"></h2>
    </div>
    <c:if test="${!add}">
        <h2>Answers</h2>
        <table border="solid 1px black" id="answers">
            <tr>
                <th class="deletecheckbox"></th>
                <th>Correct</th>
                <th>Name</th>
            </tr>
            <c:forEach var="answer" items="${entity.answers}">
                <tr>
                    <td class="deletecheckbox"><input type="checkbox" value="${answer.id}"></td>
                    <td><c:if test="${answer.isCorrect == 1}">True</c:if><c:if test="${answer.isCorrect == 0}">False</c:if></td>
                    <td><a href="AnswerEdit?id=${answer.id}">${answer.text}</a></td>
                </tr>
            </c:forEach>
        </table><br>
        <div class="input-group">
            <h2><button type="button" id="deleteAnswers" name="deleteAnswers" onclick="DeleteEntities('/AnswerEdit','answers')">Delete selected questions</button></h2>
            <h4><a class="design" id="addNewAnswer" name="addNewAnswer" href="AnswerEdit?add=true&parentId=${entity.id}">Add new answer</a></h4><br>
        </div><hr>
    </c:if>
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
        return "/QuestionEdit?parentId=${entity.test.id}&id=${entity.id}";
    }
</script>