package cn.tentact.nebula.db.dao;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SuppressWarnings("all")
public interface I_UserDao {
  public abstract long login(final Map map);
  
  public abstract Integer searchId(final String username);
  
  public abstract int register(final Map map);
  
  public abstract Map forgetPassword(final String key);
  
  public abstract String searchEmail(final String username);
  
  public abstract int updateEmail(final Map map);
}
