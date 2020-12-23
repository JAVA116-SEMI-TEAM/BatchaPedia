<%@page import="com.batcha.qna.model.QnaService"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.batcha.qna.model.QnaVO"%>
<%@page import="com.batcha.qna.model.QnaDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>

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
<div class="divForm">
<form name="frmEdit" method="post" action="edit_ok.do"> 
	<!--  수정시 필요한 no를 hidden 필드에 넣는다-->
    <input type="hidden" name="qnano" value="<%=qnano%>"/>
	
    <fieldset>
	<legend>Q&A글수정</legend>
        <div class="firstDiv">
            <label for="title">제목</label>
            <input type="text" id="title" name="title" 
            	value="<%=vo.getTitle()%>"/>
        </div>
<%--         <div>
            <label for="name">작성자</label>
            <input type="text" id="name" name="name" 
           		value="<%=vo.getName()%>"/>
        </div>
        <div>
            <label for="pwd">비밀번호</label>
            <input type="password" id="pwd" name="pwd" />
        </div> --%>
<%--         <div>
            <label for="email">이메일</label>
            <input type="text" id="email" name="email" 
            	value="<%=email%>"/>
        </div> --%>
<%--          <div>
            <label for="name">db아이디</label>
            <input type="text" id="dbId" name="dbId" 
           		value="<%=t_userid%>"/>
        </div> --%>
        <div>  
        	<label for="content">내용</label>        
 			<textarea id="content" name="content" rows="12" cols="40"><%=content%></textarea>
        </div>
        <div class="center">
            <input type = "submit" value="수정"/>
            <input type = "Button" value="글목록" onclick="location.href	='list.do'" />         
        </div>
	</fieldset>
</form>    
</div>

<%@ include file="../inc/bottom.jsp"%>