package com.batcha.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.batcha.memInfo.model.MemInfoService;
import com.batcha.memInfo.model.MemInfoVO;
import com.controller.Controller;

public class RegisterOkController implements Controller {

	@Override
	public String requestProcess(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String name=request.getParameter("name");
		String id=request.getParameter("userid");
		String pwd=request.getParameter("pwd");
		String birthday=request.getParameter("birthday");
		String hp1=request.getParameter("hp1");
		String hp2=request.getParameter("hp2");
		String hp3=request.getParameter("hp3");
		String email1=request.getParameter("email1");
		String email2=request.getParameter("email2");
		String email3=request.getParameter("email3");
		
		//2
		String msg="회원가입 실패!", url="/member/register.do";
		
		try{
			String email="", hp="";
			if(hp2!=null && !hp2.isEmpty() && hp3!=null && !hp3.isEmpty()){
				hp=hp1+"-"+hp2+"-"+hp3;
			}
			
			if(email1!=null && !email1.isEmpty()){
				if(email2.equals("etc")){
					if(email3!=null && !email3.isEmpty()){
						email=email1+"@"+email3;
					}
				}else{
					email=email1+"@"+email2;
				}
			}
			
			MemInfoService service=new MemInfoService();
			MemInfoVO vo=new MemInfoVO();
			
			vo.setName(name);
			vo.setId(id);
			vo.setPwd(pwd);
			vo.setBirthday(birthday);
			vo.setMobile(hp);
			vo.setEmail(email);
			
			int cnt=service.insertMember(vo);
			if(cnt>0){
				msg="회원가입되었습니다.";
				url="/main.do";
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
