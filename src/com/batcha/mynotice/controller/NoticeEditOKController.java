package com.batcha.mynotice.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.mynotice.model.NoticeService;
import com.batcha.mynotice.model.NoticeVO;
import com.controller.Controller;

public class NoticeEditOKController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1
		String noticeNo =request.getParameter("noticeNo");
		String ntTitle = request.getParameter("ntTitle");
		String content = request.getParameter("content");
		
		//2
		NoticeService service = new NoticeService();
		String msg="수정 실패 하였습니다", url="/notice/noticeDetail.do?noticeNo="+noticeNo;
		try {
			NoticeVO ntVo = new NoticeVO();
			ntVo.setNoticeNo(Integer.parseInt(noticeNo));
			ntVo.setTitle(ntTitle);
			ntVo.setContent(content);
			
			int cnt = service.updateNotice(ntVo);
			
			if (cnt>0) {
				msg="등록 성공하였습니다";
				url="/notice/noticeDetail.do?noticeNo="+noticeNo;
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
