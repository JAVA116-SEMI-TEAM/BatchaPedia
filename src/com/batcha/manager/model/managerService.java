package com.batcha.manager.model;

import java.sql.SQLException;
import java.util.List;

public class managerService {
	private managerDAO mngDao;
	
	public managerService() {
		mngDao = new managerDAO();
	}
	
	public managerVo mngNselect(int memNo) throws SQLException {
		return mngDao.mngNselect(memNo);
	}
	
	public int mngInsert(managerVo vo) throws SQLException {
		return mngDao.mngInsert(vo);
	}
	
	public List<managerVo> mngSelectAll() throws SQLException{
		return mngDao.mngSelectAll();
	}
}
