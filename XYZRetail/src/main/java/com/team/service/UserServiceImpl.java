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
				user.setAdmin(false);
				user.setOrderNumber(0);
				userDao.save(user);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean updateUserLastOrder(User user) {
		try {
			user.setOrderNumber(user.getOrderNumber()+1);
			userDao.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean setAdmin(User user, boolean admin) {
		user.setAdmin(admin);
		System.out.println(user);
		try {
			userDao.save(user);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("failed to change admin permissions for user");
			return false;
		}
		return true;
	}

}
