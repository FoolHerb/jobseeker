package cn.tentact.nebula.email;

@SuppressWarnings("all")
public interface I_EmailService {
  /**
   * 发送简单邮
   * @param sendTo 收件人地
   * @param titel  邮件标题
   * @param content 邮件内容
   */
  public abstract void sendSimpleEmail(final String sendTo, final String title, final String content);
  
  public abstract void sendHtmlEmail(final String sendTo, final String title, final String content);
}
