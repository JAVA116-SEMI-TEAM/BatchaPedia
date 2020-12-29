package com.batcha.mvInfo.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.mvInfo.model.MvInfoService;
import com.batcha.mvInfo.model.MvInfoVO;
import com.controller.Controller;

public class MvEditController implements Controller {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1
		//=>http://localhost:9090/Test/mvInfo/mvEdit.do?no=5
		String mvNo=request.getParameter("mvNo");
		
		if(mvNo==null || mvNo.isEmpty()) {
			request.setAttribute("msg", "잘못된 url입니다");
			request.setAttribute("url", "/mvInfo/mvList.do");
			
			return "/common/message.jsp";
		}
		//2
		MvInfoService service=new MvInfoService();
		MvInfoVO mVo = null;
		
		try {
			mVo=service.selectMv(Integer.parseInt(mvNo));
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		
		//3
		request.setAttribute("mVo", mVo);
		
		//4
		return "/mvInfo/mvEdit.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
