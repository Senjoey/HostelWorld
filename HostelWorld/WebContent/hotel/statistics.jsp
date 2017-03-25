<%@page import="hostelworld.model.RoomOrder"%>
<%@page import="hostelworld.model.Room.RoomType"%>
<%@page import="hostelworld.model.RoomPlan"%>
<%@page import="hostelworld.model.Hotel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="SaleStatisticList"
	type="hostelworld.action.SaleStatisticBean" scope="session"></jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统计信息</title>

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
Ω
</style>

</head>
<%
	Hotel hotel = (Hotel) session.getAttribute("hotel");
	String date = (String) session.getAttribute("date");
%>
<body>
	<div class="common-container">
		<div class="top-menu">
			<div class="top-btn" style="margin-left: 180px">
				<a href="../user/index.jsp" class="menu">首页</a>
			</div>
			<div class="top-btn">
				<a href="roomPlan.jsp" class="menu">房间计划</a>
			</div>
			<div class="top-btn">
				<a href="hotelinfo.jsp" class="menu">客栈中心</a>
			</div>
			<div class="top-btn" style="border-right: solid 0px;">
				<a href="#" class="menu" style="color: #C43F50;">统计信息</a>
			</div>
		</div>
		<!-- .top-menu ends -->

		<div class="nevigation-long">
			<div class="nevigation-page">
				<span class="title-ch-bold">统计信息</span>
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
					<li><a href="#" onclick="showAllOrderedRoom()" class="sys-ch">已预订房间</a></li>
					<li><a href="#" onclick="showAccommodatedRoom()" class="sys-ch">已入住房间</a></li>
					<li><a href="#" onclick="showHistoryRoom()" class="sys-ch">历史订单</a></li>
				</ul>
			</div>
			<!-- .navigation-left of main body ends -->

			<div class="main-container" style="height: 1000px;">
				<div id="allOrderedRoom" class="sub-container"
					style="visibility: hidden">
					<p class="title-ch-bold">已预订房间</p>
					<table id="cart_table" cellspacing="0" style="width: 95%;">
						<thead id="table_head">
							<tr>
								<th style="height: 44px;">
									<p>订单编号</p>
								</th>
								<th style="height: 44px;">
									<p>房间号</p>
								</th>
								<th style="height: 44px;">
									<p>价格</p>
								</th>
								<th style="height: 44px;">
									<p>会员编号</p>
								</th>
								<th style="height: 44px;">
									<p>入住日期</p>
								</th>
							</tr>
						</thead>

						<tbody id="userGroups" class="tbody">
							<%
								for (int i = 0; i < SaleStatisticList.getSaleList().size(); i++) {
									RoomOrder roomOrder = SaleStatisticList.getSaleList().get(i);
									if (roomOrder.getSaleState().equals("ordered")) {
							%>
							<tr>
								<td class="table_cell admin">
									<p class="sys-ch" style="line-height: 40px;"><%=roomOrder.getSaleId()%></p>
								</td>
								<td class="table_cell admin">
									<p class="sys-ch" style="line-height: 40px;"><%=roomOrder.getRoomId()%></p>
								</td>
								<td class="table_cell admin">
									<p class="sys-ch" style="line-height: 40px;"><%=roomOrder.getPrice()%></p>
								</td>
								<td class="table_cell admin">
									<p class="sys-ch" style="line-height: 40px;"><%=roomOrder.getMemberId()%></p>
								</td>
								<td class="table_cell admin">
									<p class="sys-ch" style="line-height: 40px;"><%=roomOrder.getCreateDate()%></p>
								</td>
							</tr>
							<%
								}
								}
							%>
						</tbody>
					</table>
				</div>

				<div id="allAcommodatedRoom" class="sub-container"
					style="visibility:">
					<p class="title-ch-bold">已入住房间</p>
					<table id="cart_table" cellspacing="0" style="width: 95%;">
						<thead id="table_head">
							<tr>
								<th style="height: 44px;">
									<p>订单编号</p>
								</th>
								<th style="height: 44px;">
									<p>房间号</p>
								</th>
								<th style="height: 44px;">
									<p>价格</p>
								</th>
								<th style="height: 44px;">
									<p>房客信息</p>
								</th>
							</tr>
						</thead>

						<tbody id="userGroups" class="tbody">
							<%
								for (int i = 0; i < SaleStatisticList.getSaleList().size(); i++) {
									RoomOrder roomOrder = SaleStatisticList.getSaleList().get(i);
									if (roomOrder.getCreateDate().equals(date) && roomOrder.getSaleState().equals("accommodated")) {
							%>
							<tr>
								<td class="table_cell admin">
									<p class="sys-ch" style="line-height: 40px;"><%=roomOrder.getSaleId()%></p>
								</td>
								<td class="table_cell admin">
									<p class="sys-ch" style="line-height: 40px;"><%=roomOrder.getRoomId()%></p>
								</td>
								<td class="table_cell admin">
									<p class="sys-ch" style="line-height: 40px;"><%=roomOrder.getPrice()%></p>
								</td>
								<td class="table_cell admin">
									<p class="sys-ch" style="line-height: 40px;"><%=roomOrder.getTenantName()%></p>
								</td>
							</tr>
							<%
								}
								}
							%>
						</tbody>
					</table>
				</div>
				<div id="allHistoryRoom" class="sub-container"
					style="visibility:hidden">
					<p class="title-ch-bold">历史订单</p>
					<table id="cart_table" cellspacing="0" style="width: 95%;">
						<thead id="table_head">
							<tr>
								<th style="height: 44px;">
									<p>订单编号</p>
								</th>
								<th style="height: 44px;">
									<p>房间号</p>
								</th>
								<th style="height: 44px;">
									<p>价格</p>
								</th>
								<th style="height: 44px;">
									<p>房客信息</p>
								</th>
								<th style="height: 44px;">
									<p>入住日期</p>
								</th>
							</tr>
						</thead>

						<tbody id="userGroups" class="tbody">
							<%
								for (int i = 0; i < SaleStatisticList.getSaleList().size(); i++) {
									RoomOrder roomOrder = SaleStatisticList.getSaleList().get(i);
									if (roomOrder.getSaleState().equals("accommodated") || roomOrder.getSaleState().equals("sold")) {
							%>
							<tr>
								<td class="table_cell admin">
									<p class="sys-ch" style="line-height: 40px;"><%=roomOrder.getSaleId()%></p>
								</td>
								<td class="table_cell admin">
									<p class="sys-ch" style="line-height: 40px;"><%=roomOrder.getRoomId()%></p>
								</td>
								<td class="table_cell admin">
									<p class="sys-ch" style="line-height: 40px;"><%=roomOrder.getPrice()%></p>
								</td>
								<td class="table_cell admin">
									<p class="sys-ch" style="line-height: 40px;"><%=roomOrder.getTenantName()%></p>
								</td>
								<td class="table_cell admin">
									<p class="sys-ch" style="line-height: 40px;"><%=roomOrder.getCreateDate()%></p>
								</td>
							</tr>
							<%
								}
								}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- .common-container ends -->
	<script src="../js/jquery.min.js"></script>
	<script src="../js/statistic.js"></script>
</body>
</html>