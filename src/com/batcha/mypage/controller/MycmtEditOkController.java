package com.batcha.mypage.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.mycmt.model.MyCmtService;
import com.batcha.mycmt.model.MyCmtVO;
import com.controller.Controller;

public class MycmtEditOkController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//cmtText
		//cmtNo
		String cmtNo=request.getParameter("cmtNo");
		String cmtText=request.getParameter("cmtText");
		
		MyCmtService service = new MyCmtService();
		MyCmtVO mcVo = new MyCmtVO();
		
		String msg="수정 실패 하였습니다", url="/myPage/mycmtDetail.do?cmtNo="+cmtNo;
		try {
			mcVo.setCmtNo(Integer.parseInt(cmtNo));
			mcVo.setCmtText(cmtText);
			int cnt = service.updateMycmt(mcVo);
			if (cnt>0) {
				msg="수정 되었습니다";
				url="/myPage/mycmtDetail.do?cmtNo="+cmtNo;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/common/message.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
