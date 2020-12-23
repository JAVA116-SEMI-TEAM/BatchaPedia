package com.batcha.qna.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.batcha.db.ConnectionPoolMgr2;
import com.mymvc.board.model.BoardVO;

public class QnaDAO {
	
private ConnectionPoolMgr2 pool;
	
	public QnaDAO() {
		pool=new ConnectionPoolMgr2();
	}
	
	
	/*
	 * public int selectById(String id) throws SQLException { Connection con=null;
	 * PreparedStatement ps=null; ResultSet rs=null;
	 * 
	 * try { //1,2 con=pool.getConnection();
	 * 
	 * //3 String sql="select memno from meminfo where id=?";
	 * ps=con.prepareStatement(sql); ps.setString(1, id);
	 * 
	 * //4 rs=ps.executeQuery(); int result=0;
	 * 
	 * if(rs.next()) { result=rs.getInt("memno"); }
	 * System.out.println("회원번호 result="+result+", 매개변수 id="+id);
	 * 
	 * return result; }finally { pool.dbClose(rs, ps, con); } }//
	 */	
	public int insertQna(QnaVO vo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			//1,2 con
			con=pool.getConnection();
			
			//3. ps
			String sql="insert into qnaboard(qnano, memno, title, content, author, userid, admincheck) " + 
					"values(qnaBoard_seq.nextval, ?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			
			/*
			 * System.out.println(vo.getMemNo()); System.out.println(vo.getTitle());
			 * System.out.println(vo.getContent()); System.out.println(vo.getAuthor());
			 */
			ps.setInt(1, vo.getMemNo());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getAuthor());
			ps.setString(5, vo.getUserid());
			ps.setInt(6, vo.getAdmincheck());
			
			
			//4. exec
			int cnt=ps.executeUpdate();
			System.out.println("글쓰기 결과 cnt="+cnt+", 매개변수 vo="+vo);
			
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}//
	
	public List<QnaVO> selectAll(String condition, String keyword)
			throws SQLException{
		/*
		select * from board 
		where name like '%길동%';
		select * from board 
		where title like '%안녕%';
		select * from board 
		where content like '%내용%';
		*/
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		List<QnaVO> list=new ArrayList<QnaVO>();
		try {
			//1,2 con
			con=pool.getConnection();
			
			//3. ps
			String sql="select * from qnaboard ";
			if(keyword!=null && !keyword.isEmpty()) { //검색
				sql += " where "+ condition +" like '%' || ? || '%'";
			}
			sql += " order by qnano desc";
			ps=con.prepareStatement(sql);
			
			if(keyword!=null && !keyword.isEmpty()) { //검색
				ps.setString(1, keyword);
			}
			
			//4. exec
			rs=ps.executeQuery();
			while(rs.next()) {
				int qnaNo=rs.getInt("qnaNo");
				int memNo=rs.getInt("memNo");
				String title=rs.getString("title");
				String content=rs.getString("content");
				String author=rs.getString("author");
				Timestamp regdate=rs.getTimestamp("regdate");
				int readCount=rs.getInt("readcount");
				
				QnaVO vo = new QnaVO(qnaNo, memNo, title, content, author, regdate, readCount);
				list.add(vo);
			}
			System.out.println("글목록 결과 list.size="+list.size()
				+", 매개변수 condition="+condition+", keyword="+keyword);
			return list;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}//
	
	public int updateReadCount(int qnano) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="update qnaboard" + 
					" set readcount=readcount+1" + 
					" where qnano=?";
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, qnano);
			
			//4
			int cnt=ps.executeUpdate();
			System.out.println("조회수 증가 결과, cnt="+cnt+", 매개변수 no="+qnano);
			
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}//
	
	public QnaVO selectByNo(int qnano) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		QnaVO vo = new QnaVO();
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="select * from qnaboard where qnano=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, qnano);
			
			//4
			rs=ps.executeQuery();
			if(rs.next()) {
				vo.setQnaNo(qnano);;
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setAuthor(rs.getString("author"));
				vo.setRegdate(rs.getTimestamp("regdate"));
				vo.setReadCount(rs.getInt("readCount"));
				vo.setUserid(rs.getString("userid"));
				vo.setAdmincheck(rs.getInt("admincheck"));
			}
			
			System.out.println("글 상세보기 결과 vo="+vo+", 매개변수 qnano="+qnano);
			
			return vo;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}//
	
	public int deleteQna(int qnano) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="delete from qnaboard where qnano=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, qnano);
			
			//4
			int cnt=ps.executeUpdate();
			System.out.println("글 삭제 결과, cnt="+cnt+", 매개변수 qnano="+qnano);
			
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}//
	
	public int updateQna(QnaVO vo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="update qnaboard" + 
					" set title=?, content=?" + 
					" where qnano=?";
			ps=con.prepareStatement(sql);
			
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getContent());
			ps.setInt(3, vo.getQnaNo());
			
			//4
			int cnt=ps.executeUpdate();
			System.out.println("글수정 결과, cnt="+cnt+", 매개변수 vo="+vo);
			
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}//
	
}
