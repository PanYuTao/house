package com.donkee.house.dao;

import java.util.List;

import com.donkee.house.util.PageInfo;


public interface BaseDao<T> {
	//��ѯ����
	public List<T> listAll();
	
	//���
	public int save(T t);
	
	//ɾ��
	public int delete(int id);
	
	//������ѯ
	public T findById(int id);
	
	//�޸�
	public int update(T t);
	
	//��ҳ��ѯ
	public PageInfo<T> findByPage(int pageNum,int pageSize);
}
