package com.batcha.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.mvInfo.model.MvInfoService;
import com.batcha.mvInfo.model.MvInfoVO;
import com.controller.Controller;

public class MainController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//BoxOffice Top 5
		MvInfoService mvService=new MvInfoService();
		List<MvInfoVO> boxOfficeList=null;
		try {
			boxOfficeList=mvService.selectBoxOfficeList();
			System.out.println("박스오피스 리스트 사이즈"+boxOfficeList.size());
			System.out.println("박스오피스 리스트 첫번째 "+boxOfficeList.get(0));
			System.out.println("박스오피스 리스트 두번째 "+boxOfficeList.get(1));
			System.out.println("박스오피스 첫번째 무비넘버"+boxOfficeList.get(0).getMvNo());
		}catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("boxOfficeList", boxOfficeList);
		return "/main.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
