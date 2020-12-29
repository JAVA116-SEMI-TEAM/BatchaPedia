<%@page import="com.batcha.memInfo.model.MemInfoService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
var s_userid=<%=session.getAttribute("userid")%>

$(function(){
	$('form[name=starsSelectsForm]').submit(function(){
		if($('s_userid').length<1){
			alert("로그인 후 평점을 선택하세요.");
			event.preventDefault();
			return false;
		}
	});
});
</script>
<!-- <form name="starsfrm" method="post" action="">
 	<div id="stars" class="stars" onclick="clickedCoords(event)">
		마우스오버 효과를 js로 만들어주고 클릭했을 때 어떤 요소가 어떤 상태인지에 따라 평점 입력 
		첫번째 별의 앞 절반에 오버한경우 1점, 앞 절반을 넘어간 경우 2점
		두번째 별의 앞 절반에 오버한경우 3점, 앞 절반을 넘어간 경우 4점
		세번째 별의 앞 절반에 오버한경우 5점, 앞 절반을 넘어간 경우 6점
		네번째 별의 앞 절반에 오버한경우 7점, 앞 절반을 넘어간 경우 8점
		다섯번째 별의 앞 절반에 오버한 경우 9점, 넘어간 경우 10점
		<div name="first"><i id="firstStar" class="fas fa-star fa-2x star star-mono"></i></div>
		<div><i id="secondStar" class="fas fa-star fa-2x star star-mono"></i></div>
		<div><i id="thirdStar" class="fas fa-star fa-2x star star-mono"></i></div>
		<div><i id="fourthStar" class="fas fa-star fa-2x star star-mono"></i></div>
		<div><i id="fifthStar" class="fas fa-star fa-2x star star-mono"></i></div>
		<input type="text" value="" id="coordSet">
	</div>이게 바뀔 때마다 값을 보내는 함수 작성 가능할까 -->
	<div class="starsDiv" style="display: flex;">
		<form method="post" action="" name="starsSelectsForm">
			<select id="starSelect" name="starSelect">
				<option value="0" selected>평점을 선택하세요</option>
				<option value="1" <c:if test="${memStars == 1}"> selected="selected" </c:if> >1점</option>
				<option value="2" <c:if test="${memStars == 2}"> selected="selected" </c:if> >2점</option>
				<option value="3" <c:if test="${memStars == 3}"> selected="selected" </c:if> >3점</option>
				<option value="4" <c:if test="${memStars == 4}"> selected="selected" </c:if> >4점</option>
				<option value="5" <c:if test="${memStars == 5}"> selected="selected" </c:if> >5점</option>
				<option value="6" <c:if test="${memStars == 6}"> selected="selected" </c:if> >6점</option>
				<option value="7" <c:if test="${memStars == 7}"> selected="selected" </c:if> >7점</option>
				<option value="8" <c:if test="${memStars == 8}"> selected="selected" </c:if> >8점</option>
				<option value="9" <c:if test="${memStars == 9}"> selected="selected" </c:if> >9점</option>
				<option value="10" <c:if test="${memStars == 10}"> selected="selected" </c:if> >10점</option>
			</select>
			<input type="submit" value="평점 입력" name="submitStar" id="submitStar" class="btn btn-sm btn-dark"/>
		</form>
	</div>
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
			}
		}//for
		
		</script>
	
<!-- </form> -->
	<!-- todo 클릭하면 평점 입력되고, 별표 더이상 마우스오버 하지 않아도 그대로 박혀있도록 -->
	<script src="../js/movieDetail_drawStars.js"></script>
