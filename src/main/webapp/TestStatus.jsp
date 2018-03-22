<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Cources"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <p><h1>${test.name}</h1></p>
    <p><h2>${test.status}</h2></p>
    <c:if test="${test.status=='Completed'}">
        <p><h2>Mark: ${test.mark}</h2></p>
    </c:if>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>