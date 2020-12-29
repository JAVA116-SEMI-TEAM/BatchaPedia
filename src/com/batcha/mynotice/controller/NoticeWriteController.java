package com.batcha.mynotice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.Controller;

public class NoticeWriteController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1
		
		//2
		
		//3
		
		//4
		
		return "/notice/noticeWrite.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
