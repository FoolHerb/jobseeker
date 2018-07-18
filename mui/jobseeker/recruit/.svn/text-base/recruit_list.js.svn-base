mui.ready(function(){
	new Vue({
		el: "#app",
		data:{
			recruit:[],
			start:10,
			end:15,
			key:"",
			state:"",
			by:0
		},
		methods:{
			orderByDate:function(){
				if(this.by == 0){
					this.by = 1
				}else if(this.by == 1){
					this.by = 0
				}
				var re = this.recruit;
				this.recruit = [];
				for(var i = 0 ; i <re.length - 1 ; i++)
				{
					for(var j = i + 1 ; j < re.length ; j++){
						
						if(this.by == 0 && re[i].date < re[j].date){
							var temp = re[i];
							re[i] = re[j];
							re[j] = temp;
						}
						if(this.by == 1 && re[i].date > re[j].date){
							var temp = re[i];
							re[i] = re[j];
							re[j] = temp;
						}
					}
				}
				this.recruit = re;
			},
			order:function(){
				if(this.by == 0){
					this.by = 1
				}else if(this.by == 1){
					this.by = 0
				}
				var re = this.recruit;
				this.recruit = [];
				for(var i = 0 ; i <re.length - 1 ; i++)
				{
					for(var j = i + 1 ; j < re.length ; j++){
						
						if(this.by == 0 && re[i].salary.split("k")[0] < re[j].salary.split("k")[0]){
							var temp = re[i];
							re[i] = re[j];
							re[j] = temp;
						}
						if(this.by == 1 && re[i].salary.split("k")[0] > re[j].salary.split("k")[0]){
							var temp = re[i];
							re[i] = re[j];
							re[j] = temp;
						}
					}
				}
				this.recruit = re;
			},
			search:function(){
				this.state = "1";
				var ref = this;
				ref.start = 10;
				ref.end = 15;
				$.ajax({
					type:"post",
					url:nebula + "/recruit/searchRecruitByKey",
					data:{
					key:ref.key,
					start:0,
					end:10
					},
					beforeSend:function(request){
					request.setRequestHeader("Authorization" , localStorage.getItem("token"));
						
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
				mui.openWindow("../jobDetails/jobDetails.html" , "ID" , {});

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
										url: nebula + "/level/updateExp_1",
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
		mounted:function(){
			this.state = "0";
			var ref = this;
			$.ajax({
				url:nebula + "/recruit/searchLimitRecruit",
				type:"get",
				data:{
					start:0,
					end:10
				},
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
			document.addEventListener("plusscrollbottom" , function(){
				if(ref.state == "0"){
					$.ajax({
				url:nebula + "/recruit/searchLimitRecruit",
				type:"get",
				data:{
					start:ref.start,
					end:ref.end
				},
				beforeSend:function(request){
					request.setRequestHeader("Authorization" ,localStorage.getItem("token"))
				},
				success:function(json){
					if(json.code==200 && json.result == true){
						ref.start = ref.start + 5;
						ref.end = ref.end + 5;
						for(one of json.data)
							ref.recruit.push(one);
					}
				},
				error:function(error){
					console.log(JSON.stringify(error));
					mui.toast("执行错误");	
				}
			});
	
				}
				else{
							$.ajax({
				url:nebula + "/recruit/searchRecruitByKey",
				type:"get",
				data:{
					start:ref.start,
					end:ref.end,
					key:ref.key
				},
				beforeSend:function(request){
					request.setRequestHeader("Authorization" ,localStorage.getItem("token"))
				},
				success:function(json){
					if(json.code==200 && json.result == true){
						ref.start = ref.start + 5;
						ref.end = ref.end + 5;
						for(one of json.data)
							ref.recruit.push(one);
					}
				},
				error:function(error){
					console.log(JSON.stringify(error));
					mui.toast("执行错误");	
				}
			});	
				}
				
			})
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
		}
	})
	new Vue({
		el:"#head",
		data:{
			
		}
	})
})

function sss(){
return;
}

mui.plusReady(function(){
	
})

mui.init({
	  pullRefresh : {
    container:"#app",//下拉刷新容器标识，querySelector能定位的css选择器均可，比如：id、.class等
    up : {
      style:'circle',//必选，下拉刷新样式，目前支持原生5+ ‘circle’ 样式
      callback :sss //必选，刷新函数，根据具体业务来编写，比如通过ajax从服务器获取新数据；
    }
}
});
