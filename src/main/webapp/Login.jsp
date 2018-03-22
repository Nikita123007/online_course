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
</style>
<div class="container main">
<div class="form">
    <form class="form-horizontal" role="form" method="POST" action="Login">
        <div class="form-group">
            <div class="form-group">
                <label for="login" class="col-sm-2 control-label">Login</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Login" name="login" id="login">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" placeholder="Password" name="password" id="password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default btn-sm">Login</button>
                </div>
            </div>
            <div class="form-group">
                <a href="Registration.jsp">Registration</a>
            </div>
        </div>
    </form>
</div>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>