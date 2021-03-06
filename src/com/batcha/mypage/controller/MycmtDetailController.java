package com.batcha.mypage.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.mycmt.model.MyCmtService;
import com.batcha.mycmt.model.MyCmtVO;
import com.controller.Controller;

public class MycmtDetailController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1
		//http://localhost:9090/BatchaPedia/myPage/mycmtDetail.do?cmtNo=49
		String cmtNo = request.getParameter("cmtNo");
		
		MyCmtService service = new MyCmtService();
		
		MyCmtVO mcVo = new MyCmtVO();
		
		//2
		try {
			mcVo=service.selectBynum(Integer.parseInt(cmtNo));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3
		request.setAttribute("mcVo", mcVo);
	
		//4
		return "/myPage/mycmtDetail.jsp?=cmtNo"+cmtNo;
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
