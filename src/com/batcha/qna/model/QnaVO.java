package com.batcha.qna.model;

import java.sql.Timestamp;

public class QnaVO {
	private int qnaNo;/* 번호 */
	private int memNo; /* 회원번호 */
	private String title; /* 제목 */
	private String content; /* 내용 */
	private String author; /* 작성자 */
	private Timestamp regdate; /* 작성일 */
	private int readCount; /* 조회수 */
	private int groupno; /* 글묶음번호 */
	private int step ; /* 단계 */
	private int sortNo; /* 소트넘버 */
	private String delFlag;/* 삭제여부 */
	private String userid;
	private int admincheck;
	
	public QnaVO() {
		super();
	}

	public QnaVO(int qnaNo, int memNo, String title, String content, String author, Timestamp regdate, int readCount,
			int groupno, int step, int sortNo, String delFlag, String userid, int admincheck) {
		super();
		this.qnaNo = qnaNo;
		this.memNo = memNo;
		this.title = title;
		this.content = content;
		this.author = author;
		this.regdate = regdate;
		this.readCount = readCount;
		this.groupno = groupno;
		this.step = step;
		this.sortNo = sortNo;
		this.delFlag = delFlag;
		this.userid = userid;
		this.admincheck = admincheck;
	}
	

	public QnaVO(int qnaNo, int memNo, String title, String content, String author, Timestamp regdate, int readCount,
			int groupno, int step, int sortNo, String delFlag) {
		super();
		this.qnaNo = qnaNo;
		this.memNo = memNo;
		this.title = title;
		this.content = content;
		this.author = author;
		this.regdate = regdate;
		this.readCount = readCount;
		this.groupno = groupno;
		this.step = step;
		this.sortNo = sortNo;
		this.delFlag = delFlag;
	}

	public int getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
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

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getGroupno() {
		return groupno;
	}

	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getSortNo() {
		return sortNo;
	}

	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getAdmincheck() {
		return admincheck;
	}

	public void setAdmincheck(int admincheck) {
		this.admincheck = admincheck;
	}

	@Override
	public String toString() {
		return "QnaVO [qnaNo=" + qnaNo + ", memNo=" + memNo + ", title=" + title + ", content=" + content + ", author="
				+ author + ", regdate=" + regdate + ", readCount=" + readCount + ", groupno=" + groupno + ", step="
				+ step + ", sortNo=" + sortNo + ", delFlag=" + delFlag + ", userid=" + userid + ", admincheck="
				+ admincheck + "]";
	}

	
	
}
