<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Courses"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <p><h1><input type="text" name="lectionName" id="lectionName" value="${entity.name}"></h1></p>
    <h6><textarea cols="100" rows="20" name="lectionText" id="lectionText">${entity.text}</textarea></h6>
    <c:if test="${!add}">
        <h2><button type="button" id="save" name="save">Save</button></h2>
        <h2><button type="button" id="delete" name="delete" onclick="DeleteEntity()">Delete</button></h2>
    </c:if>
    <c:if test="${add}">
        <h2><button type="button" id="create" name="create" onclick="CreateLection(${entity.course})">Create</button></h2>
    </c:if>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>
<script>
    function GetData() {
        return{
            name: $('#lectionName').val(),
            text: $('#lectionText').val()
        };
    }

    function SuccessChange(data) {
        var parseData = JSON.parse(data);
        if (parseData.error == ""){
            document.location.href = document.location.protocol + "//" + document.location.host + "/EditCourse?id=${entity.course}"
        }else{
            alert(parseData.error);
        }
    }
    function ErrorChange(data) {
        console.log("error");
        console.log(data);
    }
    function DeleteEntity() {
        var locationURL = document.location.protocol + "//" + document.location.host + "/LectionEdit?id=${entity.idLection}";
        $.ajax({
            url: locationURL,
            type: "DELETE",
            success: SuccessChange,
            error: ErrorChange
        });
    }
    function CreateLection(idCourse) {
        var locationURL = document.location.protocol + "//" + document.location.host + "/LectionEdit?parentId=${parentId}&add=true";
        $.ajax({
            url: locationURL,
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(GetData()),
            success: SuccessChange,
            error: ErrorChange
        });
    }
    function SuccessReguest(data) {
        var parseData = JSON.parse(data);
        if (parseData.error == ""){
            document.location.href = document.location.protocol + "//" + document.location.host + parseData.nextPage;
        }else{
            parseData.nameErrors.forEach(function(item) {
                var formGroup = $('input[name="'+item+'"]').parents('.input-group');
                formGroup.addClass('inputError').removeClass('inputSuccess');
            });
            alert(parseData.error);
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
                    inputGroup.addClass('inputSuccess').removeClass('inputError');
                } else {
                    inputGroup.addClass('inputError').removeClass('inputSuccess');
                    if (errors != ""){
                        confirm(errors);
                    }
                    validForm = false;
                }
            });
            if (validForm){
                var locationURL = document.location.protocol + "//" + document.location.host + "/LectionEdit?id=${entity.idLection}";
                $.ajax({
                    url: locationURL,
                    type: "PUT",
                    contentType: "application/json",
                    data: JSON.stringify(GetData()),
                    success: SuccessReguest,
                    error: ErrorReguest
                });
            }
        });
    });
</script>
