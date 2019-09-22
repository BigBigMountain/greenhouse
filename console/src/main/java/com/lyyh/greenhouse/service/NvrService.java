package com.lyyh.greenhouse.service;

import java.util.List;

import com.lyyh.greenhouse.pojo.Nvr;

public interface NvrService {

	List<Nvr> getAllNvrByZoneId(Integer zoneId);

	void saveOrUpdateNvr(Nvr nvr);

	void delnvr(Integer nvr_id);

	
}
