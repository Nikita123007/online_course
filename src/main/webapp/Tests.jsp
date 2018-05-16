<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Tests"; %>
<%@ include file="resources/templates/headers.html" %>
<c:set var="servletName" value = "Tests?parentId=${parentId}&"/>
<body>
<style>
    .courses{
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
    .nameCourse{
        font-size: 32px;
        display: block;
        text-align: center;
    }
    .design {
        display: inline-block; /* Строчно-блочный элемент */
        padding: 5px 20px; /* Добавляем поля */
        text-decoration: none; /* Убираем подчёркивание у ссылки */
        cursor: pointer; /* Курсор в виде руки */
        background: #deefff; /* Фон для браузеров, не поддерживающих градиент */
        /* Градиент */
        background: -moz-linear-gradient(top, #deefff 0%, #98bede 100%);
        background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#deefff), color-stop(100%,#98bede));
        background: -webkit-linear-gradient(top, #deefff 0%,#98bede 100%);
        background: -o-linear-gradient(top, #deefff 0%,#98bede 100%);
        background: -ms-linear-gradient(top, #deefff 0%,#98bede 100%);
        background: linear-gradient(top, #deefff 0%,#98bede 100%);
        border-radius: 10px; /* Скругляем уголки */
        border: 1px solid #008; /* Добавляем синюю рамку */
        font: 12px/1 Arial, sans-serif; /* Рубленый шрифт */
        color: #2c539e; /* Цвет текста и ссылки */
    }
</style>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <div class="courses">
        <c:forEach var="entity" items="${entities}">
            <div class="form-group">
                <p><h2><a <c:if test="${!entity.isTestCompleted(user.idUser)}">href="TestRun?id=${entity.idTest}"</c:if>>${entity.name}</a></h2></p>
            </div>
            <hr>
        </c:forEach>
        <%@ include file="resources/templates/PrevNext.jsp" %>
    </div>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>