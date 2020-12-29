package com.batcha.mynotice.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.mynotice.model.NoticeService;
import com.controller.Controller;

public class NoticeCountOkController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1
		String noticeNo =request.getParameter("no");
		System.out.println("카운트 noticeNo="+noticeNo);
		
		//2
		NoticeService ntService = new NoticeService();
		
		try {
			 int cnt = ntService.updateReader(Integer.parseInt(noticeNo));
			System.out.println("조회수="+cnt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3
		//4
		return "/notice/noticeDetail.do?noticeNo="+noticeNo;
	}

	@Override
	public boolean isRedirect() {
		return true;
	}

}
