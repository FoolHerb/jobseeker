mui.ready(function(){
	new Vue({
		el:"#app",
		data:{
			job:"前端工程师",
			salary:"3k-6k",
			city:"大连",
			education:"本科以上",
			date:"7-11",
			welfare1:["五险s一金","周末s双休","全s勤","加班s补助"],
			welfare2:["年终奖" , "" , "" , ""],
			companyName:"大连东软",
			companyDetails:"软件服务/股份制企业/3000人以上",
			companyId:0,
			description:""
		},
		methods:{
			toCompany:function(){
				localStorage.setItem("companyId" , this.companyId);
				mui.openWindow("../companyIntroduction/companyIntroduction.html" , "../companyIntroduction/companyIntroduction.html" , {});
			},
			sendResume:function(){
				var ref = this;
				var id = 13;//取出简历ID
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
										url: nebula + "/level/updateExp_1",
										type:"post",
										data:{},
										beforeSend:function(request){
										request.setRequestHeader("Authorization" , localStorage.getItem("token"));
										},
										success:function(json){
										},
										error:function(error){
											console.log(JSON.stringify(error));
											mui.toast("执行错误");
										}
						
									});
								}
							}
						})
					}
				})

			}
		},
		mounted:function(){
			var ref = this;
			var id = localStorage.getItem("recruitId");
			$.ajax({
				type:"post",
				url:nebula + "/recruit/searchOneRecruit",
				data:{
					recruitId : id
				},
				beforeSend:function(request){
				request.setRequestHeader("Authorization" , localStorage.getItem("token"));
							
				},
				success:function(json){
					$("#logo").attr("src","http://192.168.43.157:9800/"+ json.data.id +"logo.png");
					mui.toast(json.data.id);
					ref.job = json.data.job;
					ref.salary = json.data.salary;
					ref.city = json.data.city;
					ref.education = json.data.education;
					ref.date = json.data.date;
					ref.companyId = json.data.id;
					ref.description = "<div> 职位描述</div><ol>" + json.data.description;
					var welfare = json.data.welfare.split(",");
					for(var i = 0 ; i < 4 ; i++){
						ref.welfare1[i] = welfare[i];
					}
					for(var i = 4 ; i < 8 ; i++){
						ref.welfare2[i-4] = welfare[i];
					}
					ref.companyName = json.data.name;
					ref.companyDetails = json.data.serverType + "/" + json.data.type + "/" + json.data.scale;
				},
				error:function(error){
					console.log(JSON.stringify(error));
					mui.toast("执行错误");
				}
			});
		},
		filters:{
			
		}
		
	})
});

mui.plusReady(function(){
	
});

mui.init();
