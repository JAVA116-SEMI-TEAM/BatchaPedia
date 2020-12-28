package com.batcha.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.qna.model.QnaService;
import com.batcha.qna.model.QnaVO;
import com.controller.Controller;

public class QnaReplyOkController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String title=request.getParameter("title");
		String name=request.getParameter("name");
		String content=request.getParameter("content");
		String groupNo=request.getParameter("groupNo");
		String step=request.getParameter("step");
		String sortNo=request.getParameter("sortNo");
		String memno=request.getParameter("memno");
		String userid=request.getParameter("userid");
		String admincheck=request.getParameter("admincheck");
		
		QnaService service=new QnaService();
		QnaVO vo=new QnaVO();

		vo.setTitle(title);
		vo.setAuthor(name);
		vo.setContent(content);
		vo.setGroupno(Integer.parseInt(groupNo));
		vo.setStep(Integer.parseInt(step));
		vo.setSortNo(Integer.parseInt(sortNo));
		vo.setMemNo(Integer.parseInt(memno));
		vo.setUserid(userid);
		vo.setAdmincheck(Integer.parseInt(admincheck));
		
		
		int cnt=0;
		String msg="답글 작성 실패!", url="/qna/list.do";
		try{
			cnt=service.reply(vo);	
			if(cnt>0) {
				msg="답글 작성 성공";
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
