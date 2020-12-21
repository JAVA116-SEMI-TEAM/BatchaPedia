package com.batcha.memInfo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.batcha.db.ConnectionPoolMgr2;
import com.mystudy.member.model.MemberService;
import com.mystudy.member.model.MemberVO;

public class MemInfoDAO {
	private ConnectionPoolMgr2 pool;
	
	public MemInfoDAO() {
		pool=new ConnectionPoolMgr2();
	}
	
	public int loginCheck(String userid, String pwd) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int result=0;
		
		
		try {
			con=pool.getConnection();
			
			String sql="select pwd from memInfo" + 
					" where id=? and outdate is null";
			ps=con.prepareStatement(sql);
			ps.setString(1, userid);
			
			rs=ps.executeQuery();
			if(rs.next()) {
				String dbPwd=rs.getString(1);
				if(dbPwd.equals(pwd)) {
					result=MemInfoService.LOGIN_OK;
				}else {
					result=MemInfoService.PWD_DISAGREE;
				}
			}else {
				result=MemInfoService.ID_NONE;
			}
			
			System.out.println("로그인 처리 결과, result="+result
				+", 매개변수 userid="+userid+", pwd="+pwd);
			return result;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}//
	
	public MemInfoVO selectMember(String userid) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		MemInfoVO vo = new MemInfoVO();
		try {
			con=pool.getConnection();
			
			String sql="select * from memInfo where id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, userid);
			
			rs=ps.executeQuery();
			if(rs.next()) {
				int memNo=rs.getInt("memno");
				String id=rs.getString("id");
				String pwd=rs.getString("pwd");
				String name=rs.getString("name");
				String email=rs.getString("email");
				String mobile=rs.getString("mobile");
				String birthday=rs.getString("birthday");
				Timestamp regdate=rs.getTimestamp("regdate");
				Timestamp outdate=rs.getTimestamp("outdate");
				int adminCheck=rs.getInt("admincheck");
				
				vo.setMemNo(memNo);
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setMobile(mobile);
				vo.setBirthday(birthday);
				vo.setRegdate(regdate);
				vo.setOutdate(outdate);
				vo.setAdminCheck(adminCheck);
			}
			System.out.println("회원정보 조회 결과 vo="+vo+", 매개변수 userid="
					+userid);
			
			return vo;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}//
	
	public int checkDup(String userid) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		int result=0;
		try {
			con=pool.getConnection();
			
			String sql="select count(*) from meminfo" + 
					" where id=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, userid);
			
			rs=ps.executeQuery();
			if(rs.next()) {
				int count=rs.getInt(1);
				if(count>0) {
					result=MemInfoService.EXIST_ID; //이미 존재
				}else {
					result=MemInfoService.NON_EXIST_ID;
				}
			}//if
			
			System.out.println("아이디 중복확인 결과, result="+result
					+", 매개변수 userid="+userid);
			
			return result;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}//
	
	public int insertMember(MemInfoVO vo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			//1,2
			con=pool.getConnection();
			
			//3
			String sql="insert into meminfo(memno, id, pwd, name, email, mobile, birthday)" + 
					" values(memInfo_seq.nextval,?,?,?,?,?,?)";
			ps=con.prepareStatement(sql);
			
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPwd());
			ps.setString(3, vo.getName());
			ps.setString(4, vo.getEmail());
			ps.setString(5, vo.getMobile());
			ps.setString(6, vo.getBirthday());
			
			//4
			int cnt=ps.executeUpdate();
			System.out.println("회원가입 결과, cnt="+cnt+", 매개변수 vo="+vo);
			
			return cnt;
		}finally {
			pool.dbClose(ps, con);
		}
	}//
}
