package com.donkee.house.gen;

public class PointTest {
	public static void main(String[] args) {
		//��ʽһ:�������
		IntegerPoint p11 = new IntegerPoint();//����ֻ�ܴ�Integer����
		p11.setX(new Integer(1));
		p11.setY(new Integer(1));
		FloatPoint p12 = new FloatPoint();//����ֻ�ܴ�Float����
		p12.setX(new Float(1));
		p12.setY(new Float(1));
		//��ʽ��:�ο����Ϻͷ��ͼ���
		ObjectPoint p21 = new ObjectPoint();//�����ɴ�Integer��Float���ͣ������ܱ�֤һ����
		p21.setX(new Integer(1));
		p21.setY(new Integer(1));
		p21.setX(new Float(1));//�����������͵ļ��
		Integer x = (Integer) p21.getX();//����ǿ��ת��;�����������Ͱ�ȫ����
		
		ObjectPoint p22 = new ObjectPoint();
		p22.setX(new Float(1));
		p22.setY(new Float(1));
		
		//��ʽ��:������
		//ͨ��Point<T>�����ɴ�Integer��Float���ͣ��ұ�֤һ����
		Point<Integer> p1 = new Point<>();//ȷ��Point���е�T����������ΪInteger��
		p1.setX(new Integer(10));
		p1.setY(new Integer(12));
	//  p1.setX(new Float(1));//error:�������ͼ��
		Point<Float> p2 = new Point<>();//ȷ��Point���е�T����������ΪFloat��
		p2.setX(new Float(10));
		p2.setY(new Float(12));
	}
}
