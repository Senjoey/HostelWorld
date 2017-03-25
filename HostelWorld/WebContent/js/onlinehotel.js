var bigPart = document.getElementById("big-list");
var standardPart = document.getElementById("standard-list");
var businessPart = document.getElementById("business-list");

function showBigroom() {
	bigPart.style.visibility = "";
	standardPart.style.visibility = "hidden";
	businessPart.style.visibility = "hidden";
}

function showStandardroom() {
	bigPart.style.visibility = "hidden";
	standardPart.style.visibility = "";
	businessPart.style.visibility = "hidden";
}

function showBusinessrrom() {
	bigPart.style.visibility = "hidden";
	standardPart.style.visibility = "hidden";
	businessPart.style.visibility = "";
}


function findRecord(ctype) {
	$.ajax({
		// 提交数据的类型 POST GET
		type : "POST",
		// 提交的网址
		url : "/HostelWorld/RoomOrderServlet",
		// 提交的数据
		data : {
			type : "findtype",
			rtype : rtype
		},
		// 返回数据的格式
		datatype : "text",// "xml", "html", "script", "json", "jsonp", "text".
		success : function(data) {
			if (data)
				location.reload();
		}
	});
}
function orderRoom(rid) {
	$.ajax({
		// 提交数据的类型 POST GET
		type : "POST",
		// 提交的网址
		url : "/HostelWorld/RoomOrderServlet",
		// 提交的数据
		data : {
			type : "orderRoom",
			roomId : rid
		},
		// 返回数据的格式
		datatype : "text",// "xml", "html", "script", "json", "jsonp", "text".
		success : function(data) {
			if (data == "nobalance") {
				alert("余额不足！");
			} else if(data == "noactive") {
				alert("您的账户存在异常！");
			}else if (data == "ordered") {
				alert("该房间已预订！");
			}else if(data == "accommodated") {
				alert("该房间已入住！");
			}
			else {
				alert("预定成功！");
				window.location.href = "userinfo.jsp";
			}
		}
	});
}
function onhotelLogout() {
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