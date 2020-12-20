package com.batcha.keepData.model;

import java.sql.SQLException;

public class keepDataService {
	keepDataDAO dao;
	
	public final static int IS_NOT_KEPT=0;
	public final static int IS_KEPT=1;
	
	
	public keepDataService() {
		dao=new keepDataDAO();
	}
	
	public int isKept(int memNo, int mvNo) throws SQLException {
		return dao.isKept(memNo, mvNo);
	}
	
	
}
