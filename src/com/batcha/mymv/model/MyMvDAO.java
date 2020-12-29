package com.batcha.mymv.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.batcha.db.ConnectionPoolMgr2;

public class MyMvDAO {
	private ConnectionPoolMgr2 pool;
	
	public MyMvDAO() {
		pool=new ConnectionPoolMgr2();
	}
	
	
	public List<MyMvVO> KeepByMemNo(int memNo) throws SQLException{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<MyMvVO> list =new ArrayList<MyMvVO>();
		
		try {
			con=pool.getConnection();
			
			String sql="select keep.KEEPNO, keep.MEMNO, keep.MVNO, mvif.MVTITLE,mvif.THUMBNAIL\r\n" + 
					"from keepData keep, mvInfo mvif\r\n" + 
					"where keep.MVNO=mvif.MVNO\r\n" + 
					"and keep.MEMNO=?\r\n" + 
					"order by keepno desc";
			ps = con.prepareStatement(sql);
			ps.setInt(1, memNo);
			
			rs=ps.executeQuery();
			
			while(rs.next()) {				
				int keepNo = rs.getInt("keepNo");
				int mvNo = rs.getInt("mvNo");
				String mvTitle=rs.getString("mvTitle");
				String thumbnail = rs.getString("thumbnail");
				
				MyMvVO mmVo = new MyMvVO(keepNo,memNo,mvNo,mvTitle,thumbnail);
				
				list.add(mmVo);
			}
			
			System.out.println("회원/영화별 찜목록 전체조회 결과 list.size="+list.size()
								+", 매개변수 no="+memNo);
			return list;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}

}
