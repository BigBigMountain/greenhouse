package com.lyyh.fertilizer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyyh.fertilizer.dao.UserDao;
import com.lyyh.fertilizer.service.UserService;
import com.lyyh.greenhouse.pojo.User;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User findByUsername(String userName) {
		return userDao.findByUsername(userName);
	}

	@Override
	public User findByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public void insertOne(User user) {
		userDao.insertOne(user);
	}

}
