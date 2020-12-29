package com.batcha.faq.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.Controller;

public class FaqWriteController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		
		//4
		return "/faqMgr/faqWrite.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
