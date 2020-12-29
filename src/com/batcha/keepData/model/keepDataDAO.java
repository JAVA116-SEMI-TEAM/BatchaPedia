package com.batcha.keepData.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.batcha.db.ConnectionPoolMgr2;

public class keepDataDAO {
	ConnectionPoolMgr2 pool;
	
	public keepDataDAO() {
		pool=new ConnectionPoolMgr2();
	}
	//킵의 데이터 특성상 업데이트기능은 불필요하여 제외함
	//킵목록에 추가
	public int insertKeep(int memNo, int mvNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			con=pool.getConnection();
			
			String sql="insert into keepData(keepno, memno, mvno)" + 
					" values(keepData_seq.nextval, ?, ?)";
			
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, memNo);
			ps.setInt(2, mvNo);
			
			int cnt=ps.executeUpdate();
			System.out.println("찜 추가 결과 cnt="+cnt+", 매개변수 memNo="+memNo+", mvNo="+mvNo);
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	//킵목록에서 삭제
	public int deleteKeep(int memNo, int mvNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			con=pool.getConnection();
			
			String sql="delete from keepdata" + 
					   " where memno=? and mvno=?";
			
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, memNo);
			ps.setInt(2, mvNo);
			
			int cnt=ps.executeUpdate();
			System.out.println("찜 삭제 결과 cnt="+cnt+", 매개변수 memNo="+memNo+", mvNo="+mvNo);
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	//회원별, 영화별 모든 킵리스트 꺼내기. 총 개수가 필요하면 list.size로 활용 가능
	public List<keepDataVO> selectAllKeepByMemNo(int no, boolean isMemNo) throws SQLException{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<keepDataVO> list=null;
		
		try {
			con=pool.getConnection();
			
			String sql="select * from keepdata where ";
			
			if(isMemNo) {
				sql+=" memNo=?";
			}else {
				sql+=" mvNo=?";
			}
			
			sql+=" order by keepno desc";
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				keepDataVO keepVo=new keepDataVO();
				keepVo.setKeepNo(rs.getInt("keepNo"));
				
				if(isMemNo) {
					keepVo.setMemNo(no);
					keepVo.setMvNo(rs.getInt("mvNo"));
				}else {
					keepVo.setMemNo(rs.getInt("memNo"));
					keepVo.setMvNo(no);
				}
				
				list.add(keepVo);
			}
			
			System.out.println("회원/영화별 찜목록 전체조회 결과 list.size="+list.size()
								+", 매개변수 no="+no+", isMemNo="+isMemNo);
			return list;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
	
	//킵돼있는지 확인하기
	public int isKept(int memNo, int mvNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		keepDataVO keepVo=null;
		int result=0;
		try {
			con=pool.getConnection();
			
			String sql="select count(*) as count from keepData where mvno=? and memno=?";
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, mvNo);
			ps.setInt(2, memNo);
			
			rs=ps.executeQuery();
			
			if(rs.next()) {
				if(rs.getInt("count")==keepDataService.IS_KEPT) {
					result=keepDataService.IS_KEPT;
				}else{
					result=keepDataService.IS_NOT_KEPT;					
				}
			}
			
			System.out.println("찜 여부 조회 결과 result="+result+", 매개변수 mvNo="+mvNo+", memNo="+memNo);
			return result;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
}
