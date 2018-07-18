package cn.tentact.nebula.resume;

import java.util.Map;

@SuppressWarnings("all")
public interface I_ResumeService {
  /**
   * 投递简历
   */
  public abstract int sendMyResume(final Map map);
  
  /**
   * 查询简历
   */
  public abstract Map searchResume(final String username);
  
  /**
   * 更新用户简历
   */
  public abstract int updateResume(final Map map);
}
