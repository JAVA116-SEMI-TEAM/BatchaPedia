package com.batcha.faq.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.faq.model.FaqService;
import com.batcha.faq.model.FaqVO;
import com.controller.Controller;

public class FaqListController implements Controller {
	public FaqService faqService;
	
	public FaqListController() {
		faqService = new FaqService();
	}
	
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		/*
		 1. 게시판 목록 조회 - faqList.jsp 
		 	/faqMgr/faqList.do => FaqListController
		  	=> /faqMgr/faqList.jsp로 포워드
		 */
		//1
		String login = request.getParameter("login");
		String all= request.getParameter("all");
		String use = request.getParameter("use");
		String account= request.getParameter("account");
		//2
		List<FaqVO> faqList=null;
		try {
			
			FaqVO faqVo = new FaqVO();
			faqVo.setOpt("login");
			faqList=faqService.selectAllFaq(); //전체
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		//3
		request.setAttribute("faqList", faqList);
		
		//4
		return "/faqMgr/faqList.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
