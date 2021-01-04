package com.hp.house.entity;

public class House {
	private int hid;
	private int sid;
	private int aid;
	private String haddress;
	private String hfh;
	private String hhx;
	private String hmj;
	private String hcx;
	private double hmoney;
	private double hwf;
	private double hdx;
	private double hsf;
	private double hmq;
	private double dkd;
	private double skd;
	private double mkd;
	private String hjp;
	private String hremark;
	private String himg;
	private String hflag;	
	private String aname;
	private String sname;
	private String pic1;
	private String pic2;
	private String pic3;
	
	
	
	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getPic3() {
		return pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}

	public House(int hid, int sid, int aid, String haddress, String hfh, String hhx, String hmj, String hcx,
			double hmoney, double hwf, double hdx, double hsf, double hmq, double dkd, double skd, double mkd,
			String hjp, String hremark) {
		super();
		this.hid = hid;
		this.sid = sid;
		this.aid = aid;
		this.haddress = haddress;
		this.hfh = hfh;
		this.hhx = hhx;
		this.hmj = hmj;
		this.hcx = hcx;
		this.hmoney = hmoney;
		this.hwf = hwf;
		this.hdx = hdx;
		this.hsf = hsf;
		this.hmq = hmq;
		this.dkd = dkd;
		this.skd = skd;
		this.mkd = mkd;
		this.hjp = hjp;
		this.hremark = hremark;
	}

	public House(int sid, int aid, String haddress, String hfh, String hhx, String hmj, String hcx,
			double hmoney, double hwf, double hdx, double hsf, double hmq, double dkd, double skd, double mkd,
			String hjp, String hremark, String himg, String hflag) {
		super();
		this.sid = sid;
		this.aid = aid;
		this.haddress = haddress;
		this.hfh = hfh;
		this.hhx = hhx;
		this.hmj = hmj;
		this.hcx = hcx;
		this.hmoney = hmoney;
		this.hwf = hwf;
		this.hdx = hdx;
		this.hsf = hsf;
		this.hmq = hmq;
		this.dkd = dkd;
		this.skd = skd;
		this.mkd = mkd;
		this.hjp = hjp;
		this.hremark = hremark;
		this.himg = himg;
		this.hflag = hflag;
	}

	public House() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
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
	public String getHhx() {
		return hhx;
	}
	public void setHhx(String hhx) {
		this.hhx = hhx;
	}
	public String getHmj() {
		return hmj;
	}
	public void setHmj(String hmj) {
		this.hmj = hmj;
	}
	public String getHcx() {
		return hcx;
	}
	public void setHcx(String hcx) {
		this.hcx = hcx;
	}
	public double getHmoney() {
		return hmoney;
	}
	public void setHmoney(double hmoney) {
		this.hmoney = hmoney;
	}
	public double getHwf() {
		return hwf;
	}
	public void setHwf(double hwf) {
		this.hwf = hwf;
	}
	public double getHdx() {
		return hdx;
	}
	public void setHdx(double hdx) {
		this.hdx = hdx;
	}
	public double getHsf() {
		return hsf;
	}
	public void setHsf(double hsf) {
		this.hsf = hsf;
	}
	public double getHmq() {
		return hmq;
	}
	public void setHmq(double hmq) {
		this.hmq = hmq;
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
	public String getHjp() {
		return hjp;
	}
	public void setHjp(String hjp) {
		this.hjp = hjp;
	}
	public String getHremark() {
		return hremark;
	}
	public void setHremark(String hremark) {
		this.hremark = hremark;
	}
	public String getHimg() {
		return himg;
	}
	public void setHimg(String himg) {
		this.himg = himg;
	}
	public String getHflag() {
		return hflag;
	}
	public void setHflag(String hflag) {
		this.hflag = hflag;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}

	@Override
	public String toString() {
		return "House [hid=" + hid + ", sid=" + sid + ", aid=" + aid + ", haddress=" + haddress + ", hfh=" + hfh
				+ ", hhx=" + hhx + ", hmj=" + hmj + ", hcx=" + hcx + ", hmoney=" + hmoney + ", hwf=" + hwf + ", hdx="
				+ hdx + ", hsf=" + hsf + ", hmq=" + hmq + ", dkd=" + dkd + ", skd=" + skd + ", mkd=" + mkd + ", hjp="
				+ hjp + ", hremark=" + hremark + ", himg=" + himg + ", hflag=" + hflag + ", sname=" + sname + ", aname="
				+ aname + ", pic1=" + pic1 + ", pic2=" + pic2 + ", pic3=" + pic3 + "]";
	}


	
}
