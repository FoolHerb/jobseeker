package cn.tentact.nebula.email

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class EmailConfig {
	/**
	 * 发件邮箱
	 */
	@Value("${spring.mail.username}")
	private String emailFrom;
	
	def String getEmailFrom(){
		return emailFrom;
	}
	
	def void setEmailFrom(String emailFrom){
		this.emailFrom = emailFrom;
	}
}