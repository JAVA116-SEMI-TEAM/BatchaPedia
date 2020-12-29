package com.batcha.mvInfo.model;

import java.sql.SQLException;
import java.util.List;

public class MvInfoService {
	MvInfoDAO mvDao;
	
	public MvInfoService() {
		mvDao=new MvInfoDAO();
	}
	
	//영화 등록 - insert
	public int insertMv(MvInfoVO vo) throws SQLException {
		return mvDao.insertMv(vo);
	}
	
	//영화 전체 조회 - select
	public List<MvInfoVO> selectAllMv() throws SQLException {
		return mvDao.selectAllMv();
	}

	//번호로 영화 조회 - select(mvNo)
	public MvInfoVO selectMv(int mvNo) throws SQLException {
		return mvDao.selectMv(mvNo);
	}
	
	//영화 수정 - update
	public int updateMv(MvInfoVO mVo) throws SQLException {
		return mvDao.updateMv(mVo);
	}
	
	//영화 삭제  - delete
	public int deleteMv(int mvNo) throws SQLException {
		return mvDao.deleteMv(mvNo);
	}
	
	
}
