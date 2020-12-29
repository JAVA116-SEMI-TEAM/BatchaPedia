package com.batcha.memInfo.model;

import java.sql.SQLException;
import java.util.List;

public class MemInfoService {
	
	//로그인 처리시 사용
	public static final int LOGIN_OK=1; //로그인 성공
	public static final int ID_NONE=2; //아이디가 없는 경우
	public static final int PWD_DISAGREE=3; //비밀번호 불일치
	public static final int EXIST_ID=1; //이미 존재
	public static final int NON_EXIST_ID=2; //
	
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
	
	//등록
	public int insertMember(MemInfoVO vo) throws SQLException {
		return memInfoDao.insertMember(vo);
	}
	
	//번호조 조회
	public MemInfoVO selectMemByNo(int memNo) throws SQLException {
		return memInfoDao.selectMemByNo(memNo);
	}
	
	//전체조회
	public List<MemInfoVO> selectAll() throws SQLException{
		return memInfoDao.selectAll();
	}
	
	//
	public List<MemInfoVO> selectMemByKey(String condition, String keyword) throws SQLException{
		return memInfoDao.selectMemByKey(condition, keyword);
	}
	
	//수정
	public int updateMem(MemInfoVO vo) throws SQLException {
		return memInfoDao.updateMem(vo);
	}
	
	//삭제
	public int deleteMem(int memNo) throws SQLException {
		return memInfoDao.deleteMem(memNo);
	}
	
	public int totalCount() throws SQLException {
		return memInfoDao.totalCount();
	}

	public String selectMemByCmt(int cmtMemNo) throws SQLException {
		return memInfoDao.selectMembyCmt(cmtMemNo);
	}
	
	public int checkDup(String userid) throws SQLException{
		return memInfoDao.checkDup(userid);
	}

}
