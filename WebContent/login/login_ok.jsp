<%@page import="java.sql.SQLException"%>
<%@page import="org.apache.catalina.tribes.membership.MemberImpl"%>
<%@page import="com.batcha.memInfo.model.MemInfoVO"%>
<%@page import="com.batcha.memInfo.model.MemInfoService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="memInfoService" class="com.batcha.memInfo.model.MemInfoService" 
	scope="session"></jsp:useBean>
<%
	//1
	request.setCharacterEncoding("utf-8");
	String userid=request.getParameter("userid");
	String pwd=request.getParameter("pwd");
	String chkSave=request.getParameter("chkSave");
	
	//2
	String msg="로그인 처리 실패", url="/login/login.do";
	try{
		int result=memInfoService.loginCheck(userid, pwd);
		if(result==MemInfoService.LOGIN_OK){
			//1) 세션에 저장
			session.setAttribute("userid", userid);
			MemInfoVO vo=memInfoService.selectMember(userid);
			session.setAttribute("userName", vo.getName());
			
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
%>
<jsp:forward page="../common/message.jsp"></jsp:forward>
</body>
</html>