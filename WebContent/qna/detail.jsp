<%@page import="com.batcha.qna.model.QnaService"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.batcha.qna.model.QnaVO"%>
<%@page import="com.batcha.qna.model.QnaDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/qnaStyle.css?ver1.0" />

<%
	//1
	String qnano=request.getParameter("qnano");
	QnaVO vo = (QnaVO)request.getAttribute("vo");	
	String admin=String.valueOf(session.getAttribute("adminCheck"));
	//3
	String content=vo.getContent();
	if(content!=null){
		content=content.replace("\r\n", "<br>");
	}else{
		content="";
	}
%>
<br><br>
<table class="table detail">
	<tr>
		<th width="20%">제목</th>
		<td><%=vo.getTitle() %></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><%=vo.getAuthor() %></td>
	</tr>
	<tr>
		<th>등록일</th>
		<td><%=vo.getRegdate() %></td>
	</tr>
	<tr>
		<th>조회수</th>
		<td><%=vo.getReadCount() %></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><%=content %></td>
	</tr>
</table><br><br>

<div class="align_center">
	<%-- <%if(t_userid.equals(vo.getUserid())){ %> --%>
	<%if(vo.getUserid().equals(t_userid)){ %>
	<a href='edit.do?qnano=<%=qnano%>'>수정</a> |
	<%} %>
	<%if(Integer.parseInt(admin)==1){ %>
	<a href='reply.do?qnano=<%=qnano%>'>답변</a> |
	<%} %>
	<a href='delete.do?qnano=<%=qnano%>&userid=<%=vo.getUserid()%>'>삭제</a>
	| <a href='list.do'>목록</a>
</div><br><br>


<%@ include file="../inc/bottom.jsp"%>