package com.donkee.house.ref;
/*
 * �����ඨ��:����<����>
 * */
public class GenericClass<T> {
	private T t;//�ֶ�
	//����ֵ                                                        ����
	private T getInstance(T t) {
		//System.out.println(t.getClass().newInstance());
		return t;
	}
	
}
