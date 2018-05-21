<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<title>Cube</title>
<meta charset="utf-8">
<link rel="stylesheet" href="resources/css/layout.css">
<body>
<%@ include file="resources/templates/header.jsp" %>
<style type="text/css">
    body{
        margin: 0px;
        padding: 0px;
    }

    #holder {
        width: 200px;
        height: 200px;
        position: absolute;
        right: 0%;
        bottom: 0%;
        left: 40%;
        top: 1%;
        -webkit-animation: animnekit 10s infinite linear;
    }

    .box {
        position: relative;
        margin: 0 auto;
        height: 200px;
        width: 200px;
        top: 10px;
        -webkit-animation: spin 20s infinite linear;
        -moz-animation: spin 20s infinite linear;
        -webkit-transform-style: preserve-3d;
        -moz-transform-style: preserve-3d;
    }

    .box div {
        position: absolute;
        height: 200px;
        width: 200px;
        box-shadow: 0px 0px 1px rgba(0, 0, 0, 0.5);
        -webkit-animation:rotate 15s infinite linear;
        -moz-animation:rotate 15s infinite linear;
    }

    .box div img {width:200px; height:200px;
        border-radius:200px;
        -webkit-animation:rot 10s infinite linear;
        -moz-animation:rot 10s infinite linear;
        padding: 0;
    }

    .box div.side1 {
        background:rgba( 213, 62, 7, 0.8);
        -webkit-transform:translateZ(100px);
        -moz-transform:translateZ(100px);
    }
    .box div.side2 {
        background:rgba( 127, 255, 212, 0.8);
        -webkit-transform:rotateY(90deg) translateZ(100px);
        -moz-transform:rotateY(90deg) translateZ(100px);
    }
    .box div.side3 {
        background:rgba( 255, 219, 88, 0.8);
        -webkit-transform:rotateY(180deg) translateZ(100px);
        -moz-transform:rotateY(180deg) translateZ(100px);
    }
    .box div.side4 {
        background:rgba( 153, 17, 153, 0.8);
        -webkit-transform:rotateY(-90deg) translateZ(100px);
        -moz-transform:rotateY(-90deg) translateZ(100px);
    }
    .box div.side5 {
        background:rgba( 0, 71, 171, 0.8);
        -webkit-transform:rotateX(-90deg) translateZ(100px) rotate(180deg);
        -moz-transform:rotateX(-90deg) translateZ(100px) rotate(180deg);
    }
    .box div.side6  {
        background:rgba( 0, 171, 147, 0.8);
        -webkit-transform:rotateX(90deg) translateZ(100px);
        -moz-transform:rotateX(90deg) translateZ(100px);
    }

    @-moz-keyframes spin {
        0% {-moz-transform: rotateX(0deg) rotateY(0deg);}
        100% {-moz-transform: rotateX(1080deg) rotateY(360deg);}
    }
    @-webkit-keyframes spin {
        0% {-webkit-transform: rotateX(0deg) rotateY(0deg);}
        100% {-webkit-transform: rotateX(1080deg) rotateY(360deg);}
    }
    @-moz-keyframes rot {
        0% {-moz-transform: rotate(0deg);}
        100% {-moz-transform: rotate(360deg);}
    }
    @-webkit-keyframes rot {
        0% {-webkit-transform: rotate(0deg);}
        100% {-webkit-transform: rotate(360deg);}
    }
    @-webkit-keyframes animnekit {
        0% {top: 10%;left: 40%;}
        25% {top: 32%;left: 6%;}
        50% {top: 54%;left: 40%;}
        75% {top: 32%;left: 80%;}
        100%{top: 10%; left: 40%;}
    }

</style>
<div id="holder">
    <div class="box">
        <div class="side1"><img src="resources/images/1_1.jpg" /></div>
        <div class="side2"><img src="resources/images/1_2.jpg" /></div>
        <div class="side3"><img src="resources/images/1_3.jpg" /></div>
        <div class="side4"><img src="resources/images/1_4.jpg" /></div>
        <div class="side5"><img src="resources/images/1_5.jpg" /></div>
        <div class="side6"><img src="resources/images/1_6.jpg" /></div>
    </div>
</div>
<%@ include file="resources/templates/footer.html" %>
</body>
</html>