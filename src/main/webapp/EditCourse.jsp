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
            <p><h2><input type="text" name="courseName" id="courseName" placeholder="Course name" pattern=".{5,}" value="${entity.name}"></h2></p>
        </div>
        <p><textarea cols="100" rows="20" name="courseDesccription" id="courseDesccription" placeholder="Course decription" pattern=".{15,}" >${entity.description}</textarea></p>
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
        <h2><button type="button" id="save" name="save">Save</button></h2>
        <h2><button type="button" id="delete" name="delete" onclick="DeleteCourse()">Delete</button></h2>
    </form>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>

<script>
    function SuccessDelete(data) {
        var parseData = JSON.parse(data);
        if (parseData.error == ""){
            document.location.href = document.location.protocol + "//" + document.location.host + "/Courses"
        }else{
            confirm(parseData.error);
        }
    }
    function ErrorDelete(data) {
        console.log("error");
        console.log(data);
    }
    function DeleteCourse() {
        var locationURL = document.location.protocol + "//" + document.location.host + "/EditCourse?id=${entity.idCourse}";
        $.ajax({
            url: locationURL,
            type: "DELETE",
            success: SuccessDelete,
            error: ErrorDelete
        });
    }
    function SuccessReguest(data) {
        var parseData = JSON.parse(data);
        if (parseData.error == ""){
            document.location.href = document.location.protocol + "//" + document.location.host + parseData.nextPage;
        }else{
            parseData.nameErrors.forEach(function(item) {
                var formGroup = $('input[name="'+item+'"]').parents('.input-group');
                formGroup.addClass('inputError').removeClass('inputSeccuss');
            });
            confirm(parseData.error);
        }
    }
    function ErrorReguest(data) {
        console.log("error");
        console.log(data);
    }
    function CheckValidFormData( id, value) {
        var errors = "";
        switch (id){
            case "id":
                break;
        }
        return errors;
    }
    $(function() {
        //при нажатии на кнопку с id="save"
        $('#save').click(function() {
            var validForm = true;
            //перебрать все элементы управления input
            $('input').each(function() {
                var id = $(this).attr('id');
                var value = $(this).val();
                var inputGroup = $(this).parents('.input-group');
                var errors = CheckValidFormData(id, value);
                if (this.checkValidity() && (errors == "")) {
                    inputGroup.addClass('inputSeccuss').removeClass('inputError');
                } else {
                    inputGroup.addClass('inputError').removeClass('inputSeccuss');
                    if (errors != ""){
                        confirm(errors);
                    }
                    validForm = false;
                }
            });
            if (validForm){
                var courseNameVal = $('#courseName').val();
                var courseDescriptionVal = $('#courseDesccription').val();
                var data = {
                    name: courseNameVal,
                    description: courseDescriptionVal,
                };
                var locationURL = document.location.protocol + "//" + document.location.host + "/EditCourse?id=${entity.idCourse}&check=1";
                $.ajax({
                    url: locationURL,
                    type: "PUT",
                    contentType: "application/json",
                    data: JSON.stringify(data),
                    success: SuccessReguest,
                    error: ErrorReguest
                });
            }
        });
    });
</script>