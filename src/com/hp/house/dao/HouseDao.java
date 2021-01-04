package com.hp.house.dao;

import java.util.List;

import com.hp.house.entity.House;
import com.hp.house.entity.PageInfo;

public interface HouseDao extends BaseDao<House> {

	PageInfo<House> findByCondition(int pageNum, int i, House house);
	public List<House> findById(House house);
}
