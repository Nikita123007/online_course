<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="solid 1px black">
    <tr>
        <th>
            FirstName
        </th>
        <th>
            SecondName
        </th>
        <th>
            Age
        </th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                ${user.firstName}
            </td>
            <td>
                ${user.secondName}
            </td>
            <td>
                ${user.age}
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
