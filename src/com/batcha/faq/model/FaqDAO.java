package com.batcha.faq.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.batcha.db.ConnectionPoolMgr2;

public class FaqDAO {
	private ConnectionPoolMgr2 pool;
	
	public FaqDAO() {
		pool=new ConnectionPoolMgr2();
	}
	
	//FAQ 전체 조회 - selectAll
	public List<FaqVO> selectAllFaq() throws SQLException{
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<FaqVO> faqList=new ArrayList<FaqVO>();
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="select * from " + 
					"(select * from faqBoard order by faqNo desc) " + 
					"where rownum <=10";
			ps=con.prepareStatement(sql);
			
			//4
			rs=ps.executeQuery();
			while(rs.next()) {
				int faqNo=rs.getInt("faqNo");
				String title=rs.getString("title");
				String content=rs.getString("content");
				Timestamp regdate=rs.getTimestamp("regdate");
				
				FaqVO faqDto=new FaqVO(faqNo, title, 
						content, regdate);
				faqList.add(faqDto);
			}
			System.out.println("FAQ 전체 조회 결과, faqList.size="+faqList.size());
			
			return faqList;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
	
	//FAQ 번호로 조회 - selectFaq(faqNo)
	public FaqVO selectFaq(int faqNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		FaqVO faqVo = new FaqVO();
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="select * from faqBoard where faqNo=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, faqNo);
			
			//4
			rs=ps.executeQuery();
			if(rs.next()) {
				faqVo.setFaqNo(faqNo);
				faqVo.setTitle(rs.getString("title"));
				faqVo.setContent(rs.getString("content"));
			}
			System.out.println("FAQ 번호로 조회 결과, faqVo="+faqVo);
			return faqVo;
		}finally {
			pool.dbClose(rs, ps, con);
		}
		
	}
	
	//FAQ 수정 - update
	public int updateFaq(FaqVO faqVo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="update faqBoard " + 
					" set title=?, content=? " + 
					" where faqNo=? ";
			ps=con.prepareStatement(sql);
			ps.setString(1, faqVo.getTitle());
			ps.setString(2, faqVo.getContent());
			ps.setInt(3, faqVo.getFaqNo());
			
			//4
			int cnt=ps.executeUpdate();
			System.out.println("FAQ 수정 결과, cnt="+cnt+", 매개변수 faqVo="+faqVo);
			
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}

	//FAQ 삭제 - delete
	public int deleteFaq(int faqNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="delete from faqBoard where faqNo=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, faqNo);
			
			//4
			int cnt=ps.executeUpdate();
			System.out.println("FAQ 삭제 결과, cnt="+cnt+", 매개변수 faqNo="+faqNo);
			
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
	
	public int insertFaq(FaqVO faqVo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="insert into faqBoard(faqNo, title, content) " + 
					"values(faqBoard_seq.nextval,?,?)";
			ps=con.prepareStatement(sql);
			ps.setString(1, faqVo.getTitle());
			ps.setString(2, faqVo.getContent());
			
			//4
			int cnt=ps.executeUpdate();
			System.out.println("faq 글 등록 결과, cnt="+cnt+", 매개변수 faqVo="+faqVo);
			
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}
}

















