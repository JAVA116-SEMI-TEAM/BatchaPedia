package com.batcha.faq.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.faq.model.FaqService;
import com.batcha.faq.model.FaqVO;
import com.controller.Controller;

public class FaqEditOkController implements Controller {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		/*
		 3. faq 수정 처리 - faqEdit_ok.jsp
			/faqMgr/faqEdit_ok.do => FaqEditOkController
			/common/message.jsp로 포워드
			=> /faqMgr/faqList.do로 리다이렉트 
		 */
		
		//1
		String faqNo=request.getParameter("faqNo");
		String faqTitle=request.getParameter("faqTitle");
		String faqContent=request.getParameter("faqContent");
		
		//2
		FaqService service=new FaqService();
		
		String msg="FAQ 수정 실패", url="/faqMgr/faqEdit.do?faqNo="+faqNo;
		try {
			FaqVO faqVo=new FaqVO();
			faqVo.setFaqNo(Integer.parseInt(faqNo));
			faqVo.setTitle(faqTitle);
			faqVo.setContent(faqContent);
			int cnt=service.updateFaq(faqVo);
			
			if(cnt>0) {
				msg="FAQ 수정 성공";
				url="/faqMgr/faqList.do";
			}
		}catch(SQLException e) {
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
