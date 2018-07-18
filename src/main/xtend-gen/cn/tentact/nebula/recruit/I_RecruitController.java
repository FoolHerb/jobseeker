package cn.tentact.nebula.recruit;

import cn.tentact.nebula.web.ResponseBean;

@SuppressWarnings("all")
public interface I_RecruitController {
  /**
   * 查询最新5条招聘信息
   */
  public abstract ResponseBean searchCurrentRecruit(final String token);
  
  public abstract ResponseBean searchLimitRecruit(final String token, final int start, final int end);
  
  public abstract ResponseBean searchRecruitByWord(final String token, final int start, final int end, final String key);
  
  /**
   * 查询特定的招聘信息
   */
  public abstract ResponseBean searchOneRecruit(final String token, final int recruitId);
}
