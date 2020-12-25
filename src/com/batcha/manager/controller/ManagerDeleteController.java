package com.batcha.manager.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.memInfo.model.MemInfoService;
import com.controller.Controller;

public class ManagerDeleteController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1 �Ķ���� �ޱ�
		String memNo = request.getParameter("memNo");
		
		//2 db����
		MemInfoService service = new MemInfoService();
		
		String msg="���� ���� �߽��ϴ�",url="/managerPage/managerDetail.do"+memNo; 
		try {
			int cnt = service.deleteMem(Integer.parseInt(memNo));
			
			if (cnt>0) {
				msg="�����Ǿ����ϴ�";
				url="/managerPage/manager.do";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3 ��� ����
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		//4 ������
		return "/common/message.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
