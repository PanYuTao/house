package com.hp.housr.gen;

public class PointTest {

	public static void main(String[] args) {
		//方式二:参考集合和泛型集合
		ObjectPoint p21 = new ObjectPoint();
		p21.setX(new Integer(1));
		p21.setY(new Integer(1));
		p21.setX(new Float(1));//不做数据类型的检查
		Integer x = (Integer)p21.getX();//类型强制转换;存在数据类型安全问题
		ObjectPoint p22 = new ObjectPoint();
		p22.setX(new Float(1));
		p22.setY(new Float(1));
		//方式三:泛型类
		Point<Integer> p31 = new Point<>();//确定Point类中的T的数据类型为Integer类型
		p31.setX(new Integer(10));
		p31.setY(new Integer(12));
		// p31.setX(new Float(1)); //lerror:数据类型检查
		Integer x2 = p31.getX();
		Point<Float> p2 = new Point<>();//确左Point类中的T的数据类型为Float类型
		p2.setX(new Float(10));
		p2.setY(new Float(12));

	}
}
