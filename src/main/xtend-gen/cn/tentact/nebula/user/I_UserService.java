package cn.tentact.nebula.user;

import java.util.Map;

/**
 * 用户模块业务接口
 */
@SuppressWarnings("all")
public interface I_UserService {
  /**
   * 用户登录
   */
  public abstract boolean login(final Map map);
  
  /**
   * 查询用户概要信息
   */
  public abstract Map searchSummary(final String username);
  
  /**
   * 用户注册
   */
  public abstract int register(final Map map);
  
  /**
   * 忘记密码，发送密码至邮箱
   */
  public abstract Map forgetPassword(final String key);
  
  /**
   * 查询邮箱是否存在
   */
  public abstract int searchEmail(final String username);
  
  /**
   * 设置邮箱
   */
  public abstract int updateEmail(final Map map);
}
