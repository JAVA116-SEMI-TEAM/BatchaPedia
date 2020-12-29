package com.batcha.mycmt.model;

import java.sql.Timestamp;
	
public class MyCmtVO {
	private int cmtNo;
	private String cmtText;
	private Timestamp cmtRegdate;
	private int memNo;
	private int mvNo;
	private String mvTitle;
	
	public MyCmtVO() {
		super();
	}

	public MyCmtVO(int cmtNo, String cmtText, Timestamp cmtRegdate, int memNo, int mvNo,
			String mvTitle) {
		super();
		this.cmtNo = cmtNo;
		this.cmtText = cmtText;
		this.cmtRegdate = cmtRegdate;
		this.memNo = memNo;
		this.mvNo = mvNo;
		this.mvTitle = mvTitle;
	}

	public int getCmtNo() {
		return cmtNo;
	}

	public void setCmtNo(int cmtNo) {
		this.cmtNo = cmtNo;
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

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public int getMvNo() {
		return mvNo;
	}

	public void setMvNo(int mvNo) {
		this.mvNo = mvNo;
	}

	public String getMvTitle() {
		return mvTitle;
	}

	public void setMvTitle(String mvTitle) {
		this.mvTitle = mvTitle;
	}


	@Override
	public String toString() {
		return "MyCmtVO [cmtNo=" + cmtNo + ", cmtText=" + cmtText + ", cmtRegdate=" + cmtRegdate + ", memNo=" + memNo
				+ ", mvNo=" + mvNo + ", mvTitle=" + mvTitle + "]";
	}
	
	
	
}
