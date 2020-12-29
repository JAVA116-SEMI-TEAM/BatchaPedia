package com.batcha.faq.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.faq.model.FaqService;
import com.controller.Controller;

public class FaqDeleteController implements Controller {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		/*
		 4. faq 삭제 처리 - faqDelete.jsp
		/faqMgr/faqDelete.do => FaqDeleteController
		/common/message.jsp로 포워드
		=> /faqMgr/faqList.do로 리다이렉트 
		 */
		//1
		String faqNo=request.getParameter("faqNo");
		
		//2
		FaqService service = new FaqService();
		String msg="FAQ 삭제 실패", url="/faqMgr/faqEdit.do?faqNo="+faqNo;
		try {
			int cnt=service.deleteFaq(Integer.parseInt(faqNo));
			if(cnt>0) {
				msg="FAQ 삭제 성공";
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
		// TODO Auto-generated method stub
		return false;
	}

}
