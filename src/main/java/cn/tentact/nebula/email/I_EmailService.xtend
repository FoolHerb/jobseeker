package cn.tentact.nebula.email

interface I_EmailService {

	/**
	 * 发送简单邮
	 * @param sendTo 收件人地
	 * @param titel  邮件标题
	 * @param content 邮件内容
	 */
	 def void sendSimpleEmail(String sendTo , String title , String content);
	 
	 def void sendHtmlEmail(String sendTo , String title , String content);
}