package com.donkee.house.entity;

public class Js {
	private int jid;//½ÇÉ«±àºÅ
	private String jname;//½ÇÉ«Ãû³Æ
	public int getJid() {
		return jid;
	}
	public void setJid(int jid) {
		this.jid = jid;
	}
	public String getJname() {
		return jname;
	}
	public void setJname(String jname) {
		this.jname = jname;
	}
	@Override
	public String toString() {
		return "Js [jid=" + jid + ", jname=" + jname + "]";
	}
	

}
