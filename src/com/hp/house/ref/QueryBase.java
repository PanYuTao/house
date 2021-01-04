package com.hp.house.ref;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

import com.hp.house.entity.Dept;

public class QueryBase {

	@Test
	public void testRef() throws Exception{
		//核心类class英
		//1.得到Class类的对象的方法
		//1)方式一:Object类中的getClass方法
		Dept dept = new Dept();//创建Dept和Class类两个对象
		Class clazz1 = dept.getClass();//任何实例.getClass方法得到Class类的实似//2)万式二
		Class clazz2= Dept.class;//3)方式三
		Class clazz3 = Class.forName("com.hp.house.entity.Dept");
		//2.通过Class对象可以获取某个类中的:构造方法、成员变量、成员方法;并访问成员
		//
//		Field[] fields = clazz3.getFields();//获取所有的"公有宁段"
		Field[] fields = clazz3.getDeclaredFields(); //获取所有字段，包括﹔私有、受保护、认、公有
		System.out.println("****所有字段*******");
		for(Field field : fields){
			System.out.println(field.getName());//得到字段名称
		}
		System.out.println("********所有方法************");
		//Method[ ] methods = clazz3.getAlethods() ;//获取所有"公有方法"﹔(包含了父类的方法也包含Object类)
		Method[] methods = clazz3.getDeclaredMethods();//获取所有的成员方法，包括私有的(不包括继承的)
		for(Method method : methods) {
		System.out.println(method.getName());
		}
		//已知字段名字,为字段赋值
		Object obj = clazz3.newInstance();//得到指定类的一个实例，调用指定类的不带参的构造方法
		String fieldName = "pid";
		Field field = clazz3.getDeclaredField(fieldName) ;field.setAccessible(true);
		field.set(obj,1);
		Dept dept2 = (Dept)obj;
		System.out.println(dept2.getPid());

	}
}
