package com.batcha.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPoolMgr2 {
	private DataSource ds;
	
	public ConnectionPoolMgr2() {
		Context initCtx;
		
		try {
			initCtx=new InitialContext();
			ds=(DataSource) initCtx.lookup("java:comp/env/jdbc/oracledb");
			System.out.println("DataSource 생성");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//커넥션 객체 대여
	public Connection getConnection() throws SQLException {
		Connection con=ds.getConnection();
		//=>ConnectionPool에서 커넥션을 빌려온다
		System.out.println("db connected con="+con);
		
		return con;
	}

	public void dbClose(ResultSet rs, PreparedStatement ps, Connection con) throws SQLException {
		if(rs!=null) rs.close();
		if(ps!=null) ps.close();
		if(con!=null) con.close(); //ConnectionPool에 커넥션 반납 
	}
	
	public void dbClose(PreparedStatement ps, Connection con) throws SQLException {
		if(ps!=null) ps.close();
		if(con!=null) con.close();
	}
}
