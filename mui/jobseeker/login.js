mui.ready(function(){
	new Vue({
		el:"#app",
		data:{
			showPanel: "login",
			login:{
				username:"",
				password:"",
			},
			register:{
					username:"",
					password:"",
					repassword:""
				}
		},
		methods:{			
			showLoginPanel:function(){
				this.showPanel = "login";
			},
			showRegisterPanel:function(){
				this.showPanel = "register";
			},
			registerd:function(){
				var bool = checkUsername(this.register.username);
				bool = checkPassword(this.register.password)&&bool;
				if(this.register.password == this.register.repassword){
					bool = bool && true;
				}else{
					bool = false;
				}
				
				if(bool){
					var ref = this;
					$.ajax({
						type:"post",
						//192.168.43.157
						url:nebula + "/user/register",
						data:{
							username:ref.register.username,
							password:ref.register.password
						}, 
						success:function(json){
							alert(json);
							if(json.code==200&&json.data == 1){
								//mui.toast(json.msg);
								mui.toast("注册成功请登陆");
								mui.openWindow("login.html" , "login" , {});
							}
						},
						error:function(error){
							mui.toast("执行错误");
							console.log(JSON.stringify(error));
						}
					});
				}else{
					mui.toast("error");
				}
			},
			loginSystem:function(){
				var bool = checkUsername(this.login.username);
				bool = checkPassword(this.login.password)&&bool;
				var ref = this;
				if(bool){
					$.ajax({
						type:"post",
						//192.168.43.157
						url:nebula + "/user/login",
						data:{
							username:ref.login.username,
							password:ref.login.password
						}, 
						success:function(json){
							if(json.code==200&&json.result==true){
								//mui.toast(json.msg);
								mui.toast(json.data.token);
								localStorage.setItem("token",json.data.token);//保存令牌
								localStorage.setItem("username" , ref.login.username);//保存用户名
								localStorage.setItem("email" , 1);
								mui.openWindow("index.html" , "index.html" , {});//名字  界面 参数
							}
						},
						error:function(error){
							mui.toast("执行错误");
							console.log(JSON.stringify(error));
						}
					});
				}else{
					mui.toast("账号密码有误");
				}
			}
		}
	});
});

mui.plusReady(function(){
	plus.navigator.setStatusBarBackground("#007AFF");//顶部状态栏背景色
	plus.navigator.setStatusBarStyle("light");//顶部文字白色
	plus.screen.lockOrientation("portrait-primary");//禁止横屏转换
	//隐藏滚动条
	plus.webview.currentWebview().setStyle({
		scrollIndicator:'none'
	})
});

mui.init();
