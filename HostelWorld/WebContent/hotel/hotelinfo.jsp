<%@page import="java.beans.Visibility"%>
<%@page import="hostelworld.model.PlanRoom.RoomState"%>
<%@page import="hostelworld.model.PlanRoom"%>
<%@page import="hostelworld.model.RoomCart"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="hostelworld.model.User"%>
<%@page import="hostelworld.model.Hotel"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>客栈中心</title>

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

#totalpricetag {
	font-family: 黑体;
	font-size: 25px;
	font-weight: 600;
	color: #C43F50;
	line-height: 30px;
}
</style>

</head>
<%
	Hotel hotel = (Hotel) session.getAttribute("hotel");
%>
<body>
	<%
		RoomCart cart = (RoomCart) session.getAttribute("salecart");
		if (cart == null) {
			cart = new RoomCart();
			cart.init();
		}
	%>

	<div class="common-container">
		<div class="top-menu">
			<div class="top-btn" style="margin-left: 180px">
				<a href="../user/index.jsp" class="menu">首页</a>
			</div>
			<div class="top-btn">
				<a href="roomPlan.jsp" class="menu">房间计划</a>
			</div>
			<div class="top-btn">
				<a href="" class="menu" style="color: #C43F50;">客栈中心</a>
			</div>
			<div class="top-btn" style="border-right: solid 0px;">
				<a href="statistics.jsp" class="menu">统计信息</a>
			</div>
		</div>
		<!-- .top-menu ends -->

		<div class="nevigation-long">
			<div class="nevigation-page">
				<span class="title-ch-bold">客栈中心</span>
			</div>
			<div class="usercard">
				<a href="#" class="sys-bold"
					style="color: white; border-right: solid 1px; padding-right: 15px;"><%=hotel.getHotelId()%></a>
				<a href="#" onclick="logout()" class="sys-bold"
					style="color: white; padding-left: 10px;">Log out</a>
			</div>
		</div>
		<!-- .navigation-long ends -->

		<div style="background-color: #FBEFE1; width: 100%; overflow: auto">
			<div class="nevigation-left">
				<ul>
					<li><a href="#" onclick="showInfo()" class="sys-ch">客栈资料</a></li>
					<li><a href="#" onclick="showSale()" class="sys-ch">登记</a></li>
					<li><a href="#" onclick="showRecord()" class="sys-ch">进账记录</a>
					</li>
				</ul>
			</div>
			<!-- .navigation-left of main body ends -->

			<div class="main-container" style="height: 1000px;">
				<div id="info" class="sub-container" style="visibility: hidden">
					<p class="title-ch-bold">客栈资料</p>

					<ul style="border-bottom: solid 1px;">
						<li><span class="sys-ch-bold">客栈编号&emsp;：</span> <span
							class="sys-ch" style="color: #1B0D00"><%=hotel.getHotelId()%></span>
						</li>
						<li><span class="sys-ch-bold">客栈状态&emsp;：</span> <span
							class="sys-ch" style="color: #1B0D00"> <%
 	if (hotel.getState() == Hotel.HotelState.approved) {
 %>通过经理审核 <%
 	} else if (hotel.getState() == Hotel.HotelState.disapproved) {
 %>未通过经理审核 <%
 	} else if (hotel.getState() == Hotel.HotelState.processed) {
 %>等待经理审核中... <%
 	}
 %>
						</span></li>
						<li><span class="sys-ch-bold">客栈余额&emsp;：</span> <span
							class="sys-ch" style="color: #1B0D00">￥<%=hotel.getBalance()%></span>
						</li>
					</ul>

					<a href="#" onclick="checkState()"
						style="color: #824220; float: right; margin-right: 60px;"
						class="sys-bold">编辑</a>

					<form>
						<ul style="margin-top: 40px">
							<li style="margin-top: 20px"><span class="sys-ch-bold">名&emsp;&emsp;子：</span>
								<span id="name" class="sys-ch" style="color: #1B0D00"><%=hotel.getName()%></span>
								<input id="name_input" value="<%=hotel.getTel()%>"
								style="position: absolute; left: 130px; width: 200px; visibility: hidden">
							</li>
							<li style="margin-top: 20px"><span class="sys-ch-bold">地&emsp;&emsp;址：</span>
								<span id="address" class="sys-ch" style="color: #1B0D00"><%=hotel.getAddress()%></span>
								<input id="address_input" value="<%=hotel.getAddress()%>"
								style="position: absolute; left: 130px; width: 200px; visibility: hidden">
							</li>
							<li style="margin-top: 20px"><span class="sys-ch-bold">联系电话：</span>
								<span id="tel" class="sys-ch" style="color: #1B0D00"><%=hotel.getTel()%></span>
								<input id="tel_input" value="<%=hotel.getTel()%>"
								style="position: absolute; left: 130px; width: 200px; visibility: hidden">
							</li>
						</ul>
						<a id="editSub" class="ghbutton pink" href="#"
							onclick="SubmitInfo()"
							style="visibility: hidden; margin-top: 40px; margin-left: 40px; width: 100px; height: 20px; font-size: 14px;">确认</a>
					</form>
				</div>
				<!-- 客栈资料info ends -->
				<div id="saleRoom" class="sub-container"
					style="visibility: visible;">
					<p class="title-ch-bold">入住/离店登记</p>
					<div class="right_nr_search" style="display: inline; right: 45%">
						<input type="text" id="room_input" name="productKey" value="房间编号"
							onblur="if(this.value==''){this.value='房间编号';$('#room_input').css('color','#dbdada');}"
							onfocus="if(this.value=='房间编号'){this.value=''; $('#room_input').css('color','');}"
							style="color: rgb(219, 218, 218);"> <a
							onclick="roomCheck()" name="search_tj" class="search_tj"></a>
					</div>
					<!-- 搜索框ends -->
					<div style="position: relative; width: 95%; margin-top: 0px;">
						<table id="cart_table" cellspacing="0"
							style="margin-top: 40px; color: #333">
							<thead id="table_head">
								<tr>
									<th style="height: 30px;">
										<p>房间号</p>
									</th>
									<th style="height: 30px;">
										<p>金额</p>
									</th>
									<th style="height: 30px;">
										<p>状态</p>
									</th>
									<th style="height: 30px;">
										<p>操作</p>
									</th>
									<th style="height: 30px">
										<p>房客信息</p>
									</th>
									<th style="height: 30px;">
										<p>删除</p>
									</th>
								</tr>
							</thead>
							<tbody id="userGroups" class="tbody">
								<%
									for (int i = 0; i < cart.getPlanRooms().size(); i++) {
										PlanRoom planRoom = (PlanRoom) cart.getPlanRooms().get(i);
										String pid = "price" + i;
										String sid = "state" + i;
										String aid = "action" + i;
										String did = "delete" + i;
								%>
								<tr>
									<td class="table_cell admin" style="width: 15%;">
										<p class="sys-ch"><%=planRoom.getId()%></p>
									</td>
									<td class="table_cell admin">
										<p class="sys-ch-bold">
											￥<%=planRoom.getPrice()%></p>
									</td>
									<td class="table_cell admin" style="width: 15%;">
										<p class="sys-ch">
											<%
												if (planRoom.getRoomState() == RoomState.accommodated) {
											%>
											入住中
											<%
												} else if (planRoom.getRoomState() == RoomState.accommodating) {
											%>
											可入住
											<%
												} else if (planRoom.getRoomState() == RoomState.left) {
											%>
											已离店
											<%
												} else if (planRoom.getRoomState() == RoomState.ordered) {
											%>
											已预订
											<%
												}
											%>
										</p>
									</td>
									<td class="table_cell admin"><a href="#"
										onclick="modifyRoom(<%=i%>)" class="sys-ch"
										style="color: #A4313E"> <%
 	if (planRoom.getRoomState() == RoomState.accommodated) {
 %> 离店 <%
 	} else if (planRoom.getRoomState() == RoomState.left) {
 %> 无 <%
 	} else {
 %> 入住 <%
 	}
 %>
									</a></td>
									<td class="table_cell admin">
										<div id="showTenantName">
										<span id="tenantNameInfo">
										<%
												if (cart.getRoomOrders().isEmpty()) {
											%>
											无
											<%
												} else {
											%>
											<%=cart.getRoomOrders().get(0).getTenantName()%>
											<%
												}
											%>
										</span>
											
											<a href="#" onclick="editTenantsName()"
												style="color: #824220; margin-left: 20px; visibility:<%=cart.isHideEditTenantName()?"hidden":"" %>" class="sys-bold">编辑</a>
										</div>
										<div id="editTenantName" style = "display: none">
											<%
												if (cart.getRoomOrders().isEmpty()) {
											%>
											<input value="无" id="noTenantName"/>
											<a href="#" onclick="saveTenantName()"
												style="color: #824220;  margin-left: 10px;"
												class="sys-bold">确定</a>
											<%
												} else {
											%>
											<input
												value="<%=cart.getRoomOrders().get(0).getTenantName()%>"  id="inputTenantName"/>
												<a href="#" onclick="saveTenantNameInput(<%=i%>)"
												style="color: #824220;  margin-left: 10px;"
												class="sys-bold">确定</a>
											<%
												}
											%>
											
										</div>

									</td>
									<td class="table_cell admin"><a href="#"
										onclick="deleteRoom(<%=i%>)" class="sys-ch"
										style="color: #A4313E">删除</a></td>

								</tr>
								<%
									}
								%>
							</tbody>
						</table>
						<div
							style="margin-top: 30px; <%=cart.isHidePayPart() ? "visibility:hidden" : "visibility:visible"%>">
							<div style="text-align: right;">
								<span class="title-ch-bold"
									style="color: #C43F50; padding-right: 10px;">总价：</span> <span
									id="totalpricetag"><%=cart.getTotal()%></span>
							</div>
						</div>
						<a class="calBtn" onclick="pay()"
							style="line-height:40px;height:40px;width:150px;font-size:20px; margin-top:20px;<%=cart.isHidePayPart() ? "visibility:hidden" : "visibility:visible"%>"
							href="#">确认付款</a>
					</div>

					<div id="chargeRecord" class="sub-container"
						style="visibility: hidden;">
						<p class="title-ch-bold">进账记录</p>
					</div>
				</div>
			</div>

		</div>

	</div>
	<script src="../js/jquery.min.js"></script>
	<script src="../js/hotelInfo.js"></script>
</body>


</html>