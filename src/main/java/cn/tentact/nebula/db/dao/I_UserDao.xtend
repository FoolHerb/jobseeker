package cn.tentact.nebula.db.dao

import org.apache.ibatis.annotations.Mapper
import java.util.Map

@Mapper
interface I_UserDao {
	
	def long login(Map map);
	
	def Integer searchId(String username);
	
	def int register(Map map);
	
	def Map forgetPassword(String key);
	
	def String searchEmail(String username);
	
	def int updateEmail(Map map);
}