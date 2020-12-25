<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../inc/top.jsp"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var itemsMainDiv = ('.MultiCarousel');
						var itemsDiv = ('.MultiCarousel-inner');
						var itemWidth = "";

						$('.leftLst, .rightLst').click(function() {
							var condition = $(this).hasClass("leftLst");
							if (condition)
								click(0, this);
							else
								click(1, this)
						});

						ResCarouselSize();

						$(window).resize(function() {
							ResCarouselSize();
						});

						//this function define the size of the items
						function ResCarouselSize() {
							var incno = 0;
							var dataItems = ("data-items");
							var itemClass = ('.item');
							var id = 0;
							var btnParentSb = '';
							var itemsSplit = '';
							var sampwidth = $(itemsMainDiv).width();
							var bodyWidth = $('body').width();
							$(itemsDiv).each(
									function() {
										id = id + 1;
										var itemNumbers = $(this).find(
												itemClass).length;
										btnParentSb = $(this).parent().attr(
												dataItems);
										itemsSplit = btnParentSb.split(',');
										$(this).parent().attr("id",
												"MultiCarousel" + id);

										if (bodyWidth >= 1200) {
											incno = itemsSplit[3];
											itemWidth = sampwidth / incno;
										} else if (bodyWidth >= 992) {
											incno = itemsSplit[2];
											itemWidth = sampwidth / incno;
										} else if (bodyWidth >= 768) {
											incno = itemsSplit[1];
											itemWidth = sampwidth / incno;
										} else {
											incno = itemsSplit[0];
											itemWidth = sampwidth / incno;
										}
										$(this).css({
											'transform' : 'translateX(0px)',
											'width' : itemWidth * itemNumbers
										});
										$(this).find(itemClass).each(
												function() {
													$(this).outerWidth(
															itemWidth);
												});

										$(".leftLst").addClass("over");
										$(".rightLst").removeClass("over");

									});
						}

						//this function used to move the items
						function ResCarousel(e, el, s) {
							var leftBtn = ('.leftLst');
							var rightBtn = ('.rightLst');
							var translateXval = '';
							var divStyle = $(el + ' ' + itemsDiv).css(
									'transform');
							var values = divStyle.match(/-?[\d\.]+/g);
							var xds = Math.abs(values[4]);
							if (e == 0) {
								translateXval = parseInt(xds)
										- parseInt(itemWidth * s);
								$(el + ' ' + rightBtn).removeClass("over");

								if (translateXval <= itemWidth / 2) {
									translateXval = 0;
									$(el + ' ' + leftBtn).addClass("over");
								}
							} else if (e == 1) {
								var itemsCondition = $(el).find(itemsDiv)
										.width()
										- $(el).width();
								translateXval = parseInt(xds)
										+ parseInt(itemWidth * s);
								$(el + ' ' + leftBtn).removeClass("over");

								if (translateXval >= itemsCondition - itemWidth
										/ 2) {
									translateXval = itemsCondition;
									$(el + ' ' + rightBtn).addClass("over");
								}
							}
							$(el + ' ' + itemsDiv).css('transform',
									'translateX(' + -translateXval + 'px)');
						}

						//It is used to get some elements from btn
						function click(ell, ee) {
							var Parent = "#" + $(ee).parent().attr("id");
							var slide = $(Parent).attr("data-slide");
							ResCarousel(ell, Parent, slide);
						}

					});
</script>
<style type="text/css">
.MultiCarousel {
	float: left;
	overflow: hidden;
	padding: 15px;
	width: 100%;
	position: relative;
}

.MultiCarousel .MultiCarousel-inner {
	transition: 1s ease all;
	float: left;
}

.MultiCarousel .MultiCarousel-inner .item {
	float: left;
}

.MultiCarousel .MultiCarousel-inner .item>div {
	text-align: center;
	padding: 10px;
	margin: 10px;
	background: #f1f1f1;
	color: #666;
}

.item>div{
	background: 
}

.MultiCarousel .leftLst, .MultiCarousel .rightLst {
	position: absolute;
	border-radius: 50%;
	top: calc(50% - 20px);
}

.MultiCarousel .leftLst {
	left: 0;
}

.MultiCarousel .rightLst {
	right: 0;
}

.MultiCarousel .leftLst.over, .MultiCarousel .rightLst.over {
	pointer-events: none;
	background: #ccc;
}

.my-card {
	position: absolute;
	left: 37%;
	top: -20px;
	border-radius: 50%;
}

