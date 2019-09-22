package com.lyyh.greenhouse.dao;

import java.util.List;

import com.lyyh.greenhouse.pojo.Nvr;


public interface NvrDao {

	public List<Nvr> getAllNvrByZoneId(Integer zoneId);

	public void saveNvr(Nvr nvr);

	public void updateNvr(Nvr nvr);

	public void delnvr(Integer nvr_id);


}
