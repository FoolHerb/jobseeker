package cn.tentact.nebula.recruit

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import cn.tentact.nebula.db.dao.I_RecruitDao
import cn.tentact.nebula.db.dao.I_ResumeDao
import java.util.Map
import cn.tentact.nebula.mongo.dao.I_RecruitMongoDao

@Service
class RecruitService implements I_RecruitService {
	@Autowired
	I_RecruitDao recruitDao;
	
	@Autowired
	I_ResumeDao resumeDao;
	
	@Autowired
	I_RecruitMongoDao recruitMongoDao;
	
	override searchLimitRecruit(String username , int start , int end) {
		var resumeId = resumeDao.searchMyResumeId(username);
		if(resumeId == null)
			resumeId = -1;
		var map = newHashMap("resumeId"->resumeId , "start"->start , "count"->{end - start});
		var count = recruitDao.recruitCount();
		if(end > count)
			map.put("count" , (count - start));
		if(start > count)
			map.put("count" , 0);
		var list = recruitDao.searchRecruit(map);
		return list;	
	}
	
	override searchOneRecruit(int recruitId) {
		var map = recruitDao.searchOneRecruit(recruitId);
		var description = recruitMongoDao.getDescription(recruitId.toString);
		map.put("description" , description);
		println(description);
		return map;
	}
	
	override searchRecruitByKeyWord(String username, int start, int end , String key) {
		var resumeId = resumeDao.searchMyResumeId(username);
		if(resumeId == null)
			resumeId = -1;
		var map = newHashMap("resumeId"->resumeId , "start"->start , "count"->{end - start} , "key"->key);
		var list = recruitDao.searchRecruitByKeyWord(map);
		return list;	
	}

}
