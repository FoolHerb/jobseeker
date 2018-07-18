package cn.tentact.nebula.resume;

import cn.tentact.nebula.db.dao.I_RecruitResumeDao;
import cn.tentact.nebula.db.dao.I_ResumeDao;
import cn.tentact.nebula.db.dao.I_UserDao;
import cn.tentact.nebula.mongo.dao.I_ResumeMongoDao;
import cn.tentact.nebula.resume.I_ResumeService;
import com.google.common.base.Objects;
import java.util.Map;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@SuppressWarnings("all")
public class ResumeService implements I_ResumeService {
  @Autowired
  private I_ResumeDao resumeDao;
  
  @Autowired
  private I_UserDao userDao;
  
  @Autowired
  private I_RecruitResumeDao recruitResumeDao;
  
  @Autowired
  private I_ResumeMongoDao resumeMongoDao;
  
  @Transactional
  @Override
  public int sendMyResume(final Map map) {
    Object _get = map.get("username");
    String username = ((String) _get);
    Integer resumeId = this.resumeDao.searchMyResumeId(username);
    boolean _equals = Objects.equal(resumeId, null);
    if (_equals) {
      return (-1);
    }
    map.put("resumeId", resumeId);
    int i = this.recruitResumeDao.add(map);
    return i;
  }
  
  @Override
  public Map searchResume(final String username) {
    Integer user_id = this.userDao.searchId(username);
    Map map = this.resumeDao.searchResume((user_id).intValue());
    Object resume_id = map.get("id");
    int s = Integer.parseInt(resume_id.toString());
    Map temp = this.resumeMongoDao.getResumeDetails(Integer.valueOf(s).intValue());
    boolean _equals = Objects.equal(temp, null);
    if (_equals) {
      return map;
    }
    map.put("eduexp", temp.get("eduexp"));
    map.put("workexp", temp.get("workexp"));
    map.put("selfintro", temp.get("selfintro"));
    map.put("tech", temp.get("tech"));
    map.put("techexp", temp.get("techexp"));
    map.put("eduexpnum", temp.get("eduexpnum"));
    map.put("workexpnum", temp.get("workexpnum"));
    InputOutput.<Object>println(map.get("eduexp"));
    return map;
  }
  
  @Override
  public int updateResume(final Map map) {
    Object _get = map.get("username");
    String username = ((String) _get);
    Integer userId = this.userDao.searchId(username);
    map.put("userId", userId);
    int i = this.resumeDao.updateResume(map);
    Integer resume_id = this.resumeDao.searchMyResumeId(username);
    map.put("resume_id", resume_id);
    InputOutput.<Object>println(map.get("eduexp"));
    this.resumeMongoDao.updateResumeDetails(map);
    return 1;
  }
}
