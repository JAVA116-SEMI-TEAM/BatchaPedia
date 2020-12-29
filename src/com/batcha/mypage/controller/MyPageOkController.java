package com.batcha.mypage.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.batcha.mymv.model.MyMvService;
import com.batcha.mymv.model.MyMvVO;
import com.controller.Controller;

public class MyPageOkController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session=request.getSession();
		int memNo1 = (int)session.getAttribute("memberno");
		System.out.println("memNo="+memNo1);

		//2 db
		MyMvService mmService = new MyMvService();

		List<MyMvVO> list = null;
		try {
			list=mmService.KeepByMemNo(memNo1);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		//3 결과저장
		
		request.setAttribute("mvlist", list);

		//4 뷰페이지
		return "/myPage/myPage.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
