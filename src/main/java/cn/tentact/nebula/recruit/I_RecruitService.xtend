package cn.tentact.nebula.recruit

import java.util.List
import java.util.Map

interface I_RecruitService {
	
	/**
	 * 根据关键词查询
	 * */
	 def List<Map> searchRecruitByKeyWord(String username , int start , int end , String key);
	
	/**
	 * 查询某一条招聘信息的详情
	 * */
	 
	 def Map searchOneRecruit(int recruitId);
	
	/**
	 * 查询特定范围内招聘信息
	 * */
	 def List<Map> searchLimitRecruit(String username , int start , int end);
}