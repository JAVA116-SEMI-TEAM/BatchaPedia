<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<link rel="stylesheet" href="../css/movieDetail.css">
<%

%>

<section class="movieDetail-info">
	<div class="movieDetail-info__thumbnail">
		<!-- 썸네일-->
		<img src="../images/thumb/awakenings.jpg" class="thumbnail__movieDetail thumbnail">
	</div>
	<div class="movieDetail-info__contents">
		<div class="movieDetail-info__header">
			<div>
				<h2>${mvVo.mvTitle}</h2>
			</div>
			<div>
				<h4>Awakenings</h4>
			</div>
			<div>
				<span>${mvVo.makeYear} | ${mvVo.nation} | ${mvVo.genre}</span>
			</div>
		</div>
		<div class="movieDetail-info__contents movieDetail-info__buttons">
			<!-- 평점매기기, 찜하기 -->
			<div class="stars">
			<!-- 마우스오버 효과를 js로 만들어주고 클릭했을 때 어떤 요소가 어떤 상태인지에 따라 평점 입력 
				첫번째 별의 앞 절반에 오버한경우 1점, 앞 절반을 넘어간 경우 2점
				두번째 별의 앞 절반에 오버한경우 3점, 앞 절반을 넘어간 경우 4점
				세번째 별의 앞 절반에 오버한경우 5점, 앞 절반을 넘어간 경우 6점
				네번째 별의 앞 절반에 오버한경우 7점, 앞 절반을 넘어간 경우 8점
				다섯번째 별의 앞 절반에 오버한 경우 9점, 넘어간 경우 10점
			-->
				<div><i class="fas fa-star fa-2x star star-mono"></i></div>
				<div><i class="fas fa-star fa-2x star star-mono"></i></div>
				<div><i class="fas fa-star fa-2x star star-mono"></i></div>
				<div><i class="fas fa-star fa-2x star star-mono"></i></div>
				<div><i class="fas fa-star-half-alt fa-2x star star-mono"></i></div>
			</div>
			<div>
				<button class="btn btn-sm btn-default"><i class="fas fa-plus-circle"></i> 나중에 볼 영화</button>
				<button class="btn btn-sm btn-default"><i class="fas fa-minus-circle"></i> 볼 영화에서 빼기</button>
			</div>
		</div>
	</div>
</section>
<section class="movieDetail-starsGraph">
	<div class="movieDetail-starsGraph-header">
		<h3>평점분포</h3>
	</div>
	<div class="movieDetail-starsGraph-graph">
		<div class="progress progressdiv" >
			<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="10" style="width: 100px;"></div>
		</div>
		<div class="progress progressdiv">
			<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="10" style="width: 100px;"></div>
		</div>
		<div class="progress progressdiv">
			<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="1" aria-valuemin="0" aria-valuemax="10" style="width: 100px;"></div>
		</div>
		<div class="progress progressdiv">
			<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="10" aria-valuemin="0" aria-valuemax="50" style="width: 100px;"></div>
		</div>
		<div class="progress progressdiv">
			<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="10" aria-valuemin="0" aria-valuemax="50" style="width: 100px;"></div>
		</div>
		<div class="progress progressdiv" >
			<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="10" aria-valuemin="0" aria-valuemax="50" style="width: 100px;"></div>
		</div>
		<div class="progress progressdiv">
			<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="10" aria-valuemin="0" aria-valuemax="50" style="width: 100px;"></div>
		</div>
		<div class="progress progressdiv">
			<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="10" aria-valuemin="0" aria-valuemax="50" style="width: 100px;"></div>
		</div>
		<div class="progress progressdiv">
			<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="10" aria-valuemin="0" aria-valuemax="50" style="width: 100px;"></div>
		</div>
		<div class="progress progressdiv">
			<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="10" aria-valuemin="0" aria-valuemax="50" style="width: 100px;"></div>
		</div>
	</div>
</section>
<section class="movieDeatil-comment">
	<div class="movieDetail-comment__header">코멘트 영역</div>
	<div class="movieDetail-comment__list"></div>
</section>
<section class="movieDetail-multimedia">
	<div class="movieDetail-contents__images">
	이미지들을 쓸 수 있다면 넣기
	</div>
	<div class="movieDetail-contents__videos">
	동영상을 쓸 수 있다면 넣기
	</div>
</section>

<%@ include file="../inc/bottom.jsp" %>