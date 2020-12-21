<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../inc/top.jsp"%>

<div class="contant-warp">
	<h2>글 상세보기</h2>
	<div class="divForm">
		<div class="firstDiv">
			<span class="sp1">회원번호</span> <span></span>
		</div>
		<div>
			<span class="sp1">아이디</span> <span></span>
		</div>
		<div>
			<span class="sp1">비밀번호</span> <span></span>
		</div>
		<div>
			<span class="sp1">이메일주소</span> <span></span>
		</div>
		<div>
			<span class="sp1">휴대폰번호</span> <span></span>
		</div>
		<div>
			<span class="sp1">생년월일</span> <span></span>
		</div>
		<div>
			<span class="sp1">가입일</span> <span></span>
		</div>
		<div>
			<span class="sp1">탈퇴일</span> <span></span>
		</div>
	</div>
	
	<div class="center">
			<a href='edit.jsp?no='>수정</a> |
        	<a href='delete.jsp?no='>삭제</a> |
        	<a href='replay.jsp?no='>답변</a> |
        	<a href='list.jsp'>매니저페이지</a>			
	</div>

</div>