package com.batcha.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.qna.model.QnaService;
import com.batcha.qna.model.QnaVO;
import com.controller.Controller;


public class QnaWriteOkController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		//1		
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String userid=request.getParameter("id");
		String author=request.getParameter("name");
		int memno=Integer.parseInt(request.getParameter("memno"));
		int admincheck=Integer.parseInt(request.getParameter("admincheck"));

		//2
		QnaService service=new QnaService();
		
		try {
			QnaVO vo = new QnaVO();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setMemNo(memno);
			vo.setAuthor(author);
			vo.setUserid(userid);
			vo.setAdmincheck(admincheck);

			int cnt=service.insertQna(vo);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return "/qna/list.do";
		
	}


	@Override
	public boolean isRedirect() {
		return true;
	}

}
