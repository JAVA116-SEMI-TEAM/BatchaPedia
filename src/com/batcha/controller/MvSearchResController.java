package com.batcha.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.mvInfo.model.MvInfoService;
import com.batcha.mvInfo.model.MvInfoVO;
import com.controller.Controller;

public class MvSearchResController implements Controller {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1
		String keyword=request.getParameter("mvKeyword");
		
		//2
		List<MvInfoVO> searchList=null;
		try {
			MvInfoService service=new MvInfoService();
			searchList=service.searchMv(keyword);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		//3
		request.setAttribute("searchList", searchList);
		
		//4
		return "/mvSearch/mvSearchRes.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
