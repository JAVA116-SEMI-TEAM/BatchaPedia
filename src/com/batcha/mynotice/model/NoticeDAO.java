package com.batcha.mynotice.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.batcha.db.ConnectionPoolMgr2;

public class NoticeDAO {
	private ConnectionPoolMgr2 pool;
	
	public NoticeDAO() {
		pool = new ConnectionPoolMgr2();
	}
	
	//��� - insert
		public int insertNotice(NoticeVO vo) throws SQLException {
			Connection con=null;
			PreparedStatement ps = null;
			
			try {
				//1
				con=pool.getConnection();
				
				//2
				String sql="insert into noticeBoard(noticeNo,title,content,author,regdate)\r\n" + 
						"values(noticeBoard_seq.nextval,?,?,?,sysdate)";
				ps=con.prepareStatement(sql);
				ps.setString(1, vo.getTitle());
				ps.setString(2, vo.getContent());
				ps.setString(3, vo.getAuthor());
				
				//3
				int cnt=ps.executeUpdate();
				System.out.println("�������� ��� ���, cnt="+cnt+", �Ű����� vo="+vo);
				return cnt;
			}finally {
				pool.dbClose(ps, con);
			}
		}
		
		
		//��ü ��ȸ - select
		public List<NoticeVO> selectAllNotice() throws SQLException {
			Connection con=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			List<NoticeVO> list = new ArrayList<NoticeVO>();
			try {
				//1,2
				con=pool.getConnection();
				
				//3
				String sql="select * from noticeBoard order by noticeNo desc";
				ps=con.prepareStatement(sql);
				
				//4
				rs=ps.executeQuery();
				while(rs.next()) {
					int noticeNo=rs.getInt("noticeNo");
					String title=rs.getString("title");
					String content=rs.getString("content");
					String author=rs.getString("author");
					Timestamp regdate=rs.getTimestamp("regdate");
					int readcount=rs.getInt("readcount");
					
					NoticeVO vo = new NoticeVO(noticeNo,title,content,author,regdate,readcount);
					
					list.add(vo);
				}
				System.out.println("�������� ��ȸ ���, list.size="+list.size());
				
				return list;
			}finally {
				pool.dbClose(rs, ps, con);
			}
		}
		
		
		//��ȣ��  ��ȸ - select(mvNo)
		public NoticeVO selectNotice(int noticeNo) throws SQLException {
			Connection con=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			NoticeVO vo = new NoticeVO(); 
			try {
				//1,2
				con=pool.getConnection();
				
				//3
				String sql="select * from noticeBoard where noticeNo=? ";
				ps=con.prepareStatement(sql);
				ps.setInt(1, noticeNo);
				
				//4
				rs=ps.executeQuery();
				if(rs.next()) {
					vo.setNoticeNo(noticeNo);
					vo.setTitle(rs.getString("title"));
					vo.setContent(rs.getString("content"));
					vo.setAuthor(rs.getString("author"));
					vo.setRegdate(rs.getTimestamp("regdate"));
					vo.setReadcount(rs.getInt("readcount"));
					
				}
				System.out.println("�������� �� ��ȸ ���, vo="+vo+", �Ű����� mvNo="+noticeNo);
				return vo;
			}finally {
				pool.dbClose(rs, ps, con);
			}
		}
		
		//���� - update
		public int updateNotice(NoticeVO vo) throws SQLException {
			Connection con=null;
			PreparedStatement ps=null;
			
			try {
				//1,2
				con=pool.getConnection();
				
				//3
				String sql="update noticeBoard\r\n" + 
						"set title=?, content=?\r\n" + 
						"where noticeNo=?";
				
				ps=con.prepareStatement(sql);
				ps.setString(1, vo.getTitle());
				ps.setString(2, vo.getContent());
				ps.setInt(3, vo.getNoticeNo());
				
				//4
				int cnt=ps.executeUpdate();
				System.out.println("�������� ���� ���, cnt="+cnt+", �Ű����� vo="+vo);
				
				return cnt;
			}finally {
				pool.dbClose(ps, con);
			}
		}
		
		//���� - delete
		public int deleteNotice(int noticeNo) throws SQLException {
			Connection con=null;
			PreparedStatement ps=null;
			
			try {
				//1,2
				con=pool.getConnection();
				
				//3
				String sql="delete from noticeBoard where noticeNo=?";
				ps=con.prepareStatement(sql);
				ps.setInt(1, noticeNo);
				
				//4
				int cnt=ps.executeUpdate();
				System.out.println("�������� ���� ���, cnt="+cnt+", �Ű����� noticeNo="+noticeNo);
				
				return cnt;
			}finally {
				pool.dbClose(ps, con); 
			}
		}

		//Ű����� �˻�
		public List<NoticeVO> selectAllNotice(String condition,String keyword) throws SQLException {
			Connection con=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			try {
				//1,2
				con=pool.getConnection();
				
				//3
				String sql="select * from noticeBoard ";
				if(keyword!=null&&!keyword.isEmpty()) {
					sql+= " where "+condition+" like '%' || ? || '%'";	
				}
				sql+="order by noticeNo desc";
				ps=con.prepareStatement(sql);
				
				List<NoticeVO> list = new ArrayList<NoticeVO>();
				//4
				if(keyword!=null && !keyword.isEmpty()) {
					ps.setString(1, keyword); 
				}
				
				rs=ps.executeQuery();

				while(rs.next()) {
					int noticeNo=rs.getInt("noticeNo");
					String title=rs.getString("title");
					String content=rs.getString("content");
					String author=rs.getString("author");
					Timestamp regdate=rs.getTimestamp("regdate");
					int readcount=rs.getInt("readcount");
					
					NoticeVO vo = new NoticeVO(noticeNo,title,content,author,regdate,readcount);
					
					list.add(vo);
				}
				System.out.println("�������� ��ȸ ���, list.size="+list.size()
					+", �Ű����� condition="+condition+", keyword="+keyword);
				
				return list;
			}finally {
				pool.dbClose(rs, ps, con);
			}
		}
		

		//��ȸ�� ����
		public int updateReader(int noticeNo) throws SQLException {
			Connection con=null;
			PreparedStatement ps=null;
			
			try {
				con=pool.getConnection();
						
				String sql="update noticeBoard \r\n" + 
						"set readcount=readcount+1\r\n" + 
						"where noticeNo=?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, noticeNo);
				
				int cnt=ps.executeUpdate();
				
				System.out.println("��ȸ�� ���� ��� cnt="+cnt+"�Ű�����="+noticeNo);
				
				return cnt;
			} finally {
				pool.dbClose(ps, con);
			}
		}
		
	
}
