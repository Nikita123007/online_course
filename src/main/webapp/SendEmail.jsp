<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Send email"; %>
<%@ include file="resources/templates/headers.html" %>
<script type="text/javascript" src="resources/js/EditEntity.js"></script>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <form class="form-horizontal" onsubmit="return false;">
        <label for="emails">Emails</label>
        <input disabled id="emails" type="text" readonly value="<c:forEach var="user" items="${users}">${user.email};</c:forEach>"><br>
        <label for="title">Title</label>
        <p><h2><input type="text" name="title" id="title" placeholder="Title" autofocus required value=""></h2></p>
        <label for="text">Text</label>
        <p><textarea cols="100" rows="20" name="text" id="text" placeholder="Email text" required></textarea></p>
        <div class="input-group">
            <h2><button type="button" id="send" name="send" onclick="Send()">Send</button></h2>
            <h2><button type="button" id="back" name="back" onclick="Back()">Back</button></h2>
        </div>
    </form>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>

<script>
    function Back(){
        document.location.href = document.location.protocol + "//" + document.location.host + "/Users"
    }

    function SuccessChange(data) {
        var parseData = JSON.parse(data);
        if (parseData.error == ""){
            document.location.href = document.location.protocol + "//" + document.location.host + parseData.nextPage
        }
        else{
            confirm(parseData.error);
        }
    }

    function ErrorChange(data) {
        console.log("error");
        console.log(data);
    }

    function Send(){
        if (!Validate()){
            return;
        }
        var locationURL = "/SendEmail?id=${idString}";
        $.ajax({
            url: locationURL,
            type: "Post",
            contentType: "application/json",
            data: JSON.stringify({
                    text: $('#text').val(),
                    title: $('#title').val()
                }),
            success: SuccessChange,
            error: ErrorChange
        });
    }

    function Validate(){
        var validForm = true;
        $('input').each(function() {
            var checkValid = this.checkValidity();
            validForm = validForm && checkValid;
            if (checkValid){
                this.addClass('inputSuccess').removeClass('inputError');
            }else{
                this.addClass('inputError').removeClass('inputSuccess');
            }
        });
        return validForm;
    }
</script>