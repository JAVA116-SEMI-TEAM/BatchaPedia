<%@page import="com.batcha.qna.model.QnaService"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.batcha.qna.model.QnaVO"%>
<%@page import="com.batcha.qna.model.QnaDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/qnaStyle.css"/>

<%
	//detail.jsp에서 [수정] 링크 클릭하면 get방식으로 이동
	//=> http://localhost:9090/mystudy/board/edit.jsp?no=5
	//1
	String qnano=request.getParameter("qnano");
	QnaVO vo = (QnaVO)request.getAttribute("vo");	
	System.out.println("qnano="+qnano+"vo="+vo);
	String content=vo.getContent();
	if(content==null) content="";
%>
<div>
<form name="frmEdit" method="post" action="edit_ok.do"> 
	<!--  수정시 필요한 no를 hidden 필드에 넣는다-->
    <input type="hidden" name="qnano" value="<%=qnano%>"/><br><br>
	
    <fieldset>
        <div>
            <input type="text" id="title" name="title" 
            	value="<%=vo.getTitle()%>" />
        </div><br>
        <div>  
 			<textarea id="content" name="content" rows="12" cols="40"><%=content%></textarea>
        </div><br>
        <div class="center">
            <input type = "submit" value="수정"/>
            <input type = "Button" value="목록" onclick="location.href	='list.do'" />         
        </div><br><br><br>

<%@ include file="../inc/bottom.jsp"%>