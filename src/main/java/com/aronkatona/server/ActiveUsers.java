package com.aronkatona.server;

import java.util.ArrayList;
import java.util.List;

import com.aronkatona.model.User;

public class ActiveUsers {
	
	private static ActiveUsers instance = null;
	private List<User> activeUsers;
	
	public static ActiveUsers getInstance(){
		if(instance == null){
			instance = new ActiveUsers();
			return instance;
		}
		else{
			return instance;
		}
	}
	
	private ActiveUsers(){
		activeUsers = new ArrayList<>();
	}
	
	public List<User> getActiveUsers(){
		return activeUsers;
	}
	

}
