<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="inc/top.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="main">
<h4 class="mainTitle">박스오피스 Top 5</h4>
<div class="mainMovie">
	<c:forEach var="i" begin="0" end="4">
		<div class="boxOffice mv">
			<c:set var="boxOffice" value="${boxOfficeList[i]}" />
			<a
				href="<c:url value='/movie/movieDetail.do?mvNo=${boxOffice.mvNo}'/>">
				<img src="${boxOffice.thumbnail }" alt="moviePoster">
				<div class="info">
					<div class="mvtitle">${boxOffice.mvTitle}</div>
					<span class="mvinfo">${boxOffice.makeYear} · </span> 
					<span class="mvinfo">${boxOffice.nation}</span>
					<div class="mvinfo">${boxOffice.genre }</div>
				</div>
			</a>
		</div>
	</c:forEach>
</div>
<br><br>
<h4 class="mainTitle">흥미진진, #범죄 #스릴러</h4>
<div class="mainMovie">
	<c:forEach var="i" begin="0" end="4">
		<div class="thrillMovies mv">
			<c:set var="thrillMovie" value="${ThrillMovieList[i]}" />
			<a
				href="<c:url value='/movie/movieDetail.do?mvNo=${thrillMovie.mvNo}'/>">
				<img src="${thrillMovie.thumbnail }" alt="moviePoster">
				<div class="info">
					<div class="mvtitle">${thrillMovie.mvTitle}</div>
					<span class="mvinfo">${thrillMovie.makeYear} · </span> 
					<span class="mvinfo">${thrillMovie.nation}</span>
					<div class="mvinfo">${thrillMovie.genre }</div>
				</div>
			</a>
		</div>
	</c:forEach>
</div>
<br><br>
<h4 class="mainTitle">그 시절 그 영화, #20세기 명작</h4>
<div class="mainMovie">
	<c:forEach var="i" begin="0" end="4">
		<div class="movie20th mv">
			<c:set var="movie20th" value="${movie20thList[i]}" />
			<a
				href="<c:url value='/movie/movieDetail.do?mvNo=${movie20th.mvNo}'/>">
				<img src="${movie20th.thumbnail }" alt="moviePoster">
				<div class="info">
					<div class="mvtitle">${movie20th.mvTitle}</div>
					<span class="mvinfo">${movie20th.makeYear} · </span> 
					<span class="mvinfo">${movie20th.nation}</span>
					<div class="mvinfo">${movie20th.genre }</div>
				</div>
			</a>
		</div>
	</c:forEach>
</div>
<br><br>
<h4 class="mainTitle">한국 영화 매니아를 위한!</h4>
<div class="mainMovie">
	<c:forEach var="i" begin="0" end="4">
		<div class="korMovie mv">
			<c:set var="korMovie" value="${korMovieList[i]}" />
			<a
				href="<c:url value='/movie/movieDetail.do?mvNo=${korMovie.mvNo}'/>">
				<img src="${korMovie.thumbnail }" alt="moviePoster">
				<div class="info">
					<div class="mvtitle">${korMovie.mvTitle}</div>
					<span class="mvinfo">${korMovie.makeYear} · </span> 
					<span class="mvinfo">${korMovie.nation}</span>
					<div class="mvinfo">${korMovie.genre }</div>
				</div>
			</a>
		</div>
	</c:forEach>
</div>

<br><br>
<h4 class="mainTitle">시간 순삭! 킬링타임 영화</h4>
<div class="mainMovie">
	<c:forEach var="i" begin="0" end="4">
		<div class="comedyMovie mv">
			<c:set var="comedyMovie" value="${comedyMovieList[i]}" />
			<a
				href="<c:url value='/movie/movieDetail.do?mvNo=${comedyMovie.mvNo}'/>">
				<img src="${comedyMovie.thumbnail }" alt="moviePoster">
				<div class="info">
					<div class="mvtitle">${comedyMovie.mvTitle}</div>
					<span class="mvinfo">${comedyMovie.makeYear} · </span> 
					<span class="mvinfo">${comedyMovie.nation}</span>
					<div class="mvinfo">${comedyMovie.genre }</div>
				</div>
			</a>
		</div>
	</c:forEach>
</div>


</div>
<%@ include file="inc/bottom.jsp"%>
