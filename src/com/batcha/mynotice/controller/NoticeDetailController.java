package com.batcha.mynotice.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.mynotice.model.NoticeService;
import com.batcha.mynotice.model.NoticeVO;
import com.controller.Controller;

public class NoticeDetailController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1
		String noticeNo =request.getParameter("noticeNo");
		
		NoticeService ntService = new NoticeService();
		NoticeVO ntVo = new NoticeVO();
		//2
		try {
			ntVo= ntService.selectNotice(Integer.parseInt(noticeNo));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3
		request.setAttribute("noticeNo", noticeNo);
		request.setAttribute("ntVo", ntVo);
		
		//4
		return "/notice/noticeDetail.jsp?noticeNo="+noticeNo;
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
