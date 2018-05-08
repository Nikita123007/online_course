<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Courses"; %>
<%@ include file="resources/templates/headers.html" %>
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
        <c:forEach var="course" items="${coursesSelf}">
            <div class="form-group">
                <p><h2><a href="/" class="nameCourse">${course.name}</a></h2></p>
                <h6>Price: ${course.price}</h6>
                <h4>Level: ${course.level}</h4>
                <h4>Description: ${course.description}</h4>
                <a href="EditCourse?id=${course.idCourse}" class="design">Edit</a>
            </div>
            <hr>
        </c:forEach>
        <c:forEach var="course" items="${subscribeCourses}">
            <div class="form-group">
                <p><h2><a href="Course?id=${course.idCourse}" class="nameCourse">${course.name}</a></h2></p>
                <h4>Level: ${course.level}</h4>
                <h4>Description: ${course.description}</h4>
            </div>
            <hr>
        </c:forEach>
        <c:forEach var="course" items="${courses}">
            <div class="form-group">
                <p><h2><a href="/" class="nameCourse">${course.name}</a></h2></p>
                <h4>Level: ${course.level}</h4>
                <h4>Description: ${course.description}</h4>
                <a href="Course?id=${course.idCourse}" class="design">Subscribe</a><text class="price" id="price">${course.price}</text>
            </div>
            <hr>
        </c:forEach>
    </div>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>
