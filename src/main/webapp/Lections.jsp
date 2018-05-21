<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="entities" scope="request" type="java.util.List<hibernate.CourseEntity>"/>
<jsp:useBean id="parentId" scope="request" type="java.lang.Integer"/>

<html>
<% String title = "Lections"; %>
<%@ include file="resources/templates/headers.html" %>
<c:set var="servletName" value = "Lections?parentId=${parentId}&"/>
<body>
<%@ include file="resources/templates/header.jsp" %>
<div class="container main">
    <div class="courses">
        <c:forEach var="entity" items="${entities}">
            <div class="form-group">
                <h2><a href="Lection?id=${entity.id}">${entity.name}</a></h2>
            </div>
            <hr>
        </c:forEach>
        <%@ include file="resources/templates/PrevNext.jsp" %>
    </div>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>