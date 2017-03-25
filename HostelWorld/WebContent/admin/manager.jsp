<%@page import="hostelworld.model.User"%>
<%@page import="hostelworld.model.HotelAccommocdatedStatistic"%>
<%@page import="hostelworld.model.RoomOrder"%>
<%@page import="hostelworld.model.Hotel"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="HotelList" type="hostelworld.action.HotelListBean"
	scope="session"></jsp:useBean>
<jsp:useBean id="hotelitem" class="hostelworld.model.Hotel"
	scope="page"></jsp:useBean>
<jsp:useBean id="RoomOrderList"
	type="hostelworld.action.RoomOrderListBean" scope="session"></jsp:useBean>

<jsp:useBean id="HotelAccommocdatedStatisticList"
	type="hostelworld.action.HotelAccommocdatedStatisticBean"
	scope="session"></jsp:useBean>

<jsp:useBean id="MemberOrderList"
	type="hostelworld.action.MemberOrderListBean" scope="session"></jsp:useBean>
<jsp:useBean id="orderitem" class="hostelworld.model.RoomOrder"
	scope="page"></jsp:useBean>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>经理</title>

<!-- Stylesheets -->
<link rel="stylesheet" href="../css/area.css">
<link rel="stylesheet" href="../css/button.css">
<link rel="stylesheet" href="../css/font.css">
<link rel="stylesheet" href="../css/input.css">
<link rel="stylesheet" href="../css/table.css">

