package com.batcha.starsData.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.batcha.db.ConnectionPoolMgr2;

public class starsDataDAO {
	ConnectionPoolMgr2 pool;
	
	public starsDataDAO() {
		pool=new ConnectionPoolMgr2();
	}
	
	//특정 회원의 영화별 평점 부여했는지 여부 조회하고 부여했으면 평점도 같이 가져오기
	public int didStars(int memNo, int mvNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int didStars=0;
		
		try {
			con=pool.getConnection();
			
			String sql="select count(*) as count from starsdata where memno=? and mvno=?";
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, memNo);
			ps.setInt(2, mvNo);
			
			rs=ps.executeQuery();
			
			if(rs.next()) {
				if(rs.getInt("count")>0) { //카운트가 있으면
					didStars=starsDataService.YES_YOU_DID;
				}else {
					didStars=starsDataService.NO_YOU_DIDNT;
				}
			}
			
			System.out.println("회원의 특정영화 평점부여여부 결과 didStar="+didStars+", 매개변수 memNo="+memNo+", mvNo="+mvNo);
			return didStars;
		}finally {
			pool.dbClose(rs, ps, con);
		}
		
	}

	//영화의 평균 평점 조회
	public float getAvgStars(int mvNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		float avgStars=0;
		
		try {
			con=pool.getConnection();
			
			String sql="select TRUNC(AVG(NVL(stars, 0)), 1) as avgStars from starsData where mvNo=?";
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, mvNo);
			
			rs=ps.executeQuery();
			
			if(rs.next()) {
				avgStars=rs.getFloat("avgStars");
			}
			
			System.out.println("영화의 평점 조회 결과 stars="+avgStars+", 매개변수 mvNo="+mvNo);
			return avgStars;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
	
	public float getStarsByMemNo(int memNo, int mvNo) throws SQLException {//평점을 부여한 경우 몇점인지 찾기
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		float memStars=0;
		
		try {
			con=pool.getConnection();
			
			String sql="select stars from starsData where memno=? and mvno=?";
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, memNo);
			ps.setInt(2, mvNo);
			
			rs=ps.executeQuery();
					
			if(rs.next()) {
				memStars=rs.getFloat("stars");
			}
			
			return memStars;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	
	}
	
}
