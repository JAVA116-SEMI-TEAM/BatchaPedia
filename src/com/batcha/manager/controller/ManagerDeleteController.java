package com.batcha.manager.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.memInfo.model.MemInfoService;
import com.controller.Controller;

public class ManagerDeleteController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1 파라미터 받기
		String memNo = request.getParameter("memNo");
		
		//2 db연결
		MemInfoService service = new MemInfoService();
		
		String msg="삭제 실패 했습니다",url="/managerPage/managerDetail.do"+memNo; 
		try {
			int cnt = service.deleteMem(Integer.parseInt(memNo));
			
			if (cnt>0) {
				msg="삭제되었습니다";
				url="/managerPage/manager.do";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3 결과 저장
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		//4 포워드
		return "/common/message.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
