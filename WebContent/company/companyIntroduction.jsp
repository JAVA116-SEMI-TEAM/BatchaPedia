<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet" href="../css/company.css"/>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=74f02a55a83133c0e181e59c3f5e0497"></script>

<title>회사소개 - BatchaPedia Introduction</title>
<body >
<section id="BatchaIntro" align="center">
	<div class="intro-text">
	<h2>BatchaPeople</h2>
	<p>BatchaPedia는 이현빈, 배소정, 남은별, 서은비 네 사람이 만들어가는 공간입니다.</p>
	</div>
	<div class="intro-cards">
	   <div class=row>
	      <div class="col-md-3">
	         <div class="card mb-4">
	            <img class="card-img-top" src="../images/bin2010.jpg" alt="Card image cap">
	            <div class="card-body">
	               <h5 class="card-title">이현빈</h5>
	               <p class="card-text">1년차 개발자 JAVA/JS/AI/DL</p>
	               <a href="mailto: jazzo5947@gmail.com" class="btn btn-outline-dark btn-sm">contact to HB.LEE</a>
	            </div>
	         </div>
	      </div>
	      <div class="col-md-3">
	         <div class="card mb-4">
	            <img class="card-img-top" src="//placeimg.com/290/180/any" alt="Card image cap">
	            <div class="card-body">
	               <h5 class="card-title">배소정</h5>
	               <p class="card-text">안녕하세요? 배소정입니다. 웹 개발자입니다.</p>
	               <a href="http://www.jquery2dotnet.com/" class="btn btn-outline-dark btn-sm">contact to SJ.BAE</a>
	            </div>
	         </div>
	      </div>
	      <div class="col-md-3">
	         <div class="card mb-4">
	            <img class="card-img-top" src="//placeimg.com/290/180/any" alt="Card image cap">
	            <div class="card-body">
	               <h5 class="card-title">서은비</h5>
	               <p class="card-text">안녕하세요? 서은비입니다. 웹 개발자입니다.</p>
	               <a href="http://www.jquery2dotnet.com/" class="btn btn-outline-dark btn-sm">Contact to EB.Seo</a>
	            </div>
	         </div>
	      </div>
	      <div class="col-md-3">
	         <div class="card mb-4">
	            <img class="card-img-top" src="../images/eunbyeol.png" alt="Card image cap">
	            <div class="card-body">
	               <h5 class="card-title">남은별</h5>
	               <p class="card-text">안녕하세요? 남은별입니다. 웹 개발자입니다.</p>
	               <a href="http://www.jquery2dotnet.com/" class="btn btn-outline-dark btn-sm">Contact to EB.Nam</a>
	            </div>
	         </div>
	      </div>
	   </div>
   </div>
</section>


<section id="BatchaInfo" >
<h2 align="center">Company Location</h2>
<div class="BatchaInfo-contents">
	<div align="center" id="map" style="width:500px;height:400px;"></div>
	<div align="center" class="BatchaInfo-text">
		<ul>
			<li>주소: 서울 강남구 테헤란로 124 4층</li>
			<li>전화번호: 02-6207-0207</li>
		</ul>
	</div>

</div>
</section>
</body>
<%@ include file="../js/movieDetail_map.js" %>
<%@ include file="../inc/bottom.jsp" %>