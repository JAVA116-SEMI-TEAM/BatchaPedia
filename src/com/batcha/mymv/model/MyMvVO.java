package com.batcha.mymv.model;

public class MyMvVO {
	private int keepNo;
	private int memNo;
	private int mvNo;
	private String mvTitle;
	private String thumbnail;
	
	
	public MyMvVO() {
		super();
	}
	

	public MyMvVO(int keepNo, int memNo, int mvNo, String mvTitle, String thumbnail) {
		super();
		this.keepNo = keepNo;
		this.memNo = memNo;
		this.mvNo = mvNo;
		this.mvTitle = mvTitle;
		this.thumbnail = thumbnail;
	}



	public int getKeepNo() {
		return keepNo;
	}


	public void setKeepNo(int keepNo) {
		this.keepNo = keepNo;
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


	public String getMvTitle() {
		return mvTitle;
	}


	public void setMvTitle(String mvTitle) {
		this.mvTitle = mvTitle;
	}


	public String getThumbnail() {
		return thumbnail;
	}


	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}


	@Override
	public String toString() {
		return "MyMvVO [keepNo=" + keepNo + ", memNo=" + memNo + ", mvNo=" + mvNo + ", mvTitle=" + mvTitle
				+ ", thumbnail=" + thumbnail + "]";
	}
	
	
	
	
}
