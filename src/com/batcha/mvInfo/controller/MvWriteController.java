package com.batcha.mvInfo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.Controller;

public class MvWriteController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		return "/mvInfo/mvWrite.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;	//포워드
	}

}
