package com.hp.house.dao;

import com.hp.house.entity.Emp;

public interface EmpDao extends BaseDao<Emp> {

	public Emp findByName(String ename);
}
