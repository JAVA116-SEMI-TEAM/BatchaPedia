package com.batcha.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.mvInfo.model.MvInfoService;
import com.batcha.mvInfo.model.MvInfoVO;
import com.controller.Controller;

public class MvSearchOkController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		/*
		 /inc/top.do => MvSearchOkController
		  => /mvSearch/mvSearch.jsp로 포워드
		 */
		//1
		String mvSearch=request.getParameter("mvSearchKeyword");
		
		//2
		List<MvInfoVO> searchList=null;
		try {
			MvInfoService service=new MvInfoService();
			searchList=service.searchMv(mvSearch);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		//3
		request.setAttribute("searchList", searchList);
		
		//4
		return "/mvSearch/mvSearch.jsp?searchKeyword="+mvSearch;
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
