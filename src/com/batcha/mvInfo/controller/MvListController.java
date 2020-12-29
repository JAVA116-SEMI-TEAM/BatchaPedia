package com.batcha.mvInfo.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.common.PagingVO;
import com.batcha.mvInfo.model.MvInfoService;
import com.batcha.mvInfo.model.MvInfoVO;
import com.controller.Controller;
import com.batcha.common.*;

public class MvListController implements Controller {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		/*
		  3. 영화 목록 페이지 - mvList.jsp
			/mvInfo/mvList.do => MvListController
			=> /mvInfo/mvList.jsp로 포워드
		 */
		//1
		String option = request.getParameter("option");
		String keyword = request.getParameter("keyword");
		
		//2
		MvInfoService service = new MvInfoService();
		
		List<MvInfoVO> list = null;
		try {
			list=service.selectAllMv(option,keyword);
		}catch(SQLException e) {
			e.printStackTrace();
		}

		//페이징 처리
		int currentPage = 1;
		if(request.getParameter("currentPage")!=null 
				&& !request.getParameter("currentPage").isEmpty()) {
			currentPage
			= Integer.parseInt(request.getParameter("currentPage"));
		}

		int totalRecord = 0;
		if(list!=null) totalRecord=list.size();

		int pageSize = 10;
		int blockSize = 5;

		PagingVO pageVo = new PagingVO(currentPage, totalRecord, 
				pageSize, blockSize);
		
		//3
		request.setAttribute("mvList", list);
		request.setAttribute("pageVo", pageVo);

		//4
		return "/mvInfo/mvList.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
