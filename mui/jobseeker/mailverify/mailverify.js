mui.ready(function(){
	new Vue({
		el:"#app",
		data:{
			email:""
		},
		methods:{
			pass:function(){
				localStorage.setItem("email" , "0");
				mui.openWindow("../index.html" , "index" , {});
			},
			setEmail:function(){
				var email = this.email;
				
				var regex = new RegExp(/^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/);
				if(!regex.test(email))
				{
					mui.toast("格式错误");
					return;
				}
				
				$.ajax({
					type:"post",
					url:nebula + "/user/updateEmail",
					data:{
						email:email
					},
					beforeSend:function(request){
					request.setRequestHeader("Authorization" , localStorage.getItem("token"));
						
					},
					success:function(JSON){
						setTimeout(function(){mui.openWindow("../index.html" , "index" , {})}, 1000);
						
					},
					error:function(error){
						mui.toast("执行错误");
					}
				});
			}
		}
	})
});

mui.plusReady(function(){
	
});

mui.init();