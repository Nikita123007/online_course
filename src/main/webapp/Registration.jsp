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
<h2>Регистрация</h2>
<style>
    .form form {
        width: 800px;
        margin: 0 auto;
        padding-top: 20px;
    }
</style>
<div class="container main">
    <div class="form">
        <form class="form-horizontal" role="form" method="POST" action="Registration">
            <div class="form-group">
                <div class="form-group">
                    <label for="secondName" class="col-sm-2 control-label">Suname</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="Suname" name="secondName" id="secondName">
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">Name</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="Name" name="name" id="name">
                    </div>
                </div>
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="Email" name="email" id="email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="phone" class="col-sm-2 control-label">Phone</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="Phone" name="phone" id="phone">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">Gender</label>
                    <div class="col-sm-10">
                        <label class="radio-inline">
                            <input type="radio" name="genderRadios" value="male" checked> Man
                        </label>
                    </div>
                    <div class="col-sm-10">
                        <label class="radio-inline">
                            <input type="radio" name="genderRadios" value="female"> Woman
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" placeholder="Password" name="password" id="password">
                    </div>
                </div>
                <div class="form-group">
                    <label for="passwordConfirm" class="col-sm-2 control-label">ConfirmPassword</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" placeholder="ConfirmPassword" name="passwordConfirm" id="passwordConfirm">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <label class="checkbox-inline">
                            <input type="checkbox" name="license" value="agree">  I agree with <a href="#">conditions</a>.
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <button type="submit" class="btn btn-default btn-sm">Registration</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>
