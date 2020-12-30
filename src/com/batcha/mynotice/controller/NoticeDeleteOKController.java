package com.batcha.mynotice.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.mynotice.model.NoticeService;
import com.controller.Controller;

public class NoticeDeleteOKController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1
		//http://localhost:9090/BatchaPedia/notice/noticeDeleteOK.do?noticeNo=6
		String noticeNo = request.getParameter("noticeNo");
		
		//2
		NoticeService service = new NoticeService();
		
		String msg="삭제 실패",url="/notice/noticeDetail.do?noticeNo="+noticeNo;
		try {
			int cnt = service.deleteNotice(Integer.parseInt(noticeNo));
			if(cnt>0) {
				msg="삭제 성공";
				url="/notice/noticemain.do";
			}
		} catch (SQLException e) {
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
