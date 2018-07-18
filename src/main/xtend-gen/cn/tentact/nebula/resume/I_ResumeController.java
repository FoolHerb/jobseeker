package cn.tentact.nebula.resume;

import cn.tentact.nebula.web.ResponseBean;

@SuppressWarnings("all")
public interface I_ResumeController {
  /**
   * 投递简历
   */
  public abstract ResponseBean sendMyResume(final String token, final int recruitId);
  
  /**
   * 查询简历
   */
  public abstract ResponseBean searchResume(final String token);
  
  /**
   * 上传图像
   */
  public abstract ResponseBean upload(final String token, final String file);
  
  /**
   * 更新用户简历
   */
  public abstract ResponseBean updateResume(final String token, final String name, final String sex, final String Date, final String height, final String weight, final String marry, final String city, final String education, final String tel, final String email, final String position, final String place, final String money, final String worktime, final String eduexp, final String workexp, final String selfintro, final String tech, final String techexp, final String eduexpnum, final String workexpnum);
}
