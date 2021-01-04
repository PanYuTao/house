package com.donkee.house.util;

import java.util.List;

public class PageInfo<T> {
	private int pageNum = 1;// �ڼ�ҳ
	private int pageSize = 5;//ÿҳ������
	private int total;//�ܼ�¼��
	private int pages;//��ҳ��
	private int up;//��һҳ
	private int next;//��һҳ
	private List<T> list;//�����
	
	public void setTotal(int total) {
		this.total = total;
		pages = total/pageSize;//total:10  ,pageSize:5,4
		if (total % pageSize != 0) {
			pages++;
		}
	}
	public int getUp() {
		up = pageNum - 1;
		if( up < 1) up = 1;
		return up;
	}
	public int getNext() {
		next = pageNum + 1;
		if(next > pages) next=pages;
		return next;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}

	public void setUp(int up) {
		this.up = up;
	}
	
	public void setNext(int next) {
		this.next = next;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
}
