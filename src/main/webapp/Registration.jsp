<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Registration"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<style>
    .form form {
        width: 800px;
        margin: 0 auto;
        padding-top: 20px;
        margin-bottom: 80px;
    }
</style>
<div class="container main">
    <h2>Registration</h2>
    <div class="form">
        <form class="form-horizontal" role="form" method="POST" action="Registration" onsubmit="return false;">
            <div class="form-group">
                <div class="form-group">
                    <label for="login" class="col-sm-2 control-label">Login</label>
                    <div class="col-sm-10">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Login" name="login" id="login" autofocus required="required" pattern="[a-zA-ZА-Яа-я_0-9]{2,30}">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="email.email@gmail.com" name="email" id="email" required="required" pattern="\S+@[a-z]+\.[a-z]+">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-10">
                        <div class="input-group">
                            <input type="password" class="form-control" placeholder="Password" name="password" id="password" required="required" pattern="[a-zA-ZА-Яа-я_0-9]{5,15}">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="passwordConfirm" class="col-sm-2 control-label">ConfirmPassword</label>
                    <div class="col-sm-10">
                        <div class="input-group">
                            <input type="password" class="form-control" placeholder="Password" name="passwordConfirm" required="required" id="passwordConfirm" pattern="[a-zA-ZА-Яа-я_0-9]{5,15}">
                        </div>
                    </div>
                </div>
                <div class="form-group" hidden="hidden">
                    <div class="col-sm-10">
                        <div class="input-group">
                            <label class="checkbox-inline">
                                <input type="checkbox" id="license" name="license" value="agree">  I agree with <a href="#">conditions</a>.
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <button id="registration" type="submit" class="btn btn-default btn-sm">Registration</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>
<script>
    function SuccessRequest(data) {
        var parseData = JSON.parse(data);
        if (parseData.error === ""){
            document.location.href = document.location.protocol + "//" + document.location.host + parseData.nextPage;
        }else{
            parseData.nameErrors.forEach(function(item) {
                var formGroup = $('input[name="'+item+'"]').parents('.input-group');
                formGroup.addClass('inputError').removeClass('inputSuccess');
            });
            confirm(parseData.error);
        }
    }
    function ErrorRequest(data) {
        console.log("error");
        console.log(data);
    }
    function CheckValidFormData( id, value) {
        var errors = "";
        switch (id){
            case "passwordConfirm":
                var passwordVal = $('#password').val();
                if (value !== passwordVal){
                    errors += "Confirm password and password do not match. ";
                }
                break;
        }
        return errors;
    }
    $(function() {
        //при нажатии на кнопку с id="save"
        $('#registration').click(function() {
            var validForm = true;
            //перебрать все элементы управления input
            $('input').each(function() {
                var id = $(this).attr('id');
                var value = $(this).val();
                var inputGroup = $(this).parents('.input-group');
                var errors = CheckValidFormData(id, value);
                if (this.checkValidity() && (errors === "")) {
                    inputGroup.addClass('inputSuccess').removeClass('inputError');
                } else {
                    inputGroup.addClass('inputError').removeClass('inputSuccess');
                    if (errors !== ""){
                        confirm(errors);
                    }
                    validForm = false;
                }
            });
            if (validForm){
                var loginVal = $('#login').val();
                var emailVal = $('#email').val();
                var passwordVal = $('#password').val();
                var locationURL = document.location.protocol + "//" + document.location.host + "/Registration";
                console.log(locationURL);
                $.ajax({
                    url: locationURL,
                    type: "POST",
                    data:({login: loginVal, email: emailVal, password: passwordVal}),
                    success: SuccessRequest,
                    error: ErrorRequest
                });
            }
        });
    });
</script>
