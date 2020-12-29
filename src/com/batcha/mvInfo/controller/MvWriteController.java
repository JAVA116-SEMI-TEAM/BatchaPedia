package com.batcha.mvInfo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.Controller;

public class MvWriteController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		/*
		 	1. 영화 등록 - mvWrite.jsp
		  /mvInfo/mvWrite.do => MvWriteController
			=> /mvInfo/mvWrite.jsp로 포워드
		 */
		//1
		//2
		//3
		//4
		
		return "/mvInfo/mvWrite.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;	//포워드
	}

}