.scobox {
	margin-left: 80px;
}


#top-wrap {
	position: relative;
	padding-bottom: 50px;
}

#mytitle {
	float: left;
}

#myedit {
	float: right;
}

#myedit a {
	color: #6c757d;
	text-decoration: none;
	background-color: transparent;
}

.mycard {
	background-color: #1C1D1F;
	margin-bottom: 80px;
}
.cardtitle{
	float: left;
	color: #fff;
}

.cardmore{
	float: right;
	padding-right: 10px;
}

.cardmore a{
	color: #fff;
}

.multicar{
	clear: both;
}

.pad1 img{
width: 100%;
height: 100%;
}

.jumbotron{
	background-color: #fff;
}

.img-responsive {
    width: 80px;
    height: 121px;
}

</style>
<body>
	<div class="container">
		<div id="top-wrap">
			<div id="mytitle">
				<h2 class="page-header">님의 페이지입니다</h2>
			</div>
			<div id="myedit">
				<a href="#">회원정보수정</a>
			</div>
		</div>

		<!-- 총 평점 / 콘텐트 / 찜 목록 -->
		<div class="jumbotron mysection">
			<div class="row w-100">
				<div class="col-md-3 scobox">
					<div class="card border-warning mx-sm-1 p-3">
						<div class="card border-warning text-warning p-3 my-card">
							<span class="fa fa-inbox" aria-hidden="true"></span>
						</div>
						<div class="text-warning text-center mt-3">
							<h4>좋아요</h4>
						</div>
						<div class="text-warning text-center mt-2">
							<h1>346</h1>
						</div>
					</div>
				</div>
				<div class="col-md-3 scobox">
					<div class="card border-success mx-sm-1 p-3">
						<div class="card border-success text-success p-3 my-card">
							<span class="fa fa-eye" aria-hidden="true"></span>
						</div>
						<div class="text-success text-center mt-3">
							<h4>코멘트</h4>
						</div>
						<div class="text-success text-center mt-2">
							<h1>9332</h1>
						</div>
					</div>
				</div>
				<div class="col-md-3 scobox">
					<div class="card border-danger mx-sm-1 p-3">
						<div class="card border-danger text-danger p-3 my-card">
							<span class="fa fa-heart" aria-hidden="true"></span>
						</div>
						<div class="text-danger text-center mt-3">
							<h4>찜</h4>
						</div>
						<div class="text-danger text-center mt-2">
							<h1>346</h1>
						</div>
					</div>
				</div>

			</div>
		</div>
		
		

		<!--찜목록 카드 -->
		<div class="cardtitlewrap">
			<div class="cardtitle">
				<h3>#찜목록</h3>
			</div>
			<div class="cardmore">
				<a href="#">더보기</a>
			</div>
		</div>
		<div class="mycard">
			<div class="row multicar">
				<div class="MultiCarousel" data-items="1,3,5,6" data-slide="1"
					id="MultiCarousel" data-interval="1000">
					<div class="MultiCarousel-inner">
					<!-- for 문으로 영화 찜 목록 불러오기 -->
						<div class="item">
							<div class="pad1">
								<img alt="" src="<%=request.getContextPath()%>/images/movie_image.jpg">
							</div>
						</div>
					</div>
					<button class="btn btn-primary leftLst"><</button>
					<button class="btn btn-primary rightLst">></button>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6">
				<h2 class="page-header">My Comments</h2>
					<!-- First Comment -->
					<div class="row">
						<div class="col-md-2 col-sm-2 hidden-xs">
							<figure class="thumbnail">
								<img class="img-responsive"
									src="http://www.tangoflooring.ca/wp-content/uploads/2015/07/user-avatar-placeholder.png" />
							</figure>
						</div>
						<div class="col-md-10 col-sm-10">
							<div class="panel panel-default arrow left">
								<div class="panel-body">
									<div class="text-left">
										<div class="mycomment">영화이름</div>
										<div class="mycomment">내 평점</div>
									</div>
									<div class="comment-post">
										<p>Lorem ipsum dolor sit amet, consectetur adipisicing
											elit, sed do eiusmod tempor incididunt ut labore et dolore
											magna aliqua. Ut enim ad minim veniam</p>
									</div>
									<p class="text-right">
										<a href="#" class="btn"> 더보기</a>
									</p>
								</div>
							</div>
						</div>
					</div>
			</div>
			<div class="col-md-6"></div>
		</div>

	</div>
</body>
</html>
<%@ include file="../inc/bottom.jsp"%>