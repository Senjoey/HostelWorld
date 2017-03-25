function editInfo() {
	document.getElementById("name").style.visibility = "hidden";
	document.getElementById("address").style.visibility = "hidden";
	document.getElementById("tel").style.visibility = "hidden";
	document.getElementById("tel_input").style.visibility = "";
	document.getElementById("name_input").style.visibility = "";
	document.getElementById("address_input").style.visibility = "";
	document.getElementById("editSub").style.visibility = "";
}

function showInfo() {
	document.getElementById("info").style.visibility = "";
	document.getElementById("saleRoom").style.visibility = "hidden";
	document.getElementById("chargeRecord").style.visibility = "hidden";
	document.getElementById("tel").style.visibility = "";
	document.getElementById("name").style.visibility = "";
	document.getElementById("address").style.visibility = "";
	document.getElementById("tel_input").style.visibility = "hidden";
	document.getElementById("name_input").style.visibility = "hidden";
	document.getElementById("address_input").style.visibility = "hidden";
	document.getElementById("editSub").style.visibility = "hidden";
}

function showSale() {
	document.getElementById("info").style.visibility = "hidden";
	document.getElementById("saleRoom").style.visibility = "";
	document.getElementById("chargeRecord").style.visibility = "hidden";
}

function showRecord() {
	document.getElementById("info").style.visibility = "hidden";
	document.getElementById("saleRoom").style.visibility = "hidden";
	document.getElementById("chargeRecord").style.visibility = "";
}

function setPayPartHidden() {
	document.getElementById("payPart").style.visibility = "hidden";
}

function editTenantsName() {
	//离店后不可修改了
	$("#showTenantName").hide();
	$("#editTenantName").fadeIn();
}

function saveTenantNameInput(i) {
	//淡入淡出
	modifyTenantName(i);
	var name = document.getElementById("inputTenantName").value;
	$("#tenantNameInfo").text(name);
	$("#editTenantName").hide();
	$("#showTenantName").fadeIn();
}


function saveTenantName() {
	//淡入淡出
	var name = document.getElementById("noTenantName").value;
	$("#tenantNameInfo").text(name);
	$("#editTenantName").hide();
	$("#showTenantName").fadeIn();
}

function checkState() {
	$.ajax({
		// 提交数据的类型 POST GET
		type : "POST",
		// 提交的网址
		url : "/HostelWorld/CheckServlet",
		// 提交的数据
		data : {

		},
		// 返回数据的格式
		datatype : "text",// "xml", "html", "script", "json", "jsonp", "text".
		success : function(data) {
			if (data == "noapproved") {
				alert("您的客栈状态异常！");
			} else {
				editInfo();
			}
		}
	});
}

function roomCheck(hidePayPart) {
	$.ajax({
		// 提交数据的类型 POST GET
		type : "POST",
		// 提交的网址
		url : "/HostelWorld/CheckServlet",
		// 提交的数据
		data : {
			type : "checkRoom",
			room_id : document.getElementById("room_input").value
		},
		// 返回数据的格式
		datatype : "text",// "xml", "html", "script", "json", "jsonp", "text".
		success : function(data) {
			if (data == "noroom") {
				alert("房间编号错误！");
			} else if (data == "noapproved") {
				alert("您的客栈状态异常！");
			} else {
				location.reload();
			}
		}
	});
}

function checkShowPlay(hidePayPart) {
	if (hidePayPart) {
		document.getElementById("payPart").style.visibility = "hidden";
	} else {
		document.getElementById("payPart").style.visibility = "";
	}
}

function deleteRoom(i) {
	$.ajax({
		// 提交数据的类型 POST GET
		type : "POST",
		// 提交的网址
		url : "/HostelWorld/CheckServlet",
		// 提交的数据
		data : {
			type : "delete",
			room_index : i
		},
		// 返回数据的格式
		datatype : "text",// "xml", "html", "script", "json", "jsonp", "text".
		success : function(data) {

			location.reload();
		}
	});
}

function modifyRoom(i) {
	$.ajax({
		// 提交数据的类型 POST GET
		type : "POST",
		// 提交的网址
		url : "/HostelWorld/CheckServlet",
		// 提交的数据
		data : {
			type : "modify",
			room_index : i
		},
		// 返回数据的格式
		datatype : "text",// "xml", "html", "script", "json", "jsonp", "text".
		success : function(data) {
			location.reload();
		}
	});
}

function submitInfo() {
	$.ajax({
		// 提交数据的类型 POST GET
		type : "POST",
		// 提交的网址
		url : "/HostelWorld/HotelServlet",
		// 提交的数据
		data : {
			type : "update",
			tel : document.getElementById("tel_input").value,
			name : document.getElementById("name_input").value,
			address : document.getElementById("address_input").value
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

function pay() {
	var name = $("#tenantNameInfo").text();

	$.ajax({
		// 提交数据的类型 POST GET
		type : "POST",
		// 提交的网址
		url : "/HostelWorld/CheckServlet",
		// 提交的数据
		data : {
			type : "pay",
			paytype : "money",
			tenantName: name
		},
		// 返回数据的格式
		datatype : "text",// "xml", "html", "script", "json", "jsonp",
		// "text".
		success : function(data) {

			alert("交易完成！");
			location.reload();

		}
	});

}

function modifyTenantName(i) {
	$.ajax({
		// 提交数据的类型 POST GET
		type : "POST",
		// 提交的网址
		url : "/HostelWorld/CheckServlet",
		// 提交的数据
		data : {
			type : "editTenantName",
			room_index : i,
			tenantName: document.getElementById("inputTenantName").value
		},
		// 返回数据的格式
		datatype : "text",// "xml", "html", "script", "json", "jsonp",
		success : function(data) {
			if(data)
				alert("修改成功！");

		}
	});
}

function logout() {
	$.ajax({
		// 提交数据的类型 POST GET
		type : "POST",
		// 提交的网址
		url : "/HostelWorld/HotelLogoutServlet",
		// 提交的数据
		data : {

		},
		// 返回数据的格式
		datatype : "text",// "xml", "html", "script", "json", "jsonp", "text".
		success : function(data) {
			if (data) {
				window.location.href = "../user/index.jsp";
			}
		}
	});
}