mui.ready(function(){
	new Vue({
		el:"#app",
		data:{
			name:"上海惠普有限公司",
			type:"上市公司",
			scale:"1000-2000人",
			address:"北京市海淀区中关村东路一技大厦C座25层号院科",
			serviceType:"计算机软件，互联网",
			itro:""
		},
		methods:{
			
		},
		mounted:function(){
			var ref = this;
			var id = localStorage.getItem("companyId");
			$.ajax({
				type:"post",
				url:nebula + "/company/searchCompany",
				data:{
					companyId:id
				},
				beforeSend:function(request){
				request.setRequestHeader("Authorization" , localStorage.getItem("token"));
				},
				success:function(json){
					mui.toast(json.data.name);
					ref.name = json.data.name;
					ref.type = json.data.type;
					ref.scale = json.data.scale;
					ref.address = json.data.address;
					ref.serviceType = json.data.serverType;
					ref.itro = json.data.itroduction;
					$("#logo").attr("src","http://192.168.43.157:9800/"+ id +"company.png");
				},
				error:function(){
					mui.toast("error")
				}
			})
		}
	})
});

mui.plusReady(function(){
	
});

mui.init();