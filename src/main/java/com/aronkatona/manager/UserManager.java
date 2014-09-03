package com.aronkatona.manager;

import com.aronkatona.model.User;
import com.aronkatona.service.UserService;

public class UserManager {

	private UserService userService;
	
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	public void saveUser(User user){
		this.userService.saveUser(user);
	}
	
	public User existUser(String userName){
		User user = null;
		user = this.userService.getUserByName(userName);
		return user;
	}
	
	public void activateUser(String activationString){
		User user = null;
		user = this.userService.getUserByActivationString(activationString);
		if(user != null){
			user.setActivated(true);
			this.userService.updateUser(user);
		}
	}
	
}
