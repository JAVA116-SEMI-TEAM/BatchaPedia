package com.batcha.qna.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.batcha.db.ConnectionPoolMgr2;

public class QnaDAO {
	
private ConnectionPoolMgr2 pool;
	
	public QnaDAO() {
		pool=new ConnectionPoolMgr2();
	}
	
	public int insertQna(QnaVO vo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			//1,2 con
			con=pool.getConnection();
			
			//3. ps
			String sql="insert into qnaboard(qnano, memno, title, content, author, userid, admincheck, groupNo) " + 
					"values(qnaBoard_seq.nextval, ?,?,?,?,?,?,qnaBoard_seq.nextval)";
			ps=con.prepareStatement(sql);
			
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
			sql += " order by groupno desc, sortno";
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
				
				int groupno=rs.getInt("groupno");
				int step=rs.getInt("step");
				int sortno=rs.getInt("sortno");
				String delFlag=rs.getString("delflag");
				
				QnaVO vo = new QnaVO(qnaNo, memNo, title, content, author, regdate, readCount,
						groupno, step, sortno, delFlag);
				
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
				vo.setGroupno(rs.getInt("groupno"));
				vo.setStep(rs.getInt("step"));
				vo.setSortNo(rs.getInt("sortno"));
				vo.setDelFlag(rs.getString("delflag"));
				vo.setMemNo(rs.getInt("memno"));
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
			/* String sql="delete from qnaboard where qnano=?"; */
			String sql="update qnaboard " + 
					" set delflag='Y'" + 
					" where qnano=?";
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
	
	public int deleteQnaAdmin(int qnano) throws SQLException {
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
	
	public int reply(QnaVO vo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		
		int cnt=0;
		try {
			//1,2
			con=pool.getConnection();
			
			con.setAutoCommit(false);  //자동커밋이 안되도록 막는다
			//트랜잭션 시작
			
			//[1] update - sortNo 1 증가
			//3
			String sql="update qnaboard" + 
					" set sortno=sortno+1" + 
					" where groupno=? and sortno>?";
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, vo.getGroupno());
			ps.setInt(2, vo.getSortNo());
			
			//4
			cnt=ps.executeUpdate();
			
			//[2] insert
			sql="insert into qnaboard(qnano, memno, title, content, "+
					" author, userid, admincheck, groupNo, step, sortNo) " + 
					" values(qnaBoard_seq.nextval, ?,?,?,?,?,?,?,?,?)";
			
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, vo.getMemNo());
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getAuthor());
			ps.setString(5, vo.getUserid());
			ps.setInt(6, vo.getAdmincheck());
			
			ps.setInt(7, vo.getGroupno());
			ps.setInt(8, vo.getStep()+1);
			ps.setInt(9, vo.getSortNo()+1);
			
			cnt = ps.executeUpdate();
			System.out.println("답변하기 결과, cnt="+cnt+", 매개변수 vo="
				+ vo);
			
			con.commit(); //트랙잭션 종료, 성공
		}catch(SQLException e) {
			con.rollback();  //트랜잭션 실패
			e.printStackTrace();
		}finally {
			con.setAutoCommit(true);
			pool.dbClose(ps, con);
		}
		
		return cnt;
	}//
	
}
