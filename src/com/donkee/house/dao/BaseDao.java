package com.donkee.house.dao;

import java.util.List;

import com.donkee.house.util.PageInfo;


public interface BaseDao<T> {
	//查询所有
	public List<T> listAll();
	
	//添加
	public int save(T t);
	
	//删除
	public int delete(int id);
	
	//单个查询
	public T findById(int id);
	
	//修改
	public int update(T t);
	
	//分页查询
	public PageInfo<T> findByPage(int pageNum,int pageSize);
}
