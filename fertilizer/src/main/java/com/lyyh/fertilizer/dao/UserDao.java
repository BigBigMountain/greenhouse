package com.lyyh.fertilizer.dao;

import com.lyyh.greenhouse.pojo.User;

public interface UserDao {

	User findByUsername(String username);

	void insertOne(User user);
}
