<%@page import="hostelworld.model.RoomOrder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="hostelworld.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="ChargeList" type="hostelworld.action.ChargeListBean"
	scope="session"></jsp:useBean>
<jsp:useBean id="chargeitem" class="hostelworld.model.Charge"
	scope="page"></jsp:useBean>
<jsp:useBean id="PointList" type="hostelworld.action.PointListBean"
	scope="session"></jsp:useBean>ß
<jsp:useBean id="pointitem" class="hostelworld.model.Point"
	scope="page"></jsp:useBean>

<jsp:useBean id="RoomOrderList"
	type="hostelworld.action.RoomOrderListBean" scope="session"></jsp:useBean>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>个人中心</title>

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
				<a href="hotelchoose.jsp" class="menu">线上预定</a>
			</div>
			<div class="top-btn" style="border-right: solid 0px;">
				<a href="#" class="menu" style="color: #C43F50;">个人中心</a>
			</div>
		</div>

		<div class="nevigation-long">
			<div class="nevigation-page">
				<span class="title-ch-bold">个人中心</span>
			</div>
			<div class="usercard">
				<a href="#" class="sys-bold"
					style="color: white; border-right: solid 1px; padding-right: 15px;"><%=user.getId()%></a>
				<a href="#" onclick="logout()" class="sys-bold"
					style="color: white; padding-left: 10px;">Log out</a>

			</div>
		</div>

		<div style="background-color: #FBEFE1; width: 100%; overflow: auto">
			<div class="nevigation-left">
				<ul>
					<li><a href="#" onclick="showInfo()" class="sys-ch">个人资料</a></li>
					<li>
						<!-- 判断账户状态，若暂停或停止，我的订单记录禁用 --> <%
 	if (user.getState() == User.UserState.paused) {
 %> <a href="#" onclick="showPausedTip()" class="sys-ch"
						disabled="false">我的订单</a> <%
 	} else if (user.getState() == User.UserState.stopped) {
 %> <a href="#" onclick="showStoppedTip()" class="sys-ch"
						disabled="false">我的订单</a> <%
 	} else {
 %> <a href="#" onclick="showOrder()" class="sys-ch">我的订单</a> <%
 	}
 %>

					</li>
					<li><a href="#" onclick="showCharge()" class="sys-ch">账户充值</a>
					</li>
					<li><a href="#" onclick="showPoints()" class="sys-ch">积分兑换</a>
					</li>
				</ul>
			</div>

			<div class="main-container" style="height: 1000px;">
				<div id="info" class="sub-container" style="visibility:">
					<p class="title-ch-bold">个人资料</p>

					<ul style="border-bottom: solid 1px;">
						<li><span class="sys-ch-bold">会员编号&emsp;：</span> <span
							class="sys-ch" style="color: #1B0D00"><%=user.getId()%></span></li>
						<li><span class="sys-ch-bold">账户状态&emsp;：</span> <span
							class="sys-ch" style="color: #1B0D00"> <%
 	if (user.getState() == User.UserState.inactive) {
 %>未激活 <%
 	} else if (user.getState() == User.UserState.active) {
 %>正常 <%
 	} else if (user.getState() == User.UserState.paused) {
 %>暂停 <%
 	} else if (user.getState() == User.UserState.stopped) {
 %>停用 <%
 	}
 %>
						</span> <%
 	if (user.getState() != User.UserState.stopped) {
 %> <a id="stopSub" href="#" onclick="StopMember()" class="sys-bold"
							style="margin-left: 20px; color: #824220;">停用账户</a> <%
 	}
 %></li>
						<li><span class="sys-ch-bold">账户余额&emsp;：</span> <span
							class="sys-ch" style="color: #1B0D00;">￥<%=user.getBalance()%></span>
						</li>
						<li><span class="sys-ch-bold">会员等级&emsp;：</span> <span
							class="sys-ch" style="color: #1B0D00;"> <%
 	if (user.getLevel() == 1) {
 %>普通会员 <%
 	} else if (user.getLevel() == 2) {
 %>高级会员 <%
 	} else if (user.getLevel() == 3) {
 %>白金会员 <%
 	}
 %>
						</span></li>
						<li><span class="sys-ch-bold">会员积分&emsp;：</span> <span
							class="sys-ch" style="color: #1B0D00;"><%=user.getPoint()%></span>
						</li>
						<li><span class="sys-ch-bold">会员有效期：</span> <span
							class="sys-ch" style="color: #1B0D00"> <%
 	if (user.getDate() == null) {
 %> 无 <%
 	} else {
 %> <%=user.getDate()%> <%
 	}
 %>
						</span></li>

					</ul>

					<a href="#" onclick="EditInfo()"
						style="color: #824220; float: right; margin-right: 60px;"
						class="sys-bold">编辑</a>

					<form>
						<ul style="margin-top: 40px;">
							<li style="margin-top: 20px;"><span class="sys-ch-bold">联系电话：</span>
								<span id="tel" class="sys-ch" style="color: #1B0D00"><%=user.getTel()%></span>
								<input id="tel_input" value="<%=user.getTel()%>"
								style="position: absolute; left: 130px; width: 200px; visibility: hidden;">
							</li>
							<li><span class="sys-ch-bold">年&emsp;&emsp;龄：</span> <span
								id="age" class="sys-ch" style="color: #1B0D00"><%=user.getAge()%></span>
								<input id="age_input" value="<%=user.getAge()%>"
								style="position: absolute; left: 130px; width: 230px; visibility: hidden;">
							</li>
							<li><span class="sys-ch-bold">地&emsp;&emsp;址：</span> <span
								id="ad" class="sys-ch" style="color: #1B0D00"><%=user.getAddress()%></span>
								<input id="ad_input" value="<%=user.getAddress()%>"
								style="position: absolute; left: 130px; width: 300px; height: 40px; visibility: hidden;">
							</li>
						</ul>
						<a id="editSub" class="ghbutton pink" href="#"
							onclick="SubmitInfo()"
							style="visibility: hidden; margin-top: 40px; margin-left: 40px; width: 100px; height: 20px; font-size: 14px;">确认</a>
					</form>
				</div>

				<div id="charge" class="sub-container" style="visibility: hidden">
					<p class="title-ch-bold">账户充值</p>
					<ul>
						<li><span class="sys-ch-bold">账户余额：</span> <span
							class="sys-ch" style="color: #1B0D00">￥<%=user.getBalance()%></span>
						</li>
						<li><span class="sys-ch-bold">银行卡号：</span> <input
							id="card_input" value="<%=user.getCard()%>" style="width: 230px;"></li>
						<li><span class="sys-ch-bold">充值金额：</span> <input
							id="charge_input" style="width: 230px;"></li>
						<%
							if (user.getState() != User.UserState.stopped) {
						%>
						<a id="chargeSub" class="ghbutton pink" href="#"
							onclick="SubmitCharge()"
							style="margin-top: 20px; width: 100px; height: 20px; font-size: 14px;">确认</a>
						<a id="resetSub" class="ghbutton gray" href="#"
							onclick="ResetCharge()"
							style="margin-top: 20px; margin-left: 55px; width: 100px; height: 20px; font-size: 14px;">重置</a>
						<%
							}
						%>
					</ul>

					<ul style="margin-top: 40px; border-top: solid 1px;">
						<li><span class="sys-ch-bold" style="margin-left: 30px;">充值时间</span>
							<span class="sys-ch-bold" style="margin-left: 150px;">充值金额</span>
							<span class="sys-ch-bold" style="margin-left: 150px;">余额</span></li>
						<%
							int chargeNum = ChargeList.getChargeList().size();
							int chargePages = chargeNum / 10 + 1;
							int chargeIndex = 10;
							if (chargePages == 1)
								chargeIndex = chargeNum;
							for (int i = 0; i < chargeIndex; i++) {
								pageContext.setAttribute("chargeitem", ChargeList.getCharge(i));
						%>
						<li style="color: #1B0D00"><span class="sys-ch"
							style="margin-left: 20px;"><jsp:getProperty
									name="chargeitem" property="date" /></span> <span class="sys-ch"
							style="margin-left: 130px;">￥<jsp:getProperty
									name="chargeitem" property="sum" /></span> <span class="sys-ch"
							style="margin-left: 150px;">￥<jsp:getProperty
									name="chargeitem" property="balance" /></span></li>
						<%
							}
						%>
					</ul>
					<%
						if (chargePages > 1) {
					%>
					<div class="page fr">
						<%
							for (int j = 1; j <= chargePages; j++) {
						%>
						<a id=<%="page" + Integer.toString(j)%> class="a2"
							href="javascript:void();" onclick=""><%=j%></a>
						<%
							}
						%>
						<a id="nextpage" class="turing" href="javascript:void();"
							onclick="setList(2)">下一页</a>
					</div>
					<%
						}
					%>
				</div>

				<div id="order" class="sub-container" style="visibility: hidden;">
					<p class="title-ch-bold">我的订单</p>

					<table id="cart_table" cellspacing="0"
						style="margin-top: 40px; color: #333">
						<thead id="table_head">
							<tr>
								<th style="height: 30px;">
									<p>订单编号</p>
								</th>
								<th style="height: 30px;">
									<p>入住日期</p>
								</th>
								<th style="height: 30px;">
									<p>酒店</p>
								</th>
								<th style="height: 30px;">
									<p>房间号</p>
								</th>
								<th style="height: 30px;">
									<p>金额</p>
								</th>
								<th style="height: 30px;">
									<p>操作</p>
								</th>
							</tr>
						</thead>
						<tbody id="userGroups" class="tbody">
							<%
								for (int i = 0; i < RoomOrderList.getRoomOrderList().size(); i++) {
									RoomOrder order = RoomOrderList.getRoomOrder(i);
							%>
							<tr>
								<td class="table_cell admin" style="width: 15%;">
									<p class="sys-ch" id="saleid"><%=order.getSaleId()%></p>
								</td>
								<td class="table_cell admin" style="width: 15%;">
									<p class="sys-ch"><%=order.getCreateDate()%></p>
								</td>
								<td class="table_cell admin" style="width: 15%;">
									<p class="sys-ch"><%=order.getHotelName(order.getHotelId())%></p>
								</td>
								<td class="table_cell admin" style="width: 15%;">
									<p class="sys-ch"><%=order.getRoomId()%></p>
								</td>
								<td class="table_cell admin" style="width: 15%;">
									<p class="sys-ch">
										￥<%=order.getPrice()%></p>
								</td>

								<td class="table_cell admin"><a href="#"
									onclick="test()"
									class="sys-ch" style="color: #A4313E">取消预定</a></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>

				</div>
				<div id="points" class="sub-container" style="visibility: hidden">
					<p class="title-ch-bold">积分兑换</p>
					<ul>
						<li><span class="sys-ch-bold">会员卡余额：</span> <span
							class="sys-ch" style="color: #1B0D00">￥<%=user.getBalance()%></span>
						</li>
						<li><span class="sys-ch-bold">&emsp;剩余积分：</span> <span
							class="sys-ch" style="color: #1B0D00"><%=user.getPoint()%></span>
						</li>
						<li><span class="sys-ch-bold">&emsp;兑换积分：</span> <input
							id="points_input" value="" style="width: 230px;"></li>

						<%
							if (user.getState() != User.UserState.stopped) {
						%>
						<a id="chargeSub" class="ghbutton pink" href="#"
							onclick="SubmitPoint()"
							style="margin-top: 20px; width: 100px; height: 20px; font-size: 14px;">确认</a>
						<a id="resetSub" class="ghbutton gray" href="#"
							onclick="ResetPoint()"
							style="margin-top: 20px; margin-left: 55px; width: 100px; height: 20px; font-size: 14px;">重置</a>
						<%
							}
						%>
					</ul>

					<ul style="margin-top: 40px; border-top: solid 1px;">
						<li><span class="sys-ch-bold" style="margin-left: 30px;">兑换时间</span>
							<span class="sys-ch-bold" style="margin-left: 150px;">兑换积分数</span>
							<span class="sys-ch-bold" style="margin-left: 150px;">兑换金额</span>
							<span class="sys-ch-bold" style="margin-left: 150px;">会员卡余额</span>
						</li>
						<%
							int pointNum = PointList.getPointList().size();
							for (int i = 0; i < pointNum; i++) {
								pageContext.setAttribute("pointitem", PointList.getPoint(i));
						%>
						<li style="color: #1B0D00"><span class="sys-ch"
							style="margin-left: 20px;"> <jsp:getProperty
									name="pointitem" property="date" /></span> <span class="sys-ch"
							style="margin-left: 150px;"><jsp:getProperty
									name="pointitem" property="pontisSum" /></span> <span class="sys-ch"
							style="margin-left: 175px;"><jsp:getProperty
									name="pointitem" property="moneySum" /></span> <span class="sys-ch"
							style="margin-left: 175px;"><jsp:getProperty
									name="pointitem" property="balance" /></span></li>

						<%
							}
						%>
					</ul>

				</div>

			</div>
		</div>
	</div>

	<script src="../js/jquery.min.js"></script>
	<script src="../js/userinfo.js"></script>
</body>
</html>