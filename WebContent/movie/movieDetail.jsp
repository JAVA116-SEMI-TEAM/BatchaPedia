<%@page import="com.batcha.keepData.model.keepDataService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../inc/top.jsp" %>
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="../css/movieDetail.css">
<!------ Include the above in your HEAD tag ---------->
<%
	/* float memStars=(float)request.getAttribute("memStars");
	int didStars=(int)request.getAttribute("didStars");
	float avgStars=(float)request.getAttribute("avgStars");
 */
	
	//컨트롤러에서 킵했는지 확인해서 값 불러오기
	boolean keptCheck=false;
 	
 	if(request.getAttribute("keptCheck")!=null){
 		keptCheck=(boolean)request.getAttribute("keptCheck");
 	}
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
				<!-- <form action="" method="get" name="keepbtnFrm">
					<button id="keepBtn" name="keepBtn" value="f" style="display:none"
						class="btn btn-sm btn-default">
					<i class="fas fa-plus-circle"></i> 나중에 볼 영화
					</button>
					
					<button id="unKeepBtn" name="keepBtn" value="t" style="display:inline"
					class="btn btn-sm btn-default">
					<i class="fas fa-minus-circle"></i> 볼 영화에서 제외
					</button>
				</form> -->
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
			<c:set var="cKeptCheck" value="true"/>
			<c:if test="${!cKeptCheck}"> <!-- 킵 안한 영화인 경우  -->
				<form action="" method="post" name="keepbtnFrm">
					<button id="keepBtn" name="keepBtn" class="btn btn-sm btn-default">
					<i class="fas fa-plus-circle"></i> 나중에 볼 영화3
					</button>
					<input type="text" name="cKeptchkInput" value="${cKeptCheck}">
				</form>
				
			</c:if>
			<c:if test="${cKeptCheck}"> <!-- 킵한 영화인 경우 -->
				<form action="" method="post" name="keepbtnFrm">
					<button id="unkeepBtn" name="keepBtn" class="btn btn-sm btn-default">
					<i class="fas fa-minus-circle"></i> 볼 영화에서 제외3
					</button>
					<input type="text" name="cKeptchkInput" value="${cKeptCheck}">
				</form>
			</c:if> 
			
			</div>
		</div>
		<hr class="hrLine">
		<div class="movieDateil-info__story">
		<span>${mvVo.story} 스토리 들어갈 곳 </span>
		</div>
	</div>
</section>
<section class="movieDetail-starsGraph">
	<%-- <div class="movieDetail-starsGraph-header">
		<!-- todo 실제 더미데이터 입력 후 평점 끌어와서 그래프 그리기 -->
		<h3>평점분포</h3>
		<span>${avgStars}점 (${memCntOfMv} 명 참여)</span>
	</div>
	<canvas id="starsGraph" >
	<!-- 시간 여유되면 비율에 따라 그래프 색상변화 주기. 제일 많은 애들 둘정도를 짙은 색으로 -->
	<script src="../js/movieDetail_graph.js"></script>
	</canvas> --%>
<style>
 
.myGraph{
	display: flex;
	justify-content: space-between;
	padding: 0 10px;
	position: relative;
}

.myGraph-bar{
	background-image: url(../images/movieDetail/yellowBar.png);
	width: 50px;
	border: none;
		justify-content: space-between;
	}	
	
.barWrapper{
	text-align: center;
	position: absolute;
	top: 0;
}

.graphNo{
	item-align: center;
}

</style>

