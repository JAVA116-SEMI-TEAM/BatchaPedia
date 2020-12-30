package com.batcha.manager.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.memInfo.model.MemInfoService;
import com.batcha.memInfo.model.MemInfoVO;
import com.controller.Controller;

public class ManagerDetailController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1 �Ķ���� �ޱ�
		String memNo= request.getParameter("no");
		
		if(memNo==null || memNo.isEmpty()) {
			request.setAttribute("msg", "회원데이터가 없습니다.");
			request.setAttribute("url", "/managerPage/manager.do");
			System.out.println(memNo);

			return "/common/message.jsp";
		}
		
		//2 db����
		MemInfoService memService = new MemInfoService();
		MemInfoVO memVo = new MemInfoVO();
		try {
			memVo = memService.selectMemByNo(Integer.parseInt(memNo));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3 ��� ����
		if(memVo.getEmail()==null || memVo.getEmail().isEmpty()) {
			memVo.setEmail("-");
		}
		
		if(memVo.getMobile()==null || memVo.getMobile().isEmpty()) {
			memVo.setMobile("-");
		}
		
		if(memVo.getBirthday()==null || memVo.getBirthday().isEmpty()) {
			memVo.setBirthday("-");
		}
		
		request.setAttribute("memVo", memVo);
		
		//4.���������� ������
		return "/managerPage/managerDetail.jsp?memNo="+memNo;
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
