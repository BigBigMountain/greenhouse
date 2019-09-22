package com.lyyh.fertilizer.dao;

import java.util.List;

import com.lyyh.fertilizer.pojo.ChartsPoint;
import com.lyyh.fertilizer.pojo.Climatic;
import com.lyyh.tzgk.pojo.TclimaticData;
import com.lyyh.tzgk.pojo.TtempAndHum;

public interface TclimaticDao {

	List<TclimaticData> queryClimaticByVo(Climatic vo);

	TclimaticData selectNewestByUserName(String userName);

	TtempAndHum selectNewestTempAndHumByUserName(String userName);

	List<TtempAndHum> queryTAndHByVo(Climatic vo);

}
