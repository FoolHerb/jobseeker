package cn.tentact.nebula.email

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMailMessage
import org.springframework.mail.javamail.MimeMessageHelper
import javax.mail.MessagingException

@Service
class EmailService implements I_EmailService{
	
	@Autowired
	EmailConfig emailConfig;
	
	@Autowired
	JavaMailSender mailSender;

	
	override sendSimpleEmail(String sendTo, String title, String content) {
		
		var message = new SimpleMailMessage();
		message.from = emailConfig.emailFrom;
		message.to = sendTo;
		message.subject = title;
		message.text = content;
		mailSender.send(message);
		println(emailConfig.emailFrom);
	}
	
	override sendHtmlEmail(String sendTo, String title, String content) {
		var message = mailSender.createMimeMessage();
		
		try{
			var helper = new MimeMessageHelper(message , true);
			helper.from = emailConfig.emailFrom;
			helper.to = sendTo;
			helper.subject = title;
			helper.setText(content , true);
			mailSender.send(message);
			
		}catch(MessagingException e){
			e.printStackTrace();
		}
	}
	
}