function approveHotel(i){
	var hotelId = document.getElementById("approveHotelId").value;
	alert("You are to approve hotel " + hotelId);
	$.ajax({
		// 提交数据的类型 POST GET
		type : "POST",
		// 提交的网址
		url : "/HostelWorld/ManagerServlet",
		// 提交的数据
		data : {
			type : "approve",
			hotel_index : i,
			hotelId: hotelId
		},
		// 返回数据的格式
		datatype : "text",// "xml", "html", "script", "json", "jsonp", "text".
		success : function(data) {
			alert("审批成功！");
			location.reload();
		}
	});
}

function disapproveHotel(i) {
	var hotelId = document.getElementById("approveHotelId").value;
	alert("You are to disapprove hotel " + hotelId);
	$.ajax({
		// 提交数据的类型 POST GET
		type : "POST",
		// 提交的网址
		url : "/HostelWorld/ManagerServlet",
		// 提交的数据
		data : {
			type : "disapprove",
			hotel_index : i,
			hotelId: hotelId
		},
		// 返回数据的格式
		datatype : "text",// "xml", "html", "script", "json", "jsonp", "text".
		success : function(data) {
			alert("审批成功！");
			location.reload();
		}
	});
}

function calRoomOrder(i) {
	var saleId = document.getElementById("calOrderId").value;
	alert("You are to cal sale " + saleId);
	$.ajax({
		// 提交数据的类型 POST GET
		type : "POST",
		// 提交的网址
		url : "/HostelWorld/ManagerCalculateServlet",
		// 提交的数据
		data : {
			order_index : i,
			saleId: saleId
		},
		// 返回数据的格式
		datatype : "text",// "xml", "html", "script", "json", "jsonp", "text".
		success : function(data) {
			alert("结算成功！");
			location.reload();
		}
	});
}

function logout() {
	$.ajax({
		// 提交数据的类型 POST GET
		type : "POST",
		// 提交的网址
		url : "/HostelWorld/ManagerLogoutServlet",
		// 提交的数据
		data : {
			
		},
		// 返回数据的格式
		datatype : "text",// "xml", "html", "script", "json", "jsonp", "text".
		success : function(data) {
			window.location.href = "../user/index.jsp";
		}
	});
}

var hotel = document.getElementById("hotel");
var sale = document.getElementById("sale");
var user = document.getElementById("user");
var accommodated = document.getElementById("accommodated");
var money = document.getElementById("money");

var hotelBtn = document.getElementById("hotelBtn");
var saleBtn = document.getElementById("saleBtn");
var userBtn = document.getElementById("userBtn");
var accommodatedBtn = document.getElementById("accommodatedBtn");
var moneyBtn = document.getElementById("moneyBtn");

function init() {
//	hotelBtn.re
}

function showHotel() {
	hotel.style.visibility = "";
	sale.style.visibility = "hidden";
	user.style.visibility = "hidden";
	accommodated.style.visibility = "hidden";
	money.style.visibility = "hidden";
	document.getElementById("indexmenu").style.height = '580px';
}

function showSale() {
	hotel.style.visibility = "hidden";
	sale.style.visibility = "";
	user.style.visibility = "hidden";
	accommodated.style.visibility = "hidden";
	money.style.visibility = "hidden";
	document.getElementById("indexmenu").style.height = '1500px';
}

function showUser() {
	hotel.style.visibility = "hidden";
	sale.style.visibility = "hidden";
	user.style.visibility = "";
	accommodated.style.visibility = "hidden";
	money.style.visibility = "hidden";
	document.getElementById("indexmenu").style.height = '1500px';
}

function showMoney(balance, charge) {
	hotel.style.visibility = "hidden";
	sale.style.visibility = "hidden";
	user.style.visibility = "hidden";
	accommodated.style.visibility = "hidden";
	money.style.visibility = "";
	document.getElementById("indexmenu").style.height = '1500px';
	
	var payed = charge - balance;
	var profit = payed * 0.25;
	var toPay = charge - payed - profit;
	
	var moneyChart = echarts.init(document.getElementById("moneyChart"));
	var option={
    	    title : {
    	        text: '财务情况',
    	        x:'center'
    	    },
    	    tooltip : {
    	        trigger: 'item',
    	        formatter: "{a} <br/>{b} : {c} ({d}%)"
    	    },
    	    legend: {
    	        x : 'center',
    	        y : 'bottom',
    	        data:['已结算金额','盈利', '待结算金额']
    	    },
    	    toolbox: {
    	        show : false,
    	        feature : {
    	            mark : {show: true},
    	            dataView : {show: true, readOnly: false},
    	            magicType : {
    	                show: true,
    	                type: ['pie', 'funnel']
    	            },
    	            restore : {show: true},
    	            saveAsImage : {show: true}
    	        }
    	    },
    	    calculable : true,
    	    series : [
    	        {
    	            name:'面积模式',
    	            type:'pie',
    	            radius : [30, 110],
    	            center : ['50%', 200],
    	            roseType : 'area',
    	            data:[
    	                {value:payed, name:'已结算金额'},
    	                {value:profit, name:'盈利'},
    	                {value:toPay, name:'待结算金额'}
    	            ]
    	        }
    	    ]
    	};
	moneyChart.setOption(option);
}

function showAccommodated(memberAccommodatedOrderNum, unmemberOrderNum) {
	hotel.style.visibility = "hidden";
	sale.style.visibility = "hidden";
	user.style.visibility = "hidden";
	accommodated.style.visibility = "";
	money.style.visibility = "hidden";
	document.getElementById("indexmenu").style.height = '1500px';
	
	var orderChart = echarts.init(document.getElementById("orderChart"));
	var option={
    	    title : {
    	        text: '会员/非会员入住比例',
    	        x:'center'
    	    },
    	    tooltip : {
    	        trigger: 'item',
    	        formatter: "{a} <br/>{b} : {c} ({d}%)"
    	    },
    	    legend: {
    	        x : 'center',
    	        y : 'bottom',
    	        data:['会员','非会员']
    	    },
    	    toolbox: {
    	        show : false,
    	        feature : {
    	            mark : {show: true},
    	            dataView : {show: true, readOnly: false},
    	            magicType : {
    	                show: true,
    	                type: ['pie', 'funnel']
    	            },
    	            restore : {show: true},
    	            saveAsImage : {show: true}
    	        }
    	    },
    	    calculable : true,
    	    series : [
    	        {
    	            name:'面积模式',
    	            type:'pie',
    	            radius : [30, 110],
    	            center : ['50%', 200],
    	            roseType : 'area',
    	            data:[
    	                {value:memberAccommodatedOrderNum, name:'会员'},
    	                {value:unmemberOrderNum, name:'非会员'}
    	            ]
    	        }
    	    ]
    	};
	orderChart.setOption(option);
}
