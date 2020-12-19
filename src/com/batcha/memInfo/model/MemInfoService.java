package com.batcha.memInfo.model;

import java.sql.SQLException;

public class MemInfoService {
	
	//로그인 처리시 사용
	public static final int LOGIN_OK=1; //로그인 성공
	public static final int ID_NONE=2; //아이디가 없는 경우
	public static final int PWD_DISAGREE=3; //비밀번호 불일치
	
	private MemInfoDAO memInfoDao;
	
	public MemInfoService() {
		memInfoDao=new MemInfoDAO();
	}
	
	public int loginCheck(String userid, String pwd) throws SQLException{
		return memInfoDao.loginCheck(userid, pwd);
	}
	
	public MemInfoVO selectMember(String userid) throws SQLException{
		return memInfoDao.selectMember(userid);
	}
}
