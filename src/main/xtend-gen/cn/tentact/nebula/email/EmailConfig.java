package cn.tentact.nebula.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("all")
public class EmailConfig {
  /**
   * 发件邮箱
   */
  @Value("${spring.mail.username}")
  private String emailFrom;
  
  public String getEmailFrom() {
    return this.emailFrom;
  }
  
  public void setEmailFrom(final String emailFrom) {
    this.emailFrom = emailFrom;
  }
}
