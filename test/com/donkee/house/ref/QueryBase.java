package com.donkee.house.ref;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.donkee.house.entity.Dept;

public class QueryBase {

	@Test
	public void testRef() throws Exception {
		//������Class��
		//1.�õ�Class��Ķ��󷽷�
		//1)��ʽһ:Object���е�getClass����
		Dept dept = new Dept();//����Dept��Class����������
		Class class1 = dept.getClass();//�κ�ʵ��.getClass�����õ�Class���ʵ��
		//2)��ʽ��
	//	Class class2 = Dept.class;
		Class class2 = String.class;
		//3)��ʽ��
	//	Class class3 = Class.forName("com.donkee.house.entity.Dept");
		Class class3 = Class.forName("java.lang.String");
		
		//2.ͨ��Class������Ի�ȡĳ�����е�:���췽������Ա��������Ա����;�����ʳ�Ա
		//Field[] fields = class3.getFields();//��ȡ���е�"�����ֶ�"
		Field[] fields = class3.getDeclaredFields();//��ȡ�����ֶ�,����:˽�С��ܱ�����Ĭ�ϡ�����
		System.out.println("***************�����ֶ�****************");
		for (Field field : fields) {
			System.out.println(field.getName());//�õ��ֶε�����
		}
		System.out.println("***************���з���****************");
		//Method[] methods = class3.getMethods();//��ȡ����"���з���";(�����˸���ķ���Ҳ����Object��)
		Method[] methods = class3.getDeclaredMethods();//��ȡ���еĳ�Ա����,����˽�е�(�������̳е�)
		for (Method method : methods) {
			System.out.println(method.getName());
		}
		
		//��֪�ֶ�����,Ϊ�ֶθ�ֵ
		Object obj = class3.newInstance();//�õ�ָ�����һ��ʵ��,����ָ����Ĳ����εĹ��췽��
		String fieldName = "pid";
		Field field = class3.getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(obj, 1);
		
		Dept dept2 = (Dept) obj;
		System.out.println(dept2.getPid());
		
	}
	
	@Test
	public void test2() {
		Dept dept = new Dept();
		//java.util.Calendar������
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH+1);//Calendar.MONTH��0��ʼ
		int day = cal.get(Calendar.DAY_OF_MONTH);
		System.out.println(year+"/"+month+"/"+day);
	}
	
	@Test
	public void test3() {
		//��ͨ����
		List list1 = new ArrayList();
		Dept dept1 = new Dept();
		Dept dept2 = new Dept();
		String str = new String("abc");
		list1.add(dept1);list1.add(dept2);list1.add(str);
		//���ͼ��ϵ��ŵ�:�������ͼ��,��ȫ;���ܸ�
		List<Dept> list2 = new ArrayList<Dept>();
		list2.add(dept1);list2.add(dept2);//list2.add(str);
		Dept d2 = list2.get(0);//����Ҫǿ��ת��,���ܸ�
	}
	
}
