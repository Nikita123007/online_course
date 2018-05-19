<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <% String title = "Edit course"; %>
    <%@ include file="resources/templates/headers.html" %>
    <script type="text/javascript" src="resources/js/EditEntity.js"></script>
<body>
    <%@ include file="resources/templates/header.html" %>
    <div class="container main">
        <form class="form-horizontal" onsubmit="return false;">
            <div class="input-group">
                <p><h2><input type="text" name="name" id="name" placeholder="Course name" autofocus required value="${entity.name}" pattern="[a-zA-ZА-Яа-я_0-9 ]{2,}"></h2></p>
            </div>
            <p><textarea cols="100" rows="20" name="description" id="description" placeholder="Course description" required>${entity.description}</textarea></p>
            <p><h2><input type="number" name="price" id="price" placeholder="Price" required value="${entity.price}"></h2></p>
            <p><h2><input type="text" name="level" id="level" placeholder="Level" value="${entity.level}"></h2></p><hr>
            <p><h2>Lections</h2></p>
            <table border="solid 1px black" id="lections">
                <tr>
                    <th></th>
                    <th>Name</th>
                </tr>
                <c:forEach var="lection" items="${entity.lectionsByIdCourse}">
                    <tr>
                        <td><input type="checkbox" value="${lection.idLection}"></td>
                        <td><a href="/LectionEdit?id=${lection.idLection}">${lection.name}</a></td>
                    </tr>
                </c:forEach>
            </table><br>
            <div class="input-group">
                <h2><button type="button" id="deleteLections" name="deleteLections" onclick="DeleteEntities('/LectionEdit','lections')">Delete selected lections</button></h2>
                <h4><a class="design" id="addNewLection" name="addNewLection" href="/LectionEdit?add=true&parentId=${entity.idCourse}">Add new lection</a></h4><br>
            </div><hr>
            <p><h2>Tests</h2></p>
            <table border="solid 1px black" id="tests">
                <tr>
                    <th class="deletecheckbox"></th>
                    <th>Name</th>
                </tr>
                <c:forEach var="test" items="${entity.testsByIdCourse}">
                    <tr>
                        <td class="deletecheckbox"><input type="checkbox" value="${test.idTest}"></td>
                        <td><a href="/TestEdit?id=${test.idTest}">${test.name}</a></td>
                    </tr>
                </c:forEach>
            </table><br>
            <div class="input-group">
                <h2><button type="button" id="deleteTests" name="deleteTests" onclick="DeleteEntities('/TestEdit','tests')">Delete selected tests</button></h2>
                <h4><a class="design" id="addNewTest" name="addNewTest" href="/TestEdit?add=true&parentId=${entity.idCourse}">Add new test</a></h4><br></br></br>
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
            <c:if test="${user.admin()}">
                <div class="input-group">
                    <h2><a class="design" href="/Document?id=${entity.idCourse}&type=pdf&name=Course">Generate pdf</a></h2>
                    <h2><a class="design" href="/Document?id=${entity.idCourse}&type=csv&name=Course">Generate csv</a></h2>
                    <h2><a class="design" href="/Document?id=${entity.idCourse}&type=excel&name=Course">Generate excel</a></h2>
                </div>
            </c:if>
        </form>
    </div>
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
        return "/EditCourse?id=${entity.idCourse}";
    }
</script>