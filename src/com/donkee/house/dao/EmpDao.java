package com.donkee.house.dao;

import com.donkee.house.entity.Emp;

public interface EmpDao extends BaseDao<Emp> {
	
	public Emp findByName(String ename);
}
