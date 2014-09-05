package com.aronkatona.manager;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.aronkatona.model.User;
import com.aronkatona.service.UserService;

public class UserManager {

	private UserService userService;
	private JavaMailSender mailSender;
	
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	public void saveUser(User user){
		this.userService.saveUser(user);
	}
	
	public User getUserByName(String userName){
		return this.userService.getUserByName(userName);
	}
	
	public User getUserById(int id){
		return this.userService.getUserById(id);
	}
	
	public User existUser(String userName){
		User user = null;
		user = this.userService.getUserByName(userName);
		return user;
	}
	
	public boolean signUpUser(Map<String,String> reqPar){
		
		String userName = reqPar.get("username");
		String email = reqPar.get("email");
		String password = reqPar.get("password1");

		
		if(existUser(userName) == null){
			User user = new User(userName,email,password);
			saveUser(user);
			String text = "az aktivacios linked: " + user.getActivationString();
			sendMail(email, "udv az oldalon", text);
			return true;
		} else{
			return false;
		}
		
	}
	

	public User loginUser(Map<String, String> reqPar) {
		String userName = reqPar.get("username");
		String password = reqPar.get("password");
		
		User user = this.userService.getUserByName(userName);
		if(user != null && user.getPassword().equals(password)){
			return user;
		} 
		else{
			return null;
		}
	}
	
	public void activateUser(String activationString){
		User user = null;
		user = this.userService.getUserByActivationString(activationString);
		if(user != null){
			user.setActivated(true);
			this.userService.updateUser(user);
		}
	}
	
	
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
