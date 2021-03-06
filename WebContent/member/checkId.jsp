<%@page import="com.batcha.memInfo.model.MemInfoService"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/regStyle.css?ver1.0"/>
<jsp:useBean id="memService" 
	class="com.batcha.memInfo.model.MemInfoService" 
	scope="session"></jsp:useBean>    
<%
	
	//1
	request.setCharacterEncoding("utf-8");
	String userid=request.getParameter("userid");
	
	//2
	int result=0;
	if(userid!=null && !userid.isEmpty()){
		try{
			result=memService.checkDup(userid);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	//3
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/mainstyle.css"/>

<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#btUse').click(function(){
			$(opener.document).find('#userid').val("<%=userid%>");
			$(opener.document).find('#chkId').val("Y");	
			self.close();
		});
		
		
	});
</script>
</head>
<body>
	<h2>아이디 중복 검사</h2><br>
	<form name="frmId" method="post" action="checkId.jsp">
		<input type="text" name="userid" id="userid" class="checkId"
		 title="아이디입력" value="<%=userid%>"><br><br>
		<input type="submit"  id="submit" value="아이디 확인" class="checkId2">
		
		<%
		if(result==memService.EXIST_ID){%>
			<p class="pink">이미 등록된 아이디입니다.<br> 다른 아이디를 입력하세요.</p>
	<%	}else if(result==memService.NON_EXIST_ID){%>
			<input type="button" value="사용하기" id="btUse" class="checkId2">
			<p class="pink">사용가능한 아이디입니다.<br>[사용하기]버튼을 클릭하세요</p>
	<%	}	%>
	</form>
	
</body>
</html>