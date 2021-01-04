package com.donkee.house.dao;

import java.util.List;

import com.donkee.house.entity.House;
import com.donkee.house.util.PageInfo;

public interface HouseDao extends BaseDao<House> {
	public List<House> listArea(int aid);
	
	public PageInfo<House> findByCondition(int pageNum,int pageSize,House house);
}
