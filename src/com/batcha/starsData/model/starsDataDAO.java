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
	
	//Ư�� ȸ���� ��ȭ�� ���� �ο��ߴ��� ���� ��ȸ�ϰ� �ο������� ������ ���� ��������
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
				if(rs.getInt("count")>0) { //ī��Ʈ�� ������
					didStars=starsDataService.YES_YOU_DID;
				}else {
					didStars=starsDataService.NO_YOU_DIDNT;
				}
			}
			
			System.out.println("ȸ���� Ư����ȭ �����ο����� ��� didStar="+didStars+", �Ű����� memNo="+memNo+", mvNo="+mvNo);
			return didStars;
		}finally {
			pool.dbClose(rs, ps, con);
		}
		
	}

	//��ȭ�� ��� ���� ��ȸ
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
			
			System.out.println("��ȭ�� ���� ��ȸ ��� stars="+avgStars+", �Ű����� mvNo="+mvNo);
			return avgStars;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
	
	public float getStarsByMemNo(int memNo, int mvNo) throws SQLException {//������ �ο��� ��� �������� ã��
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
