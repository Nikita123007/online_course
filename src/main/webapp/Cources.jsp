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
</style>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <div class="cources">
        <table border="solid 1px black">
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Author</th>
            </tr>
            <c:forEach var="cource" items="${cources}">
                <tr>
                    <td><a href="Cource.jsp">${cource.name}</a></td>
                    <td>${cource.price}</td>
                    <td><a href="Author.jsp">${cource.author}</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>
