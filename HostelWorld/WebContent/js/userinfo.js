function EditInfo() {
	document.getElementById("tel").style.visibility = "hidden";
	document.getElementById("age").style.visibility = "hidden";
	document.getElementById("ad").style.visibility = "hidden";
	document.getElementById("tel_input").style.visibility = "";
	document.getElementById("age_input").style.visibility = "";
	document.getElementById("ad_input").style.visibility = "";
	document.getElementById("editSub").style.visibility = "";
}

function showInfo() {
	document.getElementById("info").style.visibility = "";
	document.getElementById("order").style.visibility = "hidden";
	document.getElementById("charge").style.visibility = "hidden";
	document.getElementById("points").style.visibility = "hidden";
	document.getElementById("tel").style.visibility = "";
	document.getElementById("age").style.visibility = "";
	document.getElementById("ad").style.visibility = "";
	document.getElementById("tel_input").style.visibility = "hidden";
	document.getElementById("age_input").style.visibility = "hidden";
	document.getElementById("ad_input").style.visibility = "hidden";
	document.getElementById("editSub").style.visibility = "hidden";
}
function showOrder() {
	document.getElementById("info").style.visibility = "hidden";
	document.getElementById("order").style.visibility = "";
	document.getElementById("charge").style.visibility = "hidden";
	document.getElementById("points").style.visibility = "hidden";
}
function showCharge() {
	document.getElementById("info").style.visibility = "hidden";
	document.getElementById("order").style.visibility = "hidden";
	document.getElementById("charge").style.visibility = "";
	document.getElementById("points").style.visibility = "hidden";
}
function showPoints() {
	document.getElementById("info").style.visibility = "hidden";
	document.getElementById("order").style.visibility = "hidden";
	document.getElementById("charge").style.visibility = "hidden";
	document.getElementById("points").style.visibility = "";
}
function showPausedTip() {
	alert("请重新激活会员资格");
	showCharge();
}
function showStoppedTip() {
	alert("您的会员已停止");
	showInfo();
}
function test() {
	$.ajax({
		// 提交数据的类型 POST GET
		type : "POST",
		// 提交的网址
		url : "/HostelWorld/RoomOrderServlet",
		// 提交的数据
		data : {
			type : "deleteOrder",
			saleId : $("#saleid").text()
		},
		// 返回数据的格式
		datatype : "text",// "xml", "html", "script", "json", "jsonp",
							// "text".
		success : function(data) {
			if (data) {
				alert("您已成功取消订单！");
				location.reload();
			}
		}
	});
}
function deleteOrder(id) {
	alert("deleteOrder: " + id);
	$.ajax({
		// 提交数据的类型 POST GET
		type : "POST",
		// 提交的网址
		url : "/HostelWorld/RoomOrderServlet",
		// 提交的数据
		data : {
			type : "deleteOrder",
			saleId : id
		},
		// 返回数据的格式
		datatype : "text",// "xml", "html", "script", "json", "jsonp",
							// "text".
		success : function(data) {
			if (data) {
				alert("您已成功取消订单！");
				location.reload();
			}
		}
	});

}
function SubmitInfo() {
	$.ajax({
		// 提交数据的类型 POST GET
		type : "POST",
		// 提交的网址
		url : "/HostelWorld/UserServlet",
		// 提交的数据
		data : {
			type : "update",
			tel : document.getElementById("tel_input").value,
			address : document.getElementById("ad_input").value,
			age : document.getElementById("age_input").value
		},
		// 返回数据的格式
		datatype : "text",// "xml", "html", "script", "json", "jsonp", "text".
		success : function(data) {
			if (data) {
				alert("修改成功！");
				showInfo();
				location.reload();
			}
		}
	});
}
function StopMember() {
	if (confirm('账户停用后无法恢复，确定要停用该账户吗？')) {
		$
				.ajax({
					// 提交数据的类型 POST GET
					type : "POST",
					// 提交的网址
					url : "/HostelWorld/UserServlet",
					// 提交的数据
					data : {
						type : "stop"
					},
					// 返回数据的格式
					datatype : "text",// "xml", "html", "script", "json",
										// "jsonp", "text".
					success : function(data) {
						if (data) {
							alert("账户已停用！");
							showInfo();
							location.reload();
							document.getElementById("editSub").style.visibility = "hidden";
							document.getElementById("stopSub").style.visibility = "hidden";
						}
					}
				});
	}
}

function ResetCharge() {
	document.getElementById("charge_input").value = "";
}

function ResetPoint() {
	document.getElementById("points_input").value = "";
}

function SubmitCharge() {
	var sum = document.getElementById("charge_input").value;
	if (sum == "")
		alert("请输入充值金额！");
	else {
		$.ajax({
			// 提交数据的类型 POST GET
			type : "POST",
			// 提交的网址
			url : "/HostelWorld/UserServlet",
			// 提交的数据
			data : {
				type : "charge",
				money : sum
			},
			// 返回数据的格式
			datatype : "text",// "xml", "html", "script", "json", "jsonp",
								// "text".
			success : function(data) {
				if (data) {
					alert("充值成功！");
					location.reload();
				}
			}
		});
	}
}

function SubmitPoint() {
	var sum = document.getElementById("points_input").value;
	if (sum == "")
		alert("请输入需要兑换的积分数！");//假设用户输入的是合法的值
	else {
		$.ajax({
			// 提交数据的类型 POST GET
			type : "POST",
			// 提交的网址
			url : "/HostelWorld/UserServlet",
			// 提交的数据
			data : {
				type : "point",
				point : sum
			},
			// 返回数据的格式
			datatype : "text",// "xml", "html", "script", "json", "jsonp",
								// "text".
			success : function(data) {
				if (data) {
					alert("兑换成功！");
					location.reload();
				}
			}
		});
	}
}



function logout() {
	$.ajax({
		// 提交数据的类型 POST GET
		type : "POST",
		// 提交的网址
		url : "/HostelWorld/UserServlet",
		// 提交的数据
		data : {
			type : "logout"
		},
		// 返回数据的格式
		datatype : "text",// "xml", "html", "script", "json", "jsonp", "text".
		success : function(data) {
			window.location.href = "index.jsp";
		}
	});
}