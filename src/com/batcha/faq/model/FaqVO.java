package com.batcha.faq.model;

import java.sql.Timestamp;

public class FaqVO {
	private int FaqNo;
	private String title;
	private String content;
	private Timestamp regdate;
	
	public FaqVO() {
		super();
	}
	
	
	
	public FaqVO(int faqNo, String title, String content, Timestamp regdate, String opt) {
		super();
		FaqNo = faqNo;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
	}

	public FaqVO(int faqNo,  String title, String content, Timestamp regdate) {
		super();
		FaqNo = faqNo;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
	}

	public int getFaqNo() {
		return FaqNo;
	}

	public void setFaqNo(int faqNo) {
		FaqNo = faqNo;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	

	@Override
	public String toString() {
		return "FaqVO [FaqNo=" + FaqNo + ", title=" + title + ", content=" + content + ", regdate=" + regdate + "]";
	}

	
}

