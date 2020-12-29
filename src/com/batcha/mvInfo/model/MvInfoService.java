package com.batcha.mvInfo.model;

import java.sql.SQLException;
import java.util.List;

public class MvInfoService {
	MvInfoDAO mvDao;
	
	public MvInfoService() {
		mvDao=new MvInfoDAO();
	}
	
	//��ȭ ��� - insert
	public int insertMv(MvInfoVO vo) throws SQLException {
		return mvDao.insertMv(vo);
	}
	
	//��ȭ ��ü ��ȸ - select
	public List<MvInfoVO> selectAllMv() throws SQLException {
		return mvDao.selectAllMv();
	}

	//��ȣ�� ��ȭ ��ȸ - select(mvNo)
	public MvInfoVO selectMv(int mvNo) throws SQLException {
		return mvDao.selectMv(mvNo);
	}
	
	//��ȭ ���� - update
	public int updateMv(MvInfoVO mVo) throws SQLException {
		return mvDao.updateMv(mVo);
	}
	
	//��ȭ ����  - delete
	public int deleteMv(int mvNo) throws SQLException {
		return mvDao.deleteMv(mvNo);
	}
	
	
}
