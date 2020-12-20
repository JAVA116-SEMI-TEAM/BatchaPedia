<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<link rel="stylesheet" href="../css/company.css"/>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=74f02a55a83133c0e181e59c3f5e0497">
</script>

<title>회사소개 - BatchaPedia Introduction</title>
<h1>BatchaPedia</h1>

<section id="BatchaIntro">
	<p>BatchaPedia는 이현빈, 배소정, 남은별, 서은비 네 사람이 만들어가는 공간입니다.</p>
	<div class="BatchaPeople">
		<div class="peopleCard">
			<img src="../image/bin.png" class="profile "/>
			<span>이현빈</span>
			<span>API 연구가</span>	
		</div>
		<div class="peopleCard">
			<img src="../image/bin.png" class="profile "/>
			<span>배소정</span>
			<span>다정한 협력가</span>	
		</div>
		<div class="peopleCard">
			<img src="../image/bin.png" class="profile "/>
			<span>서은비</span>
			<span>CRUD 전문가</span>	
		</div>
		<div class="peopleCard">
			<img src="../image/bin.png" class="profile "/>
			<span>남은별</span>
			<span>FE 마스터</span>	
		</div>
		
	
	</div>
</section>
<section id="BatchaInfo">
<h3>오시는 길</h3>
<span>주소: </span>
<span>전화번호: </span>
<div id="map" style="width:500px;height:400px;"></div>
<script type="text/javascript">
	var MapContainer = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var MapOptions = { //지도를 생성할 때 필요한 기본 옵션
		center : new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
		level : 3
	//지도의 레벨(확대, 축소 정도)
	};

	var map = new kakao.maps.Map(MapContainer, MapOptions); //지도 생성 및 객체 리턴
</script>
</section>
<%@ include file="../inc/bottom.jsp" %>