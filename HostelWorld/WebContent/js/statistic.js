var orderPart = document.getElementById("allOrderedRoom");
var accommodatedPart = document.getElementById("allAcommodatedRoom");
var historyPart = document.getElementById("allHistoryRoom");
	
function showAllOrderedRoom() {
	orderPart.style.visibility = "";
	accommodatedPart.style.visibility = "hidden";
	historyPart.style.visibility = "hidden";
}

function showAccommodatedRoom() {
	orderPart.style.visibility = "hidden";
	accommodatedPart.style.visibility = "";
	historyPart.style.visibility = "hidden";
}

function showHistoryRoom() {
	orderPart.style.visibility = "hidden";
	accommodatedPart.style.visibility = "hidden";
	historyPart.style.visibility = "";
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