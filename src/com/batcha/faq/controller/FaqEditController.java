package com.batcha.faq.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.faq.model.FaqService;
import com.batcha.faq.model.FaqVO;
import com.controller.Controller;

public class FaqEditController implements Controller {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		/*
		 2. faq 수정 화면 - faqEdit.jsp
		 /faqMgr/faqEdit.do => faqEditController
			=> /faqMgr/faqEdit.jsp로 포워드 
		 */
		
		//1
		String faqNo = request.getParameter("faqNo");
		if(faqNo==null || faqNo.isEmpty()) {
			request.setAttribute("msg", "잘못된 url입니다");
			request.setAttribute("url", "/faqMgr/faqList.do");
			
			return "/common/message.jsp";
		}
		
		//2
		FaqService service = new FaqService();
		FaqVO faqVo = null;
		try {
			faqVo=service.selectFaq(Integer.parseInt(faqNo));
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3
		request.setAttribute("faqVo", faqVo);
		
		//4
		return "/faqMgr/faqEdit.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
