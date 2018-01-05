
	window.jQuery || document.write('<script src="js/jquery.js"><\/script>');

	window.addEventListener("load", OnPageLoad, false);
    

	function OnPageLoad() {

		alert("加载完成");
		document.addEventListener("click", OnDeviceReady, false);
	}
	function OnDeviceReady(event) {
        alert("进入OnDeviceReady方法");
		//判断是否支持
		if (navigator.geolocation) {
			var option ={frequence:6000};
			navigator.geolocation.watchPosition(OnSuccessLocation,OnErrorLocation,option);

		} else {
			$("#msgLocation").html("纬度——>"+y+"经度-->"+x).css({
				'color' : 'red'
			});
		}
	}
	
	function OnSuccessLocation(position) {
		//获取位置
		alert("进入获取位置方法")		
		var x = position.coords.longitude;
		var y = position.coords.latitude;
		var cityObj = new BMap.LocalCity();
		
		cityObj.get(function(r)
				{
			      window.city=r.name;
				});
		$("#msgLocation").html("纬度-->" + y + ",经度-->" + x +",城市-->"+city).css({
			'color' : 'blue'
		});
		
		$.ajax({				
			type:"post",
			/**url:"update_action.action",*/
			url:"hbinsert_action.action",
			data:{"latitude":y,"longitude":x,"city":city},
			dataType:'json',//返回jsonp数据对象
			beforeSend:function()//发送请求前
			{					
				
		    },
			success:function(data)//接受服务器端返回的数据
			{
				$("#msgLocation").html("纬度-->" + data.latitude + ",经度-->" + data.longitude+",所在城市-->"+data.city).css({
					'color' : 'blue'
				});	
					
			},
			error:function()
			{
				alert("wrong");
			}
			
		});
		var map = new BMap.Map("container");              
		var point = new BMap.Point(x,y);   
		map.centerAndZoom(point, 15);                      
		var traffic = new BMap.TrafficLayer();          
		map.addTileLayer(traffic);                    
		//map.removeTileLayer(traffic);    

	}
	function OnErrorLocation(){
		alert("获取定位失败");
	}