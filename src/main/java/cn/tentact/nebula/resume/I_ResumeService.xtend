package cn.tentact.nebula.resume

import java.util.Map

interface I_ResumeService {
	
	/**
	 * 投递简历
	 * */
	def int sendMyResume(Map map);
	
	/**
	 * 查询简历
	 * */
	def Map searchResume(String username);
	
	/**
	 * 更新用户简历
	 * */
	def int updateResume(Map map);
}