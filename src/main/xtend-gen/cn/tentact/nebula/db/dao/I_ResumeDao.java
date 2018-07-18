package cn.tentact.nebula.db.dao;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SuppressWarnings("all")
public interface I_ResumeDao {
  /**
   * 查询用户简历id
   */
  public abstract Integer searchMyResumeId(final String username);
  
  /**
   * 查询用户简历
   */
  public abstract Map searchResume(final int user_id);
  
  /**
   * 更新用户简历
   */
  public abstract int updateResume(final Map map);
  
  /**
   * 添加简历
   */
  public abstract int insertResume(final Map map);
}
