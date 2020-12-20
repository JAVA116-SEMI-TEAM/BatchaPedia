package com.batcha.starsData.model;

import java.sql.SQLException;

public class starsDataService {

	starsDataDAO dao;

	//mvNo에 해당하는 영화에 memNo회원이 평점을 매겼는지 여부 판별
	public final static int YES_YOU_DID=1;
	public final static int NO_YOU_DIDNT=0;
	
	public starsDataService() {
		dao=new starsDataDAO();
	}

	public int didStars(int memNo, int mvNo) throws SQLException{
		return dao.didStars(memNo, mvNo);
	}
	
	public float getAvgStars(int mvNo) throws SQLException {
		return dao.getAvgStars(mvNo);
	}
	
	public float getStarsByMemNo(int memNo, int mvNo) throws SQLException {
		return dao.getStarsByMemNo(memNo, mvNo);
	}
}
