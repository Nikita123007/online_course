<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Cources"; %>
<%@ include file="resources/templates/headers.html" %>
<body>
<style>
    .cources{
        margin: auto;
        align-content: center;
    }
    .price{
        float: right;
        margin-right: 10%;
    }
    .form-group{
        font-size: 24px;
    }
    .nameCource{
        font-size: 32px;
        display: block;
        text-align: center;
    }
</style>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <div class="cources">
        <c:forEach var="cource" items="${courcesSelf}">
            <div class="form-group">
                <a href="Cource?id=${cource.id}" class="nameCource">${cource.name}</a>
                <h6>${cource.description}</h6>
                <h4>${cource.author}</h4>
                <button id="editCource" type="button" value="${cource.id}">Edit</button><text class="price" id="price">${cource.price}</text>
            </div>
        </c:forEach>
        <c:forEach var="cource" items="${courcesSelf}">
            <div class="form-group">
                <p><h2><a href="Cource?id=${cource.id}">${cource.name}</a></h2></p>
                <h6>${cource.description}</h6>
                <h4>${cource.author}</h4>
                <button id="subscribeCource" type="button" value="${cource.id}">Subscribe</button><text class="price" id="price">${cource.price}</text>
            </div>
        </c:forEach>
    </div>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>
