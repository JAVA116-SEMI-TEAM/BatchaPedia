<%@page import="com.batcha.faq.model.FaqVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../inc/top.jsp"%>
<link rel="stylesheet" href="../css/faq.css">
<style type="text/css">
h4 {
	float: left;
}

.tabMenu {
	float: right;
	margin-bottom: 10px;
}

.tab-content {
	clear: both;
}

/* Style the buttons that are used to open and close the accordion panel */
.accordion {
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

/* Style the accordion panel. Note: hidden by default */
.panel {
	padding: 0 18px;
	background-color: white;
	display: none;
	overflow: hidden;
	width:100%;
	height: 300px;
	border: 1px solid silver;
	overflow-y: scroll;
	-ms-overflow-style: none;
}

.panel::-webkit-scrollbar {
	display: none;
}

#faqWriteBtn {
	margin: 10px 0 100px 0;
	float:right;
}

footer{
	clear:both;
}

#faqTitle {
	color: white;
}

#faqTitle:focus {
	border-color: rgba(153, 153, 153, 0.8);
	box-shadow: 0 1px 1px rgba(153, 153, 153, 0.075) inset, 0 0 8px
		rgba(153, 153, 153, 0.6);
	outline: 0 none;
	color: #FF2F6E;
}

pre {
	white-space: pre-wrap;
	word-break: keep-all;
}
</style>

<%
	List<FaqVO> faqList = (List<FaqVO>)request.getAttribute("faqList");
%>
<div class="faq">

	<!-- 자주 묻는 질문 -->
	<H4>FAQ 자주 묻는 질문</H4>
	<!-- 탭 메뉴 -->
	<div class="tabMenu">
		<ul class="nav nav-tabs" >
			<li class="nav-item itemli" id="firstTab"><a class="nav-link active" 
				style="color: gray;" data-toggle="tab" href="#all">전체</a></li>
			<li class="nav-item itemli"><a class="nav-link"
				style="color: gray;" data-toggle="tab" href="#accountMg">계정관리</a></li>
			<li class="nav-item itemli"><a class="nav-link"
				style="color: gray;" data-toggle="tab" href="#personalInfo">개인정보</a></li>
			<li class="nav-item itemli"><a class="nav-link"
				style="color: gray;" data-toggle="tab" href="#use">이용방법</a></li>
		</ul>
	</div>
	<br>

	<div class="tab-content">
	<!-- 자주 묻는 질문 -->

	<!-- 전체 -->
	<div class="tab-pane show fade active" id="all">
		<input type="hidden" name="all" value="all">
		<div class="faqList" id="faqAccor">
				<%
				for(int i=0;i<faqList.size();i++) {
				FaqVO faqVo = faqList.get(i);
				%>
				<button class="accordion color btn-secondary" id="faqTitle" 
				data-toggle="tooltip" data-placement="bottom" 
				title="수정하려면 더블클릭하세요."
ondblclick="location.href='<%=request.getContextPath() %>/faqMgr/faqEdit.do?faqNo=<%=faqVo.getFaqNo()%>'">
					<%=faqVo.getTitle()%></button>
				<div class="panel">
					<pre><%=faqVo.getContent()%></pre>
				</div>
				<%}%>
			</div>
		</div>
		
	<!-- 계정관리 -->
	<div class="tab-pane fade" id="accountMg">
		<div class="faqList" id="faqAccor">
			<%
				for (int i = 0; i < 5; i++) {
				FaqVO faqVo = faqList.get(i);
			%>
			<button class="accordion color btn-secondary" id="faqTitle">
				<%=faqVo.getTitle()%></button>
			<div class="panel">
				<pre><%=faqVo.getContent()%></pre>
			</div>
			<%
				}
			%>
		</div>
	</div>
		
	<!-- 개인정보 -->
	<div class="tab-pane fade" id="personalInfo">
		<div class="faqList" id="faqAccor">
			<%
				for (int i = 0; i < 3; i++) {
				FaqVO faqVo = faqList.get(i);
			%>
			<button class="accordion color btn-secondary" id="faqTitle">
				<%=faqVo.getTitle()%></button>
			<div class="panel">
				<pre><%=faqVo.getContent()%></pre>
			</div>
			<%
				}
			%>
		</div>
	</div>
		
	<!-- 이용방법 -->
	<div class="tab-pane fade" id="use">
		<div class="faqList" id="faqAccor">
			<%
				for (int i = 0; i < 8; i++) {
				FaqVO faqVo = faqList.get(i);
			%>
			<button class="accordion color btn-secondary" id="faqTitle">
				<%=faqVo.getTitle()%></button>
			<div class="panel">
				<pre><%=faqVo.getContent()%></pre>
			</div>
			<%
				}
			%>
		</div>
	</div>
	
	<input class="btn btn-outline-secondary" id="faqWriteBtn" type="submit" value="새 글 등록"
	onclick="location.href='<%=request.getContextPath() %>/faqMgr/faqWrite.do';">
</div>
</div>
<!-- script -->
<script type="text/javascript">
	$(function() {
		
		//	var acc = document.getElementsByClassName("accordion"); //아코디언클래스리스트를 가져온다.
		var acc = $(".accordion"); //아코디언클래스리스트를 가져온다. 
		var i;
		for (i = 0; i < acc.length; i++) {
			acc[i].addEventListener("click", function() {
				this.classList.toggle("active"); //클래스를 추가하거나 삭제함. 
				var panel = this.nextElementSibling;
				if (panel.style.display == "block") {
					panel.style.display = "none";
				} else {
					panel.style.display = "block";
				}
			});

		}//for

		/* 탭 메뉴 클릭시 색 변경 */
		$('#firstTab').find('a').css("color", "#FF2F6E");
		var itemli = $('.itemli');
		itemli.click(function() {
			$(this).find('a').css("color", "#FF2F6E");
			itemli.not($(this)).find('a').css("color", "gray");

		});
		
		$('[data-toggle="tooltip"]').tooltip();
		
	
	});
</script>

<%@ include file="../inc/bottom.jsp"%>