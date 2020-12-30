package com.batcha.mypage.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.batcha.memInfo.model.MemInfoService;
import com.batcha.memInfo.model.MemInfoVO;
import com.controller.Controller;

public class MyDetailController implements Controller{

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//1
		HttpSession session=request.getSession();
	    String userid=(String)session.getAttribute("userid");
	    
		//2
		MemInfoService service = new MemInfoService();
		MemInfoVO memVo = new MemInfoVO();
		
		try {
			memVo=service.selectMember(userid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String myname = memVo.getName();
		String mybirth = memVo.getBirthday();
		
		if(mybirth==null || mybirth.isEmpty()) {
			mybirth="-";
		}
		
		//��ȣ
		String myhp = memVo.getMobile();
		String myhp1 = "",myhp2="",myhp3="";
		if (myhp != null && !myhp.isEmpty()) {
			String[] hpArr = myhp.split("-");
			myhp1 = hpArr[0];
			myhp2 = hpArr[1];
			myhp3 = hpArr[2];
		}
		
		//�̸���
		String email = memVo.getEmail();
		String email1 = "", email2 = "";
		String[] emailList = { "naver.com", "hanmail.net", "nate.com", "gmail.com" };
		
		int count=0;
		boolean isEtc = false;
		if (email!=null&& !email.isEmpty()) {
			String[] emailArr = email.split("@");
			email1 = emailArr[0]; 
			email2 = emailArr[1];
			
			for (int i = 0; i < emailList.length; i++) {
				if (email2.equals(emailList[i])) {
					count++;
					break;
				}
			}//for
			
			if (count == 0) {
				isEtc = true; //�����Է�
			}
		}
		
		//3
		request.setAttribute("memVo", memVo);
		request.setAttribute("myname", myname);
		request.setAttribute("mybirth", mybirth);
		request.setAttribute("myhp1", myhp1);
		request.setAttribute("myhp2", myhp2);
		request.setAttribute("myhp3", myhp3);
		
		request.setAttribute("isEtc", isEtc);
		request.setAttribute("email1", email1);
		request.setAttribute("email2", email2);
		
		//4
		return "/myPage/myDetail.jsp?userid="+userid;
	}

	@Override
	public boolean isRedirect() {
		return false;
	}

}
