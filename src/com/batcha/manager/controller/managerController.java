package com.batcha.manager.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.common.PagingVO;
import com.batcha.memInfo.model.MemInfoService;
import com.batcha.memInfo.model.MemInfoVO;
import com.controller.Controller;

public class managerController implements Controller {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1. 파리미터 받기
		String condition = request.getParameter("searchCondition");
		String keyword = request.getParameter("searchKeyword");
		
		//2. db작업
		MemInfoService mngservice = new MemInfoService();
		List<MemInfoVO> list = null;
		
		try {
			list = mngservice.selectMemByKey(condition,keyword);
			System.out.println("페이지사이즈");
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
		
		int pageSize=10;
		int blockSize=5;
		
		PagingVO pageVo = new PagingVO(currentPage, totalRecord, pageSize, blockSize);
		
		//3. 결과저장
		request.setAttribute("mnglist", list);
		request.setAttribute("pageVo", pageVo);
		
		//4. 뷰페이지로
		return "/managerPage/manager.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}
	
}
