package com.batcha.mypage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.batcha.mymv.model.MyMvVO;
import com.controller.Controller;

public class MyPageController implements Controller{
	
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//�Ķ���� �޾ƿ���
		HttpSession session=request.getSession();
		Integer memNo = (Integer)session.getAttribute("memberno");

		List<MyMvVO> mlist= (List<MyMvVO>)request.getAttribute("mvlist");
		
		System.out.println("���� list="+mlist);
		//db����
		//��� ���
		request.setAttribute("mymvlist",mlist);
		
		//��������
		
		return "/myPage/myPage.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
