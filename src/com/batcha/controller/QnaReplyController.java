package com.batcha.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.qna.model.QnaDAO;
import com.batcha.qna.model.QnaService;
import com.batcha.qna.model.QnaVO;
import com.controller.Controller;

public class QnaReplyController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1
		String qnano=request.getParameter("qnano");
		if(qnano==null || qnano.isEmpty()) {
			request.setAttribute("msg", "잘못된 url입니다.");
			request.setAttribute("url", "/qna/list.do");
			
			return "/common/message.jsp";
		}
		
		//2
		QnaService service = new QnaService();
		QnaVO vo=null;
		
		try{
			vo=service.selectByNo(Integer.parseInt(qnano));
		}catch(SQLException e){
			e.printStackTrace();	
		}
		
		//3
		request.setAttribute("vo", vo);
		
		return "/qna/reply.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
