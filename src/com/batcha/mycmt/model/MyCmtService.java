package com.batcha.mycmt.model;

import java.sql.SQLException;
import java.util.List;

public class MyCmtService {
	private MyCmtDAO mcDao;

	public MyCmtService() {
		mcDao = new MyCmtDAO();
	}

	public List<MyCmtVO> cmtByMemno(int memNo) throws SQLException {
		return mcDao.cmtByMemno(memNo);
	}

	public List<MyCmtVO> selectMainMyCmt(int memNo) throws SQLException{
		return mcDao.selectMainMyCmt(memNo);
	}

	public MyCmtVO selectBynum(int cmtNo) throws SQLException {
		return mcDao.selectBynum(cmtNo);
	}
	
	
	
}
