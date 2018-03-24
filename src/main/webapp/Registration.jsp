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
<style>
    .form form {
        width: 800px;
        margin: 0 auto;
        padding-top: 20px;
    }
</style>
<div class="container main">
    <h2>Registration</h2>
    <div class="form">
        <form class="form-horizontal" role="form" method="POST" action="Registration" onsubmit="return false;">
            <div class="form-group">
                <div class="form-group">
                    <label for="secondName" class="col-sm-2 control-label">Suname</label>
                    <div class="col-sm-10">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Suname" name="secondName" id="secondName" pattern="[a-zA-Z]{2,30}">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">Name</label>
                    <div class="col-sm-10">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Name" name="name" id="name" pattern="[a-zA-Z]{2,30}">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Email" name="email" id="email" pattern="\S+@[a-z]+\.[a-z]+">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone" class="col-sm-2 control-label">Phone</label>
                    <div class="col-sm-10">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Phone" name="phone" id="phone" pattern="^(80|\+375)\d{9}">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Gender</label>
                    <div class="input-group">
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="genderMan" value="male"> Man
                            </label>
                        </div>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="genderWoman" value="female"> Woman
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-10">
                        <div class="input-group">
                            <input type="password" class="form-control" placeholder="Password" name="password" id="password" pattern="[a-zA-Z0-9_]{5,15}">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="passwordConfirm" class="col-sm-2 control-label">ConfirmPassword</label>
                    <div class="col-sm-10">
                        <div class="input-group">
                            <input type="password" class="form-control" placeholder="ConfirmPassword" name="passwordConfirm" id="passwordConfirm" pattern="[a-zA-Z0-9_]{5,15}">
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
            case "passwordConfirm":
                var passwordVal = $('#password').val();
                if (value != passwordVal){
                    errors += "Confirm password and password do not match. ";
                }
                break;
            case "genderWoman":
                if(!$('#'+id).prop('checked')) {
                    if(!$('#genderMan').prop('checked')){
                        errors += "You shoud choose one gender.";
                    }
                }
                break;
            case "license":
                if(!$("#"+id).parents('.form-group').prop('hidden')) {
                    if(!$('#'+id).prop('checked')){
                        errors += "You should adree with lecense.";
                    }
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
                var nameVal = $('#name').val();
                var secondNameVal = $('#secondName').val();
                var emailVal = $('#email').val();
                var phoneVal = $('#phone').val();
                var genderVal = $('input[name="gender"]').val();
                var passwordVal = $('#password').val();
                var confirmPasswordVal = $('#passwordConfirm').val();
                var licenseVal = $('#license').prop('checked') ? "true" : "false";
                var locationURL = document.location.protocol + "//" + document.location.host + "/Registration";
                console.log(locationURL);
                $.ajax({
                    url: locationURL,
                    type: "POST",
                    data:({name: nameVal, secondName: secondNameVal, email: emailVal, phone: phoneVal, gender: genderVal, password: passwordVal, confirmPassword: confirmPasswordVal, license: licenseVal}),
                    success: SuccessReguest,
                    error: ErrorReguest
                });
            }
        });
    });
</script>
