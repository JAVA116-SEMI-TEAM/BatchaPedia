package com.batcha.controller;

import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.batcha.memInfo.model.MemInfoService;
import com.batcha.memInfo.model.MemInfoVO;
import com.controller.Controller;

public class LoginOkController implements Controller {
   
   @Override
   public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
      HttpSession session = request.getSession();
      
      String userid=request.getParameter("userid");
      String pwd=request.getParameter("pwd");
      String chkSave=request.getParameter("chkSave");
      
      //2
      MemInfoService service=new MemInfoService();
      String msg="로그인 처리 실패", url="/login/login.do";
      try{
         int result=service.loginCheck(userid, pwd);
         if(result==service.LOGIN_OK){
            //1) 세션에 저장
             session.setAttribute("userid", userid);
              MemInfoVO vo=service.selectMember(userid);
              session.setAttribute("userName", vo.getName());
              session.setAttribute("memberno", vo.getMemNo());
              session.setAttribute("pwd", vo.getPwd());
              session.setAttribute("adminCheck", vo.getAdminCheck());
            
            //2) 쿠키에 저장  - 아이디 저장하기 체크한 경우에만
             Cookie ck = new Cookie("ck_userid", userid);
                  ck.setPath("/");  
                  if(chkSave != null){  //체크한 경우
                     ck.setMaxAge(1000*24*60*60);  //쿠키 유효기간 1000일
                     response.addCookie(ck);
                  }else{ //체크하지 않은 경우
                     ck.setMaxAge(0);  //쿠키 삭제
                     response.addCookie(ck);
                  }

            msg=vo.getName() + "님 로그인되었습니다.";
            url="/main.do";
         }else if(result==MemInfoService.PWD_DISAGREE){
            msg="비밀번호가 일치하지 않습니다.";
         }else if(result==MemInfoService.ID_NONE){
            msg="해당 아이디가 존재하지 않습니다.";         
         }
      }catch(SQLException e){
         e.printStackTrace();
      }
      
      //3
      request.setAttribute("msg", msg);
      request.setAttribute("url", url);
      return "/common/message.jsp";
   }

   @Override
   public boolean isRedirect() {
      return false;
   }

}