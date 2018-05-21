<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="entity" scope="request" type="hibernate.TestEntity"/>
<jsp:useBean id="add" scope="request" type="java.lang.Boolean"/>

<html>
    <% String title = "Test edit"; %>
    <%@ include file="resources/templates/headers.html" %>
    <script type="text/javascript" src="resources/js/EditEntity.js"></script>
<body>
<%@ include file="resources/templates/header.jsp" %>
<div class="container main">
    <label for="name">Name</label>
    <div class="input-group">
        <h2><input type="text" name="name" id="name" placeholder="Test" autofocus required value="${entity.name}" pattern=".{2,}"></h2>
    </div>
    <c:if test="${!add}">
        <h2>Questions</h2>
        <table border="solid 1px black" id="questions">
            <tr>
                <th class="deletecheckbox"></th>
                <th>Name</th>
            </tr>
            <c:forEach var="question" items="${entity.questions}">
                <tr>
                    <td class="deletecheckbox"><input type="checkbox" value="${question.id}"></td>
                    <td><a href="QuestionEdit?id=${question.id}">${question.question}</a></td>
                </tr>
            </c:forEach>
        </table><br>
        <div class="input-group">
            <h2><button type="button" id="deleteQuestions" name="deleteQuestions" onclick="DeleteEntities('/QuestionEdit','questions')">Delete selected questions</button></h2>
            <h4><a class="design" id="addNewQuestion" name="addNewQuestion" href="QuestionEdit?add=true&parentId=${entity.id}">Add new question</a></h4><br>
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
        <c:if test="${user.admin()}">
            <div class="input-group">
                <h2><a class="design" href="Document?id=${entity.id}&type=pdf&name=Test">Generate pdf</a></h2>
                <h2><a class="design" href="Document?id=${entity.id}&type=csv&name=Test">Generate csv</a></h2>
                <h2><a class="design" href="Document?id=${entity.id}&type=excel&name=Test">Generate excel</a></h2>
            </div>
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
        return "/TestEdit?parentId=${entity.course.id}&id=${entity.id}";
    }
</script>
