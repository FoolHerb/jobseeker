mui.ready(function(){
	new Vue({
		el:"#app",
		data:
		{
			address:"大连市甘井子区金虹东路生态科技城D1一层腾泰科技",
			username: null,
			city:null,
			role:[],
			age:null,
			student:[
			{name:"李强" , sex:"男" , age:"20"},
			{name:"赵娜" , sex:"女" , age:"20"},
			{name:"陈强" , sex:"男" , age:"20"},
			{name:"王强" , sex:"女" , age:"20"}
			],
			level:"警告"
		},
		methods:{
			sayhello:function(){
				alert("hello world")
			}
		},
		//过滤器
		filters:{
			hideName:function(name){
				var firstName = name.charAt(0);
				var temp = firstName;
				for(var i = 1 ; i < name.length ; i++)
				{
					temp += "*";
				}
				return temp;
			}
		}
	});
});

mui.plusReady(function(){
	
});

mui.init();