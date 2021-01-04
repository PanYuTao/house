package com.donkee.house.entity;

/*
 * 实体
 * 实体与数据对应（名称/数据类型）
 * */
public class Dept {
	private int pid;//部门编号，初始值为0
	private String pname;//部门名称
	private Integer pflag;//0正常1删除，初始值为null
	private String premark;//备注
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
