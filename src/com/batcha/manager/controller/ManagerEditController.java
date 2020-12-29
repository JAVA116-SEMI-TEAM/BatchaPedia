package com.batcha.manager.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.memInfo.model.MemInfoService;
import com.batcha.memInfo.model.MemInfoVO;
import com.controller.Controller;

public class ManagerEditController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1 파라미터 받기
		String memId = request.getParameter("memId");
		String memPwd = request.getParameter("memPwd");
		String memName = request.getParameter("memName");
		String memEmail=request.getParameter("memEmail");
		String memTel=request.getParameter("memTel");
		String no=request.getParameter("memNo");
		
		MemInfoService service = new MemInfoService();
		
		MemInfoVO vo = new MemInfoVO();
		
		//2 db연결
		String msg="수정 실패", url="/managerPage/managerDetail.do?no="+no;
		try {
			vo.setId(memId);
			vo.setPwd(memPwd);
			vo.setName(memName);
			vo.setEmail(memEmail);
			vo.setMobile(memTel);
			vo.setMemNo(Integer.parseInt(no));
			
			int cnt = service.updateMem(vo);
			
			if(cnt>0) {
				msg="수정 성공";
				url="/managerPage/managerDetail.do?no="+no;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3 결과저장
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		//4 뷰페이지
		return "/common/message.jsp";
//		return "/managerPage/managerDetail.do?memNo="+memNoEd;
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
