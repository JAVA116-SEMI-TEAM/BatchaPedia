<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.batcha.memInfo.model.MemInfoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../inc/top.jsp"%>

<script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor/ckeditor.js"></script>

<script type="text/javascript">
	$(function(){
		$('#ntTitle').focus();
		
		$('form[name=ntcfmt]').submit(function(){
			if($('#ntTitle').val().length<1){
				alert('제목을 입력하세요');
				$('#ntTitle').focus();
				event.preventDefault();
			}
		});
	});
</script>

<style type="text/css">
html, body, .grid-container {
	height: 100%;
	margin: 0;
	padding: 0;
}


/* For presentation only, no need to copy the code below */

.row.nt-title {
    float: left;
    
}

.contant-warp {
	margin: 0 auto;
	width: 800px;
	height: 800px;
}

.nt-titleWarp {
    /* margin: 52px 0 16px 0; */
    padding: 50px 0 27px 0;
    width: 563px;
}

.divForm {
    clear: both;
    margin-top: 30px;
    width: 563px;
}

.goHrefOutter {
    margin: 15px auto 0;
    float: right;
}

.row.nt-title {
    float: left;
}
</style>
	<div class="contant-warp">
		<form class="form-compact" name="ntcfmt"
			action="<%=request.getContextPath()%>/notice/noticeWriteOk.do"
			method="post">
			<div class="nt-titleWarp">
				<div class="row nt-title">
					<h2 class="text-center col-12 mb-0">공지사항 등록</h2>
				</div>
				<div class="goHrefOutter">
					<input type="submit" value="등록">
					<input type="button" value="목록" onclick="location.href='<%=request.getContextPath()%>/notice/noticemain.do'">
				</div>
			</div>
			
			<div class="divForm">
				<input type="hidden" name="author" value="관리자">

				<div class="row align-items-center">
					<div class="col-12">
						<div class="input-group">
							<input type="text" id="ntTitle" name="ntTitle"
								class="form-control form-control-sm extendable">
						</div>
					</div>
				</div>

				<div class="row align-items-center">
					<div class="col-12">
						<div class="input-group ntiWrite">
							<textarea id="content" name="content" placeholder=" 내용을 입력하세요" ></textarea>
							<script type="text/javascript">
							 CKEDITOR.replace('content', { height: 300});
							</script>
						</div>
					</div>
				</div>
			</div>
			
		</form>
	</div>

<%@ include file="../inc/bottom.jsp" %>