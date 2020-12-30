<%@page import="com.batcha.mycmt.model.MyCmtVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor/ckeditor.js"></script>
<style type="text/css">

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

<%
	MyCmtVO mcVo = (MyCmtVO)request.getAttribute("mcVo");
%>

<div class="contant-warp">
<form class="form-compact" name="ntcfmt"
			action="<%=request.getContextPath()%>/myPage/mycmtEditOk.do?cmtNo=<%=mcVo.getCmtNo() %>"
			method="post">
	<div class="row ntTitle">
		<h2 class="text-center col-12 mb-0" style="color: #343a40">코멘트수정</h2>
	</div>

	<div class="divForm">
		<hr>
		<div class="row align-items-center nt-con">
			<label class="col-3 col-form-label-sm text-right">번호:</label>
			<div class="col-9">
				<span><%=mcVo.getCmtNo() %></span>
			</div>
		</div>

		<div class="row align-items-center nt-con">
			<label class="col-3 col-form-label-sm text-right">제목:</label>
			<div class="col-9">
				<span><%=mcVo.getMvTitle() %></span>
			</div>
		</div>


		<div class="row align-items-center nt-con nt-context">
			<textarea id="cmtText" name="cmtText" >
								<%=mcVo.getCmtText() %></textarea>
			<script type="text/javascript">
				 CKEDITOR.replace('cmtText', { height: 300});
			</script>
		</div>

	</div>
	<div class="goHrefOutter">
		<input type="submit" value="확인" >
		<input type="button" value="목록"
			onclick="location.href='<%=request.getContextPath()%>/myPage/mycmt.do'">
	</div>


</form>
</div>

<%@ include file="../inc/bottom.jsp" %>