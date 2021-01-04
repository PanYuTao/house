package com.hp.house.entity;

import java.util.List;

public class PageInfo<T> {

	private int pageNum = 1;//第几页
	private int pagesize = 5; //每页的数量
	private int total; //总记录数
	private int pages; //总页数get
	private int up; // 上一页,get
	private int next;// 下一页get
	private List<T> list; //结果集
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
		pages = total / pagesize;
		if (total % pagesize != 0) {
			pages++;
		}
	}
	public int getUp() {
		up = pageNum - 1;
		if(up < 1)
			up = 1;
		return up;
	}
	public void setUp(int up) {
		this.up = up;
	}
	public int getNext() {
		next = pageNum + 1;
		if(next > total)
			next = pages;
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}
	public int getPageNum() {
		return pageNum;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "PageInfo [pageNum=" + pageNum + ", pagesize=" + pagesize + ", total=" + total + ", pages=" + pages
				+ ", up=" + up + ", next=" + next + ", list=" + list + "]";
	}
	
	
}
