package com.aronkatona.manager;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class MailManager {

	private JavaMailSender mailSender;
	
	public void setMailSender(JavaMailSender mailSender){
		this.mailSender = mailSender;
	}
	
	public void sendMail(final String address, final String subject, final String text){
		mailSender.send(new MimeMessagePreparator() {
			  public void prepare(MimeMessage mimeMessage) throws MessagingException {
			    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			    message.setTo(address);
			    message.setSubject(subject);
			    message.setText(text, true);
			  }
			});
	}
	
}
