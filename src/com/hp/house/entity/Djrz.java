package com.hp.house.entity;

public class Djrz {
	private int mid;
	private String mdate;
	private int eid;
	private int cid;
	private int hid;
	private String mimg;
	private double myj;
	private double myzj;
	private int mflag;
	private String mbegintime;
	
	private String haddress;
	private String hfh;
	private String erealname;
	private String cname;
	private String ctel;
	
	public Djrz() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Djrz(int cid, int hid, double myj, double myzj, String mbegintime) {
		super();
		this.cid = cid;
		this.hid = hid;
		this.myj = myj;
		this.myzj = myzj;
		this.mbegintime = mbegintime;
	}
	
	public Djrz(Integer mid, int cid, int hid, double myj2, double myzj2, int mflag,
			String mbegintime) {
		super();
		this.mid = mid;
		this.cid = cid;
		this.hid = hid;
		this.myj = myj2;
		this.myzj = myzj2;
		this.mflag = mflag;
		this.mbegintime = mbegintime;
	}
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public String getMimg() {
		return mimg;
	}
	public void setMimg(String mimg) {
		this.mimg = mimg;
	}
	public double getMyj() {
		return myj;
	}
	public void setMyj(double myj) {
		this.myj = myj;
	}
	public double getMyzj() {
		return myzj;
	}
	public void setMyzj(double myzj) {
		this.myzj = myzj;
	}
	public int getMflag() {
		return mflag;
	}
	public void setMflag(int mflag) {
		this.mflag = mflag;
	}
	public String getMbegintime() {
		return mbegintime;
	}
	public void setMbegintime(String mbegintime) {
		this.mbegintime = mbegintime;
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
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCtel() {
		return ctel;
	}
	public void setCtel(String ctel) {
		this.ctel = ctel;
	}
	
   
	
}
