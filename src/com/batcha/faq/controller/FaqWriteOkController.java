package com.batcha.faq.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.faq.model.FaqService;
import com.batcha.faq.model.FaqVO;
import com.controller.Controller;

public class FaqWriteOkController implements Controller {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1
		String faqTitle=request.getParameter("faqTitle");
		String faqContent=request.getParameter("faqContent");
		
		//2
		FaqService service=new FaqService();
		String msg="FAQ 새 글 등록 실패", url="/faqMgr/faqWrite.do";
		try {
			FaqVO faqVo = new FaqVO();
			faqVo.setTitle(faqTitle);
			faqVo.setContent(faqContent);
			int cnt=service.insertFaq(faqVo);
			if(cnt>0) {
				msg="FAQ 새 글 등록 성공";
				url="/faqMgr/faqList.do";
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		//4
	
		return "/common/message.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
