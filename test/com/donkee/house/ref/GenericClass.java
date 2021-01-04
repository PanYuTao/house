package com.donkee.house.ref;
/*
 * 泛型类定义:类名<类型>
 * */
public class GenericClass<T> {
	private T t;//字段
	//返回值                                                        参数
	private T getInstance(T t) {
		//System.out.println(t.getClass().newInstance());
		return t;
	}
	
}
