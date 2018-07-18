package cn.tentact.nebula.mongo.dao;

import java.util.Map;

@SuppressWarnings("all")
public interface I_ResumeMongoDao {
  public abstract Map getResumeDetails(final int resume_id);
  
  public abstract int updateResumeDetails(final Map map);
}
