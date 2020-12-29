package com.batcha.mypage.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.batcha.common.PagingVO;
import com.batcha.mycmt.model.MyCmtService;
import com.batcha.mycmt.model.MyCmtVO;
import com.controller.Controller;

public class MycmtController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1
		HttpSession session=request.getSession();
		Integer memNo = (Integer)session.getAttribute("memberno");
		System.out.println("memNo="+memNo);

		MyCmtService mcService = new MyCmtService();
		List<MyCmtVO> list = null;
		//2
		try {
			list=mcService.cmtByMemno(memNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//페이징
		int currentPage=1;
		if(request.getParameter("currentPage")!=null 
				&&!request.getParameter("currentPage").isEmpty()) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			System.out.println("현재페이지"+currentPage);
		}
		int totalRecord=0;
		
		if (list!=null) {
			totalRecord=list.size();
		}
		
		int pageSize=15;
		int blockSize=5;
		
		PagingVO pageVo = new PagingVO(currentPage, totalRecord, pageSize, blockSize);
		
		//3
		request.setAttribute("mcList", list);
		request.setAttribute("mcpageVo", pageVo);
		
		//4
		
		return "/myPage/mycmt.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
