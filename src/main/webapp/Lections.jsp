<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Lections"; %>
<%@ include file="resources/templates/headers.html" %>
<c:set var="servletName" value = "Lections?parentId=${parentId}&"/>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <div class="courses">
        <c:forEach var="entity" items="${entities}">
            <div class="form-group">
                <p><h2><a href="Lection?id=${entity.idLection}">${entity.name}</a></h2></p>
            </div>
            <hr>
        </c:forEach>
        <%@ include file="resources/templates/PrevNext.jsp" %>
    </div>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>