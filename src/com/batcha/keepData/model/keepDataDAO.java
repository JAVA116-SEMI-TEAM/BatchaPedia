package com.batcha.keepData.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.batcha.db.ConnectionPoolMgr2;
import com.batcha.mvInfo.model.MvInfoVO;

public class keepDataDAO {
	ConnectionPoolMgr2 pool;
	
	public keepDataDAO() {
		pool=new ConnectionPoolMgr2();
	}
	
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
