package com.donkee.house.dao;

import com.donkee.house.entity.Zsrall2;
import com.donkee.house.util.PageInfo;

public interface Zsrall2Dao extends BaseDao<Zsrall2> {
	public PageInfo<Zsrall2> findByPage1(int pageNum, int pageSize);

}
