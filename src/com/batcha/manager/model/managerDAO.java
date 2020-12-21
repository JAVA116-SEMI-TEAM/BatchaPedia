package com.batcha.manager.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.batcha.db.ConnectionPoolMgr2;

public class managerDAO {
	private ConnectionPoolMgr2 pool;
	
	public managerDAO() {
		pool = new ConnectionPoolMgr2();
	}
	
	//등록
	public int mngInsert(managerVo vo) throws SQLException {
		Connection con=null;
		PreparedStatement ps = null;
		
		managerVo memVo = new managerVo();
		int cnt=0;
		try {
			con = pool.getConnection();
			
			String sql = "insert into memInfo(memNo,id,pwd,name,email,mobile,birthday)\r\n" + 
					"values (memInfo_seq.nextval,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, memVo.getId());
			ps.setString(2, memVo.getPwd());
			ps.setString(3, memVo.getName());
			ps.setString(4, memVo.getEmail());
			ps.setString(5, memVo.getMobile());
			ps.setString(6, memVo.getBirthday());
			
			cnt = ps.executeUpdate();
			
			System.out.println("등록 성공="+cnt);
			return cnt;
			
		} finally {
		}
		
	}
	
	//수정
	
	
	//삭제
	
	
	//번호로 셀렉트
	public managerVo mngNselect(int memNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		
		managerVo memVo = new managerVo();
		try {
			con = pool.getConnection();
			
			String sql = "select * from memInfo where memNo=?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, memNo);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				String id=rs.getString("id");
				String pwd=rs.getString("pwd");
				String name=rs.getString("name");
				String email=rs.getString("email");
				String mobile=rs.getString("mobile");
				String birthday=rs.getString("birthday");
				Timestamp regdate=rs.getTimestamp("regdate");
				Timestamp outdate=rs.getTimestamp("outdate");
				
				memVo.setMemNo(memNo);
				memVo.setId(id);
				memVo.setPwd(pwd);
				memVo.setName(name);
				memVo.setEmail(email);
				memVo.setMobile(mobile);
				memVo.setBirthday(birthday);
				memVo.setRegdate(regdate);
				memVo.setOutdate(outdate);
			}
			System.out.println("memVo"+memVo+"매개변수 memNo"+memNo);
			return memVo;
		} finally {
			pool.dbClose(rs, ps, con);
		}
	} 
	
	//전체조회
	public List<managerVo> mngSelectAll() throws SQLException{
		Connection con=null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		
		try {
			con=pool.getConnection();
			
			String sql="select * from memInfo order by memNo desc";
			ps = con.prepareStatement(sql);
			
			List<managerVo> list = new ArrayList<managerVo>();
			rs=ps.executeQuery();
			while(rs.next()) {
				int memNo = rs.getInt("memNo");
				String id=rs.getString("id");
				String pwd=rs.getString("pwd");
				String name=rs.getString("name");
				String email=rs.getString("email");
				String mobile=rs.getString("mobile");
				String birthday=rs.getString("birthday");
				Timestamp regdate=rs.getTimestamp("regdate");
				Timestamp outdate=rs.getTimestamp("outdate");
				int adminCheck = rs.getInt("adminCheck");
				
				managerVo memVo= new managerVo(memNo,id,pwd,name,email,mobile,birthday,regdate,outdate,adminCheck);
				
				list.add(memVo);
			}
			System.out.println("list.size="+list.size());
			return list;
		} finally {
			pool.dbClose(rs, ps, con);
		}
	}
	
}
