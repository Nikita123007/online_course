<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <% String title = "Courses"; %>
    <%@ include file="resources/templates/headers.html" %>
    <script ty pe="text/javascript" src="resources/js/EditEntity.js"></script>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <div class="input-group">
        <p><h2><input type="text" name="name" id="name" placeholder="Test name" required value="${entity.name}"></h2></p>
    </div>
    <p><h2>Questions</h2></p>
    <table border="solid 1px black" id="questions">
        <tr>
            <th>Name</th>
        </tr>
        <c:forEach var="question" items="${entity.testQuestionsByIdTest}">
            <tr>
                <td><a href="/QuestionEdit?id=${question.idTestQuestion}">${question.question}</a></td>
            </tr>
        </c:forEach>
    </table>
    <h4><a id="addNewQuestion" name="addNewQuestion" href="/QuestionEdit?add=true&parentId=${entity.idTest}">Add new question</a></h4><br>
    <c:if test="${!add}">
        <h2><button type="button" id="save" name="save" onclick="Save()">Save</button></h2>
        <h2><button type="button" id="delete" name="delete" onclick="Delete()">Delete</button></h2>
    </c:if>
    <c:if test="${add}">
        <h2><button type="button" id="create" name="create" onclick="Create()">Create</button></h2>
    </c:if>
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
