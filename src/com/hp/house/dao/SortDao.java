package com.hp.house.dao;

import java.util.List;

import com.hp.house.entity.Dept;
import com.hp.house.entity.Js;
import com.hp.house.entity.PageInfo;
import com.hp.house.entity.Sort;

public interface SortDao {
	
	public List<Sort> ListAll();
	public Sort selectSortById(Integer sid);
	
	public int update(Sort sort);
	
	public int save(String name);
	
	public int del(Integer sid);
	
	public PageInfo<Sort> findByPage(int pageNum,int pageSize);
}
