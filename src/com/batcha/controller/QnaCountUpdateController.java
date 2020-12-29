package com.batcha.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.qna.model.QnaService;
import com.controller.Controller;

public class QnaCountUpdateController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1
		String qnano=request.getParameter("qnano");
		
		//2
		QnaService service=new QnaService();
		
		int cnt=0;
		try{
			cnt=service.updateReadCount(Integer.parseInt(qnano));
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		return "/qna/detail.do?qnano="+qnano;
	}

	@Override
	public boolean isRedirect() {
		return true;
	}

}
