mui.ready(function(){
	new Vue({
		el:"#app",
		data:{
			exp:0,
			resumeCount:0,
			username:localStorage.getItem("username"),
			progress:null,
			recruit:[],
			sended:0
		},
		methods:{
			toRecruitList:function(){
				mui.openWindow("recruit/recruit_list.html" , "recruit/recruit_list.html" , {});
			},
			toResume:function(){
				mui.openWindow("resume/resume.html" , "resume/resume.html" , {});
			},
			toJobDetails:function(s , e){
				var ref = this;
				var obj = $(e.target); //触发事件的元素
				var name = obj.attr("name");
				var element = null;
				if(name == "recruit"){
					element = obj;
				}
				else{
					element = obj.parents("dt[name='recruit']");
				}
				var id = element.data("id");//取出简历ID
				localStorage.setItem("recruitId" , id);
				mui.openWindow("jobDetails/jobDetails.html" , "ID" , {});

			},
			sendResume:function(s , e){
				var ref = this;
				var obj = $(e.target); //触发事件的元素
				var name = obj.attr("name");
				var element = null;
				if(name == "recruit"){
					element = obj;
				}
				else{
					element = obj.parents("dt[name='recruit']");
				}
				var id = element.data("id");//取出简历ID
				mui.confirm("是否投递简历" , "通知消息" , ["取消" , "确定"] , function(e){
					if(e.index == 1){
						console.log(id);
						$.ajax({
							url:nebula + "/resume/sendMyResume",
							type:"post",
							data:{
								recruitId:id
							},
							beforeSend:function(request){
							request.setRequestHeader("Authorization" , localStorage.getItem("token"));
							
							},
							success:function(json){
								if(json.data == -1)
								{
									mui.toast("请先创建简历");
								}
								else if(json.data == 0)
								{
									mui.toast("已投递简历，请不要重复投递");
								}
								else{
									mui.toast("投递成功");
									$.ajax({
										url: nebula + "/level//updateExp_1",
										type:"post",
										data:{},
										beforeSend:function(request){
										request.setRequestHeader("Authorization" , localStorage.getItem("token"));
										},
										success:function(json){
											if(json.data == 1){
												ref.exp += 10;
												ref.progress = "width:" + ref.exp % 1000 / 10 + "%";
												
												mui.toast("经验值加10")
											}
										}
						
									});
									for(one of ref.recruit){
										if(one.id == id){
											one.sended = 1;
											ref.resumeCount++;
										}
									}
								}
							}
						})
					}
				})

			}
		},
		filters:{
			welfareFilter:function(welfare){
				var temp = welfare.split(",");
				if(temp.length > 0){
					return temp[0];
				}
				else{
					return  null;
				}
			}
		},
		//=vue对象初始化完毕后默认执行的回调方法
		mounted:function(){
			//发送AJAX请求
			var ref = this;
			$.ajax({
				url:nebula + "/user/searchSummary",
				type:"post",
				data:{},
				beforeSend:function(request){
					request.setRequestHeader("Authorization" , localStorage.getItem("token"));
					
				},
				success:function(json){
					//alert("123456");
					ref.exp = json.data.exp;
					ref.resumeCount = json.data.resumeCount;
					ref.progress = "width:" + ref.exp%1000 / 10 + "%";
					var img = document.getElementById("photo");
					img.style.backgroundImage = "url(" + "http://192.168.43.157:9800/" + localStorage.getItem("username")+".jpg)"; 
				},
				error:function(error){
					console.log(JSON.stringify(error));
					mui.toast("执行错误");
				}
			});
			
			$.ajax({
				type:"post",
				url:nebula + "/user/searchEmail",
				data:{},
				beforeSend:function(request){
					request.setRequestHeader("Authorization" , localStorage.getItem("token"));
					
				},
				success:function(json){
					if(json.data == 0 && localStorage.getItem("email") == 1){
						mui.openWindow("mailverify/mailverify.html" , "email" , {});
					}
				},
				error:function(error){
					console.log(JSON.stringify(error));
					mui.toast("执行错误");
				}
			});
			
			$.ajax({
				url:nebula + "/recruit/searchCurrentRecruit",
				type:"get",
				beforeSend:function(request){
					request.setRequestHeader("Authorization" ,localStorage.getItem("token"))
				},
				success:function(json){
					if(json.code==200 && json.result == true){
						ref.recruit = json.data;
					}
				},
				error:function(error){
					console.log(JSON.stringify(error));
					mui.toast("执行错误");
				}
			});
		}
	})
});

mui.plusReady(function(){

});

mui.init({
	gestureConfig:{
		longtap:true,
		release:true
	}
});