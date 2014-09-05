package com.aronkatona.controllersWebManager;

import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aronkatona.manager.UserManager;
import com.aronkatona.model.RegImage;
import com.aronkatona.model.User;
import com.aronkatona.server.ServerThread;
import com.aronkatona.service.RegImageService;
import com.aronkatona.service.UserService;

@Controller
public class HomeController {

	private UserManager userManager = new UserManager();
	private ServerThread serverThread = ServerThread.getInstance();
	private RegImageService regImageService;

	@Autowired
	public void setMailService(JavaMailSender mailSender){
		this.userManager.setMailSender(mailSender);
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
	public String home(Locale locale, Model model,HttpSession session) {
		model.addAttribute("successSignUp",session.getAttribute("successSignUp"));
		session.setAttribute("successSignUp", "");
		model.addAttribute("successLogin", session.getAttribute("successLogin"));
		session.setAttribute("successLogin","");
		
		if(session.getAttribute("userName") == null){
			session.setAttribute("userName", "");
		}
		
		if(!session.getAttribute("userName").equals("") ){
			return "redirect:/welcome";
		}
		
		return "home";
	}
	
	@RequestMapping(value="/activate.{activationString}")
	public String activateRegistration(Locale locale,Model model, @PathVariable String activationString){
		this.userManager.activateUser(activationString);
		return "redirect:/";
	}
	
	@RequestMapping(value="/loginForm")
	public String loginForm(){
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model,HttpSession session,@RequestParam Map<String, String> reqPar){

		User user = this.userManager.loginUser(reqPar);

		if(user != null && session.getAttribute("userName").equals("")  ){
			session.setAttribute("userName",  user.getName());
			return "redirect:/welcome";
		} 
		else if(user != null && session.getAttribute("userName").equals(user.getName())){
			session.setAttribute("userName", user.getName());
			return "redirect:/welcome";
		}
		else if( !session.getAttribute("userName").equals("")){
			session.setAttribute("successLogin", "alreadyIn");
			return "redirect:/";
		}		
		else if( user == null){
			session.setAttribute("successLogin", "notSuccessLogin");
			return "redirect:/";	
		} 
		return "redirect:/";
	}
	
	@RequestMapping(value="/signupForm")
	public String signupForm(Model model){
		Random random = new Random();	
		final int sizeOfPictureList = 2;
		RegImage regImage = this.regImageService.getRegImageById(random.nextInt(sizeOfPictureList)+1);
		model.addAttribute("imageSource", regImage.getSourceName());
		model.addAttribute("imageValue", regImage.getValue());
		return "signup";
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public String signup(Locale locale, Model model, @RequestParam Map<String,String> reqPar,HttpSession session){
		
		if(this.userManager.signUpUser(reqPar)){
			session.setAttribute("successSignUp", "successSignUp");
		} else{
			session.setAttribute("successSignUp", "notSuccessSignUp");
		}
		
		return "redirect:/";
	}
	
	

}
