package com.hp.house.entity;

public class Custom {
	private int cid;
	private String cname;
	private String csex;
	private String ctel;
	private String ctel1;
	private String ccard;

	
	public Custom(String cname, String csex, String ctel, String ctel1, String ccard) {
		super();
		this.cname = cname;
		this.csex = csex;
		this.ctel = ctel;
		this.ctel1 = ctel1;
		this.ccard = ccard;
	}

	public Custom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCsex() {
		return csex;
	}

	public void setCsex(String csex) {
		this.csex = csex;
	}

	public String getCtel() {
		return ctel;
	}

	public void setCtel(String ctel) {
		this.ctel = ctel;
	}

	public String getCtel1() {
		return ctel1;
	}

	public void setCtel1(String ctel1) {
		this.ctel1 = ctel1;
	}

	public String getCcard() {
		return ccard;
	}

	public void setCcard(String ccard) {
		this.ccard = ccard;
	}

}
