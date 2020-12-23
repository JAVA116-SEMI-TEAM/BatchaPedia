package com.batcha.cmtData.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CmtDataService {
	CmtDataDAO cmtDao;
	
	public CmtDataService() {
		cmtDao = new CmtDataDAO();
	}

	//코멘트 추가 메소드
	public int insertCmt(CmtDataVO cmtVo) throws SQLException {
		return cmtDao.insertCmt(cmtVo);
	}
	
	//코멘트 업데이트 메소드
	public int updateCmt(CmtDataVO cmtVo) throws SQLException {
		return cmtDao.updateCmt(cmtVo);
	}
	
	//코멘트 삭제 메소드
	public int deleteCmt(int cmtNo) throws SQLException {
		return cmtDao.deleteCmt(cmtNo);
	}
	
	//코멘트를 영화번호나 멤버번호에 따라 셀렉트하는 메소드
	//no가 회원번호면 isMemNo=true, 영화번호면 false로 매개변수 줄 것
	public List<CmtDataVO> selectCmtByNo(int no, boolean isMemNo) throws SQLException{
		return cmtDao.selectCmtByNo(no, isMemNo);
	}
	
	//회원이나 영화번호에 따른 코멘트 개수 가져오기
	//no가 회원번호면 isMemNo=true, 영화번호면 false로 매개변수 줄 것
	public int getCmtCntByNo(int no, boolean isMemNo) throws SQLException{
		return cmtDao.getCmtCntByNo(no, isMemNo);
	}
	
	//코멘트 전체목록.. 이걸 쓸 곳이 있을까?
	public List<CmtDataVO> selectAllCmt() throws SQLException{
		return cmtDao.selectAllCmt();
	}
	
}
