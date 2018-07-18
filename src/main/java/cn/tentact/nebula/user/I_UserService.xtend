package cn.tentact.nebula.user

import java.util.Map

/**
 * 用户模块业务接口
 */
interface I_UserService {
	/**
	 * 用户登录
	 */
	def boolean login(Map map);
	
	/**
	 * 查询用户概要信息
	 */
	def Map searchSummary(String username);
	
	/**
	 * 用户注册
	 * */
	 
	def int register(Map map); 
	
	/**
	 * 忘记密码，发送密码至邮箱
	 */
	def Map forgetPassword(String key);
	/**
	 * 查询邮箱是否存在
	 */
	def int searchEmail(String username);
	
	/**
	 * 设置邮箱
	 */
	def int updateEmail(Map map);
}
