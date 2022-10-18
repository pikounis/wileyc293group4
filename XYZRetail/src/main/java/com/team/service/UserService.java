package com.team.service;

import com.team.entity.User;

public interface UserService {
	
	public User loginUser(User user);
	public boolean registerUser(User user);
}
