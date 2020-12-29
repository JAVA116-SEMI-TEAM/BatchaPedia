<%@page import="com.batcha.faq.model.FaqVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
	integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
	crossorigin="anonymous"></script>
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

.faqList {
	margin-bottom: 100px;
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
}
</style>

<%
	List<FaqVO> faqList = (List<FaqVO>) request.getAttribute("faqList");
%>
<div class="faq">

	<!-- 자주 묻는 질문 -->
	<H4>FAQ 자주 묻는 질문</H4>

	<!-- 탭 메뉴 -->
	<div class="tabMenu">
		<ul class="nav nav-tabs">
			<li class="nav-item itemli"><a class="nav-link active"
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

	<!-- 자주 묻는 질문 -->

	<div class="tab-content">
		<!-- 수정 -->
		<div class="tab-pane show fade active" id="all">
			<div class="faqList" id="faqAccor">
				<%
					for (int i = 0; i < faqList.size(); i++) {
					FaqVO faqVo = faqList.get(i);
				%>
				<button class="accordion color btn-secondary" id="faqTitle">
					<%=faqVo.getTitle()%></button>
				<textarea class="panel"><%=faqVo.getContent()%></textarea>
				<%
					}
				%>
			</div>
		</div>
		<!--  -->
		<div class="tab-pane fade" id="accountMg">
			<div class="faqList" id="faqAccor">
				<%
					for (int i = 0; i < 5; i++) {
					FaqVO faqVo = faqList.get(i);
				%>
				<button class="accordion color btn-secondary" id="faqTitle">
					<%=faqVo.getTitle()%></button>
				<textarea class="panel"><%=faqVo.getContent()%></textarea>
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
				<textarea class="panel"><%=faqVo.getContent()%></textarea>
				</div>
				<%
					}
				%>
			</div>
		</div>

		<!-- 삭제 -->
		<div class="tab-pane fade" id="use">
			<div class="faqList" id="faqAccor">
				<%
					for (int i = 0; i < 8; i++) {
					FaqVO faqVo = faqList.get(i);
				%>
				<button class="accordion color btn-secondary" id="faqTitle">
					<%=faqVo.getTitle()%></button>
				<textarea class="panel"><%=faqVo.getContent()%></textarea>
				<%
					}
				%>
			</div>
		</div>
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
				//클릭이벤트를 추가한다. /* Toggle between adding and removing the "active" class, to highlight the button that controls the panel */ 
				//this.classList.toggle("active"); //클래스를 추가하거나 삭제함. 
				this.classList.toggle("active"); //클래스를 추가하거나 삭제함. 
				/* Toggle between hiding and showing the active panel */
				var panel = this.nextElementSibling;
				//현재 아코디언의 다음노트를 가져온다. panel이 옴 
				if (panel.style.display == "block") {
					//출력모드가 블럭인지 none인지 체크한다.
					panel.style.display = "none";
				} else {
					panel.style.display = "block";
				}
			});

		}//for

		/* 탭 메뉴 클릭시 색 변경 */
		var itemli = $('.itemli');
		itemli.click(function() {
			$(this).find('a').css("color", "#FF2F6E");
			itemli.not($(this)).find('a').css("color", "gray");

		});
	
	});
</script>

<%@ include file="../inc/bottom.jsp"%>