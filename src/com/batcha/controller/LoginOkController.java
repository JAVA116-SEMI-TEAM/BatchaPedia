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
      String msg="�α��� ó�� ����", url="/login/login.do";
      try{
         int result=service.loginCheck(userid, pwd);
         if(result==service.LOGIN_OK){
            //1) ���ǿ� ����
             session.setAttribute("userid", userid);
              MemInfoVO vo=service.selectMember(userid);
              session.setAttribute("userName", vo.getName());
              session.setAttribute("memberno", vo.getMemNo());
              session.setAttribute("pwd", vo.getPwd());
              session.setAttribute("adminCheck", vo.getAdminCheck());
            
            //2) ��Ű�� ����  - ���̵� �����ϱ� üũ�� ��쿡��
             Cookie ck = new Cookie("ck_userid", userid);
                  ck.setPath("/");  
                  if(chkSave != null){  //üũ�� ���
                     ck.setMaxAge(1000*24*60*60);  //��Ű ��ȿ�Ⱓ 1000��
                     response.addCookie(ck);
                  }else{ //üũ���� ���� ���
                     ck.setMaxAge(0);  //��Ű ����
                     response.addCookie(ck);
                  }

            msg=vo.getName() + "�� �α��εǾ����ϴ�.";
            url="/main.do";
         }else if(result==MemInfoService.PWD_DISAGREE){
            msg="��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
         }else if(result==MemInfoService.ID_NONE){
            msg="�ش� ���̵� �������� �ʽ��ϴ�.";         
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