<style>
* {
	margin: 0;
	padding: 0;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

body {
	background: #FBEFE1;
	font-family: 'Roboto', Arial, Tahoma, Sans-serif, Verdana, Helvetica;
	font-size: 62.5%;
	fon-color: #333;
}

h1 {
	color: #2C1201;
	font-size: 23px;
	font-family: 微软雅黑;
}

h2 {
	font-family: 微软雅黑;
	font-size: 20px;
	color: #C43F50;
}

a {
	text-decoration: none;
}

li {
	list-style-type: none;
	margin-top: 10px;
	margin-bottom: 10px;
	height: 60px;
}

.subtitle-ch-bold {
	font-family: 微软雅黑;
    font-size: 20px;
    font-weight: 600;
    color:#C43F50;
}
</style>
</head>
<%User manager = (User)session.getAttribute("manager"); %>
<body>
	<div class="nevigation-long">
		<div class="nevigation-page">
			<span class="title-ch-bold">客栈管理</span>
		</div>
		<div class="usercard">
			<a href="#" class="sys-bold"
				style="color: white; border-right: solid 1px; padding-right: 15px;">manager</a>
			<a href="#" onclick="logout()" class="sys-bold"
				style="color: white; padding-left: 10px;">Log out</a>
		</div>
	</div>
	<!-- .navigation-long ends -->
	<div id="indexmenu" style="padding-top: 50px;">
		<a href="#" onclick="showHotel()" class="btn btn-2" id="hotelBtn">客栈审核</a> 
		<a href="#" onclick="showSale()" class="btn btn-2" id="saleBtn">待结算</a> 
		<a href="#" onclick="showUser()" class="btn btn-2" id="userBtn">会员订单</a> 
		<a href="#" onclick="showAccommodated(<%=HotelAccommocdatedStatisticList.getMemberAccommodatedOrderNum()%>, <%=HotelAccommocdatedStatisticList.getUnmemberOrderNum() %>)" class="btn btn-2" id="accommodatedBtn">各店入住情况</a>
		<a href="#" onclick="showMoney(<%=manager.getBalance() %>, <%=manager.getCharge() %>)" class="btn btn-2" id="moneyBtn">财务状况</a>
	</div>

	<div class="right-container" id="hotel" style="visibility: hidden">
		<h1>待审核客栈</h1>
		<table id="cart_table" cellspacing="0">
			<thead id="table_head">
				<tr>
					<th style="height: 44px;">
						<p>编号</p>
					</th>
					<th style="height: 44px;">
						<p>名称</p>
					</th>
					<th style="height: 44px;">
						<p>地址</p>
					</th>
					<th style="height: 44px;">
						<p>电话</p>
					</th>
					<th style="height: 44px;">
						<p>银行卡</p>
					</th>
					<th style="height: 44px;">
						<p>操作</p>
					</th>

				</tr>
			</thead>
			<tbody id="userGroups" class="tbody">
				<%
					for (int i = 0; i < HotelList.getHotelList().size(); i++) {
						Hotel hotel = (Hotel) HotelList.getHotelList().get(i);
				%>
				<tr>
					<td class="table_cell admin" style="width: 15%;">
						<p class="sys-ch" style="line-height: 44px;"><%=hotel.getHotelId()%>
						</p>
					</td>
					<td class="table_cell admin" style="width: 15%;">
						<p class="sys-ch" style="line-height: 44px;"><%=hotel.getName()%></p>
					</td>
					<td class="table_cell admin" style="width: 15%;">
						<p class="sys-ch" style="line-height: 44px;"><%=hotel.getAddress()%></p>
					</td>
					<td class="table_cell admin" style="width: 15%;">
						<p class="sys-ch" style="line-height: 44px;"><%=hotel.getTel()%></p>
					</td>
					<td class="table_cell admin" style="width: 15%;">
						<p class="sys-ch" style="line-height: 44px;"><%=hotel.getBankCard()%></p>
					</td>
					<td class="table_cell admin" style="width: 15%;"><a href="#"
						onclick="approveHotel(<%=i%>)" class="sys-ch"
						style="color: #A4313E;">通过</a> <input id="approveHotelId"
						value="<%=hotel.getHotelId()%>" style="visibility: hidden" /> <a
						href="#" onclick="disapproveHotel(<%=i%>)" class="sys-ch"
						style="color: #A4313E">不通过</a> <input id="disapproveHotelId"
						value="<%=hotel.getHotelId()%>" style="visibility: hidden" /></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>

	<div class="right-container" id="sale" style="visibility:">
		<h1>待结算订单</h1>
		
		<table id="cart_table" cellspacing="0">
			<thead id="table_head">
				<tr>
					<th style="height: 44px;">
						<p>编号</p>
					</th>
					<th style="height: 44px;">
						<p>客栈</p>
					</th>
					<th style="height: 44px;">
						<p>房间</p>
					</th>
					<th style="height: 44px;">
						<p>价格</p>
					</th>
					<th style="height: 44px;">
						<p>入住日期</p>
					</th>
					<th style="height: 44px;">
						<p>会员</p>
					</th>
					<th style="height: 44px;">
						<p>操作</p>
					</th>
				</tr>
			</thead>
			<tbody id="userGroups" class="tbody">
				<%
					for (int i = 0; i < RoomOrderList.getRoomOrderList().size(); i++) {
						RoomOrder order = (RoomOrder) RoomOrderList.getRoomOrderList().get(i);
				%>
				<tr>
					<td class="table_cell admin" style="width: 15%;">
						<p class="sys-ch" style="line-height: 44px;"><%=order.getSaleId()%>
						</p>
					</td>
					<td class="table_cell admin" style="width: 15%;">
						<p class="sys-ch" style="line-height: 44px;"><%=order.getHotelNameByHotelId(order.getHotelId())%></p>
					</td>
					<td class="table_cell admin" style="width: 15%;">
						<p class="sys-ch" style="line-height: 44px;"><%=order.getRoomId()%></p>
					</td>
					<td class="table_cell admin" style="width: 15%;">
						<p class="sys-ch" style="line-height: 44px;"><%=order.getPrice()%></p>
					</td>
					<td class="table_cell admin" style="width: 15%;">
						<p class="sys-ch" style="line-height: 44px;"><%=order.getMemberId()%></p>
					</td>
					<td class="table_cell admin" style="width: 15%;">
						<p class="sys-ch" style="line-height: 44px;"><%=order.getCreateDate()%></p>
					</td>
					<td class="table_cell admin" style="width: 15%;"><a href="#"
						onclick="calRoomOrder(<%=i%>)" class="sys-ch"
						style="color: #A4313E;">通过</a> <input id="calOrderId"
						value="<%=order.getSaleId()%>" style="visibility: hidden" /></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>

	<div class="right-container" id="user" style="visibility: hidden;">
		<h1>会员订单</h1>

		<table id="cart_table" cellspacing="0">
			<thead id="table_head">
				<tr>
					<th style="height: 44px;">
						<p>编号</p>
					</th>
					<th style="height: 44px;">
						<p>酒店名称</p>
					</th>
					<th style="height: 44px;">
						<p>房间号</p>
					</th>
					<th style="height: 44px;">
						<p>会员</p>
					</th>
					<th style="height: 44px;">
						<p>价格</p>
					</th>
					<th style="height: 44px;">
						<p>入住日期</p>
					</th>
					<th style="height: 44px;">
						<p>订单状态</p>
					</th>

				</tr>
			</thead>
			<tbody id="userGroups" class="tbody">
				<%
					for (int i = 0; i < MemberOrderList.getMemberOrderList().size(); i++) {
						RoomOrder rOrder = MemberOrderList.getRoomOrder(i);
				%>
				<tr>
					<td class="table_cell admin" style="width: 15%;">
						<p class="sys-ch" style="line-height: 44px;"><%=rOrder.getSaleId()%>
						</p>
					</td>
					<td class="table_cell admin" style="width: 15%;">
						<p class="sys-ch" style="line-height: 44px;"><%=rOrder.getHotelNameByHotelId(rOrder.getHotelId())%></p>
					</td>
					<td class="table_cell admin" style="width: 15%;">
						<p class="sys-ch" style="line-height: 44px;"><%=rOrder.getRoomId()%></p>
					</td>
					<td class="table_cell admin" style="width: 15%;">
						<p class="sys-ch" style="line-height: 44px;"><%=rOrder.getMemberId()%></p>
					</td>
					<td class="table_cell admin" style="width: 15%;">
						<p class="sys-ch" style="line-height: 44px;">
							￥<%=rOrder.getPrice()%></p>
					</td>
					<td class="table_cell admin" style="width: 15%;">
						<p class="sys-ch" style="line-height: 44px;"><%=rOrder.getCreateDate()%></p>
					</td>
					<td class="table_cell admin" style="width: 15%;">
						<p class="sys-ch" style="line-height: 44px;"><%=rOrder.getSaleStateByEnglish(rOrder.getSaleState())%></p>
					</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
	<div class="right-container" id="accommodated"
		style="visibility: hidden; height: 1000px;">
		<h1>各店入住情况</h1>
		
		<table id="cart_table" cellspacing="0">
			<thead id="table_head">
				<tr>
					<th style="height: 44px;">
						<p>酒店编号</p>
					</th>
					<th style="height: 44px;">
						<p>酒店名称</p>
					</th>
					<th style="height: 44px;">
						<p>总订单数</p>
					</th>
				</tr>
			</thead>
			<tbody id="userGroups" class="tbody">
				<%
					for (int i = 0; i < HotelAccommocdatedStatisticList.getHotelAccommocdatedStatistics().size(); i++) {
						HotelAccommocdatedStatistic hAccommocdatedStatistic = HotelAccommocdatedStatisticList
								.getHotelAccommocdatedStatistic(i);
				%>
				<tr>
					<td class="table_cell admin" style="width: 10%;">
						<p class="sys-ch" style="line-height: 44px;"><%=hAccommocdatedStatistic.getHotelId()%>
						</p>
					</td>
					<td class="table_cell admin" style="width: 15%;">
						<p class="sys-ch" style="line-height: 44px;"><%=hAccommocdatedStatistic.getHotelName()%></p>
					</td>
					<td class="table_cell admin" style="width: 5%;">
						<p class="sys-ch" style="line-height: 44px;"><%=hAccommocdatedStatistic.getOrderNum()%></p>
					</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<div id="orderChart" style="position:relative;float:left;margin-top:50px;width:100%;height:380px"></div>
	</div>
	<div class="right-container" id="money"
		style="visibility: hidden; height: 1000px;">
		<h1>财务状况</h1>
		<div id="moneyChart" style="position:relative;float:left;margin-top:50px;width:100%;height:380px"></div>
	</div>
	<script src="../js/echarts.min.js"></script>
	<script src="../js/jquery.min.js"></script>
	<script src="../js/manager.js"></script>
</body>

</html>