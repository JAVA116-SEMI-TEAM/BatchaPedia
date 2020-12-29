package com.batcha.mypage.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.batcha.memInfo.model.MemInfoService;
import com.batcha.memInfo.model.MemInfoVO;
import com.controller.Controller;

public class MyEditController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//파라미터 받기
		HttpSession session=request.getSession();
	    String userid=(String)session.getAttribute("userid");
	    System.out.println("userid="+userid);
	    
	    String mynum=(String)request.getParameter("mynum");
	    String myname=request.getParameter("myname");
	    String mybirth =request.getParameter("mybirth");

	    String mypwd= (String)request.getParameter("mypwd1");
	    System.out.println("mypwd="+mypwd);
	    
		String myhp1= (String)request.getParameter("myhp1");
		String myhp2= (String)request.getParameter("myhp2");
		String myhp3= (String)request.getParameter("myhp3");
		
		String email1= (String)request.getParameter("email1");
		String email2= (String)request.getParameter("email2");
		String email3= (String)request.getParameter("email3");
		
		String myhp = myhp1+"-"+myhp2+"-"+myhp3;
		System.out.println("전화번호="+myhp);
		
		String email ="";
		if(email1!=null && !email1.isEmpty()){
			if (email2.equals("etc")) {
				if (email3!=null && !email3.isEmpty()) {
					email = email1+"@"+email3;
				}
			}else {
				email = email1+"@"+email2;
			}
		}
		System.out.println("이메일="+email);
		
		//db
	    MemInfoService service = new MemInfoService();
	    MemInfoVO memVo = new MemInfoVO();
	    
	    String msg="회원 수정 실패",url="/myPage/myDetail.do";
	    try {
	    	memVo.setMemNo(Integer.parseInt(mynum));
	    	memVo.setId(userid);
	    	memVo.setPwd(mypwd);
	    	memVo.setName(myname);
	    	memVo.setEmail(email);
	    	memVo.setMobile(myhp);
	    	memVo.setBirthday(mybirth);
	    	int cnt=service.updateMem(memVo);
	    	System.out.println("결과2= "+cnt);
	    	
	    	if (cnt>0) {
	    		msg="회원 수정 완료";
	    		url="/myPage/myDetail.do";
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	    //결과저장
	    request.setAttribute("msg", msg);
	    request.setAttribute("url", url);
	    
	    //뷰페이지
		return "/common/message.jsp";
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
