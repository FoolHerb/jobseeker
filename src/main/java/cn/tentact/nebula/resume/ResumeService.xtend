package cn.tentact.nebula.resume

import org.springframework.stereotype.Service
import java.util.Map
import org.springframework.beans.factory.annotation.Autowired
import cn.tentact.nebula.db.dao.I_ResumeDao
import cn.tentact.nebula.db.dao.I_RecruitResumeDao
import org.springframework.transaction.annotation.Transactional
import cn.tentact.nebula.db.dao.I_UserDao
import cn.tentact.nebula.mongo.dao.I_RecruitMongoDao
import cn.tentact.nebula.mongo.dao.I_ResumeMongoDao

@Service
class ResumeService implements I_ResumeService{
	@Autowired
	I_ResumeDao resumeDao;
	
	@Autowired
	I_UserDao userDao;
		
	@Autowired
	I_RecruitResumeDao recruitResumeDao;
	
	@Autowired
	I_ResumeMongoDao resumeMongoDao;
	//统一事物，管理多个dao为一个事物
	@Transactional
	override sendMyResume(Map map) {

		var username=map.get("username") as String;
		var resumeId = resumeDao.searchMyResumeId(username); //查询我的简历
		if(resumeId==null){
			return -1; //用户没有撰写简历
		}
		map.put("resumeId" , resumeId);
		var i=recruitResumeDao.add(map);
		return i;
	}
	
	override searchResume(String username) {
		
		var user_id = userDao.searchId(username); 
		var map = resumeDao.searchResume(user_id);
		var resume_id = map.get("id");
		var s =  Integer.parseInt(resume_id.toString);
		var temp = resumeMongoDao.getResumeDetails(s.intValue);
		if(temp == null)
		{
			return map;
		}
		map.put("eduexp" , temp.get("eduexp"));
		map.put("workexp" , temp.get("workexp"));
		map.put("selfintro" , temp.get("selfintro"));
		map.put("tech" , temp.get("tech"));
		map.put("techexp" , temp.get("techexp"));
		map.put("eduexpnum" , temp.get("eduexpnum"));
		map.put("workexpnum" , temp.get("workexpnum"));
		println(map.get("eduexp"));
		return map;	
	}
	
	override updateResume(Map map) {
		var username =  map.get("username") as String;
		var userId = userDao.searchId(username);
		map.put("userId" , userId);
		var i = resumeDao.updateResume(map);
		var resume_id = resumeDao.searchMyResumeId(username);
		map.put("resume_id" , resume_id);
		println(map.get("eduexp"));
		resumeMongoDao.updateResumeDetails(map);
		return 1;	
	}
	
}