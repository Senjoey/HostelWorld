<%@page import="hostelworld.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="HotelList" type="hostelworld.action.HotelListBean"
	scope="session"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>线上预定</title>
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
<%
	User user = (User) session.getAttribute("user");
%>
<body>
	<div class="common-container">
		<div class="top-menu">
			<div class="top-btn" style="margin-left: 180px;">
				<a href="index.jsp" class="menu">首页</a>
			</div>
			<div class="top-btn">
				<a href="#" class="menu" style="color: #C43F50;">线上预定</a>
			</div>
			<div class="top-btn" style="border-right: solid 0px;">
				<a href="userinfo.jsp" class="menu">个人中心</a>
			</div>
		</div><!-- .top-menu ends -->
		<div class="nevigation-long">
			<div class="nevigation-page"><span class="title-ch-bold">线上预定</span></div>
            <div class="usercard">
                <a href="userinfo.jsp" class="sys-bold" style="color:white;border-right:solid 1px;padding-right:15px;"><%=user.getId() %></a> 
                <a href="#" onclick="logout()" class="sys-bold" style="color:white;padding-left:10px;">Log out</a>              
            </div>
		</div><!-- .nevigation-long ends -->
		<div style="background-color: #FBEFE1;width:100%;height:500px;">
			<div style="width:40%;margin:0 auto;padding-top:100px;">
				<ul>
					<li>
						<span class="title-ch-bold">酒店选择：</span>
						<select id="hotel" style="width:250px;height:30px;">
						<%for(int i = 0; i < HotelList.getHotelList().size(); i++){ %>
						<option value="<%=HotelList.getHotel(i).getHotelId()%>"><%=HotelList.getHotel(i).getName()%></option>
						<%} %>
						</select>
					</li>
					<li>
						<span class="title-ch-bold" style="margin-top:30px;"> 入住日期：</span>
						<input id="planDate" class="inputArea" style="margin-top:30px;width:250px;height:30px" type="text" name="date" onClick="WdatePicker()" onChange="setTaskTime()" placeholder="点击选择日期"/>
					</li>
				</ul>
				<a style="margin-right:150px;margin-top:40px;" class="calBtn" href="#" onclick="findHotel()">确  认</a>
			</div>
		</div><!-- main body ends -->
	</div>
	<!-- .common-container ends -->
	<script language="javascript" type="text/javascript" src="../css/My97DatePicker/WdatePicker.js"></script>
	<script src="../js/jquery.min.js"></script>
	<script src="../js/hotelchoose.js"></script>
</body>
</html>