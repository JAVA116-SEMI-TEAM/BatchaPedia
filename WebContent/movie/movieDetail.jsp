<%@page import="com.batcha.keepData.model.keepDataService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<link rel="stylesheet" href="../css/movieDetail.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4"></script>
<%
	/* float memStars=(float)request.getAttribute("memStars");
	int didStars=(int)request.getAttribute("didStars");
	float avgStars=(float)request.getAttribute("avgStars");
 */
	
	//컨트롤러에서 킵했는지 확인해서 값 불러오기
	boolean keptCheck=(boolean)request.getAttribute("keptCheck");
 	System.out.println("keptCheck="+keptCheck);
	//불러온 값에 따라 화면에서 다르게 뿌려주기
	
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
				<span>${mvVo.makeYear}　|　 ${mvVo.nation}　|　 ${mvVo.genre}</span>
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
			<!-- todo 버튼 선택 시 킵리스트에 넣어주기 데이터 연동 -->
			<div class="keepBtns">
				<label class="toggleBtn">
					<input type="checkbox" id="toggleCheckBox" onclick="toggle(this)"/>
				</label>
				<!-- <form action="" method="get" name="keepbtnFrm">
					<button id="keepBtn" name="keepBtn" value="f" style="display:none"
						class="btn btn-sm btn-default">
					<i class="fas fa-plus-circle"></i> 나중에 볼 영화
					</button>
					
					<button id="unKeepBtn" name="keepBtn" value="t" style="display:inline"
					class="btn btn-sm btn-default">
					<i class="fas fa-minus-circle"></i> 볼 영화에서 제외
					</button>
				 -->
			<script type="text/javascript">
				function toggle(element){
					console.log(element.checked);
					if(element.checked){
						keptCheck=true;
						document.getElementById('toggleCheckBox').setAttribute("keptCheck", true);
						location.reload();
					}else{
						keptCheck=false;
						document.getElementById('toggleCheckBox').setAttribute("keptCheck", false);
						location.reload();
					}
					console.log(keptCheck);
				}
				
				//로그인 안했을 경우 얼럿 띄워줘야 함
			//	alert('로그인 하셔야 합니다.');
			</script>
			<%-- 
			<c:set var=none value="display:none"></c:set>
			<c:if test="${keptCheck==false}"> <!-- 킵 안한 영화인 경우  -->
				<form action="" method="post" name="keepbtnFrm">
					<button id="keepBtn" name="keepBtn" value="false" class="btn btn-sm btn-default">
					<i class="fas fa-plus-circle"></i> 나중에 볼 영화3
					</button>
				</form>
			</c:if>
			<c:if test="${keptCheck==true}"> <!-- 킵한 영화인 경우 -->
				<form action="" method="post" name="keepbtnFrm">
					<button id="unkeepBtn" name="keepBtn" value="true" class="btn btn-sm btn-default">
					<i class="fas fa-minus-circle"></i> 볼 영화에서 제외3
					</button>
				</form>
			</c:if> --%>
			
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
		<span>${avgStars}점 (${memCntOfMv} 명 참여)</span>
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