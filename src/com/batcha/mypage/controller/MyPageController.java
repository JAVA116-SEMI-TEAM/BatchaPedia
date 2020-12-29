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
		//파라미터 받아오기
		HttpSession session=request.getSession();
		Integer memNo = (Integer)session.getAttribute("memberno");

		List<MyMvVO> mlist= (List<MyMvVO>)request.getAttribute("mvlist");
		
		System.out.println("메인 list="+mlist);
		//db연결
		//결과 담기
		request.setAttribute("mymvlist",mlist);
		
		//뷰페이지
		
		return "/myPage/myPage.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
