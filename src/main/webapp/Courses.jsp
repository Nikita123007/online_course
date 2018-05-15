<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<% String title = "Courses"; %>
<%@ include file="resources/templates/headers.html" %>
<c:set var="servletName" value = "Courses?"/>
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
        <h3><a href="EditCourse?add=true" class="design">Create</a></h3>
        <c:forEach var="course" items="${entities}">
            <div class="form-group">
                <p><h2><a <c:if test="${course.isSubscribed(user.idUser)}">href="Course?id=${course.idCourse}"</c:if> class="nameCourse">${course.name}</a></h2></p>
                <h6>Price: ${course.price}</h6>
                <h4>Level: ${course.level}</h4>
                <h4>Description: ${course.description}</h4>
                <c:if test="${course.checkRights(user, ActionType.Edit)}">
                    <h3><a href="EditCourse?id=${course.idCourse}" class="design">Edit</a></h3>
                </c:if>
                <c:if test="${!course.isSubscribed(user.idUser)}">
                    <h3><button type="button" id="subscribe" name="subscribe" onclick="SubscribeOnCourse(${course.idCourse})">Subscribe</button></h3><text class="price" id="price">${course.price}</text>
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
    function SuccessChange(data) {
        var parseData = JSON.parse(data);
        if (parseData.error == ""){
            document.location.reload();
        }else{
            confirm(parseData.error);
        }
    }
    function ErrorChange(data) {
        console.log("error");
        console.log(data);
    }
    function SubscribeOnCourse(idCourse) {
        var locationURL = document.location.protocol + "//" + document.location.host + "/Courses";
        $.ajax({
            url: locationURL,
            type: "POST",
            data:({idCourse: idCourse}),
            success: SuccessChange,
            error: ErrorChange
        });
    }
</script>