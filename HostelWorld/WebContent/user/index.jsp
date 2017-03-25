<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="hostelworld.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>首页</title>
<!-- Fuentes de Google -->
<link href='http://fonts.useso.com/css?family=Roboto:400,300,700'
	rel='stylesheet' type='text/css'>

<!-- Stylesheets -->
<link rel="stylesheet" href="../css/slide.css">
<link rel="stylesheet" href="../css/area.css">
<link rel="stylesheet" href="../css/button.css">

<!--[if lt IE 9]>
		<script src="js/html5shiv.min.js"></script>
	<![endif]-->

<!-- Scripts -->
<script src="../js/jquery.min.js"></script>

<!-- Stylesheets -->
<link rel="stylesheet" href="../css/area.css">
<link rel="stylesheet" href="../css/button.css">
<link rel="stylesheet" href="../css/font.css">
<link rel="stylesheet" href="../css/input.css">

<style>
body {
	background-color: #2C1201;
	color: #824220;
}

li {
	list-style-type: none;
}

#list-detail {
	margin: 0;
	padding: 0;
}

#list-detail li {
	float: left;
	POSITION: relative;
	display: none;
	padding-right: 11px;
	padding-top: 10px;
	display: block;
	width: 225px;
	height: 254px;
	text-align: center;
	background: url(../images/proBgl.jpg) no-repeat 0 0;
}

.pic_name {
	height: 36px;
	line-height: 36px;
	font-family: 微软雅黑;
	font-size: 16px;
	font-weight: 500;
}

.add {
	display: block;
	padding-top: 8px;
	font-family: 黑体;
	font-size: 18px;
	font-weight: 600;
	color: #C43F50;
}

#chart {
	width: 50px;
	height: 25px;
	line-height: 25px;
	position: absolute;
	top: 30px;
	right: 200px;
}
</style>
</head>
<body>
	<div class="common-container">
		<div class="top-menu">
			<div class="top-btn" style="margin-left: 180px;">
				<a href="index.html" class="menu" style="color: #C43F50;">首页</a>
			</div>
			<div class="top-btn">
				<a href="signup.jsp" target="_blank" class="menu">会员注册</a>
			</div>
			<div class="top-btn" style="border-right: solid 0px;">
				<a href="../hotel/signup.jsp" target="_blank" class="menu">我要开店</a>
			</div>
		</div>

		<div class="nevigation-long">
			<div class="usercard" style="width: 250px">
				<%
					User user = (User) session.getAttribute("user");
					if (user != null) {
				%>

				<a href="userinfo.jsp" class="sys-bold"
					style="color: white; border-right: solid 1px; padding-right: 15px;"><%=user.getId()%></a>
				<a href="#" onclick="logout()" class="sys-bold"
					style="color: white; padding-left: 10px;">Log out</a>
				<%
					} else {
				%>

				<a href="../admin/login.jsp" class="sys-bold"
					style="color: white; border-right: solid 1px; padding-right: 15px;">经理登录</a>
				<a href="../hotel/login.jsp" class="sys-bold"
					style="color: white; border-right: solid 1px; padding-right: 15px; padding-left: 10px;">客栈登录</a>
				<a href="login.jsp" class="sys-bold"
					style="color: white; padding-left: 10px;">会员登录</a>

				<%
					}
				%>
			</div>
		</div>

		<div
			style="background-color: #FBEFE1; width: 100%; height: 500px; overflow: hidden">
			<section class="slider-container">
			<ul id="slider" class="slider-wrapper">
				<li class="slide-current"><img src="../images/roomTwo_min.png"
					alt="Slider Imagen 1" style="width: 100%; height: 100%">
				</li>
			</ul>
			<!-- Controles -->
			<ul id="slider-controls" class="slider-controls"></ul>


			</section>
		</div>
	</div>
	<!-- Scripts -->
	<script src="../js/jquery.min.js"></script>
	<script src="../js/main.js"></script>
	</div>

</body>
</html>