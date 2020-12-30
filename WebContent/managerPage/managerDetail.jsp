<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.batcha.memInfo.model.MemInfoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../inc/top.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style type="text/css">
html, body, .grid-container {
	height: 100%;
	margin: 0;
}

.grid-container {
	display: grid;
	grid-template-columns: 0.8fr 1.2fr 1.2fr 0.8fr;
	grid-template-rows: 0.6fr 1.8fr 0.6fr;
	gap: 0px 0px;
	grid-template-areas: ". . . ." ". detail_form detail_form ." ". . . .";
}

.detail_form {
	grid-area: detail_form;
	border: 1px solid;
}

/* For presentation only, no need to copy the code below */
.grid-container * {
	border: 1px solid red;
	position: relative;
}

.grid-container *:after {
	content: attr(class);
	position: absolute;
	top: 0;
	left: 0;
}

.contant-warp {
	margin: 0 auto;
	width: 500px;
	height: 600px;
}

.divForm {
	padding: 0 41px 0 0;
}

.goHrefOutter {
    margin: 0 auto;
    padding-left: 255px;
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
	MemInfoVO memVo=(MemInfoVO)request.getAttribute("memVo");
	String memNo=request.getParameter("no");
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<body>

	<div class="contant-warp">
		<form class="form-compact" action="<%=request.getContextPath()%>/managerPage/managerEdit_ok.do?memNo=<%=memNo%>" method="post">
			<div class="row">
				<h2 class="text-center col-12 mb-0">회원 상세 관리</h2>
			</div>
			<div class="dropdown-divider mb-3"></div>
			
			<div class="divForm">
				<div class="form-group row align-items-center">
					<label for="memNo"
						class="col-3 col-form-label-sm text-right">회원번호:</label>
					<div class="col-9">
						<div class="input-group">
<%-- 							<span><%=memNo %></span> --%>
							<input type="text" id="memNo" name="memNo" value="<%=memNo %>"
								class="form-control form-control-sm extendable" readonly>
						</div>
					</div>
				</div>
				
				<div class="form-group row align-items-center">
					<label for="memId"
						class="col-3 col-form-label-sm text-right">아이디:</label>
					<div class="col-9">
						<div class="input-group">
<%-- 							<span><%=memVo.getId() %></span> --%>
							<input type="text" id="memId" name="memId" value="<%=memVo.getId()%>"
								class="form-control form-control-sm extendable" readonly>
						</div>
					</div>
				</div>
				
				<div class="form-group row align-items-center">
					<label for="memPwd"
						class="col-3 col-form-label-sm text-right">비밀번호:</label>
					<div class="col-9">
						<div class="input-group">
							<input type="text" id="memPwd" name="memPwd" value="<%=memVo.getPwd() %>"
								class="form-control form-control-sm extendable">
						</div>
					</div>
				</div>
				
				<div class="form-group row align-items-center">
					<label for="memEmail"
						class="col-3 col-form-label-sm text-right">이름:</label>
					<div class="col-9">
						<div class="input-group">
							<input type="text" id="memName" name="memName" value="<%=memVo.getName() %>"
								class="form-control form-control-sm extendable"readonly>
						</div>
					</div>
				</div>
				
				<div class="form-group row align-items-center">
					<label for="memEmail"
						class="col-3 col-form-label-sm text-right">이메일주소:</label>
					<div class="col-9">
						<div class="input-group">
							<input type="text" id="memEmail" name="memEmail" value="<%=memVo.getEmail() %>"
								class="form-control form-control-sm extendable">
						</div>
					</div>
				</div>
				
				<div class="form-group row align-items-center">
					<label for="memTel"
						class="col-3 col-form-label-sm text-right">휴대폰번호:</label>
					<div class="col-9">
						<div class="input-group">
							<input type="text" id="memTel" name="memTel" value="<%=memVo.getMobile() %>"
								class="form-control form-control-sm extendable">
						</div>
					</div>
				</div>
				
				<div class="form-group row align-items-center">
					<label for="memBirth"
						class="col-3 col-form-label-sm text-right">생년월일:</label>
					<div class="col-9">
						<div class="input-group">
<%-- 							<span><%=memVo.getBirthday() %></span> --%>
							<input type="text" id="memBirth" name="memBirth" value="<%=memVo.getBirthday() %>"
								class="form-control form-control-sm extendable"readonly>
						</div>
					</div>
				</div>
				
				<div class="form-group row align-items-center">
					<label for="memRegdate"
						class="col-3 col-form-label-sm text-right">가입일:</label>
					<div class="col-9">
						<div class="input-group">
<%-- 							<span><%=sdf.format(memVo.getRegdate()) %></span> --%>
							<input type="text" id="memRegdate" name="memRegdate" value="<%=sdf.format(memVo.getRegdate()) %>"
								class="form-control form-control-sm extendable" readonly>
						</div>
					</div>
				</div>
				
				<div class="form-group row align-items-center">
					<label for="memOutdate"
						class="col-3 col-form-label-sm text-right">탈퇴일:</label>
					<div class="col-9">
						<div class="input-group">
<%-- 							<span><%if(memVo.getOutdate()!=null){%> --%>
<%-- 							<%=sdf.format(memVo.getOutdate()) %> <%}%></span> --%>
							
							<input type="text" id="memOutdate" name="memOutdate" 
							value="<%if(memVo.getOutdate()!=null){%>
							<%=sdf.format(memVo.getOutdate()) %> <%}%>"
								class="form-control form-control-sm extendable" readonly>
						</div>
					</div>
				</div>

			</div>
		<div class="goHrefOutter">
	<%--<a href='<%=request.getContextPath() %>/edit.jsp?no='>수정</a> |  --%>
			<input type="submit" value="수정"> 
			<input type="button" value="삭제" name="btdel"
				onclick="location.href='<%=request.getContextPath()%>/managerPage/managerDelete_ok.do?memNo=<%=memNo%>'"> 
			<input type="button" value="매니저페이지" onclick="location.href='<%=request.getContextPath()%>/managerPage/manager.do'">

		</div>
		</form>


	</div>

</body>
</html>
