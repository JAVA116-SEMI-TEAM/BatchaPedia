package com.batcha.starsData.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class starsDataService {

	starsDataDAO starsDao;

	//mvNo에 해당하는 영화에 memNo회원이 평점을 매겼는지 여부 판별
	public final static int YES_YOU_DID=1;
	public final static int NO_YOU_DIDNT=0;
	
	public starsDataService() {
		starsDao=new starsDataDAO();
	}

	//영화의 평균 평점 조회
	public float getAvgStars(int mvNo) throws SQLException {
		return starsDao.getAvgStars(mvNo);
	}
	
	//회원/영화번호로 평점리스트 조회하기. list.size로 회원/영화별 평점 개수도 확인 가능
	public List<starsDataVO> selectAllStarsByNo(int no, boolean isMemNo) throws SQLException{
		return starsDao.selectAllStarsByNo(no, isMemNo);
	}
	
	//셀렉트인데.. 평점 한개만 가져오는 것
	//이 메소드 결과값이 0이면 결국 평점을 입력하지 않았다는 뜻인데
	public int getStarsByMemNo(int memNo, int mvNo) throws SQLException {//평점을 부여한 경우 몇점인지 찾기
		return starsDao.getStarsByMemNo(memNo, mvNo);
	}
	
	//평점 입력하기
	public int insertStars(starsDataVO starsVo) throws SQLException {
		return starsDao.insertStars(starsVo);
	}
	
	//특정 회원의 특정 영화 평점 삭제하기
	public int deleteStars(int memNo, int mvNo) throws SQLException {
		return starsDao.deleteStars(memNo, mvNo);
	}
	
	public int updateStars(starsDataVO starsVo) throws SQLException {
		return starsDao.updateStars(starsVo);
	}
	
	//특정 회원의 영화별 평점 부여했는지 여부 조회 평점조회에서 확인가능할거같은데 있어야 하나 없애야 하나 고민
	public int didStars(int memNo, int mvNo) throws SQLException {
		return starsDao.didStars(memNo, mvNo);
	}
}
