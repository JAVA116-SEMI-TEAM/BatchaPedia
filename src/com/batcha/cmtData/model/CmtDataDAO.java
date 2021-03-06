package com.batcha.cmtData.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.batcha.db.ConnectionPoolMgr2;

public class CmtDataDAO {
	private ConnectionPoolMgr2 pool;
	
	public CmtDataDAO() {
		pool=new ConnectionPoolMgr2();
	}
	
	//코멘트 추가 메소드
	public int insertCmt(CmtDataVO cmtVo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		int cnt=0;
		
		try {
			con=pool.getConnection();
			
			String sql="insert into cmtData(cmtno, cmttext, mvno, memno, userid)"
					+ " values(cmtData_seq.nextval, ?, ?, ?, ?)";
			ps=con.prepareStatement(sql);
			
			ps.setString(1, cmtVo.getCmtText());
			ps.setInt(2, cmtVo.getMvNo());
			ps.setInt(3, cmtVo.getMemNo());
			ps.setString(4, cmtVo.getUserid());
			
			cnt=ps.executeUpdate();
			System.out.println("코멘트 등록 결과 cnt="+cnt+", 매개변수 cmtVo="+cmtVo);
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	//코멘트 업데이트 메소드
	public int updateCmt(CmtDataVO cmtVo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		int cnt=0;
		
		try {
			con=pool.getConnection();
			
			String sql="update cmtdata" + 
					" set cmttext=?, cmtregdate=sysdate" + 
					" where cmtno=?";
			ps=con.prepareStatement(sql);
			
			ps.setString(1, cmtVo.getCmtText());
			ps.setInt(2, cmtVo.getCmtNo());
			
			cnt=ps.executeUpdate();
			
			System.out.println("코멘트 수정 결과 cnt="+cnt+", 매개변수 cmtVo="+cmtVo);
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	//코멘트 삭제 메소드
	public int deleteCmt(int cmtNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		int cnt=0;
		
		try {
			con=pool.getConnection();
			
			String sql="delete from cmtdata where cmtno=?";
			
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, cmtNo);
			
			cnt=ps.executeUpdate();
			
			System.out.println("코멘트 삭제 결과 cnt="+cnt+", 매개변수 cmtNo="+cmtNo);
			return cnt;
			
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	//코멘트를 영화번호나 멤버번호에 따라 셀렉트하는 메소드
	//no가 회원번호면 isMemNo=true, 영화번호면 false로 매개변수 줄 것
	public List<CmtDataVO> selectCmtByNo(int no, boolean isMemNo) throws SQLException{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<CmtDataVO> list=new ArrayList<CmtDataVO>();
		
		try {
			con=pool.getConnection();
			
			String sql="select * from cmtData where";		
			
			if(isMemNo) {
				sql+=" memNo=? order by cmtno desc";
			}else {
				sql+=" mvNo=? order by cmtno desc";
			}
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, no);
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				CmtDataVO cmtVo=new CmtDataVO();
				
				cmtVo.setCmtNo(rs.getInt("cmtNo"));
				cmtVo.setCmtText(rs.getString("cmtText"));
				cmtVo.setCmtRegdate(rs.getTimestamp("cmtRegdate"));
				cmtVo.setUserid(rs.getString("userid"));
				
				if(isMemNo) {
					cmtVo.setMemNo(no);
					cmtVo.setMvNo(rs.getInt("mvNo"));
				}else {
					cmtVo.setMemNo(rs.getInt("memNo"));
					cmtVo.setMvNo(no);
				}
				list.add(cmtVo);
			}
			System.out.println("코멘트 전체조회 결과 list.size="+list.size()+", 매개변수 no="+no+", isMemNo="+isMemNo);
			return list;
		}finally{
			pool.dbClose(rs, ps, con);
		}
	}
	
	//회원이나 영화번호에 따른 코멘트 개수 가져오기
	//no가 회원번호면 isMemNo=true, 영화번호면 false로 매개변수 줄 것
	public int getCmtCntByNo(int no, boolean isMemNo) throws SQLException{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int cnt=0;
		try {
			con=pool.getConnection();
			
			String sql="select count(*) from cmtData where";
			
			if(isMemNo) {
				sql+=" memNo=?";
			}else {
				sql+=" mvNo=?";
			}
		
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, no);
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				cnt=rs.getInt("count");
			}
			
			System.out.println("코멘트 영화/회원번호당 개수 cnt="+cnt+", 매개변수 no="+no+", isMemNo="+isMemNo);
			return cnt;
			
		}finally{
			pool.dbClose(rs, ps, con);
		}
	}
	
	//코멘트 전체목록.. 이걸 쓸 곳이 있을까?
	public List<CmtDataVO> selectAllCmt() throws SQLException{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<CmtDataVO> list=null;
		
		try {
			con=pool.getConnection();
			
			String sql="select * from cmtData";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				CmtDataVO cmtVo=new CmtDataVO();
				cmtVo.setCmtNo(rs.getInt("cmtNo"));
				cmtVo.setCmtText(rs.getString("cmtText"));
				cmtVo.setMemNo(rs.getInt("memNo"));
				cmtVo.setMvNo(rs.getInt("mvNo"));
				cmtVo.setCmtRegdate(rs.getTimestamp("cmtRegdate"));
				
				list.add(cmtVo);
			}
			
			System.out.println("코멘트 전체조회 결과 list.size="+list.size());
			return list;
			
		}finally{
			pool.dbClose(rs, ps, con);
		}
	}

	public int alreadyWroteOrNot(int memNo, int mvNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int result=0;
		
		try {
			con=pool.getConnection();
			
			String sql="select count(*) as count from cmtData where memno=? and mvno=?";
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, memNo);
			ps.setInt(2, mvNo);
			
			rs=ps.executeQuery();
			
			if(rs.next()) {
				int count=rs.getInt("count");
				if(count>0) { //작성했다면
					result=CmtDataService.ALREADY_WROTE;
				}else if(count==0) { //작성하지 않았다면
					result=CmtDataService.DIDNT_WROTE_YET;
				}
			}
			System.out.println("코멘트 작성여부 조회 결과 result="+result+", 매개변수 memNo"+memNo+", mvNo="+mvNo);
			return result;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
	
	public CmtDataVO selectOneCmt(int memNo, int mvNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			con=pool.getConnection();
			
			String sql="select * from cmtData where memno=? and mvno=?";
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, memNo);
			ps.setInt(2, mvNo);
			
			rs=ps.executeQuery();
			CmtDataVO cmtVo=null;
			System.out.println("if문 전 rs.next()="+rs.next());
			if(rs.next()) {
				System.out.println("if문 후 rs.next()="+rs.next());
				cmtVo.setUserid(rs.getString("userid"));
				cmtVo.setCmtNo(rs.getInt("cmtNo"));
				cmtVo.setCmtRegdate(rs.getTimestamp("cmtRegdate"));
				cmtVo.setCmtText(rs.getString("cmtText"));
				cmtVo.setMemNo(memNo);
				cmtVo.setMvNo(mvNo);
			}
			System.out.println("코멘트 작성여부 조회 결과 cmtVo="+cmtVo+", 매개변수 memNo="+memNo+", mvNo="+mvNo);
			return cmtVo;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
}
