package com.donkee.house.gen;

import com.donkee.house.entity.Dept;
import com.donkee.house.entity.Emp;

public class GenericMethodTest {
	/*
	 *     ���ͷ���   <��д��ĸ> ������������
	 * */
	public <T> T get(T t) {
		return t;
	}
	
	public static void main(String[] args) {
		Dept dept = new GenericMethodTest().get(new Dept());
		Emp emp = new GenericMethodTest().get(new Emp());
	}
}
