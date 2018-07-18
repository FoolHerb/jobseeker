package cn.tentact.nebula.recruit

import cn.tentact.nebula.web.ResponseBean

interface I_RecruitController {
	/**
	 * 查询最新5条招聘信息
	 */
	def ResponseBean searchCurrentRecruit(String token);
	
	def ResponseBean searchLimitRecruit(String token , int start , int end);
	
	def ResponseBean searchRecruitByWord(String token , int start , int end , String key);

	/**
	 * 查询特定的招聘信息
	 * */
	def ResponseBean searchOneRecruit(String token , int recruitId);
}