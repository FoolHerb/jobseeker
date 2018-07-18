package cn.tentact.nebula.user;

import cn.tentact.nebula.db.dao.I_RecruitResumeDao;
import cn.tentact.nebula.db.dao.I_ResumeDao;
import cn.tentact.nebula.db.dao.I_UserDao;
import cn.tentact.nebula.db.dao.I_UserLevelDao;
import cn.tentact.nebula.user.I_UserService;
import com.google.common.base.Objects;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户模块实现类
 */
@Service
@SuppressWarnings("all")
public class UserService implements I_UserService {
  @Autowired
  private I_UserDao userDao;
  
  @Autowired
  private I_UserLevelDao userLevelDao;
  
  @Autowired
  private I_RecruitResumeDao recruitResumeDao;
  
  @Autowired
  private I_ResumeDao resumeDao;
  
  @Override
  public boolean login(final Map map) {
    long count = this.userDao.login(map);
    if ((count == 1)) {
      return true;
    } else {
      return false;
    }
  }
  
  @Override
  public Map searchSummary(final String username) {
    int exp = this.userLevelDao.searchExp(username);
    long resumeCount = this.recruitResumeDao.searchMyResumeCount(username);
    Pair<String, Integer> _mappedTo = Pair.<String, Integer>of("exp", Integer.valueOf(exp));
    Pair<String, Long> _mappedTo_1 = Pair.<String, Long>of("resumeCount", Long.valueOf(resumeCount));
    return Collections.<String, Number>unmodifiableMap(CollectionLiterals.<String, Number>newHashMap(_mappedTo, _mappedTo_1));
  }
  
  @Transactional
  @Override
  public int register(final Map map) {
    Object _get = map.get("username");
    Integer j = this.userDao.searchId(((String) _get));
    boolean _notEquals = (!Objects.equal(j, null));
    if (_notEquals) {
      return 0;
    }
    int i = this.userDao.register(map);
    Object _get_1 = map.get("username");
    Integer userId = this.userDao.searchId(((String) _get_1));
    map.put("userId", userId);
    int _insertUserLevel = this.userLevelDao.insertUserLevel((userId).intValue());
    int _multiply = (_insertUserLevel * i);
    i = _multiply;
    int _insertResume = this.resumeDao.insertResume(map);
    int _multiply_1 = (_insertResume * i);
    i = _multiply_1;
    return i;
  }
  
  @Override
  public Map forgetPassword(final String key) {
    Map map = this.userDao.forgetPassword(key);
    InputOutput.<Map>println(map);
    boolean _equals = Objects.equal(map, null);
    if (_equals) {
      Pair<String, String> _mappedTo = Pair.<String, String>of("result", "无此用户");
      HashMap<String, String> temp = CollectionLiterals.<String, String>newHashMap(_mappedTo);
      return temp;
    } else {
      map.put("result", null);
    }
    if ((Objects.equal(map.get("email"), null) && (!Objects.equal(map.get("password"), null)))) {
      map.put("result", "未设置邮箱");
      return map;
    }
    return map;
  }
  
  @Override
  public int searchEmail(final String username) {
    String email = this.userDao.searchEmail(username);
    if ((Objects.equal(email, null) || Objects.equal(email, ""))) {
      InputOutput.<String>println("asdf");
      return 0;
    }
    return 1;
  }
  
  @Override
  public int updateEmail(final Map map) {
    int i = this.userDao.updateEmail(map);
    return i;
  }
}
