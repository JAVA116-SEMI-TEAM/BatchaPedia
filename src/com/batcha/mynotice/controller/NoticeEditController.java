package com.batcha.mynotice.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.mynotice.model.NoticeService;
import com.batcha.mynotice.model.NoticeVO;
import com.controller.Controller;

public class NoticeEditController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1
		//http://localhost:9090/BatchaPedia/notice/noticeEdit.do?noticeNo=5
		String noticeNo =request.getParameter("noticeNo");
		
		//2
		NoticeService service = new NoticeService();
		
		NoticeVO ntVo = new NoticeVO();
		
		try {
			ntVo=service.selectNotice(Integer.parseInt(noticeNo));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3
		request.setAttribute("ntVo", ntVo);
		
		//4
		
		return "/notice/noticeEdit.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}
	
}
