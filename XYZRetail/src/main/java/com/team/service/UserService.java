package com.team.service;

import com.team.entity.User;

public interface UserService {
	
	public User loginUser(User user);
	public boolean registerUser(User user);
	public boolean updateUserLastOrder(User user);
	public boolean setAdmin(User user, boolean admin);
}
