<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="memVo" class="com.batcha.memInfo.model.MemInfoVO" 
	scope="page"></jsp:useBean>
<jsp:useBean id="memService" class="com.batcha.memInfo.model.MemInfoService" 
	scope="session"></jsp:useBean>

<%
	//post
	//1
	request.setCharacterEncoding("utf-8");
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
		
		memVo.setName(name);
		memVo.setId(id);
		memVo.setPwd(pwd);
		memVo.setBirthday(birthday);
		memVo.setMobile(hp);
		memVo.setEmail(email);
		
		int cnt=memService.insertMember(memVo);
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
%>
<jsp:forward page="../common/message.jsp"></jsp:forward>
</body>
</html>