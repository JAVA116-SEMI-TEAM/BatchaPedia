package com.batcha.memInfo.model;

import java.sql.Timestamp;

public class MemInfoVO {
	private int memNo;
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String mobile;
	private String birthday;
	private Timestamp regdate;
	private Timestamp outdate;
	private int adminCheck;
	
	public MemInfoVO() {
		super();
	}
	
	public MemInfoVO(int memNo, String id, String pwd, String name, String email, String mobile, String birthday,
			Timestamp regdate, Timestamp outdate, int adminCheck) {
		super();
		this.memNo = memNo;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.birthday = birthday;
		this.regdate = regdate;
		this.outdate = outdate;
		this.adminCheck = adminCheck;
	}
	
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public Timestamp getOutdate() {
		return outdate;
	}
	public void setOutdate(Timestamp outdate) {
		this.outdate = outdate;
	}
	public int getAdminCheck() {
		return adminCheck;
	}
	public void setAdminCheck(int adminCheck) {
		this.adminCheck = adminCheck;
	}
	
	@Override
	public String toString() {
		return "MemInfoVO [memNo=" + memNo + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email
				+ ", mobile=" + mobile + ", birthday=" + birthday + ", regdate=" + regdate + ", outdate=" + outdate
				+ ", adminCheck=" + adminCheck + "]";
	}
}
