package cn.tentact.nebula.db.dao

import org.apache.ibatis.annotations.Mapper
import java.util.List
import java.util.Map

@Mapper
interface I_RecruitDao {
	/**
	 * 查询最新5条招聘信息
	 */
	def List<Map> searchRecruit(Map map);
	
	def List<Map> searchRecruitByKeyWord(Map map);
	
	def int recruitCount();
	
	def Map searchOneRecruit(int recruitId);
}
