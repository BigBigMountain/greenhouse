package com.lyyh.greenhouse.service;

import java.util.List;

import com.lyyh.greenhouse.pojo.User;

public interface UserService {

	List<User> queryAll();

	void saveUser(User user);

	void updateUser(User user);

	void delById(Integer userId);

}
