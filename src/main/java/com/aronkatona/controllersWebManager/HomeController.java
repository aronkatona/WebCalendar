package com.aronkatona.controllersWebManager;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aronkatona.manager.MailManager;
import com.aronkatona.manager.UserManager;
import com.aronkatona.model.RegImage;
import com.aronkatona.model.User;
import com.aronkatona.server.ServerThread;
import com.aronkatona.service.RegImageService;
import com.aronkatona.service.UserService;

@Controller
public class HomeController {

	private UserManager userManager = new UserManager();
	private MailManager mailManager = new MailManager();
	private ServerThread serverThread = ServerThread.getInstance();
	private RegImageService regImageService;

	@Autowired
	public void setMailService(JavaMailSender mailSender){
		this.mailManager.setMailSender(mailSender);
	}

	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService us) {
		this.userManager.setUserService(us);
		this.serverThread.setUserService(us);
	}
	
	@Autowired(required = true)
	@Qualifier(value = "regImageService")
	public void setRegImageService(RegImageService rs) {
		this.regImageService = rs;
	}

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		String image1 = this.regImageService.getRegImageById(1).getSourceName();
		String image2 = this.regImageService.getRegImageById(2).getSourceName();
		model.addAttribute("image1", image1);
		model.addAttribute("image2", image2);
		return "home";
	}
	
	@RequestMapping(value ="/sendMail")
	public String sendActivateMail(Locale locale, Model model){
		
		
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/activate.{activationString}")
	public String activateRegistration(Locale locale,Model model, @PathVariable String activationString){
		this.userManager.activateUser(activationString);
		return "redirect:/";
	}
	
	
	

}
