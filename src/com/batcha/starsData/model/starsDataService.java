package com.batcha.starsData.model;

import java.sql.SQLException;

public class starsDataService {

	starsDataDAO dao;

	//mvNo�� �ش��ϴ� ��ȭ�� memNoȸ���� ������ �Ű���� ���� �Ǻ�
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
