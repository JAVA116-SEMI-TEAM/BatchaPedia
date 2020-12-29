package com.batcha.mypage.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.batcha.mymv.model.MyMvService;
import com.batcha.mymv.model.MyMvVO;
import com.controller.Controller;

public class MymvController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1 파라미터
		HttpSession session=request.getSession();
		Integer memNo = (Integer)session.getAttribute("memberno");
		System.out.println("memNo="+memNo);
		
		//2 db
		MyMvService mmService = new MyMvService();
		
		
		List<MyMvVO> list = null;
		MyMvVO mmvo = new MyMvVO();
		String mmTb="", mmTitle="";		
		try {
			list=mmService.KeepByMemNo(memNo);
			
			mmTb=mmvo.getThumbnail();
			mmTitle =mmvo.getMvTitle();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("mmTitle="+mmTitle);
		System.out.println("mmTb="+mmTb);
		
		//3 결과저장
		request.setAttribute("mmTitle", mmTitle);
		request.setAttribute("mmTb", mmTb);
		request.setAttribute("mvlist", list);
		
		//4 뷰페이지
		return "/myPage/mymv.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
