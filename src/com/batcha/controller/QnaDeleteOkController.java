package com.batcha.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.batcha.qna.model.QnaService;
import com.controller.Controller;

public class QnaDeleteOkController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		/*
		9. 삭제 처리 (기존 delete_ok.jsp), delete, 메시지 띄우기

		/board/delete_ok.do => DeleteOkController
		=> message.jsp 로 포워드
		(=> list.do 로 redirect)
	*/

	//1
	String qnano=request.getParameter("qnano");
	String pwd=request.getParameter("pwd");
	String dbPwd=request.getParameter("dbPwd");
	String dbId=request.getParameter("dbId");
	String userid=request.getParameter("userid");
	int admincheck=Integer.parseInt(request.getParameter("admincheck"));
	
	if(pwd.equals(dbPwd)) {
		System.out.println("pwd와 dbPwd 똑같음");
	}else {
		System.out.println("pwd와 dbPwd 안똑같음");
	}
	//2
	QnaService service = new QnaService();

	String msg="Q&A 삭제 실패! 작성자가 아닙니다.", url="/qna/delete.do?qnano="+qnano+"&userid="+userid;
	try {
		if(dbId.equals(userid)) {
			if(dbPwd.equals(pwd)) {
				int cnt=service.deleteQna(Integer.parseInt(qnano));
				if(cnt>0) {
					msg="Q&A 삭제 성공!";
					url="/qna/list.do";
				}else {
					msg="Q&A 삭제 실패!";
				}
			}
			else {
				msg="Q&A 삭제 실패! 비밀번호가 다릅니다.";
			}
		}else if(admincheck==1){
			int cnt=service.deleteQna(Integer.parseInt(qnano));
			if(cnt>0) {
				msg="Q&A 관리자 삭제 성공!";
				url="/qna/list.do";
			}else {
				msg="Q&A 관리자 삭제 실패!";
			}
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}

	//3
	request.setAttribute("msg", msg);
	request.setAttribute("url", url);
	
	//4
	return "/common/message.jsp";
	
	//return "/board/list.do";
	}

	@Override
	public boolean isRedirect() {
		// TODO Auto-generated method stub
		return false;
	}

}
