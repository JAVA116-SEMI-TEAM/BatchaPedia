package com.batcha.mynotice.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.common.PagingVO;
import com.batcha.mynotice.model.NoticeService;
import com.batcha.mynotice.model.NoticeVO;
import com.controller.Controller;

public class NoticemainController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1. 파리미터 받기
		String condition = request.getParameter("searchCondition");
		String keyword = request.getParameter("searchKeyword");

		//2. db작업
		NoticeService ntservice = new NoticeService();
		List<NoticeVO> list = null;

		try {
			list = ntservice.selectAllNotice(condition, keyword);
			System.out.println("페이지사이즈="+list.size());
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
		request.setAttribute("ntlist", list);
		request.setAttribute("pageVo", pageVo);

		//4. 뷰페이지로

		return "/notice/noticemain.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}


}
