package com.batcha.manager.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.manager.model.managerService;
import com.batcha.manager.model.managerVo;
import com.controller.Controller;

public class managerController implements Controller {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1. 파라미터
		//2. db연결
		managerService mngservice = new managerService();
		List<managerVo> list = null;
		
		try {
			list = mngservice.mngSelectAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3. 결과저장
		request.setAttribute("mnglist", list);
		
		//4. 뷰페이지
		
		return "/managerPage/manager.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}
	
}
