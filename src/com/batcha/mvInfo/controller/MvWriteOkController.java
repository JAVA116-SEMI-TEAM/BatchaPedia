package com.batcha.mvInfo.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.common.Utility;
import com.batcha.mvInfo.model.MvInfoService;
import com.batcha.mvInfo.model.MvInfoVO;
import com.controller.Controller;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.Part;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import java.util.*;
import java.io.*;


public class MvWriteOkController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		/*
		 2. 영화 등록 처리 - mvWrite_ok.jsp
			/mvInfo/mvWrite_ok.do=> MvWriteOkController
			=> /common/message.jsp로 포워드
			(=> /mvInfo/mvList.do 로 redirect) 
		 */
		
		//1
		//
		String mvCode=request.getParameter("mvCode");
		String mvTitleEn=request.getParameter("mvTitleEn");
		String mvTitle=request.getParameter("mvTitle");
		String director=request.getParameter("director");
		String actors=request.getParameter("actors");
		String genre=request.getParameter("genre");
		String nation=request.getParameter("nation");
		String makeYear=request.getParameter("makeYear");
		String story=request.getParameter("story");

		//2
		MvInfoService service = new MvInfoService();
		String msg="영화 등록 실패", url="/mvInfo/mvWrite.do";
		try {
			MvInfoVO vo = new MvInfoVO();
			vo.setMvCode(mvCode);
			vo.setMvTitleEn(mvTitleEn);
			vo.setMvTitle(mvTitle);
			vo.setDirector(director);
			vo.setGenre(genre);
			vo.setActors(actors);
			vo.setNation(nation); 
			vo.setMakeYear(makeYear);
			vo.setStory(story);
			
			int cnt=service.insertMv(vo);
			if(cnt>0) {
				msg="영화 등록 성공";
				url="/mvInfo/mvList.do";
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		//3
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		//4
		return "/common/message.jsp";
	}
/*	
	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		/*
		 2. 영화 등록 처리 - mvWrite_ok.jsp
			/mvInfo/mvWrite_ok.do=> MvWriteOkController
			=> /common/message.jsp로 포워드
			(=> /mvInfo/mvList.do 로 redirect) 
		
		
		//1
		String mvTitle=request.getParameter("mvTitle");
		String director=request.getParameter("director");
		String actors=request.getParameter("actors");
		String genre=request.getParameter("genre");
		String nation=request.getParameter("nation");
		String showYear=request.getParameter("showYear");
		String makeYear=request.getParameter("makeYear");
		String startdate=request.getParameter("startdate")+" 00:00:00.0";
		String enddate=request.getParameter("enddate")+" 00:00:00.0";
		String story=request.getParameter("story");
		String boxOffice=request.getParameter("boxOffice");
		
		java.sql.Timestamp start = java.sql.Timestamp.valueOf(startdate);
		java.sql.Timestamp end = java.sql.Timestamp.valueOf(enddate);
		
		//2
		MvInfoService service = new MvInfoService();
		String msg="영화 등록 실패", url="/mvInfo/mvWrite.do";
		try {
			MvInfoVO vo = new MvInfoVO();
			vo.setStartdate(start);
			vo.setEnddate(end);
			vo.setMvTitle(mvTitle);
			vo.setDirector(director);
			vo.setGenre(genre);
			vo.setActors(actors);
			vo.setNation(nation); 
			vo.setMakeYear(makeYear);
			vo.setStory(story);
			vo.setBoxOffice(Integer.parseInt(boxOffice));
			
			int cnt=service.insertMv(vo);
			if(cnt>0) {
				msg="영화 등록 성공";
				url="/mvInfo/mvList.do";
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		//3
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		//4
		return "/common/message.jsp";
	}
*/
	@Override
	public boolean isRedirect() {
		return false;
	}

}
