<%@page import="com.batcha.qna.model.QnaService"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.batcha.qna.model.QnaVO"%>
<%@page import="com.batcha.qna.model.QnaDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp"%>

<%
	//list.jsp에서 제목 링크 클릭하면 get방식으로 이동
	//=> http://localhost:9090/BatchaPedia/qna/detail.jsp?qnano=29
	//1
	String qnano=request.getParameter("qnano");
	QnaVO vo = (QnaVO)request.getAttribute("vo");	
	//3
	String content=vo.getContent();
	if(content!=null){
		content=content.replace("\r\n", "<br>");
	}else{
		content="";
	}
%>
	<h2>Q&A 상세보기</h2>
	<div class="divForm">
		<div class="firstDiv">
			<span class="sp1">제목</span> <span><%=vo.getTitle() %></span>
		</div>
		<div>
			<span class="sp1">작성자</span> <span><%=vo.getAuthor() %></span>
		</div>
		<div>
			<span class="sp1">등록일</span> <span><%=vo.getRegdate() %></span>
		</div>
		<div>
			<span class="sp1">조회수</span> <span><%=vo.getReadCount() %></span>
		</div>
		<div class="lastDiv">			
			<p class="content"><%=content %></p>
		</div>
		<div class="center">
		<%if(t_userid.equals(vo.getUserid())){ %>
			<a href='edit.do?qnano=<%=qnano%>'>수정</a> |
			<%} %>
        	<a href='delete.do?qnano=<%=qnano%>&userid=<%=vo.getUserid()%>'>삭제</a> |
        	<a href='list.do'>목록</a>			
		</div>
	</div>

<%@ include file="../inc/bottom.jsp"%>