package com.batcha.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.Controller;

public class StarsMarkController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		int memNo=0;
		if(session.getAttribute("memno")!=null) {
			String t_memno=String.valueOf(session.getAttribute("memno"));
			memNo=Integer.parseInt(t_memno);
		}
		
		return null;
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
