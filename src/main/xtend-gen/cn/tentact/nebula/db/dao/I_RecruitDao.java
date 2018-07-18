package cn.tentact.nebula.db.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@SuppressWarnings("all")
public interface I_RecruitDao {
  /**
   * 查询最新5条招聘信息
   */
  public abstract List<Map> searchRecruit(final Map map);
  
  public abstract List<Map> searchRecruitByKeyWord(final Map map);
  
  public abstract int recruitCount();
  
  public abstract Map searchOneRecruit(final int recruitId);
}
