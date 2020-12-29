package com.batcha.starsData.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.batcha.db.ConnectionPoolMgr2;

public class starsDataDAO {
	ConnectionPoolMgr2 pool;
	
	public starsDataDAO() {
		pool=new ConnectionPoolMgr2();
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
	
	//ȸ��/��ȭ��ȣ�� ��������Ʈ ��ȸ�ϱ�. list.size�� ȸ��/��ȭ�� ���� ������ Ȯ�� ����
	public List<starsDataVO> selectAllStarsByNo(int no, boolean isMemNo) throws SQLException{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<starsDataVO> list=null;
		
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
				starsVo.setStars(rs.getFloat("stars"));
				
				list.add(starsVo);
			}
			System.out.println("ȸ�� �Ǵ� ��ȭ��ȣ�� ��ü��ȸ ��� list.size="+list.size()
							  +", �Ű����� no="+no+", isMemNo="+isMemNo);
			return list;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
	
	//����Ʈ�ε�.. ���� �Ѱ��� �������� ��
	//�� �޼ҵ� ������� 0�̸� �ᱹ ������ �Է����� �ʾҴٴ� ���ε�
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
			System.out.println("Ư�� ȸ���� Ư�� ��ȭ�� �ű� ���� ��ȸ ��� memStars="+memStars
							+", �Ű����� memNo="+memNo+", mvNo="+mvNo);
			return memStars;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	
	}
	
	//���� �Է��ϱ�
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
			ps.setFloat(3, starsVo.getStars());
			
			cnt=ps.executeUpdate();
			
			System.out.println("���� �Է� ��� cnt="+cnt+", �Ű����� starsVo="+starsVo);
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	//Ư�� ȸ���� Ư�� ��ȭ ���� �����ϱ�
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
			
			System.out.println("���� ���� ��� cnt="+cnt+", �Ű����� memNo="+memNo+", mvNo="+mvNo);
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
			
			ps.setFloat(1, starsVo.getStars());
			ps.setInt(2, starsVo.getMemNo());
			ps.setInt(3, starsVo.getMvNo());
			
			cnt=ps.executeUpdate();
			
			System.out.println("���� ������Ʈ ��� cnt="+cnt+", �Ű����� starsVo="+starsVo);
			
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	//Ư�� ȸ���� ��ȭ�� ���� �ο��ߴ��� ���� ��ȸ
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
}
