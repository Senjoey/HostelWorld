<%@page import="hostelworld.model.Room.RoomType"%>
<%@page import="hostelworld.model.PlanRoom.RoomState"%>
<%@page import="hostelworld.model.Room"%>
<%@page import="hostelworld.action.RoomListBean"%>
<%@page import="hostelworld.model.RoomCart"%>
<%@page import="hostelworld.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="RoomList" type="hostelworld.action.RoomListBean"
	scope="session"></jsp:useBean>
<jsp:useBean id="roomitem" class="hostelworld.model.Room" scope="page"></jsp:useBean>
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
	position: relative;
	display: block;
	width: 700px;
	height: 350px;
	
	/* background: url(../images/proBgl.jpg) no-repeat 0 0; */
}

.pic_name {
	height: 36px;
	line-height: 36px;
	font-family: 微软雅黑;
	font-size: 16px;
	font-weight: 500;
}

.add {
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
	right: 50px;
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
				<a href="hotelchoose.jsp" class="menu" style="color: #C43F50;">线上预定</a>
			</div>
			<div class="top-btn" style="border-right: solid 0px;">
				<a href="userinfo.jsp" class="menu">个人中心</a>
			</div>
		</div>
		<!-- .top-menu ends -->
		<div class="nevigation-long">
			<div class="nevigation-page">
				<span class="title-ch-bold">线上预定</span>
			</div>
			<div class="usercard">
				<a href="userinfo.jsp" class="sys-bold"
					style="color: white; border-right: solid 1px; padding-right: 15px;"><%=user.getId()%></a>
				<a href="#" onclick="onhotelLogout()" class="sys-bold"
					style="color: white; padding-left: 10px;">Log out</a>

			</div>
		</div>
		<div style="background-color: #FBEFE1; width: 100%; overflow: auto">
			<div class="nevigation-left">
				<ul>
					<li><a href="#" onclick="showBigroom()"
						class="sys-ch">大床房</a></li>
					<li><a href="#" onclick="showStandardroom()"
						class="sys-ch">标准间</a></li>
					<li><a href="#" onclick="showBusinessrrom()"
						class="sys-ch">商务间</a></li>
				</ul>
			</div>
			<!-- .nevigation-left ends -->
			<div class="main-container" style="height: 1500px;">
				<div id="big-list" class="sub-container" style="visibility: ">
					<div class="right_nr">
						<div id="subtitle" class="sys-ch">舒适温馨</div>
						<span></span>
					</div>
					
					<%
							for (int i = 0; i < RoomList.getRoomList().size(); i++) {
								Room room = RoomList.getRoom(i);
								if(room.getType() == RoomType.bigBedroom){
									pageContext.setAttribute("roomitem", RoomList.getRoom(i));
								
						%>
						<li><img src="../images/<%=room.getId()%>.png"
							border="0" width="480" height="350"> <span style="padding-bottom:100px">
							<a href="#" onclick="orderRoom('<%=room.getId()%>')" class="calBtn" style="float:clear;margin-right:295px;margin-top:150px">预定房间</a></span>
						</li>
							
						<%
								}
							}
						%>
				</div>
				
				<div id="standard-list" class="sub-container" style="visibility: hidden">
					<div class="right_nr">
						<div id="subtitle" class="sys-ch">超级划算</div>
						<span></span>
					</div>
					
					<ul id="list-detail">
						<%
							for (int i = 0; i < RoomList.getRoomList().size(); i++) {
								Room room = RoomList.getRoom(i);
								if(room.getType() == RoomType.standardRoom){
									pageContext.setAttribute("roomitem", RoomList.getRoom(i));
								
						%>
						<li><img src="../images/<%=room.getId()%>.png"
							border="0" width="480" height="350"> <span style="padding-bottom:100px">
							<a href="#" onclick="orderRoom('<%=room.getId()%>')" class="calBtn" style="margin-top:140px;">预定房间</a></span>
						</li>
							
						<%
								}
							}
						%>
					</ul>
				</div>
				
				<div id="business-list" class="sub-container" style="visibility:hidden">
					<div class="right_nr">
						<div id="subtitle" class="sys-ch">适合办公</div>
						<span></span>
					</div>
					
					<%
							for (int i = 0; i < RoomList.getRoomList().size(); i++) {
								Room room = RoomList.getRoom(i);
								if(room.getType() == RoomType.businessRoom){
									pageContext.setAttribute("roomitem", RoomList.getRoom(i));
								
						%>
						<li><img src="../images/<%=room.getId()%>.png"
							border="0" width="480" height="350"> <span style="padding-bottom:100px;">
							<a href="#" onclick="orderRoom('<%=room.getId()%>')" class="calBtn" style="margin-top:140px;float:clear;margin-right:295px">预定房间</a></span>
						</li>
							
						<%
								}
							}
						%>
				</div>
				
			</div>
			<!-- main body ends -->
		</div>
	</div>
	<!-- .common-container ends -->


	<script src="../js/jquery.min.js"></script>
	<script src="../js/onlinehotel.js"></script>
</body>

</html>