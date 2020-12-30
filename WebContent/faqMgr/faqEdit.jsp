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

.editBtn{
	float: right;
	margin-bottom: 10px;
}


#faqEditWrite {
	clear: both;
}

/* Style the buttons that are used to open and close the faqTitle panel */
.faqTitle {
	/* background-color: white;  */
	/* 	color: #444;  */
	cursor: pointer;
	padding: 18px;
	width: 100%;
	text-align: left;
	border: 1PX SOLID SILVER;
	border-collapse: none;
	outline: none;
	transition: 0.4s;
}

/* Style the faqTitle panel. Note: hidden by default */
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

.faqList {
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
	color: #FF2F6E;
}


</style>
<%
	FaqVO faqVo= (FaqVO)request.getAttribute("faqVo");
	String faqNo=request.getParameter("faqNo");
%>
<div class="faq">

	<!-- 자주 묻는 질문 -->
	<H4>FAQ 수정 / 삭제</H4>
	<form name="faqEidtForm" method="post">
	<!-- 수정, 삭제 버튼 -->
	<div class="editBtn">
		<input class="btn btn-outline-secondary" id="faqEditBtn" type="submit" value="수정"
	onclick="javascript:form.action='<%=request.getContextPath() %>/faqMgr/faqEdit_ok.do?faqNo=<%=faqNo%>';">
		<input class="btn btn-outline-secondary" id="faqDelBtn" type="submit" value="삭제"
	onclick="javascript:form.action='<%=request.getContextPath() %>/faqMgr/faqDelete.do?faqNo=<%=faqNo%>';">
		<input class="btn btn-outline-secondary" id="faqListBtn" type="submit" value="목록"
	onclick="javascript:form.action='<%=request.getContextPath() %>/faqMgr/faqList.do';">
	</div>
	<br>

		<div class="tab-pane show active" id="faqEditWrite">
			<div class="faqList" id="faqAccor">
				<input type="text" class="faqTitle" id="faqTitle" name="faqTitle"
					value="<%=faqVo.getTitle()%>">
				<textarea class="panel" id="faqContent" 
				name="faqContent"><%=faqVo.getContent()%></textarea>
				<script>
				CKEDITOR.replace('faqContent',{height:'400px'});
				CKEDITOR.ENTER_BR;
				CKEDITOR.editorConfig = function( config ) {
	                  config.enterMode = CKEDITOR.ENTER_BR;
	        	};
				//CKEDITOR.fillEmptyBlocks = false;
				</script>
			</div>
		</div>
</form>
</div>
<!-- script -->
<script type="text/javascript">
	$(function() {
		$(function(){
			$('#faqDelBtn').click(function(){
				if(!confirm('삭제하시겠습니까?')){
					event.preventDefault();
				}
			});
		});
	});
</script>

<%@ include file="../inc/bottom.jsp"%>