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
	//ŵ�� ������ Ư���� ������Ʈ����� ���ʿ��Ͽ� ������
	//ŵ��Ͽ� �߰�
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
			System.out.println("�� �߰� ��� cnt="+cnt+", �Ű����� memNo="+memNo+", mvNo="+mvNo);
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	//ŵ��Ͽ��� ����
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
			System.out.println("�� ���� ��� cnt="+cnt+", �Ű����� memNo="+memNo+", mvNo="+mvNo);
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	//ȸ����, ��ȭ�� ��� ŵ����Ʈ ������. �� ������ �ʿ��ϸ� list.size�� Ȱ�� ����
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
			
			System.out.println("ȸ��/��ȭ�� ���� ��ü��ȸ ��� list.size="+list.size()
								+", �Ű����� no="+no+", isMemNo="+isMemNo);
			return list;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
	
	//ŵ���ִ��� Ȯ���ϱ�
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
			
			System.out.println("�� ���� ��ȸ ��� result="+result+", �Ű����� mvNo="+mvNo+", memNo="+memNo);
			return result;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
}
