package cn.tentact.nebula.user

import cn.tentact.nebula.web.ResponseBean

/**
 * 用户模块网络接口
 */
interface I_UserController {
	/**
	 * 用户登录
	 */
	def ResponseBean login(String username, String password);
	
	/**
	 * 查询用户摘要信息
	 */
	def ResponseBean searchSummary(String token);
	
	/**
	 * 用户注册
	 * */
	 def ResponseBean register(String username , String password);
	/**
	 * 忘记密码
	 */
	def ResponseBean forgetPassword(String key);

	/**
	 * 重置密码
	 */
	 def ResponseBean setpassword(String password);
	
	/**
	 * 查询用户邮箱
	 */ 
	def ResponseBean searchEmail(String token);
	
	/**
	 * 设置用户邮箱
	 */
	def ResponseBean updateEmail(String token , String email);
}