<div class="myGraph">
	<div class="barWrapper">
		<div class="myGraph-bar" style=""></div><div class="graphNo" style="height:100px">1</div>
	</div>
	<div class="barWrapper">
		<div class="myGraph-bar" style=""></div><div class="graphNo">2</div>
	</div>
	<div class="barWrapper">
		<div class="myGraph-bar" style=""></div><div class="graphNo">3</div>
	</div>
	<div class="barWrapper">
		<div class="myGraph-bar" style=""></div><div class="graphNo">4</div>
	</div>
	<div class="barWrapper">
		<div class="myGraph-bar" style=""></div><div class="graphNo">5</div>
	</div>
	<div class="barWrapper">
		<div class="myGraph-bar" style=""></div><div class="graphNo">6</div>
	</div>
	<div class="barWrapper">
		<div class="myGraph-bar" style=""></div><div class="graphNo">7</div>
	</div>
	<div class="barWrapper">
		<div class="myGraph-bar" style=""></div><div class="graphNo">8</div>
	</div>
	<div class="barWrapper">
		<div class="myGraph-bar" style=""></div><div class="graphNo">9</div>
	</div>
	<div class="barWrapper">
		<div class="myGraph-bar" style=""></div><div class="graphNo">10</div>
	</div>
</div>

</section>
<section class="movieDeatil-comment">
	<div class="movieDetail-comment__header"><h3>코멘트</h3></div>
	<div class="movieDetail-info-inputComment">
		<form action="/movie/cmtWrite_ok.do" method="post" >
			<label for="exampleFormControlTextarea1">코멘트 남기기</label>
   			<textarea class="form-control" placeholder="코멘트를 입력해보세요."  id="exampleFormControlTextarea1" rows="5"></textarea>
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
	<h3>코멘트 목록</h3>
		<div class="container">
	<div class="row">
		<div class="span12">
    	    <div class="well"> 
                <div id="myCarousel" class="carousel slide">
                 
                <!-- Carousel items -->
                <div class="carousel-inner">
                    
                <div class="item active">
                	<div class="row-fluid">
                	  <div class="span3"><a href="#x" class="thumbnail"></a><div>작성자 | yyyy-MM-dd</div><div>코멘트내용입니다. </div></div>
                	  <div class="span3"><a href="#x" class="thumbnail"><img src="http://placehold.it/250x250" alt="Image" style="max-width:100%;" /></a></div>
                	  <div class="span3"><a href="#x" class="thumbnail"><img src="http://placehold.it/250x250" alt="Image" style="max-width:100%;" /></a></div>
                	</div><!--/row-fluid-->
                </div><!--/item-->
                 
                <div class="item">
                	<div class="row-fluid">
                		<div class="span3"><a href="#x" class="thumbnail"><img src="http://placehold.it/250x250" alt="Image" style="max-width:100%;" /></a></div>
                		<div class="span3"><a href="#x" class="thumbnail"><img src="http://placehold.it/250x250" alt="Image" style="max-width:100%;" /></a></div>
                		<div class="span3"><a href="#x" class="thumbnail"><img src="http://placehold.it/250x250" alt="Image" style="max-width:100%;" /></a></div>
                		<div class="span3"><a href="#x" class="thumbnail"><img src="http://placehold.it/250x250" alt="Image" style="max-width:100%;" /></a></div>
                	</div><!--/row-fluid-->
                </div><!--/item-->
                 
                <div class="item">
                	<div class="row-fluid">
                		<div class="span3"><a href="#x" class="thumbnail"><img src="http://placehold.it/250x250" alt="Image" style="max-width:100%;" /></a></div>
                		<div class="span3"><a href="#x" class="thumbnail"><img src="http://placehold.it/250x250" alt="Image" style="max-width:100%;" /></a></div>
                		<div class="span3"><a href="#x" class="thumbnail"><img src="http://placehold.it/250x250" alt="Image" style="max-width:100%;" /></a></div>
                		<div class="span3"><a href="#x" class="thumbnail"><img src="http://placehold.it/250x250" alt="Image" style="max-width:100%;" /></a></div>
                	</div><!--/row-fluid-->
                </div><!--/item-->
                 
                </div><!--/carousel-inner-->
                 
                <a class="left carousel-control" href="#myCarousel" data-slide="prev">‹</a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">›</a>
                </div><!--/myCarousel-->
                 
            </div><!--/well-->   
		</div>
	</div>
</div>
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