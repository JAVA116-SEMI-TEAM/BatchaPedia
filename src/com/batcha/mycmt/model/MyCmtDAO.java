package com.batcha.mycmt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.batcha.db.ConnectionPoolMgr2;

public class MyCmtDAO {
	private ConnectionPoolMgr2 pool;

	public MyCmtDAO() {
		pool = new ConnectionPoolMgr2();
	}

	//ȸ������ �� �ڸ�Ʈ ��������
	public List<MyCmtVO> cmtByMemno(int memNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps = null;
		ResultSet rs=null;

		List<MyCmtVO> list= new ArrayList<MyCmtVO>();
		try {
			con = pool.getConnection();

			String sql = "select cmt.cmtNo,cmt.cmtText,cmt.cmtRegdate,cmt.MEMNO,cmt.MVNO,mvif.MVTITLE\n" + 
					"from cmtData cmt, mvInfo mvif\n" + 
					"where cmt.MVNO = mvif.MVNO\n" + 
					"and cmt.MEMNO=? \n" + 
					"order by cmtno desc";
			ps=con.prepareStatement(sql);
			ps.setInt(1, memNo);

			rs=ps.executeQuery();


			while(rs.next()) {
				int cmtNo = rs.getInt("cmtNo");
				String cmtText = rs.getString("cmtText");
				Timestamp cmtRegdate = rs.getTimestamp("cmtRegdate");
				int mvNo = rs.getInt("mvNo");
				String mvTitle = rs.getString("mvTitle");

				MyCmtVO mcVo = new MyCmtVO(cmtNo,cmtText,cmtRegdate,memNo,mvNo,mvTitle);

				list.add(mcVo);
			}
			System.out.println("�ڸ�Ʈ ü��ȸ ��� list.size="+list.size()+"ȸ����ȣ="+memNo);
			return list;
		} finally {
			pool.dbClose(rs, ps, con);
		}
	}

	//�ֽű� �����ֱ�
	public List<MyCmtVO> selectMainMyCmt(int memNo) throws SQLException{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs = null;

		List<MyCmtVO> list = new ArrayList<MyCmtVO>();
		try {
			con = pool.getConnection();

			String sql="select * from (select cmt.cmtNo,cmt.cmtText,mvif.MVTITLE \r\n" + 
					"from cmtData cmt, mvInfo mvif \r\n" + 
					"where cmt.MVNO = mvif.MVNO and cmt.MEMNO=?\r\n" + 
					"order by cmt.cmtNo desc) \r\n" + 
					"where rownum<=4";
			ps = con.prepareStatement(sql);
			ps.setInt(1, memNo);

			rs=ps.executeQuery();

			while(rs.next()) {
				int cmtNo = rs.getInt("cmtNo");
				String cmtText = rs.getString("cmtText");
				String mvTitle = rs.getString("mvTitle");

				MyCmtVO mcVo = new MyCmtVO();
				mcVo.setCmtNo(cmtNo);
				mcVo.setCmtText(cmtText);
				mcVo.setMvTitle(mvTitle);

				list.add(mcVo);
			}
			System.out.println("�ڸ�Ʈ ü��ȸ ��� list.size="+list.size()+"ȸ����ȣ="+memNo);
			return list;

		}finally {
			pool.dbClose(rs, ps, con);
		}
	}


	public MyCmtVO selectBynum(int cmtNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;

		try {
			con=pool.getConnection();

			String sql = "select * from \r\n" + 
					"(\r\n" + 
					"    select cmt.MEMNO,cmt.cmtNo,cmt.cmtText,mvif.MVTITLE \r\n" + 
					"    from cmtData cmt, mvInfo mvif\r\n" + 
					"    where cmt.MVNO = mvif.MVNO \r\n" + 
					"    and cmt.cmtNo=?\r\n" + 
					")";
			ps=con.prepareStatement(sql);
			ps.setInt(1, cmtNo);

			rs=ps.executeQuery();

			MyCmtVO mcVo = new MyCmtVO();
			while(rs.next()) {
				mcVo.setMemNo(rs.getInt("memNo"));
				mcVo.setCmtNo(cmtNo);
				mcVo.setCmtText(rs.getString("cmtText"));
				mcVo.setMvTitle(rs.getString("mvTitle"));
			}

			System.out.println("��ȸ��� vo="+mcVo +"�Ű����� cmtNo="+cmtNo);

			return mcVo;
		}finally {
			pool.dbClose(rs, ps, con);
		}

	}


	public int deleteMycmt(int cmtNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;

		try {
			con=pool.getConnection();

			String sql="delete from cmtData cmt\r\n" + 
					"where exists (\r\n" + 
					"    select 1 from mvInfo mvif\r\n" + 
					"    where mvif.MVNO = cmt.MVNO\r\n" + 
					"    and cmt.cmtNo=?)";
			ps=con.prepareStatement(sql);
			ps.setInt(1, cmtNo);
			
			int cnt=ps.executeUpdate();
			System.out.println("������� cnt="+cnt+"�ްԺ��� cmtNo="+cmtNo);
			
			return cnt;
		} finally {
			pool.dbClose(ps, con);
		}
	}
	
	public int updateMycmt(MyCmtVO mcVo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			con=pool.getConnection();
			
			String sql="update cmtdata set cmttext=? where cmtno=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, mcVo.getCmtText());
			ps.setInt(2, mcVo.getCmtNo());
			
			int cnt = ps.executeUpdate();
			System.out.println("코멘트 수정 결과 cnt="+cnt+", 매개변수 mcVo="+mcVo);
			
			return cnt;
		} finally {
			pool.dbClose(ps, con);
		}
	}
}










