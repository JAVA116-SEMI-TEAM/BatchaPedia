package com.batcha.cmtData.model;

import java.sql.Timestamp;

public class CmtDataVO {
	private int cmtNo;
	private String userid;
	private String cmtText;
	private Timestamp cmtRegdate;
	private int agrCnt;
	private int dagrCnt;
	private int memNo;
	private int mvNo;
	
	public CmtDataVO() {
		super();
	}

	public CmtDataVO(int cmtNo, String userid, String cmtText, Timestamp cmtRegdate, int agrCnt, int dagrCnt, int memNo,
			int mvNo) {
		super();
		this.cmtNo = cmtNo;
		this.userid = userid;
		this.cmtText = cmtText;
		this.cmtRegdate = cmtRegdate;
		this.agrCnt = agrCnt;
		this.dagrCnt = dagrCnt;
		this.memNo = memNo;
		this.mvNo = mvNo;
	}

	public int getCmtNo() {
		return cmtNo;
	}

	public void setCmtNo(int cmtNo) {
		this.cmtNo = cmtNo;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCmtText() {
		return cmtText;
	}

	public void setCmtText(String cmtText) {
		this.cmtText = cmtText;
	}

	public Timestamp getCmtRegdate() {
		return cmtRegdate;
	}

	public void setCmtRegdate(Timestamp cmtRegdate) {
		this.cmtRegdate = cmtRegdate;
	}

	public int getAgrCnt() {
		return agrCnt;
	}

	public void setAgrCnt(int agrCnt) {
		this.agrCnt = agrCnt;
	}

	public int getDagrCnt() {
		return dagrCnt;
	}

	public void setDagrCnt(int dagrCnt) {
		this.dagrCnt = dagrCnt;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public int getMvNo() {
		return mvNo;
	}

	public void setMvNo(int mvNo) {
		this.mvNo = mvNo;
	}

	@Override
	public String toString() {
		return "CmtDataVO [cmtNo=" + cmtNo + ", userid=" + userid + ", cmtText=" + cmtText + ", cmtRegdate="
				+ cmtRegdate + ", agrCnt=" + agrCnt + ", dagrCnt=" + dagrCnt + ", memNo=" + memNo + ", mvNo=" + mvNo
				+ "]";
	}
}
