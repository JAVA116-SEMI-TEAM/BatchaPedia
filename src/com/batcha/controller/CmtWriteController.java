package com.batcha.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.batcha.cmtData.model.CmtDataService;
import com.batcha.cmtData.model.CmtDataVO;
import com.controller.Controller;

public class CmtWriteController implements Controller {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//파라미터 받아오기
		HttpSession session = request.getSession();
		int memNo=0;
		if(session.getAttribute("memno")!=null) {
			String t_memno=String.valueOf(session.getAttribute("memno"));
			memNo=Integer.parseInt(t_memno);
		}
		String mvNo=request.getParameter("mvNo");
		
		CmtDataService cmtService=new CmtDataService();
		String userid=request.getParameter("userid");
		String cmtText=request.getParameter("cmtText");
		CmtDataVO cmtVo=new CmtDataVO();
		cmtVo.setMemNo(memNo);
		cmtVo.setCmtText(cmtText);
		//로직
		String msg="코멘트 입력 실패!", url="/movie/movieDetail?no="+mvNo;
		try {
			int cnt=cmtService.insertCmt(cmtVo);
			msg="코멘트가 작성되었습니다.";
			url="/movie/movieDetail?no="+mvNo;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//세팅, 뷰페이지 턴
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "common/message";
	}

	@Override
	public boolean isRedirect() {
		return true;
	}

}
