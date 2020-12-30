package com.batcha.mypage.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.mycmt.model.MyCmtService;
import com.batcha.mycmt.model.MyCmtVO;
import com.controller.Controller;

public class MycmtEditController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//http://localhost:9090/BatchaPedia/myPage/mycmtEdit.do?cmtNo=48
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
		return "/myPage/mycmtEdit.jsp?=cmtNo"+cmtNo;
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
