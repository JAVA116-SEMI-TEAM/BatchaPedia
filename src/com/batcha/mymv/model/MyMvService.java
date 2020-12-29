package com.batcha.mymv.model;

import java.sql.SQLException;
import java.util.List;

public class MyMvService{
	private MyMvDAO mmDao;
	
	public MyMvService(){
		mmDao= new MyMvDAO();
	}
	
	public List<MyMvVO> KeepByMemNo(int memNo) throws SQLException{
		return mmDao.KeepByMemNo(memNo);
	}
}
