package com.batcha.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.qna.model.QnaService;
import com.batcha.qna.model.QnaVO;
import com.controller.Controller;

public class QnaEditOkController implements Controller {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1
		String qnano=request.getParameter("qnano");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		//2
		QnaService service = new QnaService();
		
		String msg="글 수정 실패!", url="/qna/edit.do?qnano="+qnano;
		try {
			QnaVO vo = new QnaVO();
			vo.setContent(content);
			vo.setTitle(title);
			vo.setQnaNo(Integer.parseInt(qnano));
			
			int cnt=service.updateQna(vo);
			if(cnt>0) {
				msg="Q&A 수정 성공";
				url="/qna/detail.do?qnano="+qnano;
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
		return false;
	}

}
