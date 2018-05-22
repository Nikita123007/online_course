<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="entity" scope="request" type="hibernate.CourseEntity"/>
<jsp:useBean id="user" scope="request" type="hibernate.UserEntity"/>
<jsp:useBean id="add" scope="request" type="java.lang.Boolean"/>

<html>
    <% String title = "Edit course"; %>
    <%@ include file="resources/templates/headers.html" %>
    <script type="text/javascript" src="resources/js/EditEntity.js"></script>
<body>
    <%@ include file="resources/templates/header.jsp" %>
    <div class="container main">
        <form class="form-horizontal" onsubmit="return false;">
            <label for="name">Name</label>
            <div class="input-group">
                <h2><input type="text" name="name" id="name" placeholder="Course name" autofocus required value="${entity.name}" pattern=".{2,}"></h2>
            </div>
            <label for="description">Description</label>
            <p><textarea cols="100" rows="20" name="description" id="description" placeholder="Course description" required>${entity.description}</textarea></p>
            <label for="price">Price</label>
            <h2><input type="number" name="price" id="price" placeholder="Price" required value="${entity.price}"></h2>
            <label for="level">Level</label>
            <h2><input type="text" name="level" id="level" placeholder="Level" value="${entity.level}"></h2><hr>
            <h2>Lections</h2>
            <table border="solid 1px black" id="lections">
                <tr>
                    <th></th>
                    <th>Name</th>
                </tr>
                <c:forEach var="lection" items="${entity.lections}">
                    <tr>
                        <td><input type="checkbox" value="${lection.id}"></td>
                        <td><a href="LectionEdit?id=${lection.id}">${lection.name}</a></td>
                    </tr>
                </c:forEach>
            </table><br>
            <div class="input-group">
                <h2><button type="button" id="deleteLections" name="deleteLections" onclick="DeleteEntities('/LectionEdit','lections')">Delete selected lections</button></h2>
                <h4><a class="design" id="addNewLection" name="addNewLection" href="LectionEdit?add=true&parentId=${entity.id}">Add new lection</a></h4><br>
            </div><hr>
            <h2>Tests</h2>
            <table border="solid 1px black" id="tests">
                <tr>
                    <th class="deletecheckbox"></th>
                    <th>Name</th>
                </tr>
                <c:forEach var="test" items="${entity.tests}">
                    <tr>
                        <td class="deletecheckbox"><input type="checkbox" value="${test.id}"></td>
                        <td><a href="TestEdit?id=${test.id}">${test.name}</a></td>
                    </tr>
                </c:forEach>
            </table><br>
            <div class="input-group">
                <h2><button type="button" id="deleteTests" name="deleteTests" onclick="DeleteEntities('/TestEdit','tests')">Delete selected tests</button></h2>
                <h4><a class="design" id="addNewTest" name="addNewTest" href="TestEdit?add=true&parentId=${entity.id}">Add new test</a></h4><br>
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
            <hr>
        </form>
    </div>
    <c:if test="${user.admin()}">
        <div class="input-group" style="position: absolute;top: 70px;display: inline-block;left: 75%;border: 1px solid black;width: 220px;padding: 20px;text-align: center;border-radius: 10px;">
            <a class="design" style="width: 100%;margin: 3px;" href="Document?id=${entity.id}&type=pdf&name=Course">Generate pdf</a>
            <a class="design" style="width: 100%;margin: 3px;" href="Document?id=${entity.id}&type=csv&name=Course">Generate csv</a>
            <a class="design" style="width: 100%;margin: 3px;" href="Document?id=${entity.id}&type=excel&name=Course">Generate excel</a>
        </div>
    </c:if>
    <%@ include file="resources/templates/footer.html" %>
</body>
</html>

<script>
    function GetData(){
        return {
            name: $('#name').val(),
            description: $('#description').val(),
            price: parseInt($('#price').val()),
            level: $('#level').val()
        };
    }

    function GetEditUrl(){
        return "/EditCourse?id=${entity.id}";
    }
</script>