package com.batcha.mvInfo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.batcha.db.ConnectionPoolMgr2;

public class MvInfoDAO {
	private ConnectionPoolMgr2 pool;
	
	public MvInfoDAO() {
		pool=new ConnectionPoolMgr2();
	}
	//영화 등록 - insert
	public int insertMv(MvInfoVO vo) throws SQLException {
		Connection con=null;
		PreparedStatement ps = null;
		
		try {
			//1
			con=pool.getConnection();
			
			//2
			String sql="insert into mvInfo(mvNo,mvTitle,genre,director, " + 
					"    actors,story,thumbnail,nation,makeyear, " + 
					"    boxoffice,startdate,enddate,mvTitleEn,mvCode) " + 
					" values(mvInfo_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getMvTitle());
			ps.setString(2, vo.getGenre());
			ps.setString(3, vo.getDirector());
			ps.setString(4, vo.getActors());
			ps.setString(5, vo.getStory());
			ps.setString(6, vo.getThumbnail());
			ps.setString(7, vo.getNation());
			ps.setString(8, vo.getMakeYear());
			ps.setInt(9, vo.getBoxOffice());
			ps.setTimestamp(10, vo.getStartdate());
			ps.setTimestamp(11, vo.getEnddate());
			ps.setString(12, vo.getMvTitleEn());
			ps.setString(13, vo.getMvCode());
			
			//3
			int cnt=ps.executeUpdate();
			System.out.println("영화 등록 결과, cnt="+cnt+", 매개변수 vo="+vo);
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	//영화 전체 조회 - select
	public List<MvInfoVO> selectAllMv() throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<MvInfoVO> list = new ArrayList<MvInfoVO>();
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="select * from mvInfo order by mvNo desc";
			ps=con.prepareStatement(sql);
			
			//4
			rs=ps.executeQuery();
			while(rs.next()) {
				int mvNo=rs.getInt("mvNo");
				String mvTitle=rs.getString("mvTitle");
				String genre=rs.getString("genre");
				String director=rs.getString("director");
				String actors=rs.getString("actors");
				String story=rs.getString("story");
				String thumbnail=rs.getString("thumbnail");
				String nation=rs.getString("nation");
				String makeYear=rs.getString("makeYear");
				int boxOffice=rs.getInt("boxOffice");
				Timestamp startdate=rs.getTimestamp("startdate");
				Timestamp enddate=rs.getTimestamp("enddate");
				Timestamp regdate=rs.getTimestamp("regdate");
				String mvCode=rs.getString("mvCode");
				String mvTitleEn=rs.getString("mvTitleEn");
				
				MvInfoVO vo = new MvInfoVO(mvNo, mvTitle, genre, director, 
						actors, story, thumbnail, nation, makeYear,
						boxOffice, startdate, enddate, regdate, mvCode, mvTitleEn);
				list.add(vo);
			}
			System.out.println("영화 조회 결과, list.size="+list.size());
			
			return list;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
	
	//번호로 영화 조회 - select(mvNo)
	public MvInfoVO selectMv(int mvNo) throws SQLException {

		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		MvInfoVO vo = new MvInfoVO(); 
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="select * from mvInfo where mvNo=? ";
			ps=con.prepareStatement(sql);
			ps.setInt(1, mvNo);
			
			//4
			rs=ps.executeQuery();
			if(rs.next()) {
				vo.setMvNo(mvNo);
				vo.setMvTitle(rs.getString("mvTitle"));
				vo.setGenre(rs.getString("genre"));
				vo.setDirector(rs.getString("director"));
				vo.setActors(rs.getNString("actors"));
				vo.setStory(rs.getString("story"));
				vo.setThumbnail(rs.getString("thumbnail"));
				vo.setNation(rs.getString("nation"));
				vo.setMakeYear(rs.getString("makeYear"));
				vo.setBoxOffice(rs.getInt("boxOffice"));
				vo.setStartdate(rs.getTimestamp("startdate"));
				vo.setEnddate(rs.getTimestamp("enddate"));
				vo.setRegdate(rs.getTimestamp("regdate"));
				vo.setMvCode(rs.getString("mvCode"));
				vo.setMvTitleEn(rs.getString("mvTitleEn"));
				
			}
			System.out.println("영화 상세 조회 결과, vo="+vo+", 매개변수 mvNo="+mvNo);
			return vo;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
	
	//영화 삭제 - delete
	public int deleteMv(int mvNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="delete from mvInfo where mvNo=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, mvNo);
			
			//4
			int cnt=ps.executeUpdate();
			System.out.println("영화 삭제 결과, cnt="+cnt+", 매개변수 mvNo="+mvNo);
			
			return cnt;
		}finally {
			pool.dbClose(ps, con); 
		}
	}

	//키워드로 영화 검색
	public List<MvInfoVO> selectAllMv(String option,String keyword) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<MvInfoVO> list = new ArrayList<MvInfoVO>();
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="select * from mvInfo ";
			if(keyword!=null && !keyword.isEmpty()) {
				sql+=" where "+option+" like '%' || ? ||'%' ";
			}
			
			sql+=" order by mvNo desc";
			ps=con.prepareStatement(sql);
			
			//4
			if(keyword!=null && !keyword.isEmpty()) {
				ps.setString(1, keyword); 
			}
			
			rs=ps.executeQuery();

			while(rs.next()) {
				int mvNo=rs.getInt("mvNo");
				String mvTitle=rs.getString("mvTitle");
				String genre=rs.getString("genre");
				String director=rs.getString("director");
				String actors=rs.getString("actors");
				String story=rs.getString("story");
				String thumbnail=rs.getString("thumbnail");
				String nation=rs.getString("nation");
				String makeYear=rs.getString("makeYear");
				int boxOffice=rs.getInt("boxOffice");
				Timestamp startdate=rs.getTimestamp("startdate");
				Timestamp enddate=rs.getTimestamp("enddate");
				Timestamp regdate=rs.getTimestamp("regdate");
				String mvCode=rs.getString("mvCode");
				String mvTitleEn=rs.getString("mvTitleEn");
				
				MvInfoVO vo = new MvInfoVO(mvNo, mvTitle, genre, director, 
						actors, story, thumbnail, nation, makeYear,
						boxOffice, startdate, enddate, regdate, mvCode, mvTitleEn);
				list.add(vo);
			}
			System.out.println("영화 조회 결과, list.size="+list.size()
				+", 매개변수 option="+option+", keyword="+keyword);
			
			return list;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
	

	//영화 수정(MvEditOkController) - update
	public int updateMvInfo(MvInfoVO mVo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="update mvInfo " + 
					" set mvTitle=?,genre=?,director=?, " + 
					"    actors=?,story=?,thumbnail=?,nation=?, " + 
					"    makeyear=?,boxoffice=?,startdate=?,enddate=?, " + 
					"    mvTitleEn=?,mvCode=?" + 
					" where mvNo=?";
			
			ps=con.prepareStatement(sql);
			ps.setString(1, mVo.getMvTitle());
			ps.setString(2, mVo.getGenre());
			ps.setString(3, mVo.getDirector());
			ps.setString(4, mVo.getActors());
			ps.setString(5, mVo.getStory());
			ps.setString(6, mVo.getThumbnail());
			ps.setString(7, mVo.getNation());
			ps.setString(8, mVo.getMakeYear());
			ps.setInt(9, mVo.getBoxOffice());
			ps.setTimestamp(10, mVo.getStartdate());
			ps.setTimestamp(11, mVo.getEnddate());
			ps.setInt(12, mVo.getMvNo());
			
			//4
			int cnt=ps.executeUpdate();
			System.out.println("영화 수정 결과, cnt="+cnt+", 매개변수 mVo="+mVo);
			
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}

	//박스오피스 리스트
	public List<MvInfoVO> selectBoxOfficeList() throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<MvInfoVO> list=new ArrayList<MvInfoVO>();
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="select * from mvInfo where boxOffice is not null order by boxOffice";
			ps=con.prepareStatement(sql);
			
			//4
			rs=ps.executeQuery();
			
			while(rs.next()) {
				MvInfoVO vo=new MvInfoVO();
				vo.setMvNo(rs.getInt("mvNo"));
				vo.setMvCode(rs.getString("mvCode"));
				vo.setMvTitle(rs.getString("mvTitle"));
				vo.setMvTitleEn(rs.getString("mvTitleEn"));
				vo.setGenre(rs.getString("genre"));
				vo.setDirector(rs.getString("director"));
				vo.setActors(rs.getString("actors"));
				vo.setStory(rs.getString("story"));
				vo.setThumbnail(rs.getString("thumbnail"));
				vo.setNation(rs.getString("nation"));
				vo.setMakeYear(rs.getString("makeYear"));
				vo.setBoxOffice(rs.getInt("boxOffice"));
				vo.setStartdate(rs.getTimestamp("startdate"));
				vo.setEnddate(rs.getTimestamp("enddate"));
				vo.setRegdate(rs.getTimestamp("regdate"));
				
				list.add(vo);
			}
			System.out.println("박스오피스 영화 조회 결과, list.size="+list.size());
			System.out.println(list.get(0));
			System.out.println(list.get(1));
			return list;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
	   
	   //한국영화
	   public List<MvInfoVO> selectKorMovie() throws SQLException {
		   Connection con=null;
		   PreparedStatement ps=null;
		   ResultSet rs=null;
		   List<MvInfoVO> list=new ArrayList<MvInfoVO>();
		   try {
			   //1,2
			   con=pool.getConnection();
			   
			   //3
			   String sql="select * from mvinfo where nation='한국' and ROWNUM<=5";
			   ps=con.prepareStatement(sql);
			   
			   //4
			   rs=ps.executeQuery();
			   
			   while(rs.next()) {
				   MvInfoVO vo=new MvInfoVO();
				   vo.setMvNo(rs.getInt("mvNo"));
				   vo.setMvCode(rs.getString("mvCode"));
				   vo.setMvTitle(rs.getString("mvTitle"));
				   vo.setMvTitleEn(rs.getString("mvTitleEn"));
				   vo.setGenre(rs.getString("genre"));
				   vo.setDirector(rs.getString("director"));
				   vo.setActors(rs.getString("actors"));
				   vo.setStory(rs.getString("story"));
				   vo.setThumbnail(rs.getString("thumbnail"));
				   vo.setNation(rs.getString("nation"));
				   vo.setMakeYear(rs.getString("makeYear"));
				   vo.setBoxOffice(rs.getInt("boxOffice"));
				   vo.setStartdate(rs.getTimestamp("startdate"));
				   vo.setEnddate(rs.getTimestamp("enddate"));
				   vo.setRegdate(rs.getTimestamp("regdate"));
				   
				   list.add(vo);
			   }
			   System.out.println("박스오피스 영화 조회 결과, list.size="+list.size());
			   System.out.println(list.get(0));
			   System.out.println(list.get(1));
			   return list;
		   }finally {
			   pool.dbClose(rs, ps, con);
		   }
	   }
	   
	   //20세기 영화
	   public List<MvInfoVO> select20th() throws SQLException {
		   Connection con=null;
		   PreparedStatement ps=null;
		   ResultSet rs=null;
		   List<MvInfoVO> list=new ArrayList<MvInfoVO>();
		   try {
			   //1,2
			   con=pool.getConnection();
			   
			   //3
			   String sql="select * from mvinfo where makeYear<='2000' and rownum<=5";
			   ps=con.prepareStatement(sql);
			   
			   //4
			   rs=ps.executeQuery();
			   
			   while(rs.next()) {
				   MvInfoVO vo=new MvInfoVO();
				   vo.setMvNo(rs.getInt("mvNo"));
				   vo.setMvCode(rs.getString("mvCode"));
				   vo.setMvTitle(rs.getString("mvTitle"));
				   vo.setMvTitleEn(rs.getString("mvTitleEn"));
				   vo.setGenre(rs.getString("genre"));
				   vo.setDirector(rs.getString("director"));
				   vo.setActors(rs.getString("actors"));
				   vo.setStory(rs.getString("story"));
				   vo.setThumbnail(rs.getString("thumbnail"));
				   vo.setNation(rs.getString("nation"));
				   vo.setMakeYear(rs.getString("makeYear"));
				   vo.setBoxOffice(rs.getInt("boxOffice"));
				   vo.setStartdate(rs.getTimestamp("startdate"));
				   vo.setEnddate(rs.getTimestamp("enddate"));
				   vo.setRegdate(rs.getTimestamp("regdate"));
				   
				   list.add(vo);
			   }
			   System.out.println("박스오피스 영화 조회 결과, list.size="+list.size());
			   System.out.println(list.get(0));
			   System.out.println(list.get(1));
			   return list;
		   }finally {
			   pool.dbClose(rs, ps, con);
		   }
	   }
	   //코미디
	   public List<MvInfoVO> selectComedy() throws SQLException {
		   Connection con=null;
		   PreparedStatement ps=null;
		   ResultSet rs=null;
		   List<MvInfoVO> list=new ArrayList<MvInfoVO>();
		   try {
			   //1,2
			   con=pool.getConnection();
			   
			   //3
			   String sql="select * from mvinfo where genre='코미디' and rownum<=5";
			   ps=con.prepareStatement(sql);
			   
			   //4
			   rs=ps.executeQuery();
			   
			   while(rs.next()) {
				   MvInfoVO vo=new MvInfoVO();
				   vo.setMvNo(rs.getInt("mvNo"));
				   vo.setMvCode(rs.getString("mvCode"));
				   vo.setMvTitle(rs.getString("mvTitle"));
				   vo.setMvTitleEn(rs.getString("mvTitleEn"));
				   vo.setGenre(rs.getString("genre"));
				   vo.setDirector(rs.getString("director"));
				   vo.setActors(rs.getString("actors"));
				   vo.setStory(rs.getString("story"));
				   vo.setThumbnail(rs.getString("thumbnail"));
				   vo.setNation(rs.getString("nation"));
				   vo.setMakeYear(rs.getString("makeYear"));
				   vo.setBoxOffice(rs.getInt("boxOffice"));
				   vo.setStartdate(rs.getTimestamp("startdate"));
				   vo.setEnddate(rs.getTimestamp("enddate"));
				   vo.setRegdate(rs.getTimestamp("regdate"));
				   
				   list.add(vo);
			   }
			   System.out.println("박스오피스 영화 조회 결과, list.size="+list.size());
			   System.out.println(list.get(0));
			   System.out.println(list.get(1));
			   return list;
		   }finally {
			   pool.dbClose(rs, ps, con);
		   }
	   }
	   //스릴러,범죄
	   public List<MvInfoVO> selectThrill() throws SQLException {
		   Connection con=null;
		   PreparedStatement ps=null;
		   ResultSet rs=null;
		   List<MvInfoVO> list=new ArrayList<MvInfoVO>();
		   try {
			   //1,2
			   con=pool.getConnection();
			   
			   //3
			   String sql="select * from mvinfo where (genre='스릴러' or genre='범죄') and rownum<=5";
			   ps=con.prepareStatement(sql);
			   
			   //4
			   rs=ps.executeQuery();
			   
			   while(rs.next()) {
				   MvInfoVO vo=new MvInfoVO();
				   vo.setMvNo(rs.getInt("mvNo"));
				   vo.setMvCode(rs.getString("mvCode"));
				   vo.setMvTitle(rs.getString("mvTitle"));
				   vo.setMvTitleEn(rs.getString("mvTitleEn"));
				   vo.setGenre(rs.getString("genre"));
				   vo.setDirector(rs.getString("director"));
				   vo.setActors(rs.getString("actors"));
				   vo.setStory(rs.getString("story"));
				   vo.setThumbnail(rs.getString("thumbnail"));
				   vo.setNation(rs.getString("nation"));
				   vo.setMakeYear(rs.getString("makeYear"));
				   vo.setBoxOffice(rs.getInt("boxOffice"));
				   vo.setStartdate(rs.getTimestamp("startdate"));
				   vo.setEnddate(rs.getTimestamp("enddate"));
				   vo.setRegdate(rs.getTimestamp("regdate"));
				   
				   list.add(vo);
			   }
			   System.out.println("박스오피스 영화 조회 결과, list.size="+list.size());
			   System.out.println(list.get(0));
			   System.out.println(list.get(1));
			   return list;
		   }finally {
			   pool.dbClose(rs, ps, con);
		   }
	   }
}
