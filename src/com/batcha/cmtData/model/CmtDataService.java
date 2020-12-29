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

	//�ڸ�Ʈ �߰� �޼ҵ�
	public int insertCmt(CmtDataVO cmtVo) throws SQLException {
		return cmtDao.insertCmt(cmtVo);
	}
	
	//�ڸ�Ʈ ������Ʈ �޼ҵ�
	public int updateCmt(CmtDataVO cmtVo) throws SQLException {
		return cmtDao.updateCmt(cmtVo);
	}
	
	//�ڸ�Ʈ ���� �޼ҵ�
	public int deleteCmt(int cmtNo) throws SQLException {
		return cmtDao.deleteCmt(cmtNo);
	}
	
	//�ڸ�Ʈ�� ��ȭ��ȣ�� �����ȣ�� ���� ����Ʈ�ϴ� �޼ҵ�
	//no�� ȸ����ȣ�� isMemNo=true, ��ȭ��ȣ�� false�� �Ű����� �� ��
	public List<CmtDataVO> selectCmtByNo(int no, boolean isMemNo) throws SQLException{
		return cmtDao.selectCmtByNo(no, isMemNo);
	}
	
	//ȸ���̳� ��ȭ��ȣ�� ���� �ڸ�Ʈ ���� ��������
	//no�� ȸ����ȣ�� isMemNo=true, ��ȭ��ȣ�� false�� �Ű����� �� ��
	public int getCmtCntByNo(int no, boolean isMemNo) throws SQLException{
		return cmtDao.getCmtCntByNo(no, isMemNo);
	}
	
	//�ڸ�Ʈ ��ü���.. �̰� �� ���� ������?
	public List<CmtDataVO> selectAllCmt() throws SQLException{
		return cmtDao.selectAllCmt();
	}
	
}
