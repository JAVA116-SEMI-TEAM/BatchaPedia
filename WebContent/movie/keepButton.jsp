<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- todo 버튼 선택 시 킵리스트에 넣어주기 데이터 연동 -->
<%
	boolean keptCheck=(boolean)request.getAttribute("keptCheck");
	String keepBtnValue=request.getParameter("keepBtnValue");
%>
			<div class="keepBtns">
				<form action="" method="post" name="keepBtnForm">
					<input type="submit" name="keepBtn" 
value="${keepBtnValue}" class="btn btn-outline-primary">
				</form>
			</div>
			<script type="text/javascript">
				//로그인 안했을 경우 얼럿 띄워줘야 함
				// alert('로그인 하셔야 합니다.');
			</script>
			
		