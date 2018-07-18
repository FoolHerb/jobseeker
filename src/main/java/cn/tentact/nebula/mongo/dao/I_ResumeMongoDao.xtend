package cn.tentact.nebula.mongo.dao

import java.util.Map

interface I_ResumeMongoDao {
	def Map getResumeDetails(int resume_id);
	
	def int updateResumeDetails(Map map);
}