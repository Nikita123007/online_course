<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="entity" scope="request" type="hibernate.LectionEntity"/>
<jsp:useBean id="user" scope="request" type="hibernate.UserEntity"/>
<jsp:useBean id="add" scope="request" type="java.lang.Boolean"/>

<html>
    <% String title = "Lection edit"; %>
    <%@ include file="resources/templates/headers.html" %>
    <script type="text/javascript" src="resources/js/EditEntity.js"></script>
<body>
    <%@ include file="resources/templates/header.jsp" %>
    <div class="container main">
        <label for="name">Name</label>
        <h1><input type="text" placeholder="Lection name" name="name" id="name" value="${entity.name}" autofocus required pattern=".{2,}"></h1>
        <label for="text">Text</label>
        <h6><textarea placeholder="Lection text" cols="100" rows="20" name="text" id="text" required>${entity.text}</textarea></h6>
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
            text: $('#text').val()
        };
    }

    function GetEditUrl(){
        return "/LectionEdit?parentId=${entity.course.id}&id=${entity.id}";
    }
</script>
