package com.hp.house.dao;

import java.util.List;

import com.hp.house.entity.Js;
import com.hp.house.entity.PageInfo;

public interface JsDao {

	public List<Js> ListAll();
	public Js selectJsById(Integer jid);
	
	public int update(Js js);
	
	public int save(String name);
	
	public int del(Integer jid);
	
	//分页查询
	public PageInfo<Js> findByPage(int pageNum,int pageSize);
}
