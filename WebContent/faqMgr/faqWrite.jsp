<%@page import="com.batcha.faq.model.FaqVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../inc/top.jsp"%>

<!-- ckEditor 추가-->
<script src = "../resource/ckeditor/ckeditor.js"></script>
<link rel="stylesheet" href="../css/faq.css">
<style type="text/css">
h4 {
	float: left;
}

.WriteBtn{
	float: right;
	margin-bottom: 10px;
}

#faqRegisterWrite {
	clear: both;
}

.faqTitle {
	cursor: pointer;
	padding: 18px;
	width: 100%;
	text-align: left;
	border: 1PX SOLID SILVER;
	border-collapse: none;
	outline: none;
	transition: 0.4s;
}

.panel {
	padding: 0 18px;
	background-color: white;
	display: block;
	overflow: hidden;
	width:100%;
	height: 500px;
	border: 1px solid silver;
	overflow-y: scroll;
	-ms-overflow-style: none;
}

.faqWrite {
	margin-bottom: 100px;
}

#faqTitle {
	color: gray;
	background: white;
}

#faqTitle:focus {
	border-color: rgba(153, 153, 153, 0.8);
	box-shadow: 0 1px 1px rgba(153, 153, 153, 0.075) inset, 0 0 8px
		rgba(153, 153, 153, 0.6);
	outline: 0 none;
	color: black;
}


</style>
<%
	FaqVO faqVo= (FaqVO)request.getAttribute("faqVo");
	String faqNo=request.getParameter("faqNo");
%>
<div class="faq">

	<!-- 자주 묻는 질문 -->
	<H4>FAQ 등록</H4>
	<form name="faqWriteForm" method="post"> 
	<!-- 수정, 삭제 버튼 -->
	<div class="WriteBtn">
	<input class="btn btn-outline-secondary" id="faqWriteBtn" type="submit" value="등록"
	onclick="javascript:form.action='<%=request.getContextPath() %>/faqMgr/faqWrite_ok.do';">
	<input class="btn btn-outline-secondary" id="faqListBtn" type="submit" value="목록"
	onclick="javascript:form.action='<%=request.getContextPath() %>/faqMgr/faqList.do';">
	</div>
	<br>

		<div class="tab-pane show active" id="faqRegisterWrite">
			<div class="faqWrite" id="faqAccor">
				<input type="text" class="faqTitle" id="faqTitle" name="faqTitle"
				 placeholder = "제목을 입력하세요">
				<textarea class="panel" id="faqContent" name="faqContent"></textarea>
				<script>
				CKEDITOR.replace('faqContent',{height:'400px'});
				CKEDITOR.ENTER_BR;
				CKEDITOR.editorConfig = function( config ) {
	                  config.enterMode = CKEDITOR.ENTER_BR;
	        	};
				</script>
			</div>
		</div>
</form>
</div>


<%@ include file="../inc/bottom.jsp"%>