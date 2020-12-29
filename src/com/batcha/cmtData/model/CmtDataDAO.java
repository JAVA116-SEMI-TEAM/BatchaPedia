package com.batcha.cmtData.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.batcha.db.ConnectionPoolMgr2;
import com.batcha.mvInfo.model.MvInfoVO;

public class CmtDataDAO {
	private ConnectionPoolMgr2 pool;
	
	public CmtDataDAO() {
		pool=new ConnectionPoolMgr2();
	}
	
	//�ڸ�Ʈ �߰� �޼ҵ�
	public int insertCmt(CmtDataVO cmtVo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		int cnt=0;
		
		try {
			con=pool.getConnection();
			
			String sql="insert into cmtData(cmtno, cmttext, mvno, memno)"
					+ " values(cmtData_seq.nextval, ?, ?, ?)";
			ps=con.prepareStatement(sql);
			
			ps.setString(1, cmtVo.getCmtText());
			ps.setInt(2, cmtVo.getMvNo());
			ps.setInt(3, cmtVo.getMemNo());
			
			cnt=ps.executeUpdate();
			
			System.out.println("�ڸ�Ʈ ��� ��� cnt="+cnt+", �Ű����� cmtVo="+cmtVo);
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	//�ڸ�Ʈ ������Ʈ �޼ҵ�
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
			
			System.out.println("�ڸ�Ʈ ���� ��� cnt="+cnt+", �Ű����� cmtVo="+cmtVo);
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	//�ڸ�Ʈ ���� �޼ҵ�
	public int deleteCmt(int cmtNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		int cnt=0;
		
		try {
			con=pool.getConnection();
			
			String sql="update cmtdata" + 
					" set cmttext=?, cmtregdate=sysdate" + 
					" where cmtno=?";
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, cmtNo);
			
			cnt=ps.executeUpdate();
			
			System.out.println("�ڸ�Ʈ ���� ��� cnt="+cnt+", �Ű����� cmtNo="+cmtNo);
			return cnt;
			
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	//�ڸ�Ʈ�� ��ȭ��ȣ�� �����ȣ�� ���� ����Ʈ�ϴ� �޼ҵ�
	//no�� ȸ����ȣ�� isMemNo=true, ��ȭ��ȣ�� false�� �Ű����� �� ��
	public List<CmtDataVO> selectCmtByNo(int no, boolean isMemNo) throws SQLException{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<CmtDataVO> list=null;
		
		try {
			con=pool.getConnection();
			
			String sql="select *";
					
			sql+=" from cmtData where";		
			
			if(isMemNo) {
				sql+=" memNo=?";
			}else {
				sql+=" mvNo=?";
			}
			
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, no);
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				CmtDataVO cmtVo=new CmtDataVO();
				cmtVo.setCmtNo(rs.getInt("cmtNo"));
				cmtVo.setCmtText(rs.getString("cmtText"));
				cmtVo.setCmtRegdate(rs.getTimestamp("cmtRegdate"));
				
				if(isMemNo) {
					cmtVo.setMemNo(no);
					cmtVo.setMvNo(rs.getInt("mvNo"));
				}else {
					cmtVo.setMemNo(rs.getInt("memNo"));
					cmtVo.setMvNo(no);
				}
				
				list.add(cmtVo);
				}
			System.out.println("�ڸ�Ʈ ��ü��ȸ ��� list.size="+list.size()+", �Ű����� no="+no+", isMemNo="+isMemNo);
			return list;
		}finally{
			pool.dbClose(rs, ps, con);
		}
	}
	
	//ȸ���̳� ��ȭ��ȣ�� ���� �ڸ�Ʈ ���� ��������
	//no�� ȸ����ȣ�� isMemNo=true, ��ȭ��ȣ�� false�� �Ű����� �� ��
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
			
			System.out.println("�ڸ�Ʈ ��ȭ/ȸ����ȣ�� ���� cnt="+cnt+", �Ű����� no="+no+", isMemNo="+isMemNo);
			return cnt;
			
		}finally{
			pool.dbClose(rs, ps, con);
		}
	}
	
	//�ڸ�Ʈ ��ü���.. �̰� �� ���� ������?
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
			
			System.out.println("�ڸ�Ʈ ��ü��ȸ ��� list.size="+list.size());
			return list;
			
		}finally{
			pool.dbClose(rs, ps, con);
		}
	}

	
}
