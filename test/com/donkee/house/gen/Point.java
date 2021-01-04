package com.donkee.house.gen;
/*
 * 泛型类:类名<大写字母> ,E实体 ,K键key,V值value
 * */
public class Point<T> {
	private T x;//将数据类型进行参数化
	private T y;
	public T getX() {
		return x;
	}
	public void setX(T x) {
		this.x = x;
	}
	public T getY() {
		return y;
	}
	public void setY(T y) {
		this.y = y;
	}
	
}
