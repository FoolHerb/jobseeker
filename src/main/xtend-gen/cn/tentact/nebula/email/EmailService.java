package cn.tentact.nebula.email;

import cn.tentact.nebula.email.EmailConfig;
import cn.tentact.nebula.email.I_EmailService;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("all")
public class EmailService implements I_EmailService {
  @Autowired
  private EmailConfig emailConfig;
  
  @Autowired
  private JavaMailSender mailSender;
  
  @Override
  public void sendSimpleEmail(final String sendTo, final String title, final String content) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(this.emailConfig.getEmailFrom());
    message.setTo(sendTo);
    message.setSubject(title);
    message.setText(content);
    this.mailSender.send(message);
    InputOutput.<String>println(this.emailConfig.getEmailFrom());
  }
  
  @Override
  public void sendHtmlEmail(final String sendTo, final String title, final String content) {
    MimeMessage message = this.mailSender.createMimeMessage();
    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setFrom(this.emailConfig.getEmailFrom());
      helper.setTo(sendTo);
      helper.setSubject(title);
      helper.setText(content, true);
      this.mailSender.send(message);
    } catch (final Throwable _t) {
      if (_t instanceof MessagingException) {
        final MessagingException e = (MessagingException)_t;
        e.printStackTrace();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
}
