package cn.tentact.nebula.recruit;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public interface I_RecruitService {
  /**
   * 根据关键词查询
   */
  public abstract List<Map> searchRecruitByKeyWord(final String username, final int start, final int end, final String key);
  
  /**
   * 查询某一条招聘信息的详情
   */
  public abstract Map searchOneRecruit(final int recruitId);
  
  /**
   * 查询特定范围内招聘信息
   */
  public abstract List<Map> searchLimitRecruit(final String username, final int start, final int end);
}
