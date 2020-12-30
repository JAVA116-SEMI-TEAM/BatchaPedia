<%@page import="com.batcha.mycmt.model.MyCmtVO"%>
<%@page import="com.batcha.mymv.model.MyMvVO"%>
<%@page import="java.util.List"%>
<%@page import="com.batcha.memInfo.model.MemInfoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../inc/top.jsp"%>

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
.jumbotron.mysection {
    margin-bottom: 90px;
}

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
	padding-bottom: 140px;
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
    margin-bottom: 95px;
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
	float: left;
}

.mainCmt-warp {
    width: 100%;
    margin-bottom: 150px;
    position: relative;
}

.go-cmt {
    float: right;
    padding: 0px 10px 5px 3px;
    
}

.go-cmta{
	color: #343a40 ;
	
}


.mycmtRd {
    margin: 7px;
    height: 162px;
    clear: both;
}

.mycmtsbox {
    height: 211px;
    width: 264px;
    margin: 0 5px 0;
    /* padding: 5px; */
    float: left;
    padding: 0 5px;
}

.maincmt-title{
	float: left;
}


</style>

<%

	HttpSession session1=request.getSession();
	String myId=(String)session1.getAttribute("userid");
	
	List<MyMvVO> mvlist = (List<MyMvVO>)request.getAttribute("mvlist");
	
%>
<body>
	<input type="hidden" name="myno" value="<%=myId%>">
	<div class="container">
		<div id="top-wrap">
			<div id="mytitle">
				<h2 class="page-header"><%=myId %>님의 페이지입니다
				</h2>
			</div>
			<div id="myedit">
				<a href="<%=request.getContextPath()%>/myPage/myDetail.do">회원정보수정</a>
			</div>
		</div>



		<!--찜목록 카드 -->
		<div class="cardtitlewrap">
			<div class="cardtitle">
				<h3>#찜목록</h3>
			</div>
			<div class="cardmore">
				<a href="<%=request.getContextPath()%>/myPage/mymv.do">더보기</a>
			</div>
		</div>
		<div class="mycard">
			<div class="row multicar">
				<div class="MultiCarousel" data-items="1,3,5,6" data-slide="1"
					id="MultiCarousel" data-interval="1000">
					<div class="MultiCarousel-inner">
						<!-- for 문으로 영화 찜 목록 불러오기 -->
						<%for(int i=0;i<mvlist.size();i++){%>
						<%MyMvVO mmvo=mvlist.get(i); %>
						<div class="item">
							<div class="pad1">
								<div>
									<img src="<%=mmvo.getThumbnail() %>" alt="영화 사진"> <span><%=mmvo.getMvTitle() %></span>
								</div>
							</div>
						</div>
						<%}%>
					</div>
					<button class="btn btn-primary leftLst"><</button>
					<button class="btn btn-primary rightLst">></button>
				</div>
			</div>
		</div>

		<div class="mainCmt-warp">
			<div class="maincmt-title"><h3>#내 코멘트<h3></div>
			<%@ include file="/myPage/mainCmt.jsp" %>
		</div>

	</div>
	<div style="clear: both;"></div>
</body>

<%@ include file="../inc/bottom.jsp"%>