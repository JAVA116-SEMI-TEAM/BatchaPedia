package com.batcha.mvInfo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.batcha.db.ConnectionPoolMgr2;

public class MvInfoDAO {
	ConnectionPoolMgr2 pool;
	
	public MvInfoDAO(){
		pool=new ConnectionPoolMgr2();
	}
	
	public MvInfoVO selectMvByMvNo(int mvNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		MvInfoVO mvVo=new MvInfoVO();
		
		try {
			con=pool.getConnection();
			
			String sql="select * from mvinfo where mvno=?";
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, mvNo);
			
			rs=ps.executeQuery();
			
			if(rs.next()) {
				System.out.println("테스트로 무비타이틀"+rs.getString("mvTitle"));
				
				String mvTitle=rs.getString("mvTitle");
				String genre=rs.getString("genre");
				String director=rs.getString("director");
				String actors=rs.getString("actors");
				String story=rs.getString("story");
				String thumbnail=rs.getString("thumbnail");
				String nation=rs.getString("nation");
				String showYear=rs.getString("showYear");
				String makeYear=rs.getString("makeYear");
				int boxOffice=rs.getInt("boxOffice");
				Timestamp startdate=rs.getTimestamp("startdate");
				Timestamp enddate=rs.getTimestamp("enddate");
				Timestamp regdate=rs.getTimestamp("regdate");
				
				mvVo.setActors(actors);
				mvVo.setBoxOffice(boxOffice);
				mvVo.setDirector(director);
				mvVo.setEnddate(enddate);
				mvVo.setGenre(genre);
				mvVo.setMakeYear(makeYear);
				mvVo.setMvNo(mvNo);
				mvVo.setMvTitle(mvTitle);
				mvVo.setNation(nation);
				mvVo.setRegdate(regdate);
				mvVo.setShowYear(showYear);
				mvVo.setStartdate(startdate);
				mvVo.setStory(story);
				mvVo.setThumbnail(thumbnail);
			}
			System.out.println("번호로 영화조회 결과 mvVo="+mvVo+", 매개변수 mvNo="+mvNo);
			return mvVo;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
	
}
