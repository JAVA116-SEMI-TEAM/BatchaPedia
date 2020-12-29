<%@page import="com.batcha.keepData.model.keepDataService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css"> -->
<link rel="stylesheet" href="../css/movieDetail.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
	<c:if test="${!t_isLogin}">
		<script>
		//로그인 안 한 상태에서 평점입력 버튼 누르면 로그인하세요 얼럿
			$(function(){
				$('#submitStar').click(function(){
					console.log('평점입력 버튼 누름');
					alert('로그인해주세요.');
					event.preventDefault();
					return false;
				});
				
				$('#keepBtn')click(function(){
					alert('로그인해주세요.');
					event.preventDefault();
					return false;
				});
				
		//로그인 안 한 상태로 코멘트입력 버튼 누르면 로그인하세요 얼럿
				$('#cmtBtn').click(function(){
					alert('로그인해주세요.');
					event.preventDefault();
					return false;
				});
			});
		</script>
	</c:if>
<div class="MovieDetailWrapper">
	<section class="movieDetail-info">
		<div class="movieDetail-info__thumbnail">
		<!-- 썸네일-->
		<img src="${mvVo.thumbnail}" class="thumbnail__movieDetail">
		</div>
		<div class="movieDetail-info__contents">
			<div class="movieDetail-info__header">
				<div>
					<h2>${mvVo.mvTitle}</h2>
					<h5>${mvVo.mvTitleEn}</h5>
				</div>
				<div>
					<span>${mvVo.makeYear}　|　 ${mvVo.nation}　|　 ${mvVo.genre}</span>
				</div>
			</div>
			<div class="movieDetail-info__btns">
				<div class="movieDetail-info__stars">
					<!-- 평점매기기 기능 -->	
					<%@ include file="starChoose.jsp" %>
				</div>
				<div class="movieDetail-info__keep">
					<!-- 찜하기 기능 기능 -->			
					<%@ include file="keepButton.jsp" %>
				</div>
			</div>
		<hr class="movieDetail-hrLine">
			<div class="movieDateil-info__story">
				<span>${mvVo.story}</span>
			</div>
	</section>

<!-- 코멘트 작성 포맷 출력 -->
<%@ include file="cmtWrite.jsp" %>

<!-- 평점 그래프 출력 -->
<%@ include file="starsGraph.jsp" %>

<!-- 코멘트 리스트 출력 -->
<%@ include file="cmtList.jsp" %>

</div>
<%@ include file="../inc/bottom.jsp" %>