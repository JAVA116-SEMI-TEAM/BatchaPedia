<%@page import="com.batcha.keepData.model.keepDataService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="../css/movieDetail.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
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
				</div>
				<div>
					<span>${mvVo.makeYear}　|　 ${mvVo.nation}　|　 ${mvVo.genre}</span>
				</div>
			</div>
			<div class="movieDetail-info__buttons">
				<!-- 평점매기기 기능 -->	
				<%@ include file="starChoose.jsp" %>
				
				<!-- 찜하기 기능 기능 -->			
				<%@ include file="keepButton.jsp" %>
			
		<hr class="movieDetail-hrLine">
		<div class="movieDateil-info__story">
		<span>${mvVo.story}</span>
		</div>
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