package com.batcha.memInfo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.batcha.db.ConnectionPoolMgr2;

public class MemInfoDAO {
	private ConnectionPoolMgr2 pool;

	public MemInfoDAO() {
		pool=new ConnectionPoolMgr2();
	}

	//로그인
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

	//아이디로 조회
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
	//중복체크
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


	//가입 입력
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


	//번호로 조회
	public MemInfoVO selectMemByNo(int memNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps = null;
		ResultSet rs=null;

		MemInfoVO memVo = new MemInfoVO();
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
	public List<MemInfoVO> selectAll() throws SQLException{
		Connection con=null;
		PreparedStatement ps = null;
		ResultSet rs=null;

		try {
			con=pool.getConnection();

			String sql="select * from memInfo order by memNo desc";
			ps = con.prepareStatement(sql);

			List<MemInfoVO> list = new ArrayList<MemInfoVO>();
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

				MemInfoVO memVo= new MemInfoVO(memNo,id,pwd,name,email,mobile,birthday,regdate,outdate,adminCheck);

				list.add(memVo);
			}
			System.out.println("list.size="+list.size());
			return list;
		} finally {
			pool.dbClose(rs, ps, con);
		}
	}

	//검색후 전체조회
	public List<MemInfoVO> selectMemByKey(String condition, String keyword) 
			throws SQLException{
		Connection con=null;
		PreparedStatement ps = null;
		ResultSet rs=null;

		try {
			con=pool.getConnection();

			String sql="select * from memInfo ";
			if(keyword!=null&&!keyword.isEmpty()) { 
				sql+=" where "+ condition +" like '%' || ? || '%'";
			}
			sql+= "order by memNo desc";
			ps = con.prepareStatement(sql);

			if(keyword!=null&&!keyword.isEmpty()) {
				ps.setString(1, keyword);
			}

			List<MemInfoVO> list = new ArrayList<MemInfoVO>();
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

				MemInfoVO memVo= new MemInfoVO(memNo,id,pwd,name,email,mobile,birthday,regdate,outdate,adminCheck);

				list.add(memVo);
			}
			System.out.println("list.size="+list.size());
			return list;
		} finally {
			pool.dbClose(rs, ps, con);
		}
	}

	//수정
	public int updateMem(MemInfoVO vo) throws SQLException {
		Connection con=null;
		PreparedStatement ps = null;

		try {
			con=pool.getConnection();

			String sql = "update memInfo " + 
					"set id=?, pwd=?, name=? ,email=?, mobile=?,birthday=?\n" + 
					"where memNo=?";

			ps = con.prepareStatement(sql);
			ps.setString(1,	vo.getId());
			ps.setString(2,	vo.getPwd());
			ps.setString(3,	vo.getName());
			ps.setString(4,	vo.getEmail());
			ps.setString(5,	vo.getMobile());
			ps.setString(6,	vo.getBirthday());
			ps.setInt(7, vo.getMemNo());

			int cnt=ps.executeUpdate();

			System.out.println("등록성공 cnt="+cnt+"vo="+vo);

			return cnt;

		} finally {
			pool.dbClose(ps, con);
		}
	}

	//삭제
	public int deleteMem(int memNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps = null;

		try {
			con =pool.getConnection();

			String sql = "delete from memInfo where memNo=?";
			ps= con.prepareStatement(sql);
			ps.setInt(1, memNo);

			int cnt = ps.executeUpdate();

			System.out.println("삭제성공: "+cnt+", 매개변수"+memNo);

			return cnt;
		} finally {
			pool.dbClose(ps, con);
		}
	}

	
	public int totalCount() throws SQLException {
		Connection con=null;
		PreparedStatement ps = null;

		try {
			con = pool.getConnection();
			
			String sql = "select count(*) from memInfo\n" + 
					"where to_char(regdate,'YYYYMMDD')= to_char(sysdate,'YYYYMMDD')\n" + 
					"and outdate IS NULL";
			ps=con.prepareStatement(sql);
			
			int cnt = ps.executeUpdate();
			
			return cnt;
		} finally {
			pool.dbClose(ps, con);
		}
	}

	public String selectMembyCmt(int cmtMemNo) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String memId="";
		
		try {
			con=pool.getConnection();
			
			String sql="select m.id as userid from memInfo m join cmtData c" + 
					" on m.memno=?";
			ps=con.prepareStatement(sql);
			
			ps.setInt(1, cmtMemNo);
			
			rs=ps.executeQuery();
			
			if(rs.next()) {
				memId=rs.getString("userid");
			}
			
			return memId;
		}finally {
			pool.dbClose(rs, ps, con);
		}
	}
}
