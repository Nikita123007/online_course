<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Login"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<style>
    .form form {
        width: 300px;
        margin: 0 auto;
        padding-top: 20px;
        margin-top: 160px;
    }
    .hrefReg{
        margin-left: 25px;
    }
</style>
<div class="container main">
<div class="form">
    <form class="form-horizontal" role="form" method="POST" action="Login" onsubmit="return false;">
            <div class="form-group">
                <label for="login" class="col-sm-2 control-label">Login</label>
                <div class="col-sm-10">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Login" name="login" id="login" autofocus required="required" pattern=".{5,20}">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <div class="input-group">
                        <input type="password" class="form-control" placeholder="Password" name="password" id="password" required="required" pattern="[a-zA-Z0-9_]{5,15}">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button id="enterLogin" type="submit" class="btn btn-default btn-sm">Login</button>
                    <a class="hrefReg" href="Registration">Registration</a>
                </div>
            </div>
    </form>
</div>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>
<script>
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
            case "login":
                break;
            case "password":
                break;
        }
        return errors;
    }
    $(function() {
        //при нажатии на кнопку с id="save"
        $('#enterLogin').click(function() {
            var validForm = true;
            //перебрать все элементы управления input
            $('input').each(function() {
                var id = $(this).attr('id');
                var value = $(this).val();
                var inputGroup = $(this).parents('.input-group');
                var errors = CheckValidFormData(id, value);
                if (this.checkValidity() && (errors == "") {
                    inputGroup.addClass('inputSeccuss').removeClass('inputError');
                } else {
                    inputGroup.addClass('inputError').removeClass('inputSeccuss');
                    if (errors != "") {
                        confirm(errors);
                    }
                    validForm = false;
                }
            });
            if (validForm){
                var loginVal = $('#login').val();
                var passwordVal = $('#password').val();
                var locationURL = document.location.protocol + "//" + document.location.host + "/Login";
                console.log(locationURL);
                $.ajax({
                    url: locationURL,
                    type: "POST",
                    data:({login: loginVal, password: passwordVal}),
                    success: SuccessReguest,
                    error: ErrorReguest
                });
            }
        });
    });
</script>