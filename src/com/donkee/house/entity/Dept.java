package com.donkee.house.entity;

/*
 * ʵ��
 * ʵ�������ݶ�Ӧ������/�������ͣ�
 * */
public class Dept {
	private int pid;//���ű�ţ���ʼֵΪ0
	private String pname;//��������
	private Integer pflag;//0����1ɾ������ʼֵΪnull
	private String premark;//��ע
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getPflag() {
		return pflag;
	}
	public void setPflag(Integer pflag) {
		this.pflag = pflag;
	}
	public String getPremark() {
		return premark;
	}
	public void setPremark(String premark) {
		this.premark = premark;
	}
	
	public Dept(String pname, Integer pflag, String premark) {
		super();
		this.pname = pname;
		this.pflag = pflag;
		this.premark = premark;
	}
	public Dept() {
		super();
	}
	@Override
	public String toString() {
		return "Dept [pid=" + pid + ", pname=" + pname + ", pflag=" + pflag + ", premark=" + premark + "]";
	}
	
	
}
