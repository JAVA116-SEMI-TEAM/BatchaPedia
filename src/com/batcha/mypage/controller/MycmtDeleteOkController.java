package com.batcha.mypage.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.mycmt.model.MyCmtService;
import com.controller.Controller;

public class MycmtDeleteOkController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1
		//http://localhost:9090/BatchaPedia/myPage/mycmtDeleteOk.do?cmtNo=49
		String cmtNo=request.getParameter("cmtNo");
		
		MyCmtService service = new MyCmtService();
		//2
		
		String msg="코멘트 삭제 실패", url="/myPage/mycmtDetail.do?cmtNo="+cmtNo;
		try {
			int cnt = service.deleteMycmt(Integer.parseInt(cmtNo));
			if (cnt>0) {
				msg="코멘트가 삭제되었습니다.";
				url="/myPage/mycmt.do";
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
