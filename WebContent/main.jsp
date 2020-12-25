<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="inc/top.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<article id="top5" class="movie">
	<div class="mainTitle">영화 순위</div>
	<div class="movieInfo">
		<ul>
			<li>
				<c:set var="boxOfficeFirst" value="${boxOfficeList[0]}"/>
				<a href="<c:url value='/movie/movieDetail.do?mvNo=${boxOfficeFirst.mvNo}'/>">
					<img src="<%=request.getContextPath()%>/images/movieSample.jpg" alt="moviePoster" style="width:15%;">
					<div class="info">
					<div>${boxOfficeFirst.mvTitle}</div>
					<div>${boxOfficeFirst.nation}</div>
					<div>${boxOfficeFirst.makeYear}</div>
					</div>
				</a>
			</li>
			<li>
				<a href="#">
					<img src="<%=request.getContextPath()%>/images/movieSample.jpg" alt="moviePoster" style="width:15%;">
					<div class="info">
					<div>해리포터</div>
					<div>미국</div>
					<div>해리포터</div>
					</div>
				</a>
			</li>
			<li>
				<a href="#">
					<img src="<%=request.getContextPath()%>/images/movieSample.jpg" alt="moviePoster" style="width:15%;">
					<div class="info">
					<div>해리포터</div>
					<div>미국</div>
					<div>해리포터</div>
					</div>
				</a>
			</li>
			<li>
				<a href="#">
					<img src="<%=request.getContextPath()%>/images/movieSample.jpg" alt="moviePoster" style="width:15%;">
					<div class="info">
					<div>해리포터</div>
					<div>미국</div>
					<div>해리포터</div>
					</div>
				</a>
			</li>
		</ul>
	</div>
</article>


main입니다.main입니다.main입니다.main입니다.main입니다.main입니다.main입니다.main입니다.main입니다.main입니다.main입니다.main입니다.main입니다.main입니다.main입니다.main입니다.main입니다.main입니다.main입니다.main입니다.main입니다.main입니다.
<pre>






































----------마지막 테스트
</pre>
<%@ include file="inc/bottom.jsp" %>