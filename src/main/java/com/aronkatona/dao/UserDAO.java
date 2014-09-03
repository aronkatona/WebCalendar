package com.aronkatona.dao;

import java.util.List;

import com.aronkatona.model.User;

public interface UserDAO {

	public void saveUser(User u);
	public void updateUser(User u);
	public List<User> getUsers();
	public User getUserById(int id);
	public void removeUser(int id);
	public User getUserByName(String userName);
	public User getUserByActivationString(String activationString);
} 
