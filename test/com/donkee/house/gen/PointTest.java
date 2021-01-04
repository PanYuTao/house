package com.donkee.house.gen;

public class PointTest {
	public static void main(String[] args) {
		//方式一:类的数量
		IntegerPoint p11 = new IntegerPoint();//参数只能传Integer类型
		p11.setX(new Integer(1));
		p11.setY(new Integer(1));
		FloatPoint p12 = new FloatPoint();//参数只能传Float类型
		p12.setX(new Float(1));
		p12.setY(new Float(1));
		//方式二:参考集合和泛型集合
		ObjectPoint p21 = new ObjectPoint();//参数可传Integer及Float类型，但不能保证一致性
		p21.setX(new Integer(1));
		p21.setY(new Integer(1));
		p21.setX(new Float(1));//不做数据类型的检查
		Integer x = (Integer) p21.getX();//类型强制转换;存在数据类型安全问题
		
		ObjectPoint p22 = new ObjectPoint();
		p22.setX(new Float(1));
		p22.setY(new Float(1));
		
		//方式三:泛型类
		//通过Point<T>参数可传Integer及Float类型，且保证一致性
		Point<Integer> p1 = new Point<>();//确定Point类中的T的数据类型为Integer类
		p1.setX(new Integer(10));
		p1.setY(new Integer(12));
	//  p1.setX(new Float(1));//error:数据类型检查
		Point<Float> p2 = new Point<>();//确定Point类中的T的数据类型为Float类
		p2.setX(new Float(10));
		p2.setY(new Float(12));
	}
}
