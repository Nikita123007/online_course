<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Edit course"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <form class="form-horizontal" onsubmit="return false;">
        <div class="input-group">
            <p><h2><input type="text" name="name" id="name" placeholder="Course name" required value="${entity.name}"></h2></p>
        </div>
        <p><textarea cols="100" rows="20" name="description" id="description" placeholder="Course description" required>${entity.description}</textarea></p>
        <p><h2><input type="number" name="price" id="price" placeholder="Price" required value="${entity.price}"></h2></p>
        <p><h2><input type="text" name="level" id="level" placeholder="Level" value="${entity.level}"></h2></p>
        <p><h2>Lections</h2></p>
        <table border="solid 1px black" id="lections">
            <tr>
                <th>Name</th>
            </tr>
            <c:forEach var="lection" items="${entity.lectionsByIdCourse}">
                <tr>
                    <td><a href="/LectionEdit?id=${lection.idLection}">${lection.name}</a></td>
                </tr>
            </c:forEach>
        </table>
        <h4><a id="addNewLection" name="addNewLection" href="/LectionEdit?add=true&parentId=${entity.idCourse}">Add new lection</a></h4><br>
        <p><h2>Tests</h2></p>
        <table border="solid 1px black" id="tests">
            <tr>
                <th>Name</th>
            </tr>
            <c:forEach var="test" items="${entity.testsByIdCourse}">
                <tr>
                    <td><a href="/TestEdit?id=${test.idTest}">${test.name}</a></td>
                </tr>
            </c:forEach>
        </table>
        <h4><a id="addNewTest" name="addNewTest" href="/TestEdit?add=true&parentId=${entity.idCourse}">Add new test</a></h4><br></br></br>
        <c:if test="${!add}">
            <h2><button type="button" id="save" name="save" onclick="Save()">Save</button></h2>
            <h2><button type="button" id="delete" name="delete" onclick="Delete()">Delete</button></h2>
        </c:if>
        <c:if test="${add}">
            <h2><button type="button" id="create" name="create" onclick="Create(${entity.idCourse})">Create</button></h2>
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

    function Validate(){
        var validForm = true;
        $('input').each(function() {
            validForm = validForm && this.checkValidity();
        });
        return validForm;
    }

    function SuccessChange(data) {
        var parseData = JSON.parse(data);
        if (parseData.error == ""){
            document.location.href = document.location.protocol + "//" + document.location.host + parseData.nextPage
        }else{
            confirm(parseData.error);
        }
    }
    function ErrorChange(data) {
        console.log("error");
        console.log(data);
    }

    function SendAction(actionType){
        var locationURL = document.location.protocol + "//" + document.location.host + GetEditUrl();
        $.ajax({
            url: locationURL,
            type: actionType,
            contentType: "application/json",
            data: JSON.stringify(GetData()),
            success: SuccessChange,
            error: ErrorChange
        });
    }

    function Create(){
        if (Validate()){
            SendAction("POST");
        }
    }

    function Delete() {
        SendAction("DELETE");
    }

    function Save() {
            if (Validate()){
                SendAction("PUT");
            }
        };
</script>