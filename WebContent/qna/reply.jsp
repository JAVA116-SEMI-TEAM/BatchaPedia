<%@page import="com.batcha.qna.model.QnaVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/qnaStyle.css"/>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<%
	QnaVO vo = (QnaVO)request.getAttribute("vo");	
%>
 <div>
<form name="frmWrite" method="post" action="reply_ok.do" >


<input type="text" name="groupNo" 
		value="<%=vo.getGroupno() %>" style="visibility: hidden"/>
	<input type="text" name="step" 
		value="<%=vo.getStep() %>"  style="visibility: hidden"/>
	<input type="text" name="sortNo" 
		value="<%=vo.getSortNo() %>"  style="visibility: hidden"/>
	<input type="text" name="memno" 
		value="<%=t_memnumber%>"  style="visibility: hidden"/>
	<input type="text" name="userid" 
		value="<%=t_userid%>"  style="visibility: hidden"/>
	<input type="text" name="admincheck" 
		value="<%=t_admincheck%>"  style="visibility: hidden"/>
	
 <fieldset>

        <div class="firstDiv">
            <input type="text" id="title" name="title" 
            	value=" Re : <%=vo.getTitle() %>" />
        </div>
        <div>
            <input type="text" id="name" name="name" value="관리자"  style="visibility: hidden"/>
        </div>
        <div>  
 			<textarea id="content" name="content" rows="12" cols="40"></textarea>
        </div><br>
        <div class="center">
            <input type = "submit" value="답변"/>
            <input type = "Button" value="목록" onclick="location.href	='list.do'"/>         
        </div>
        <br><br>
    </fieldset>
</form>
</div>   

<%@ include file="../inc/bottom.jsp"%>