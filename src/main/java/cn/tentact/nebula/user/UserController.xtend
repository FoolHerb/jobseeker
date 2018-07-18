package cn.tentact.nebula.user

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.beans.factory.annotation.Autowired
import cn.tentact.nebula.web.ResponseBean
import cn.tentact.nebula.shiro.JwtUtil
import org.springframework.data.redis.core.RedisTemplate
import java.util.concurrent.TimeUnit
import org.springframework.beans.factory.annotation.Qualifier
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authz.annotation.RequiresAuthentication
import cn.tentact.nebula.shiro.JwtToken
import org.springframework.web.bind.annotation.RequestHeader
import org.apache.shiro.authz.annotation.RequiresRoles
import cn.tentact.nebula.email.I_EmailService

/**
 * 用户模块网络类
 */
@RestController
@RequestMapping("/user")
class UserController implements I_UserController {
	@Qualifier("redisTemplate")
	@Autowired
	RedisTemplate redis;

	@Autowired
	I_UserService userService;
	
	@Autowired
	I_EmailService emailService;

	@RequestMapping("/login")
	override login(String username, String password) {
		var bool = userService.login(#{"username" -> username, "password" -> password});
		if(bool) {
			var token = JwtUtil.sign(username, password); // 生成令牌
			// 把令牌缓存到Redis中
			redis.opsForHash.putAll(username, #{"token" -> token, "password" -> password});
			redis.expire(username, JwtUtil.EXPIRE_TIME, TimeUnit.MILLISECONDS); // 缓存令牌的过期时间
			return new ResponseBean(200, "登录成功", bool, #{"token" -> token});
		} else {
			return new ResponseBean(200, "登录失败", bool, null);
		}

	}
	
	@RequestMapping("/searchSummary")
	@RequiresRoles("求职者")
	override searchSummary(@RequestHeader("Authorization") String token) {
		var username=JwtUtil.getUsername(token);
		var map = userService.searchSummary(username);
		return new ResponseBean(200, "查询成功", true, map);
	}
	
	@RequestMapping("/register")
	override register(String username, String password) {
		var map = newHashMap("username"->username , "password"->password);
		var i = userService.register(map);
		return new ResponseBean(200 , "注册结果" , true , i);
	}
	
	
	
	
	@RequestMapping("/forgetPassword")
	override forgetPassword(String key) {
		var map = userService.forgetPassword(key);
		if(map.get("email") == null || map.get("password") == null)
			return new ResponseBean(200 , "邮件发送结果" , true , map.get("result") as String);	
		var password = map.get("password") as String;
		//println(password);
		var sendTo = map.get("email") as String;
		emailService.sendHtmlEmail(sendTo , "密码" , password);
		return new ResponseBean(200 , "邮件发送结果" , true , "发送成功");	
	}
	
	@RequestMapping("/setPassword")
	override setpassword(String password) {
		println(password);
		return new ResponseBean(200 , "邮件发送结果" , true , "修改成功");
	}
	
	@RequestMapping("/searchEmail")
	@RequiresRoles("求职者")
	override searchEmail(@RequestHeader("Authorization") String token) {
		var username = JwtUtil.getUsername(token);
		var i = userService.searchEmail(username);
		return new ResponseBean(200 , "查询结果" , true , i);
	}
	
	@RequestMapping("/updateEmail")
	@RequiresRoles("求职者")
	override updateEmail(@RequestHeader("Authorization")String token, String email) {
		var username = JwtUtil.getUsername(token);
		var map = newHashMap("username"->username , "email"->email);
		var i = userService.updateEmail(map);	
		return new ResponseBean(200 , "设置结果" , true , i);
	}

}
