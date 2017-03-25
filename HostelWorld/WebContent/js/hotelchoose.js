function findHotel(){
			var hotelselect = document.getElementById("hotel").selectedIndex;
			if(document.getElementById("planDate").value=="")
        		alert("请选择入住日期！")
        	else{
        		$.ajax({
                    //提交数据的类型 POST GET
                    type:"POST",
                    //提交的网址
                    url:"/HostelWorld/RoomOrderServlet",
                    //提交的数据
                    data:
                    {
                    	type:"find",
                    	hotel:document.getElementById("hotel")[hotelselect].value,
                    	date:document.getElementById("planDate").value,
                    },
                    //返回数据的格式
                    datatype: "text",//"xml", "html", "script", "json", "jsonp", "text".
                    success:function(data){
                        if(data=="noplan"){
                        	alert("该店当日销售计划尚未制定，无法进行预定！");
                        }
                        else{
                        	window.location.href="onlinehotel.jsp"; 
                        }
                    }
                });
        	}
		}

function logout(){
	$.ajax({
        //提交数据的类型 POST GET
        type:"POST",
        //提交的网址
        url:"/HostelWorld/UserServlet",
        //提交的数据
        data:{type:"logout"
             },
        //返回数据的格式
        datatype: "text",//"xml", "html", "script", "json", "jsonp", "text".
        success:function(data){
        	window.location.href="index.jsp"; 
        }
    });	
}