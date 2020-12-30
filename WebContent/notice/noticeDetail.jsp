<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.batcha.mynotice.model.NoticeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../inc/top.jsp"%>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');

*{
	font-family: 'Noto Sans KR', sans-serif;
    color: #343a40;
}
.ntTitle {
    margin-bottom: -45px;
}

.detail_form {
	grid-area: detail_form;
	border: 1px solid;
}

.contant-warp {
	margin: 0 auto;
	width: 600px;
	height: 800px;
}

.divForm {
	padding: 0 41px 0 0;
}

.goHrefOutter {
	margin: 18px auto;
    padding-left: 384px;
}

.nt-con {
    margin: 5px 3px;
    border-bottom: solid 1px gainsboro;
    padding: 11px 0;
}

.nt-context {
    padding: 29px 0 100px 61px;
    word-break:break-all;
}

input[type="submit"], input[type="Button"] {
    background: #FCFCFC;
    border: 0.5px solid gray;
    width: auto;
    height: 35px;
}


</style>

<script type="text/javascript">
$(function(){
	$('input[name=btdel]').click(function(){
		confirm("삭제하시겠습니까?")
	});
});
</script>

<%
	NoticeVO ntVo=(NoticeVO)request.getAttribute("ntVo");
	int noticeNo = Integer.parseInt((String)request.getAttribute("noticeNo")); 
	

	SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
%>


<div class="contant-warp">
	<div class="row ntTitle">
		<h2 class="text-center col-12 mb-0" style="color: #343a40">공지사항</h2>
	</div>

	<div class="divForm">
		<hr>
		<div class="row align-items-center nt-con">
			<label class="col-3 col-form-label-sm text-right">번호:</label>
			<div class="col-9">
				<span><%=ntVo.getNoticeNo() %></span>
			</div>
		</div>

		<div class="row align-items-center nt-con">
			<label class="col-3 col-form-label-sm text-right">제목:</label>
			<div class="col-9">
				<span><%=ntVo.getTitle() %></span>
			</div>
		</div>

		<div class="row align-items-center nt-con">
			<label class="col-3 col-form-label-sm text-right">작성자:</label>
			<div class="col-9">
				<span>관리자</span>
			</div>
		</div>

		<div class="row align-items-center nt-con">
			<label class="col-3 col-form-label-sm text-right">작성일:</label>
			<div class="col-9">
				<span><%=sdf.format(ntVo.getRegdate())%></span>
			</div>
		</div>

		<div class="row align-items-center nt-con nt-context">
			<div>
				<div><%=ntVo.getContent()%> </div>
			</div>
		</div>

	</div>
	<div class="goHrefOutter">
		<input type="button" value="삭제" name="btdel"
			onclick="location.href='<%=request.getContextPath()%>/notice/noticeDeleteOK.do?noticeNo=<%=noticeNo%>'">
		<input type="button" value="수정" 
			onclick="location.href='<%=request.getContextPath()%>/notice/noticeEdit.do?noticeNo=<%=noticeNo%>'">
		<input type="button" value="목록"
			onclick="location.href='<%=request.getContextPath()%>/notice/noticemain.do'">
	</div>


</div>

<%@ include file="../inc/bottom.jsp" %>
