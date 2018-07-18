//界面渲染成功的回调函数
mui.ready(function(){
	//打电话
	var btn1 = document.getElementById("btn1");
	btn1.addEventListener("tap",function(){
		plus.device.dial("10086" , false);
	});
	//发短信
	var btn2 = document.getElementById("btn2");
	btn2.addEventListener("tap" , function(){
		var msg = plus.messaging.createMessage(plus.messaging.TYPE_SMS);
		msg.to = ['13591635100'];
		msg.body = "hello world";
		plus.messaging.sendMessage(msg);
	});
	
	var btn3 = document.getElementById("btn3");
	btn3.addEventListener("tap" , function(){
		var cmr = plus.camera.getCamera();
		var res = cmr.supportedImageResolutions[0];
		var fmt = cmr.supportedImageFormats[0];
		cmr.captureImage(function(path){
			alert(path);
		} , function(){
			alert("执行错误");
		},
		{
			resolution:res,
			format:fmt
		})
	});
	
}); 

//硬件资源初始化成功的回调函数,硬件先初始化
mui.plusReady(function(){

});

mui.init(); //硬件和界面的初始化 