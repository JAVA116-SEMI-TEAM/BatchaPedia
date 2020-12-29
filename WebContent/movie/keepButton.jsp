<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- todo 버튼 선택 시 킵리스트에 넣어주기 데이터 연동 -->
<%
	boolean keptCheck=(boolean)request.getAttribute("keptCheck");
	String keepBtnValue=request.getParameter("keepBtnValue");
%>
<script>
var s_userid=<%=session.getAttribute("userid")%>

$(function(){
	$('form[name=keepBtnForm]').submit(function(){
		if($('s_userid').length<1){
			alert("로그인하세요.");
			event.preventDefault();
			return false;
		}
	});
});
</script>
			<div class="keepBtns">
				<form action="" method="post" name="keepBtnForm">
					<input type="submit" name="keepBtn" id="keepBtn"
value="${keepBtnValue}" class="btn btn-outline-primary">
		</form>
	</div>