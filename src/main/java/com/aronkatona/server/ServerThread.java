package com.aronkatona.server;


import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;

import com.aronkatona.model.User;
import com.aronkatona.service.UserService;

public class ServerThread extends Thread {

	private static ServerThread instance = null;
	private static final int DELAYAFTERREGISTRATION = -1;
	
	private UserService userService;
	
	
	public static ServerThread getInstance() {
	      if(instance == null) {
	         instance = new ServerThread();
	         instance.start();
	         return instance;
	      }
	      return instance;
	}
	
	
	@Override
	public void run(){
		
				try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		while(true){
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date actDateTmp = new Date();
			List<User> userList = this.userService.getUsers();
			for(User user: userList){
				if(!user.isActivated()){
					DateTime actDate = new DateTime(actDateTmp);
					DateTime regDate = new DateTime(user.getRegistrationDate());
					Days days = Days.daysBetween(actDate, regDate);
					if(days.getDays() < DELAYAFTERREGISTRATION){
						System.out.println(days.getDays());
						this.userService.removeUser(user.getId());
					}
					
				}
			}
			
			
		}
	}
	

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	
	
}
