package com.batcha.manager.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.memInfo.model.MemInfoService;
import com.batcha.memInfo.model.MemInfoVO;
import com.controller.Controller;

public class ManagerDetailController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1 파라미터 받기
		String memNo= request.getParameter("no");
		
		if(memNo==null || memNo.isEmpty()) {
			request.setAttribute("msg", "잘못된 url입니다");
			request.setAttribute("url", "/managerPage/manager.do");
			System.out.println(memNo);

			return "/common/message.jsp";
		}
		
		//2 db연결
		MemInfoService memService = new MemInfoService();
		MemInfoVO memVo = new MemInfoVO();
		try {
			memVo = memService.selectMemByNo(Integer.parseInt(memNo));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3 결과 저장
		request.setAttribute("memVo", memVo);
		
		//4.뷰페이지로 포워드
		return "/managerPage/managerDetail.jsp?memNo="+memNo;
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
