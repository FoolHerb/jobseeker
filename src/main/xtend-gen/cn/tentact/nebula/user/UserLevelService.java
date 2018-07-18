package cn.tentact.nebula.user;

import cn.tentact.nebula.db.dao.I_UserDao;
import cn.tentact.nebula.db.dao.I_UserLevelDao;
import cn.tentact.nebula.user.I_UserLevelService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("all")
public class UserLevelService implements I_UserLevelService {
  @Autowired
  private I_UserDao userDao;
  
  @Autowired
  private I_UserLevelDao userLevelDao;
  
  @Override
  public int updateExp(final Map map) {
    Object _get = map.get("username");
    String username = ((String) _get);
    Integer userId = this.userDao.searchId(username);
    map.put("userId", userId);
    int i = this.userLevelDao.updateExp(map);
    return i;
  }
}
