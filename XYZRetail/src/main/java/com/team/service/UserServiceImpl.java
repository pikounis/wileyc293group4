package com.team.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.entity.User;
import com.team.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;
	
	@Override
	public User loginUser(User user) {
		
		User loginUser = userDao.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (loginUser == null)
			return null;
		else 
			return loginUser;
	}

	@Override
	public boolean registerUser(User user) {
		if (!user.getUsername().equals("")) {
			try {
				int rows = userDao.insertUser(user.getUsername(), user.isAdmin(), 0, user.getPassword());
				if (rows == 0){
					return false;
				} else {
					return true;
				}
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean updateUserLastOrder(User user) {
		int rows = 0;//userDao.updateLastOrder(user.getUsername(), user.getLastOrder()+1);
		if (rows == 0){
			return false;
		} else {
			return true;
		}
	}
}
