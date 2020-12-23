package com.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 /tips/book.do
 /tips/travel.do
 
 모든 요청이 DispatcherServlet으로 들어온다
 매핑파일을 참고해서 담당자(명령어 처리 클래스)를 찾아 일을 시킨다(메소드 호출)
*/
public class DispatcherServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private Properties props;
   
   //init(): 해당 서블릿이 호출될 때 한번만 호출되는 메소드
   //매핑파일을 읽어서 Properties 컬렉션에 저장한다
   @Override
   public void init(ServletConfig config) throws ServletException {
      //web.xml에서 init-param의 값 읽어오기
      
      String configFile=config.getInitParameter("configFile");
      System.out.println("configFile="+configFile);
      //=> /config/CommandProproperties //이건 물리적인 경로가 아니야
      
      //load()메소드에서 fis를 넣으려면 매핑파일의 물리적인 경로를 알아와야 함 
      String realConfigFile = config.getServletContext().getRealPath(configFile);
      System.out.println("realConfigFile="+realConfigFile+"\n");
      props = new Properties();
      FileInputStream fis=null;
      
      try {
         fis=new FileInputStream(realConfigFile);
         props.load(fis); //CommandPro.properties 파일 정보를 Properties 컬렉션에 저장
      }catch(FileNotFoundException e) {
         e.printStackTrace();
      }catch(IOException e) {
         e.printStackTrace();
      }finally {
         try {
            if(fis!=null) fis.close();
         }catch(IOException e) {
            e.printStackTrace();
         }
      }
   }

   //doGet, doPost는 사용자 요청이 들어올 때 마다 호출됨
   //요청이 들어올때마다 매핑 Properties를 참고해서 담당자를 구해 일 시킴
   protected void doGet(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
      requestPro(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
      requestPro(request, response);
   }

   private void requestPro(HttpServletRequest request, HttpServletResponse response) 
         throws ServletException, IOException {
      /*
       매핑 properties 컬렉션에서 사용자의 URI(/tips/book.do)에 
       해당하는 담당자를 구해서(명령어 처리 클래스, BookController) 일시킨다
       (해당 컨트롤러의 메소드 호출)
       그리고 결과를 리턴받아 해당 뷰페이지로 포워딩시킨다 
       */
      
      //한글 처리 둘다 해줘야 하나봐 보내는것도 가야해서 그런가
      request.setCharacterEncoding("utf-8");
      response.setCharacterEncoding("utf-8");
      
      //사용자의 URI 읽어오기
      //=> /mymvc/tips/book.do, /mymvc/tips/travel.do
      String uri=request.getRequestURI();
      System.out.println("uri="+uri);
      
      //uri중 ContextPath 제거하기 (명령어 구하기 시작)
      String contextPath=request.getContextPath(); //=> /mymvc
      System.out.println("contextPath="+contextPath);
      
      if(uri.indexOf(contextPath)!=-1) { //contextPath가 있는지. 있으면 첫번째인덱스, 없으면 -1 반환
         uri=uri.substring(contextPath.length()); //=> /tips/travel.do 명령어만 추출해냄!
      }
      
      System.out.println("컨텍스트패스 제거 후 uri="+uri);
      
      //명령어에 해당하는 명령어 처리 클래스 구하기 (ㅇㅇController2)
      String command=props.getProperty(uri);
      System.out.println("명령어 처리 클래스 command="+command);
      try {
         //해당 문자열을 클래스로 만든다
         Class commandClass=Class.forName(command);
         
         //클래스의 객체 생성
         Controller controller=(Controller)commandClass.newInstance();
         
         //해당 클래스의 메소드 호출(일시키기).. 뷰 일수도 있지만 뷰가 아닐수도 있다. 그레서 결과페이지로 이름 바꿈
         String resultPage=controller.requestProcess(request, response);
         System.out.println("호출된 뷰페이지 resultPage="+resultPage);
         
         if(controller.isRedirect()) {
            //해당 페이지로 redirect
            System.out.println("redirect 페이지\n");
            response.sendRedirect(contextPath+resultPage); 
            //리다이렉트는 컨텍스트패스 자동으로 안 붙으니까 넘어가기 전에 미리 붙여주기
         }else {
            //해당 뷰페이지로 포워딩
            System.out.println("forward 페이지\n");
            RequestDispatcher dispatcher=request.getRequestDispatcher(resultPage);
            dispatcher.forward(request, response);
         }
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      } catch (InstantiationException e) {
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         e.printStackTrace();
      } catch (Throwable e) {
         e.printStackTrace();
      }
   }
   
}