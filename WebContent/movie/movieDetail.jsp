<%@page import="com.batcha.keepData.model.keepDataService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="../css/movieDetail.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
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
<div class="MovieDetailWrapper">
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
				<form name="starsfrm" method="post" action="<c:url value="/movie/movieDetail.do" />">
				<div id="stars" class="stars" onclick="clickedCoords(event)">
				<!-- 마우스오버 효과를 js로 만들어주고 클릭했을 때 어떤 요소가 어떤 상태인지에 따라 평점 입력 
				첫번째 별의 앞 절반에 오버한경우 1점, 앞 절반을 넘어간 경우 2점
				두번째 별의 앞 절반에 오버한경우 3점, 앞 절반을 넘어간 경우 4점
				세번째 별의 앞 절반에 오버한경우 5점, 앞 절반을 넘어간 경우 6점
				네번째 별의 앞 절반에 오버한경우 7점, 앞 절반을 넘어간 경우 8점
				다섯번째 별의 앞 절반에 오버한 경우 9점, 넘어간 경우 10점 -->
					<div name="first"><i id="firstStar" class="fas fa-star fa-2x star star-mono"></i></div>
					<div><i id="secondStar" class="fas fa-star fa-2x star star-mono"></i></div>
					<div><i id="thirdStar" class="fas fa-star fa-2x star star-mono"></i></div>
					<div><i id="fourthStar" class="fas fa-star fa-2x star star-mono"></i></div>
					<div><i id="fifthStar" class="fas fa-star fa-2x star star-mono"></i></div>
					<input type="text" value="" id="coordSet">
					<script type="text/javascript">
					var clickedCoords = function(event) {
				    	var x = event.offsetX;
				    	var y = event.offsetY;
				    	console.log(x+", "+y);
						return {
							offsetX: x,
							offsetY: y
						};
					var xyset=clickedCoords();
					document.getElementById('coordSet').text(xyset);
					
					const stars=document.getElementById('stars');
					const relativeTop = stars.getBoundingClientRect().top;
					const relativeBottom = stars.getBoundingClientRect().bottom;
					const relativeRight = stars.getBoundingClientRect().right;
					const relativeLeft = stars.getBoundingClientRect().left;
					
					var starH=relativeBottom-relativeTop;
					var starW=relativeRight-relativeLeft;
					
					var starXMin=relativeRight;
					var starXMax=relativeLeft;
					var starMark=(starXMin-starXMax)/2;
					console.log("starXMax="+starXMin);
					console.log("starXMin="+starXMax);
					console.log("starMark="+starMark);
					
					const scrolledTopLength=window.pageYOffset; //스크롤된 길이
					const absoluteTop=scrolledTopLength+relativeTop; //절대좌표
					//마우스로 클릭시 값 저장
					var clickedPosX;
					var clickedPosY;
					$(function(){
							$('#stars').click(function(e){
								clickedPosX=e.pageX;
								clickedPosY=e.pageY;
							});
						});
					
					var selectedMarkArr=new Array();
					var starPoint=0;
					
					for(var j=10; j>=1; j--){
						selectedMarkArr[j-1]=starXMin+starMark*j; // 넓이값 10개 나왔음
						console.log("selectedMark "+j+": "+selectedMarkArr[j-1]);
						if(clickedPosX <= selectedMarkArr[j-1]){
							starPoint=j;
							console.log("j="+j);
							console.log("starPoint="+starPoint);
							console("clickedPosX="+clickedPosX+", selectedMark:"+selectedMarkArr[j-1])
						}//if
						
					}//for
					
					</script>
			</div>
		</form>
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
			
			</div>
		</div>
		<hr class="movieDetail-hrLine">
		<div class="movieDateil-info__story">
		<span>${mvVo.story} 스토리 들어갈 곳 </span>
		</div>
		</div>
	</section>
	<section class="movieDeatil-commentWrite">
		<div class="movieDetail-comment__header"><h3>코멘트 쓰기</h3></div>
		<div class="movieDetail-info-inputComment">
			<form name="cmtFrm" action='<c:url value="/movie/cmtWrite.do"/>' method="post">
   			<textarea class="form-control" placeholder="코멘트를 입력해보세요." id="cmtTa" rows="5"></textarea>
   			<div class="underCommentTa">
	  				<div class="input-group-prepend">
	  					<div class="input-group-text">
	     					<input type="checkbox" aria-label="spoiler check">&nbsp;<span>스포일러가 있습니다.</span>
	    				</div>
	  				</div>
				<input type="submit" id="cmtBtn" class="btn btn-sm btn-dark" value="코멘트 등록"></button>
			</div>
			</form>
		</form>
		</div>
	</section>

	<section class="movieDetail-starsGraph">
		<div class="movieDetail-starsGraph-header">
			<!-- todo 실제 더미데이터 입력 후 평점 끌어와서 그래프 그리기 -->
			<h3>평점분포</h3>
			<span>${avgStars}점 (${memCntOfMv} 명 참여)</span>
		</div>
		<canvas id="starsGraph">
		<!-- <script src="../js/movieDetail_graph.js"></script> -->
		</canvas> 
		<canvas>
			<script>
			var starsData = new Array();
			<c:forEach items="${graphData}" var="item">
			starsData.push("${item}")
			</c:forEach>
			console.log("starsData[0]="+starsData[0]);//여기까진 정상이야
			
			var graphData = document.getElementById('starsGraph').getContext('2d');
			var myChart = new Chart(graphData, {
			    type: 'bar',
			    data: {
			        labels: ['1','2','3','4','5','6','7','8','9','10'],
			        datasets: [{
			            data: [starsData[0],starsData[1],starsData[2],starsData[3],starsData[4],
							   starsData[5],starsData[6],starsData[7],starsData[8],starsData[9]],
			            backgroundColor: 'gold',
			        }]
			    },
			    options: {
			    	legend: {
			        	display: false
			        },
			        title: {
			        	display: false
			        },
			    	scales: {
			    	  xAxes: [{
			              display: true
			            }],
			          yAxes: [{
			              display: false
			            }],
			        },
			        responsive: true,
			        maintainAspectRatio: false,
			    }
			});
			</script>
		</canvas>
	</section>


	<section class="movieDeatil-commentList">
		<div class="movieDetail-comment__list">
		<h3>코멘트 목록</h3>
			<div class="row">
			<c:set var="cmtListSize" value="${fn:length(cmtList)}"/>
			<c:forEach var="cmt" items="${cmtList}" varStatus="status">
				<c:if test="${cmtListSize<1}">
					<p style="align:center">등록된 코멘트가 없습니다. 코멘트를 등록해보세요.</p>
				</c:if>
				<c:if test="${cmtListSize>=1}">
				  <div class="col-sm-6 col-md-4">
				    <div class="userId">
				    <h5>${cmt.userid}유저아이디</h5>
				      <div class="caption">
				        <p>${cmt.cmtText}</p>
				        <p><a href="#" class="btn btn-group-xs" role="group"><i class="fas fa-thumbs-up"></i></a>
				        <a href="#" class="btn btn-group-xs" role="group"><i class="far fa-thumbs-up"></i></a>
				        <a href="#" class="btn btn-group-xs" role="group"><i class="fas fa-thumbs-down"></i></a>
				        <a href="#" class="btn btn-group-xs" role="group"><i class="far fa-thumbs-down"></i></a>
				      </div>
				    </div>
				  </div>
				</c:if>
			</c:forEach>
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