package com.hp.house.entity;

public class Biao {
	private int bid;
	private int hid;
	private int eid;
	private double dkd;
	private double skd;
	private double mkd;
	private String mtime;

	private String haddress;
	private String hfh;
	private String erealname;

	
	
	public Biao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Biao(int hid, double dkd, double skd, String mtime, String hfh) {
		super();
		this.hid = hid;
		this.dkd = dkd;
		this.skd = skd;
		this.mtime = mtime;
		this.hfh = hfh;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getHid() {
		return hid;
	}

	public void setHid(int hid) {
		this.hid = hid;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public double getDkd() {
		return dkd;
	}

	public void setDkd(double dkd) {
		this.dkd = dkd;
	}

	public double getSkd() {
		return skd;
	}

	public void setSkd(double skd) {
		this.skd = skd;
	}

	public double getMkd() {
		return mkd;
	}

	public void setMkd(double mkd) {
		this.mkd = mkd;
	}

	public String getMtime() {
		return mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}

	public String getHaddress() {
		return haddress;
	}

	public void setHaddress(String haddress) {
		this.haddress = haddress;
	}

	public String getHfh() {
		return hfh;
	}

	public void setHfh(String hfh) {
		this.hfh = hfh;
	}

	public String getErealname() {
		return erealname;
	}

	public void setErealname(String erealname) {
		this.erealname = erealname;
	}

}
