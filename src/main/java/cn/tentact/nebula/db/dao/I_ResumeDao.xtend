package cn.tentact.nebula.db.dao

import org.apache.ibatis.annotations.Mapper
import java.util.Map

@Mapper
interface I_ResumeDao {
	/**
	 * 查询用户简历id
	 */
	def Integer searchMyResumeId(String username);
	
	/**
	 * 查询用户简历
	 * */
	def Map searchResume(int user_id);
	 
	/**
	 * 更新用户简历
	 * */
	def int updateResume(Map map);
	
	/**
	 * 添加简历
	 * */
	def int insertResume(Map map);
}