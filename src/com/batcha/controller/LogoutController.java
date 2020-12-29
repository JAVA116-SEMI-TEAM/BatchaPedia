package com.batcha.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.Controller;

public class LogoutController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/login/logout.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
