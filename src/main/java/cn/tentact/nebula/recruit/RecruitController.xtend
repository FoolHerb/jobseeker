package cn.tentact.nebula.recruit

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import cn.tentact.nebula.web.ResponseBean
import org.apache.shiro.authz.annotation.RequiresRoles
import org.springframework.web.bind.annotation.RequestHeader
import cn.tentact.nebula.shiro.JwtUtil

@RestController
@RequestMapping("/recruit")
class RecruitController implements I_RecruitController {
	@Autowired
	I_RecruitService recruitService;

	@RequiresRoles("求职者")
	@RequestMapping("/searchCurrentRecruit")
	override searchCurrentRecruit(@RequestHeader("Authorization") String token) {
		var username = JwtUtil.getUsername(token);
		var map = newHashMap("start"->0 , "end"->5);
		var list = recruitService.searchLimitRecruit(username ,0 , 5);
		return new ResponseBean(200 , "招聘信息" , true , list);
	}
	
	@RequiresRoles("求职者")
	@RequestMapping("/searchLimitRecruit")
	override searchLimitRecruit(@RequestHeader("Authorization") String token, int start, int end) {		
		
		var username = JwtUtil.getUsername(token);
		var map = newHashMap("start"->start , "end"->end);
		var list = recruitService.searchLimitRecruit(username ,start , end);
		return new ResponseBean(200 , "招聘信息" , true , list);
	}
	
	@RequiresRoles("求职者")
	@RequestMapping("/searchOneRecruit")
	override searchOneRecruit(@RequestHeader("Authorization") String token,int recruitId) {
		var map = recruitService.searchOneRecruit(recruitId);
		return new ResponseBean(200 , "招聘信息" , true , map);
	}
	
	@RequiresRoles("求职者")
	@RequestMapping("/searchRecruitByKey")
	override searchRecruitByWord(@RequestHeader("Authorization") String token, int start, int end, String key) {
		var username = JwtUtil.getUsername(token);
		println(key);
		if(key == null){
			var list = recruitService.searchLimitRecruit(username , start , end);
			return new ResponseBean(200 , "招聘信息" , true , list);
		}
		else{
			var list = recruitService.searchRecruitByKeyWord(username ,start , end , key);
			return new ResponseBean(200 , "招聘信息" , true , list);
		}
	}

}
