<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Cources"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <p><h1><input type="text" name="lectionName" id="lectionName" value="${lection.name}"></h1></p>
    <h3><input type="text" name="lectionDescription" id="lectionDescription" value="${lection.description}"></h3>
    <h6><textarea cols="100" rows="25" name="lectionText" id="lectionText">${lection.text}</textarea></h6>
    <input type="button" name="editLection" id="editLection" value="Edit">
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>
