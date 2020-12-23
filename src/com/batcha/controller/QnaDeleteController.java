package com.batcha.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.Controller;

public class QnaDeleteController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String qnano=request.getParameter("qnano");
		if(qnano==null || qnano.isEmpty()) {
			request.setAttribute("msg", "잘못된 url입니다.");
			request.setAttribute("url", "/qna/list.do");

			return "/common/message.jsp";
		}
		
		//2		
		//3
		//4
		return "/qna/delete.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
