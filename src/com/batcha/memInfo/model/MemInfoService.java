package com.batcha.memInfo.model;

import java.sql.SQLException;

public class MemInfoService {
	//아이디 중복확인시 사용
	public static final int EXIST_ID=1;  //아이디가 이미 존재하는 경우
	public static final int NON_EXIST_ID=2; //존재하지 않는 경우
	
	//로그인 처리시 사용
	public static final int LOGIN_OK=1; //로그인 성공
	public static final int ID_NONE=2; //아이디가 없는 경우
	public static final int PWD_DISAGREE=3; //비밀번호 불일치
	
 	private static int memNo=0;
	private MemInfoDAO memInfoDao;
	
  public static int getMemNo() {
		return memNo;
	}
	
	public void setMemNo(int memNo) {
		this.memNo=memNo;
	}
  
	public MemInfoService() {
		memInfoDao=new MemInfoDAO();
	}
	
	public int loginCheck(String userid, String pwd) throws SQLException{
		return memInfoDao.loginCheck(userid, pwd);
	}
	
	public MemInfoVO selectMember(String userid) throws SQLException{
		return memInfoDao.selectMember(userid);
	}

	public String selectMemByCmt(int cmtMemNo) throws SQLException {
		return memInfoDao.selectMembyCmt(cmtMemNo);

	
	public int checkDup(String userid) throws SQLException{
		return memInfoDao.checkDup(userid);
	}
	
	public int insertMember(MemInfoVO vo) throws SQLException{
		return memInfoDao.insertMember(vo);
	}
}
