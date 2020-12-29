package com.batcha.mynotice.model;

import java.sql.Timestamp;

public class NoticeVO {
	private int noticeNo;
	private String title;
	private String content;
	private String author;
	private Timestamp regdate;
	private int readcount;
	
	
	public NoticeVO() {
		super();
	}


	public NoticeVO(int noticeNo, String title, String content, String author, Timestamp regdate,int readcount) {
		super();
		this.noticeNo = noticeNo;
		this.title = title;
		this.content = content;
		this.author = author;
		this.regdate = regdate;
		this.readcount=readcount;
	}


	public int getNoticeNo() {
		return noticeNo;
	}


	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
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


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public Timestamp getRegdate() {
		return regdate;
	}


	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	
	public int getReadcount() {
		return readcount;
	}


	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}


	@Override
	public String toString() {
		return "NoticeVO [noticeNo=" + noticeNo + ", title=" + title + ", content=" + content + ", author=" + author
				+ ", regdate=" + regdate + ", readcount=" + readcount + "]";
	}
	
	
}
