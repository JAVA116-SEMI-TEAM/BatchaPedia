<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<link rel="stylesheet" href="../css/company.css"/>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=74f02a55a83133c0e181e59c3f5e0497">
</script>

<title>회사소개 - BatchaPedia Introduction</title>
<section class="companyWrraper">
	<section class="companyIntro">
		<h2 class="company-header">BatchaPedia</h2>
		<p class="compadyIntro__text">BatchaPedia는 이현빈, 배소정, 남은별, 서은비 네 사람이 만들어가는 공간입니다.</p>
		<div class="company__BatchaPeople">
			<div class="intro-cards">
				<div class="row">
			      <div class="col-md-4">
			         <div class="card mb-4 border-dark">
			            <img src="../images/bin2010.jpg" class="profile"/>
			            <div class="card-body">
			               <h5 class="card-title">이현빈</h5>
			               <p class="card-text">Web / DL / ML / EDU</p>
			               <a href="https://github.com/jazzo5947" class="btn btn-dark btn-sm">Contact</a>
			            </div>
			         </div>
			      </div>
				  <div class="col-md-4">
			         <div class="card mb-4 border-dark">
			            <img class="card-img-top" src="../images/sojeong.jpg" alt="Card image cap">
			            <div class="card-body">
			               <h5 class="card-title">배소정</h5>
			               <p class="card-text">웹개발자 입니다.</p>
			               <a href="#" class="btn btn-dark btn-sm">Contact</a>
			            </div>
			         </div>
			      </div>  
			      <div class="col-md-4">
			         <div class="card mb-4 border-dark">
			           <img src="../images/eunbyeol.png" class="profile"/>
			            <div class="card-body">
			               <h5 class="card-title">남은별</h5>
			               <p class="card-text">웹개발자 입니다.</p>
			               <a href="#" class="btn btn-dark btn-sm">Contact</a>
			            </div>
			         </div>
			      </div>  
			      <div class="col-md-4">
			         <div class="card mb-4 border-dark">
			            <img class="card-img-top" src="../images/eunbi.jpg" alt="Card image cap">
			            <div class="card-body">
			               <h5 class="card-title">서은비</h5>
			               <p class="card-text">웹개발자 입니다.</p>
			               <a href="#" class="btn btn-dark btn-sm">Contact</a>
			            </div>
			         </div>
			      </div>
				</div>
			</div>
		</div>
	</section>
	<section id="BatchaInfo">
	<h2 class="company-header">오시는 길</h2>
		<div class="BatchaInfo-contents">
			<div id="map" style="width:500px;height:400px;"></div>
			<ul>
				<li>주소: 서울특별시 강남구 테헤란로 124 4층 (역삼동, 삼원타워)</li>
				<li>전화번호: 02-6255-8002</li>
			</ul>
		</div>
	<script type="text/javascript">
		var MapContainer = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
		var MapOptions = { //지도를 생성할 때 필요한 기본 옵션
			center : new kakao.maps.LatLng(37.4986855,127.0297111), //지도의 중심좌표.
			level : 3
		//지도의 레벨(확대, 축소 정도)
		};
	
		var map = new kakao.maps.Map(MapContainer, MapOptions); //지도 생성 및 객체 리턴
		
		// 마커가 표시될 위치입니다 
		var markerPosition  = new kakao.maps.LatLng(37.4986855,127.0297111); 

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
		    position: markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);
	</script>
	</section>
</section>
<%@ include file="../inc/bottom.jsp" %>