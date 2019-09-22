package com.lyyh.fertilizer.service;

import com.lyyh.greenhouse.pojo.User;

public interface UserService {

	public User findByUsername(String userName);
	
	public User findByUserId(int userId);
	
	public void insertOne(User user);
}
