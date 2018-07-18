package cn.tentact.nebula.resume

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestHeader
import cn.tentact.nebula.shiro.JwtUtil
import cn.tentact.nebula.web.ResponseBean
import org.apache.shiro.authz.annotation.RequiresRoles
import java.util.Arrays
import java.util.List
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import java.util.ArrayList
import java.util.Map
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.FileInputStream
import java.io.IOException
import sun.misc.BASE64Decoder

@RestController
@RequestMapping("/resume")
class ResumeController implements I_ResumeController{
	
	@Autowired
	I_ResumeService resumeService;
	
	@RequiresRoles("求职者")
	@RequestMapping("/sendMyResume")	
	override sendMyResume(@RequestHeader("Authorization") String token, int recruitId) {
		var username = JwtUtil.getUsername(token);
		var map = newHashMap("username"->username , "recruitId"->recruitId);
		
		var i = resumeService.sendMyResume(map);
		return new ResponseBean(200 , "投递结果" , true , i);
	}
	
	
	@RequiresRoles("求职者")
	@RequestMapping("/searchResume")
	override searchResume(@RequestHeader("Authorization") String token) {
			var username = JwtUtil.getUsername(token);
			var map = resumeService.searchResume(username);
			return new ResponseBean(200 , "查询结果" , true , map);
	}
	
	
	@RequiresRoles("求职者")
	@RequestMapping("/updateResume")
	override updateResume(@RequestHeader("Authorization") String token, String name, String sex, String Date, String height, String weight, String marry, String city, String education, String tel, String email, String position, String place, String money, String worktime ,String eduexp, String workexp,String selfintro, String tech,String techexp,String eduexpnum,String workexpnum) {
		var username = JwtUtil.getUsername(token);
		var gson = new Gson();
		var stus = new ArrayList<Map>();
		var stus2 = new ArrayList<Map>();
		var parser = new JsonParser();//创建一个JsonParser
		var parser2 = new JsonParser();
		var jarray = parser.parse(workexp).getAsJsonArray();
		var jarray2 = parser2.parse(eduexp).getAsJsonArray();
		for(JsonElement jsonelement:jarray){
			stus.add(gson.fromJson(jsonelement, Map));
		}
		stus.forEach[one|println(one)];
		for(JsonElement jsonelement:jarray2){
			stus2.add(gson.fromJson(jsonelement, Map));
		}
		stus2.forEach[one|println(one)];
		var map = newHashMap("username"->username  ,"workexp"->stus, "eduexp"->stus2 , "name"->name , "sex"->sex , "Date"->Date , "height"->height , "weight"->weight , "marry"->marry , "city"->city , "education"->education , "tel"->tel , "email"->email , "position"->position , "place"->place , "money"->money ,"worktime"->worktime);
		map.put("selfintro" , selfintro);
		map.put("tech" , tech);
		map.put("techexp" , techexp);
		map.put("eduexpnum" , eduexpnum);
		map.put("workexpnum" , workexpnum);
		var i = resumeService.updateResume(map);
		return new ResponseBean(200 , "更新结果" , true , 1);
	}
	
	@RequestMapping(value = "/upload",method = RequestMethod.POST)
	override upload(@RequestHeader("Authorization") String token,String file) {
		var username = JwtUtil.getUsername(token);
		var decoder = new BASE64Decoder();
		println(file);
		try {
			 // Base64解码
			 var b = decoder.decodeBuffer(file);
			 println(file);
			 for (var i=0; i < b.length;i++){
				if (b.get(i)< 0){// 调整异常数据
					var tmp = b.get(i);
					tmp=(b.get(i)+256) as byte;
					b.set(i,tmp);
				}
			}
			var path = "D://image//" + username + ".jpg";
			var out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return new ResponseBean(200 , "更新结果" , true , 1);
		
	
    }	
}