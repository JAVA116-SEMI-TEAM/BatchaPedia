package com.batcha.faq.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.faq.model.FaqService;
import com.batcha.faq.model.FaqVO;
import com.controller.Controller;

public class FaqClListController implements Controller {
	public FaqService faqService;
	
	public FaqClListController() {
		faqService = new FaqService();
	}
	
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1
		/*
		 1. 게시판 목록 조회 - faqClList.jsp 
		 	/faqCl/faqClList.do => FaqClListController
		  	=> /faqCl/faqClList.jsp로 포워드
		 */
		//2
		List<FaqVO> faqList=null;
		try {
			faqList=faqService.selectAllFaq();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		//3
		request.setAttribute("faqList", faqList);
		
		//4
		return "/faqCl/faqClList.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
