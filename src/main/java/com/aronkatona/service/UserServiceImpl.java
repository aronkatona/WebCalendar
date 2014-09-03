package com.aronkatona.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aronkatona.dao.UserDAO;
import com.aronkatona.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public void saveUser(User u) {
		this.userDAO.saveUser(u);
	}

	@Override
	@Transactional
	public void updateUser(User u) {
		this.userDAO.updateUser(u);
	}

	@Override
	@Transactional
	public List<User> getUsers() {
		return this.userDAO.getUsers();
	}

	@Override
	@Transactional
	public User getUserById(int id) {
		return this.userDAO.getUserById(id);
	}

	@Override
	@Transactional
	public void removeUser(int id) {
		this.userDAO.removeUser(id);
	}

	@Override
	@Transactional
	public User getUserByName(String userName) {
		return this.userDAO.getUserByName(userName);
	}

	@Override
	@Transactional
	public User getUserByActivationString(String activationString) {
		return this.userDAO.getUserByActivationString(activationString);
	}

} 