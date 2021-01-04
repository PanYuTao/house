package com.hp.house.entity;

public class Dept {
	
	private Integer pid;//部门编号     初始值为0
	private String pname;//部门名称
	private Integer pflag;//0正常1删除 初始值 null
	private String premark;//备注
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
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
	@Override
	public String toString() {
		return "Dept [pid=" + pid + ", pname=" + pname + ", pflag=" + pflag + ", premark=" + premark + "]";
	}
	public Dept(String pname, String premark) {
		super();
		this.pname = pname;
		this.premark = premark;
	}
	
	public Dept(Integer pid, String pname, String premark) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.premark = premark;
	}
	public Dept() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
