<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="entities" scope="request" type="java.util.List<hibernate.UserEntity>"/>

<html>
<% String title = "Users"; %>
<%@ include file="resources/templates/headers.html" %>
<c:set var="servletName" value = "Users?"/>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <div class="courses">
        <button type="button" id="email" name="email" onclick="SendEmail()">Send emails</button>
        <c:forEach var="user" items="${entities}">
            <div class="form-group">
                <h2><input type="checkbox" value="${user.id}"><a href="SendEmail?id=${user.id}">${user.name}</a></h2>
                <h4>Email: ${user.email}</h4>
                <c:if test="${user.admin()}">
                    <h4>Role: Admin</h4>
                </c:if>
                <c:if test="${!user.admin()}">
                    <h4>Role: User</h4>
                </c:if>
            </div>
            <hr>
        </c:forEach>
        <%@ include file="resources/templates/PrevNext.jsp" %>
    </div>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>
<script>
    function SendEmail() {
        var idStr = "";
        var inputs = document.getElementsByTagName("input");
        for(var i = 0; i < inputs.length; i++) {
            if(inputs[i].type === "checkbox" && inputs[i].checked) {
                idStr = idStr.concat(inputs[i].value).concat("_");
            }
        }
        document.location.href = document.location.protocol + "//" + document.location.host + "/SendEmail?id=" + idStr;
    }
</script>
