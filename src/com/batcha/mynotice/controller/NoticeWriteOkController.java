package com.batcha.mynotice.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.mynotice.model.NoticeService;
import com.batcha.mynotice.model.NoticeVO;
import com.controller.Controller;

public class NoticeWriteOkController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1
		String ntTitle=request.getParameter("ntTitle");
		String content=request.getParameter("content");
		String author = request.getParameter("author");
		
		//2
		NoticeService ntService = new NoticeService();
		NoticeVO vo= new NoticeVO();
		vo.setTitle(ntTitle);
		vo.setContent(content);
		vo.setAuthor(author);
		
		String msg="등록 실패하였습니다", url="/notice/noticeWrite.do";
		try {
			int cnt= ntService.insertNotice(vo);
			
			if(cnt>0) {
				msg="등록 성공하였습니다";
				url="/notice/noticemain.do";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/common/message.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
