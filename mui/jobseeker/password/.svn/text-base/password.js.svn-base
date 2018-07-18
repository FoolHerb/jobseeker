mui.ready(function(){
	new Vue({
		el:"#app",
		data:{
			nameOrEmail:""
		},
		methods:{
			send:function(){
				var text = this.nameOrEmail;
				$.ajax({
					type:"post",
					url:nebula + "/user/forgetPassword",
					data:{
						key:text
					},
					success:function(JSON){
						mui.toast(JSON.data);
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