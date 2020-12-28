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
      List<MvInfoVO> korMovieList=null;
      List<MvInfoVO> movie20thList=null;
      List<MvInfoVO> comedyMovieList=null;
      List<MvInfoVO> ThrillMovieList=null;
      try {
         boxOfficeList=mvService.selectBoxOfficeList();
         korMovieList=mvService.selectKorMovie();
         movie20thList=mvService.select20th();
         comedyMovieList=mvService.selectComedy();
         ThrillMovieList=mvService.selectThrill();
      }catch (Exception e) {
         e.printStackTrace();
      }
      request.setAttribute("boxOfficeList", boxOfficeList);
      request.setAttribute("korMovieList", korMovieList);
      request.setAttribute("movie20thList", movie20thList);
      request.setAttribute("comedyMovieList", comedyMovieList);
      request.setAttribute("ThrillMovieList", ThrillMovieList);
      return "/main.jsp";
   }

   @Override
   public boolean isRedirect() {
      return false;
   }

}