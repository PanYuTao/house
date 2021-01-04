package com.donkee.house.dao;

import com.donkee.house.entity.Dq;
import com.donkee.house.util.PageInfo;

public interface DqDao extends BaseDao<Dq> {
	public PageInfo<Dq> findByPage1(int pageNum, int pageSize);

}
