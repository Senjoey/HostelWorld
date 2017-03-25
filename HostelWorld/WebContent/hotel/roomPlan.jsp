<%@page import="hostelworld.model.Room.RoomType"%>
<%@page import="hostelworld.model.RoomPlan"%>
<%@page import="hostelworld.model.Hotel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="HotelList" type="hostelworld.action.HotelListBean"
	scope="session"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>房间计划</title>

<!-- Stylesheets -->
<link rel="stylesheet" href="../css/area.css">
<link rel="stylesheet" href="../css/button.css">
<link rel="stylesheet" href="../css/font.css">
<link rel="stylesheet" href="../css/input.css">
<link rel="stylesheet" href="../css/table.css">

<style>
body {
	background-color: #2C1201;
	color: #824220;
}

li {
	list-style-type: none;
}

* {
	margin: 0;
	padding: 0;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}
</style>

</head>
<%
	Hotel hotel = (Hotel) session.getAttribute("hotel");
%>
<body>
	<div class="common-container">
		<div class="top-menu">
			<div class="top-btn" style="margin-left: 180px">
				<a href="../user/index.jsp" class="menu">首页</a>
			</div>
			<div class="top-btn">
				<a href="" class="menu" style="color: #C43F50;">房间计划</a>
			</div>
			<div class="top-btn">
				<a href="hotelinfo.jsp" class="menu">客栈中心</a>
			</div>
			<div class="top-btn" style="border-right: solid 0px;">
				<a href="statistics.jsp"" class="menu">统计信息</a>
			</div>
		</div>
		<!-- .top-menu ends -->

		<div class="nevigation-long">
			<div class="nevigation-page">
				<span class="title-ch-bold">房间计划</span>
			</div>
			<div class="usercard">
				<a href="hotelinfo.jsp" class="sys-bold"
					style="color: white; border-right: solid 1px; padding-right: 15px;"><%=hotel.getHotelId()%></a>
				<a href="#" onclick="logout()" class="sys-bold"
					style="color: white; padding-left: 10px;">Log out</a>
			</div>
		</div>
		<!-- .nevigation-long ends -->

		<div style="background-color: #FBEFE1; width: 100%; overflow: auto">
			<div class="nevigation-left">
				<ul>
					<li><a href="#" onclick="showPlan()" class="sys-ch">销售计划</a></li>
					<li><a href="#" onclick="showCreatePlan()" class="sys-ch">新建计划</a></li>
				</ul>
			</div>
			<!-- .navigation-left of main body ends -->

			<div class="main-container" style="height: 1000px;">
				<div id="plan" class="sub-container" style="visibility: hidden">
					<p class="title-ch-bold">销售计划</p>
				</div>

				<div id="createPlan" class="sub-container" style="visibility:">
					<p class="title-ch-bold" style="margin-bottom: 20px; margin-top:5px">新建计划</p>
					<%
						RoomPlan plan = new RoomPlan();
						plan.initRoom();
					%>
					<span class="sys-ch">计划日期:</span> 
					<input id="roomPlanDate"
						class="inputArea" style="width: 150px; height: 30px" type="text"
						name="date" onClick="WdatePicker()" placeholder="点击选择日期" /> 
					<table id="cart_table" cellspacing="0" style="width: 95%;">
						<thead id="table_head">
							<tr>
								<th style="height: 44px;">
									<p>房间号</p>
								</th>
								<th style="height: 44px;">
									<p>房间类型</p>
								</th>
								<th style="height: 44px;">
									<p>价格</p>
								</th>
							</tr>
						</thead>
						<tbody id="userGroups" class="tbody">
							<%
								for (int i = 0; i < plan.getRooms().size(); i++) {
									String pid = "price" + i;
							%>
							<tr>
								<td class="table_cell admin">
									<p class="sys-ch" style="line-height: 40px;"><%=plan.getRooms().get(i).getId()%></p>
								</td>
								<td class="table_cell admin">
									<p class="sys-ch" style="line-height: 40px;">
									<%if(plan.getRooms().get(i).getRoomType() == RoomType.bigBedroom){ %>
									大床房
									<%}else if(plan.getRooms().get(i).getRoomType() == RoomType.standardRoom){ %>
									标准间
									<%}else{ %>
									商务房
									<%} %>
									</p>
								</td>
								<td class="table_cell admin">
									<div class="item-amount">
										<a href="#" onclick="min(this)">-</a> <input id="<%=pid%>"
											class="numvalue" type="text" value="<%=plan.getRooms().get(i).getPrice()%>"> <a href="#"
											onclick="add(this)">+</a>
									</div>
								</td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
					<a class="calBtn"
						style="margin-top: 20px; float: right; line-height: 40px; height: 40px; width: 150px; font-size: 20px; margin-right:42px"
						href="#" onclick="addPlan('<%=plan.getRooms().size()%>')">提交</a>
				</div>
			</div>
		</div>
	</div>
	<!-- .common-container ends -->
	<script language="javascript" type="text/javascript"
		src="../css/My97DatePicker/WdatePicker.js"></script>
	<script src="../js/jquery.min.js"></script>
	<script src="../js/roomPlan.js"></script>
</body>
</html>