<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="entities" scope="request" type="java.util.List<hibernate.CourseEntity>"/>
<jsp:useBean id="user" scope="request" type="hibernate.UserEntity"/>

<html>
<% String title = "Courses"; %>
<%@ include file="resources/templates/headers.html" %>
<c:set var="servletName" value = "Courses?"/>
<body>
<%@ include file="resources/templates/header.html" %>
<div class="container main">
    <div class="courses">
        <h3><a href="EditCourse?add=true" class="design">Create</a></h3>
        <c:forEach var="course" items="${entities}">
            <div class="form-group">
                <c:set var="uid" value = "${user.id}"/>
                <p><h2><a <c:if test="${course.isSubscribed(uid)}">href="Course?id=${course.id}"</c:if> class="nameCourse">${course.name}</a></h2></p>
                <h6>Price: ${course.price}</h6>
                <h4>Level: ${course.level}</h4>
                <h4>Description: ${course.description}</h4>
                <div class="input-group">
                    <c:if test="${course.checkRights(user, ActionType.Edit)}">
                        <h3><a href="EditCourse?id=${course.id}" class="design">Edit</a></h3>
                    </c:if>
                    <c:if test="${!course.isSubscribed(user.id)}">
                        <h3><button type="button" id="subscribe" name="subscribe" onclick="SubscribeOnCourse(${course.id})">Subscribe</button></h3>
                    </c:if>
                </div>
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
        if (parseData.error === ""){
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