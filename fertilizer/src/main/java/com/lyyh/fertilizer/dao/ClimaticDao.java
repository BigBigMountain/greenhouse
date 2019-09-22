package com.lyyh.fertilizer.dao;

import java.util.List;

import com.lyyh.fertilizer.pojo.ChartsPoint;
import com.lyyh.fertilizer.pojo.Climatic;

public interface ClimaticDao {

	List<ChartsPoint> queryClimaticByVo(Climatic vo);

}
