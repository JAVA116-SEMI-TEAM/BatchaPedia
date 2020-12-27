package com.batcha.starsData.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.batcha.db.ConnectionPoolMgr2;

public class starsDataDAO {
	ConnectionPoolMgr2 pool;
	
	public starsDataDAO() {
		pool=new ConnectionPoolMgr2();
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
	
	//회원/영화번호로 평점리스트 조회하기. list.size로 회원/영화별 평점 개수도 확인 가능
	public List<starsDataVO> selectAllStarsByNo(int no, boolean isMemNo) throws SQLException{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<starsDataVO> list = new ArrayList<starsDataVO>();
		
		try {
			con=pool.getConnection();
			
			String sql="select * from starsData where";
			
			if(isMemNo) {
				sql+=" memNo=? order by memNo desc";
			}else {
				sql+=" mvNo=? order by mvNo desc";
			}
					
			ps=con.prepareStatement(sql);
			ps.setInt(1, no);
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				starsDataVO starsVo=new starsDataVO();
				starsVo.setMemNo(rs.getInt("memNo"));
				starsVo.setMvNo(rs.getInt("mvNo"));
				starsVo.setStarsNo(rs.getInt("starsNo"));
				starsVo.setStars(rs.getInt("stars"));
				
				list.add(starsVo);
			}
			System.out.println("회원 또는 영화번호로 전체조회 결과 list.size="+list.size()
							  +", 매개변수 no="+no+", isMemNo="+isMemNo);
			return list;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
	
	//셀렉트인데.. 평점 한개만 가져오는 것
	//이 메소드 결과값이 0이면 결국 평점을 입력하지 않았다는 뜻인데
	public int getStarsByMemNo(int memNo, int mvNo) throws SQLException {//평점을 부여한 경우 몇점인지 찾기
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int memStars=0;
		
		try {
			con=pool.getConnection();
			
			String sql="select stars from starsData where memno=? and mvno=?";
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, memNo);
			ps.setInt(2, mvNo);
			
			rs=ps.executeQuery();
					
			if(rs.next()) {
				memStars=rs.getInt("stars");
			}
			System.out.println("특정 회원의 특정 영화에 매긴 평점 조회 결과 memStars="+memStars
							+", 매개변수 memNo="+memNo+", mvNo="+mvNo);
			return memStars;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	
	}
	
	//평점 입력하기
	public int insertStars(starsDataVO starsVo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		int cnt=0;
		
		try {
			con=pool.getConnection();
			
			String sql="insert into starsdata(starsno, memNo, mvNo, stars)"
					+ " values(starsdata_seq.nextval, ?, ?, ?)";
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, starsVo.getMemNo());
			ps.setInt(2, starsVo.getMvNo());
			ps.setInt(3, starsVo.getStars());
			
			cnt=ps.executeUpdate();
			
			System.out.println("평점 입력 결과 cnt="+cnt+", 매개변수 starsVo="+starsVo);
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	//특정 회원의 특정 영화 평점 삭제하기
	public int deleteStars(int memNo, int mvNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		int cnt=0;
		
		try {
			con=pool.getConnection();
			
			String sql="delete from starsData where memNo=? and mvNo=?";
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, memNo);
			ps.setInt(2, mvNo);
			
			cnt=ps.executeUpdate();
			
			System.out.println("평점 삭제 결과 cnt="+cnt+", 매개변수 memNo="+memNo+", mvNo="+mvNo);
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	public int updateStars(starsDataVO starsVo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		int cnt=0;
		
		try {
			con=pool.getConnection();
			
			String sql="update starsData"
					+ " set stars=?"
					+ " where memNo=? and mvNo=?";
			
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, starsVo.getStars());
			ps.setInt(2, starsVo.getMemNo());
			ps.setInt(3, starsVo.getMvNo());
			
			cnt=ps.executeUpdate();
			
			System.out.println("평점 업데이트 결과 cnt="+cnt+", 매개변수 starsVo="+starsVo);
			
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	//특정 회원의 영화별 평점 부여했는지 여부 조회
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

	public int[] makeStarGraph(List<starsDataVO> starsList) {
		int[] graphData = {0,0,0,0,0,0,0,0,0,0};
		
		for(int i=0; i<starsList.size(); i++) { //평점리스트의 n번째가 평점 몇점인지 가져와서 데이터배열에 입력
			int star=starsList.get(i).getStars();
			System.out.println("star="+star);
			if(star==1) {
				graphData[0]++;
				System.out.println("graphData[0]"+graphData[0]);
			}else if(star==2) {
				graphData[1]++;
				System.out.println("graphData[1]"+graphData[1]);
			}else if(star==3) {
				graphData[2]++;
				System.out.println("graphData[2]"+graphData[2]);
			}else if(star==4) {
				graphData[3]++;
				System.out.println("graphData[3]"+graphData[3]);
			}else if(star==5) {
				graphData[4]++;
				System.out.println("graphData[4]"+graphData[4]);
			}else if(star==6) {
				graphData[5]++;
				System.out.println("graphData[5]"+graphData[5]);
			}else if(star==7) {
				graphData[6]++;
				System.out.println("graphData[6]"+graphData[6]);
			}else if(star==8) {
				graphData[7]++;
				System.out.println("graphData[7]"+graphData[7]);
			}else if(star==9) {
				graphData[8]++;
				System.out.println("graphData[8]"+graphData[8]);
			}else if(star==10) {
				graphData[9]++;
				System.out.println("graphData[9]"+graphData[9]);
			}
		}
		
		return graphData;
	}
}
