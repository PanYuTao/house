package com.hp.house.dao;

import com.hp.house.entity.PageInfo;
import com.hp.house.entity.Zsrall2;

public interface Zsrall2Dao extends BaseDao<Zsrall2> {

	PageInfo<Zsrall2> findByCondition(int pageNum, int i, Zsrall2 zsrall2);
}
