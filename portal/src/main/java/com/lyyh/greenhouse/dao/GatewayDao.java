package com.lyyh.greenhouse.dao;

import org.apache.ibatis.annotations.Param;

import com.lyyh.greenhouse.pojo.Gateway;

public interface GatewayDao {

	Gateway selectById(@Param("id")Integer id);
}
