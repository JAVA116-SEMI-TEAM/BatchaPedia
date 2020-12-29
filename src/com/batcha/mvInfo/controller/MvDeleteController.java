package com.batcha.mvInfo.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.mvInfo.model.MvInfoService;
import com.controller.Controller;

public class MvDeleteController implements Controller {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		/*
		 6. 영화 삭제 처리 - mvDelete.jsp
		/mvInfo/mvDelete.do => MvDeleteController
		=> /mvInfo/mvList.do로 리다이렉트 
		 */
		//1
		//=>
		String mvNo=request.getParameter("mvNo");
		
		//2
		MvInfoService service=new MvInfoService();
		String msg="영화 정보 삭제 실패", url="/mvInfo/mvEdit.do?mvNo="+mvNo;
		try {
			int cnt=service.deleteMv(Integer.parseInt(mvNo));
			if(cnt>0) {
				msg="영화 정보 삭제 성공";
				url="/mvInfo/mvList.do";
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
