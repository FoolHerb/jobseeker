package cn.tentact.nebula.user

import java.util.Map
import cn.tentact.nebula.db.dao.I_UserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import cn.tentact.nebula.db.dao.I_UserLevelDao
import cn.tentact.nebula.db.dao.I_RecruitResumeDao
import org.springframework.transaction.annotation.Transactional
import cn.tentact.nebula.db.dao.I_ResumeDao
import cn.tentact.nebula.email.I_EmailService

/**
 * 用户模块实现类
 */
@Service
class UserService implements I_UserService {

	@Autowired
	I_UserDao userDao;

	@Autowired
	I_UserLevelDao userLevelDao;

	@Autowired
	I_RecruitResumeDao recruitResumeDao;
	
	@Autowired
	I_ResumeDao resumeDao;
	

	override login(Map map) {
		var count = userDao.login(map);
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}

	override searchSummary(String username) {
		var exp = userLevelDao.searchExp(username); // 查询用户经验值
		var resumeCount = recruitResumeDao.searchMyResumeCount(username); // 查询用户投递简历数量
		return #{"exp" -> exp, "resumeCount" -> resumeCount};
	}
	@Transactional
	override register(Map map) {
		var j = userDao.searchId(map.get("username") as String);
		if(j != null)
			return 0;
		var i = userDao.register(map);
		var userId = userDao.searchId(map.get("username") as String);
		map.put("userId" , userId);
		i = userLevelDao.insertUserLevel(userId) * i;
		i = resumeDao.insertResume(map)*i;
		return i;
	}
	
	override forgetPassword(String key) {
		var map = userDao.forgetPassword(key);	
		println(map);
		
		if(map == null){
			var temp = newHashMap("result"->"无此用户");
			return temp;
		}else{
			map.put("result" , null);
		}
		if(map.get("email") == null && map.get("password") != null){
			map.put("result" , "未设置邮箱")
			return map;
		}
		return map;
	}
	
	override searchEmail(String username) {
		var email = userDao.searchEmail(username);
		if(email == null || email == "")
		{
			println("asdf");
			return 0;
		}	
		return 1;
	}
	
	override updateEmail(Map map) {
		var i = userDao.updateEmail(map);
		return i;	
	}

}
