<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="inc/top.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section class="movieInfo boxOffice-Wrapper" style="margin: 100px;">
	<h3>박스오피스 Top 5</h3>
	<div class="movieInfo-boxOffice" 
		style="display:flex; justify-content: space-between;">
		<div class="movieInfo-boxOffice__first" style="padding-right: 20px;">
			<c:set var="boxOfficeFirst" value="${boxOfficeList[0]}"/>
			<a href="<c:url value='/movie/movieDetail.do?mvNo=${boxOfficeFirst.mvNo}'/>"
				style="text-decoration: none">
				<img src="${boxOfficeFirst.thumbnail}" alt="moviePoster"
					style="width: 100%">
				<div class="info" >
				<div>${boxOfficeFirst.mvTitle}</div>
				<div>${boxOfficeFirst.nation}</div>
				<div>${boxOfficeFirst.makeYear}</div>
				</div>
			</a>
		</div>
		<div class="movieInfo-boxOffice__second" style="padding-right: 20px;">
			<c:set var="boxOfficeSecond" value="${boxOfficeList[1]}"/>
			<a href="<c:url value='/movie/movieDetail.do?mvNo=${boxOfficeSecond.mvNo}'/>"
				style="text-decoration: none">
				<img src="${boxOfficeSecond.thumbnail}" alt="moviePoster"
					style="width: 100%">
				<div class="info" >
				<div>${boxOfficeSecond.mvTitle}</div>
				<div>${boxOfficeSecond.nation}</div>
				<div>${boxOfficeSecond.makeYear}</div>
				</div>
			</a>
		</div>
		<div class="movieInfo-boxOffice__third" style="padding-right: 20px;">
			<c:set var="boxOfficeThird" value="${boxOfficeList[2]}"/>
			<a href="<c:url value='/movie/movieDetail.do?mvNo=${boxOfficeThird.mvNo}'/>"
				style="text-decoration: none">
				<img src="${boxOfficeThird.thumbnail } alt="moviePoster"
					style="width: 100%">
				<div class="info" >
				<div>${boxOfficeThird.mvTitle}</div>
				<div>${boxOfficeThird.nation}</div>
				<div>${boxOfficeThird.makeYear}</div>
				</div>
			</a>
		</div>
		<div class="movieInfo-boxOffice__fourth" style="padding-right: 20px;">
			<c:set var="boxOfficeFourth" value="${boxOfficeList[3]}"/>
			<a href="<c:url value='/movie/movieDetail.do?mvNo=${boxOfficeFourth.mvNo}'/>"
				style="text-decoration: none; padding-right:10px;">
				<img src="${boxOfficeFourth.thumbnail }" alt="moviePoster"
					style="width: 100%">
				<div class="info" >
				<div>${boxOfficeFourth.mvTitle}</div>
				<div>${boxOfficeFourth.nation}</div>
				<div>${boxOfficeFourth.makeYear}</div>
				</div>
			</a>
		</div>
		<div class="movieInfo-boxOffice__first" style="padding-right: 10px;">
			<c:set var="boxOfficeFifth" value="${boxOfficeList[4]}"/>
			<a href="<c:url value='/movie/movieDetail.do?mvNo=${boxOfficeFifth.mvNo}'/>"
				style="text-decoration: none">
				<img src="${boxOfficeFifth.thumbnail }" alt="moviePoster"
					style="width: 100%">
				<div class="info">
				<div>${boxOfficeFifth.mvTitle}</div>
				<div>${boxOfficeFifth.nation}</div>
				<div>${boxOfficeFifth.makeYear}</div>
				</div>
			</a>
		</div>
	</div>
</section>

<%@ include file="inc/bottom.jsp" %>