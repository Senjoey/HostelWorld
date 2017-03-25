function showPlan() {
	document.getElementById("plan").style.visibility = "";
	document.getElementById("createPlan").style.visibility = "hidden";
}

function showCreatePlan() {
	document.getElementById("plan").style.visibility = "hidden";
	document.getElementById("createPlan").style.visibility = "";
}

function add(Obj) {
	var p = Obj.previousSibling.previousSibling;
	var num = parseInt(p.value);
	num = num + 1;
	p.value = num;
}

function min(Obj) {
	var p = Obj.nextSibling.nextSibling;
	var num = parseInt(p.value);
	if (num != 0)
		num = num - 1;
	p.value = num;
}

function addPlan(index) {
	if (document.getElementById("roomPlanDate").value == "")
		alert("请选择计划日期");
	else {
		var prices = "";
		for (var i = 0; i < index; i++) {
			var p = document.getElementById("price" + i).value;
			prices = prices + p + ",";
		}
		
		$.ajax({
			// 提交数据的类型 POST GET
			type : "POST",
			// 提交的网址
			url : "/HostelWorld/RoomPlanServlet",
			// 提交的数据
			data : {
				type : "add",
				roomPlanDate: document.getElementById("roomPlanDate").value,
				prices : prices
			},
			// 返回数据的格式
			datatype : "text",// "xml", "html", "script", "json", "jsonp",
								// "text".
			success : function(data) {
				if(data == "noapproved") {
					alert("您的客栈状态存在异常！");
				}else if (data == "true") {
					alert("计划添加成功！");
					window.location.href = "roomPlan.jsp";
				} else {
					alert("该店计划已存在，添加失败!");
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
		url : "/HostelWorld/HotelLogoutServlet",
		// 提交的数据
		data : {
			
		},
		// 返回数据的格式
		datatype : "text",// "xml", "html", "script", "json", "jsonp", "text".
		success : function(data) {
			if(data) {
				window.location.href = "../user/index.jsp";
			}
		}
	});
}