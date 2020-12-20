<%@page import="com.batcha.keepData.model.keepDataService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<link rel="stylesheet" href="../css/movieDetail.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4"></script>
<%
	int isKept=(int)request.getAttribute("isKept");
	float memStars=(float)request.getAttribute("memStars");
	int didStars=(int)request.getAttribute("didStars");
	float avgStars=(float)request.getAttribute("avgStars");
	
	boolean keptCheck=false;
	if(isKept==keepDataService.IS_KEPT){
		keptCheck=true;
	}
%>
<div class="bodyWrapper">
<section class="movieDetail-info">
	<div class="movieDetail-info__thumbnail">
		<!-- 썸네일-->
		<img src="../images/thumb/awakenings.jpg" class="thumbnail__movieDetail">
	</div>
	<div class="movieDetail-info__contents">
		<div class="movieDetail-info__header">
			<div>
				<h2>${mvVo.mvTitle}</h2>
			</div>
			<div>
				<span>제작년도 ${mvVo.makeYear}　|　제작국가 ${mvVo.nation}　|　장르 ${mvVo.genre}</span>
			</div>
		</div>
		<div class="movieDetail-info__buttons">
			<!-- 평점매기기, 찜하기 -->
			<div id="stars" class="stars">
			<!-- 마우스오버 효과를 js로 만들어주고 클릭했을 때 어떤 요소가 어떤 상태인지에 따라 평점 입력 
				첫번째 별의 앞 절반에 오버한경우 1점, 앞 절반을 넘어간 경우 2점
				두번째 별의 앞 절반에 오버한경우 3점, 앞 절반을 넘어간 경우 4점
				세번째 별의 앞 절반에 오버한경우 5점, 앞 절반을 넘어간 경우 6점
				네번째 별의 앞 절반에 오버한경우 7점, 앞 절반을 넘어간 경우 8점
				다섯번째 별의 앞 절반에 오버한 경우 9점, 넘어간 경우 10점 -->
				<div><i id="firstStar" class="fas fa-star fa-2x star star-mono"></i></div>
				<div><i id="secondStar" class="fas fa-star fa-2x star star-mono"></i></div>
				<div><i id="thirdStar" class="fas fa-star fa-2x star star-mono"></i></div>
				<div><i id="fourthStar" class="fas fa-star fa-2x star star-mono"></i></div>
				<div><i id="fifthStar" class="fas fa-star fa-2x star star-mono"></i></div>
			</div>
			<!-- todo 클릭하면 평점 입력되고, 별표 더이상 마우스오버 하지 않아도 그대로 박혀있도록 -->
			<script src="../js/movieDetail_drawStars.js"></script>
			<div>
			<!-- todo 버튼 선택 시 킵리스트에 넣어주기 데이터 연동 -->
			<button id="keepBtn" class="btn btn-sm btn-default" style="display:
			<%if(keptCheck){//킵리스트에 있으면 %>
            	 none
			<%} else{//킵리스트에 없으면 %>
            	 hidden
			<%} %>"><i class="fas fa-plus-circle"></i> 나중에 볼 영화</button>
			<button id="unkeepBtn" class="btn btn-sm btn-default" style="display:
			<%if(keptCheck){//킵리스트에 있으면 %>
            	 hidden
			<%} else{//킵리스트에 없으면 %>
            	 none
			<%} %>"><i class="fas fa-minus-circle"></i> 볼 영화에서 제외</button>
			</div>
		</div>
		<hr class="hrLine">
		<div class="movieDateil-info__story">
		<span>${mvVo.story} 스토리 들어갈 곳 </span>
		</div>
	</div>
</section>
<section class="movieDetail-starsGraph">
	<div class="movieDetail-starsGraph-header">
		<!-- todo 실제 더미데이터 입력 후 평점 끌어와서 그래프 그리기 -->
		<h3>평점분포</h3>
		<span>8.7 ( 명 참여)</span>
	</div>
	<canvas id="starsGraph" >
	<!-- 시간 여유되면 비율에 따라 그래프 색상변화 주기. 제일 많은 애들 둘정도를 짙은 색으로 -->
	<script src="../js/movieDetail_graph.js"></script>
	</canvas>
</section>
<section class="movieDeatil-comment">
	<div class="movieDetail-comment__header"><h3>코멘트</h3></div>
	<div class="movieDetail-info-inputComment">
		<form action="#" method="post" >
			<label for="exampleFormControlTextarea1">코멘트 남기기</label>
   			<textarea class="form-control" id="exampleFormControlTextarea1" rows="5"></textarea>
   			<div class="underCommentTa">
	  				<div class="input-group-prepend">
	  					<div class="input-group-text">
	     					<input type="checkbox" aria-label="spoiler check">&nbsp;<span>스포일러가 있습니다.</span>
	    				</div>
	  				</div>
				<input type="submit" id="cmtBtn" class="btn btn-sm btn-default" value="코멘트 등록"></button>
			</div>
		</form>
		</div>
	<div class="movieDetail-comment__list">
	
	</div>
</section>
<section class="movieDetail-multimedia">
	<div class="movieDetail-contents__images">
	이미지들을 쓸 수 있다면 넣기
	</div>
	<div class="movieDetail-contents__videos">
	동영상을 쓸 수 있다면 넣기
	</div>
</section>
</div>
<%@ include file="../inc/bottom.jsp" %>