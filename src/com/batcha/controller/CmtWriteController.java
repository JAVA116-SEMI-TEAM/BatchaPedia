package com.batcha.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.batcha.cmtData.model.CmtDataService;
import com.batcha.cmtData.model.CmtDataVO;
import com.batcha.memInfo.model.MemInfoService;
import com.batcha.memInfo.model.MemInfoVO;
import com.controller.Controller;

public class CmtWriteController implements Controller {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//파라미터 받아오기
		//코멘트 작성 관련 파라미터 받기
		String userid=request.getParameter("userid");
		String strMemNo=request.getParameter("memNo");
		String strMvNo=request.getParameter("mvNo");
		String cmtText=request.getParameter("cmtText");
		int memNo=Integer.parseInt(strMemNo);
		int mvNo=Integer.parseInt(strMvNo);
		
		//서비스, CmtVO 객체생성
		CmtDataService cmtService=new CmtDataService();
		CmtDataVO cmtVo=new CmtDataVO();
		cmtVo.setUserid(userid);
		System.out.println("getUserid="+cmtVo.getUserid());
		cmtVo.setMemNo(memNo);
		cmtVo.setCmtText(cmtText);
		cmtVo.setMvNo(mvNo);
		
		//로직
		String msg="코멘트 입력 실패!", url="/movie/movieDetail.do?mvNo="+mvNo;
		try {
			int cnt=cmtService.insertCmt(cmtVo);
			System.out.println("코멘트 입력 완료 cnt="+cnt);
			if(cnt>0) {
				msg="코멘트가 작성되었습니다.";
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//세팅, 뷰페이지 턴
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/common/message.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}
}
