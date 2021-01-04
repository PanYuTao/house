package com.donkee.house.gen;

import com.donkee.house.entity.Dept;
import com.donkee.house.entity.Emp;

public class GenericMethodTest {
	/*
	 *     泛型方法   <大写字母> 方法返回类型
	 * */
	public <T> T get(T t) {
		return t;
	}
	
	public static void main(String[] args) {
		Dept dept = new GenericMethodTest().get(new Dept());
		Emp emp = new GenericMethodTest().get(new Emp());
	}
}
