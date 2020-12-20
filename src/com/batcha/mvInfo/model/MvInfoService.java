package com.batcha.mvInfo.model;

import java.sql.SQLException;

public class MvInfoService {
	MvInfoDAO dao;
	
	public MvInfoService() {
		dao=new MvInfoDAO();
	}
	
	public MvInfoVO selectMvByMvNo(int mvNo) throws SQLException {
		return dao.selectMvByMvNo(mvNo);
	}
}
