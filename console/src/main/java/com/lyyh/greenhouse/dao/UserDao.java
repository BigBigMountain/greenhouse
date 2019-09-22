package com.lyyh.greenhouse.dao;

import java.util.List;

import com.lyyh.greenhouse.pojo.User;

public interface UserDao {

	User findByUsername(String username);

	List<User> queryAll();

	void saveUser(User user);

	void updateUser(User user);

	void delById(int userId);

}
