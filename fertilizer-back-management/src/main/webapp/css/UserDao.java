package com.lyyh.greenhouse.dao;

import com.lyyh.greenhouse.pojo.User;

public interface UserDao {

	User findByUsername(String username);

}
