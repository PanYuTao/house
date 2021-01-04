package com.hp.house.dao;

import java.util.List;

import com.hp.house.entity.House;
import com.hp.house.entity.PageInfo;


public interface BaseDao<T> {

	public List<T> ListAll();
	public T selectById(Integer id);
	
	public int update(T t);
	
	public int save(T t);
	
	public int del(Integer id);
	
	//分页查询
	public PageInfo<T> findByPage(int pageNum,int pageSize);
}
