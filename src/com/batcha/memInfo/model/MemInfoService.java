package com.batcha.memInfo.model;

public class MemInfoService {
	MemInfoDAO dao;
	private static int memNo=0;
	
	public static int getMemNo() {
		return memNo;
	}
	
	public void setMemNo(int memNo) {
		this.memNo=memNo;
	}
	
	public MemInfoService() {
		dao=new MemInfoDAO();
	}
}
