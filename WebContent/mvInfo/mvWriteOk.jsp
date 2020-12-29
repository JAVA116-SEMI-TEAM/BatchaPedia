<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/mvInfo.css" />
<!-- Drowdown -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" 
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" 
	integrity="sha512-mSYUmp1HYZDFaVKK//63EcZq4iFWFjxSL+Z3T/aCt4IO9Cejm03q3NKKYN6pFQzY0SBOr8h+eCIAZHPXcpZaNw==" crossorigin="anonymous" />
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" 
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" 
integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js" integrity="sha512-T/tUfKSV1bihCnd+MxKD0Hm1uBBroVYBOYSk1knyvQ9VyZJpc/ALb4P0r6ubwVPSGB2GvjeoMAJJImBG12TiaQ==" crossorigin="anonymous"></script>
<!-- ckEditor 추가-->
<script src="../resource/ckeditor/ckeditor.js"></script>
<script type='text/javascript'>
	$(function() {
		$("#boxOffice, #period").hide();
		
		$('#startdate, #enddate').datepicker({
			calendarWeeks : false,
			todayHighlight : true,
			autoclose : true,
			format : "yyyy-mm-dd",
			language : "kr"
		});
	
	});
		function show(){
			$("#boxOffice, #period").show();
			$('#status').html("상영중");
		}
		
		function hide(){
			$("#boxOffice, #period").hide();
			$('#status').html("상영종료");
		}
</script>
<style type="text/css">
input[type=text]:focus, #inlineFormCustomSelect:focus, .boxOffice:focus
	{
	border-color: rgba(153, 153, 153, 0.8);
	box-shadow: 0 1px 1px rgba(153, 153, 153, 0.075) inset, 0 0 8px
		rgba(153, 153, 153, 0.6);
	outline: 0 none;
}

.btn-group{
	margin-left: 553px;
	margin-bottom:50px;
	margin-top:0px;
	
}


</style>


<%
String realFolder="";
String uploadFolder="thumbnail";
String encType="utf-8";
int maxSize=5*1024*1024;

ServletContext context=getServletContext();

realFolder=context.getRealPath(uploadFolder);


%>
<div class="mvInfo">
	<h3>영화 정보 등록</h3>
	
	<form name="mvWriteFrm" method="post" 
		enctype="multipart/form-data">
			
		<!-- 상영 상태  -->
		<div class="btn-group" style="float:left">
		  <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" 
		  data-display="static" aria-haspopup="true" aria-expanded="false" id="status">
		   상영상태
		  </button>
		  <div class="dropdown-menu dropdown-menu-lg-right">
		    <button class="dropdown-item" type="button" 
		    onclick="hide()">상영종료</button>
		    <button class="dropdown-item" type="button" 
		    onclick="show()" style="margin-right:0;">상영중</button>
		  </div>
		</div>
		<br> <br>
		
		<!-- 썸네일 이미지 -->
		<div class="card" id="card" style="clear:both;width:200px;height:250px;float:left;">
			<a href="<%=request.getContextPath()%>/faqMgr/faqList.do"> <img
				src="../images/test.jpg" id="highRank" class="card-img-top"
				alt="..."></a>
			<div class="card-body">
				<span class="card-text title">인생은 아름다워</span> <span
					class="card-text">2020</span>
			</div>
		</div>
		
		
		<div style="float:left;width:522px;margin-left:30px">
		<!-- 영화 코드 -->
		<div >
			<div class="row mb-3">
			<label for="mvTitle" class="col-sm-2">영화코드</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="mvTitle" name="mvTitle">
			</div>
		</div>
		
		
		
		<!-- 영화 제목 -->
		
		<div class="row mb-3">
			<label for="mvTitle" class="col-sm-2">제목</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="mvTitle" name="mvTitle">
			</div>
		</div>
		<div class="row mb-3">
			<label for="mvTitle" class="col-sm-2">제목</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="mvTitle" name="mvTitle">
			</div>
		</div>
		
		<!-- 장르 -->
		<div class="row mb-3">
			<label for="genre" class="col-sm-2">장르</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="genre" name="genre">
			</div>
		</div>
		
		<!-- 감독 -->
		<div class="row mb-3">
			<label for="director" class="col-sm-2">감독</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="director"
					name="director">
			</div>
		</div>
		
		</div><!--div닫기-->

</div> <!-- 오른쪽 -->
		<!-- 주연배우 -->
		<div class="row mb-3" style="clear:both;">
			<label for="actors" class="col-sm-2">주연 배우</label>
			<div class="col-sm-8">
				<input type="text" class="form-control"
					placeholder="주연배우 1/주연배우2/주연배우3/주연배우4" id="actors" name="actors">
			</div>
		</div>
		
		<!-- 파일 업로드 -->
		<div class="row mb-3">
			<div class="col-sm-8">
				<input type="file" class="form-control" name="filename" id="filename">
				<button class="btn btn-dark" id="write" type="submit"
			onclick="javascript:form.action='<%=request.getContextPath()%>/mvInfo/mvWrite_ok.do';">
						이미지 등록</button>
			</div>
		</div>
		

		<!-- 제작국가  -->
		<div class="row mb-3">
			<label for="nation" class="col-sm-2">제작국가</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="nation" name="nation">
			</div>
		</div>


		<!-- 제작년도 -->
		<div class="row mb-3">
			<label for="makeYear" class="col-sm-2">제작년도</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="makeYear"
					name="makeYear">
			</div>
		</div>

		<!-- 박스오피스 순위 -->
		<div class="row mb-3" id="boxOffice">
			<label for="boxOffice" class="col-sm-2">순위</label>
			<div class="col-sm-8">
				<select class="form-control boxOffice" aria-label="boxOffice"
					name="boxOffice">
					<option selected>박스오피스순위</option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
				</select>
			</div>
		</div>

		<!-- 상영 기간-->
		<div class="row mb-3" id="period">
			<label for="startdate" class="col-sm-2">상영기간</label>
			<div class="col-sm-4">
				<div class="input-group date">
					<input type="text" class="form-control" name="startdate"
						id="startdate"> <span class="input-group-addon"> <i
						class="glyphicon glyphicon-calendar"></i></span>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="input-group date">
					<input type="text" class="form-control" name="enddate" id="enddate">
					<span class="input-group-addon"> <i
						class="glyphicon glyphicon-calendar"></i></span>
				</div>
			</div>
		</div>
	
		<!-- 줄거리 -->
		<div class="row mb-3">
			<div class="col-sm-10">
				<label for="floatingTextarea2">영화 줄거리</label>
				<textarea class="form-control" placeholder="줄거리 입력" id="story"
					name="story"></textarea>
				<script>
					CKEDITOR.replace('story');
				</script>
			</div>
		</div>
		
		<!-- 등록 버튼 -->
		<div class="row mb-3" style="text-align: center;">
			<div class="col-sm-10">
				<button class="btn btn-dark" id="write" type="submit"
					onclick="javascript:form.action='<%=request.getContextPath()%>/mvInfo/mvWrite_ok.do';">
					등록</button>
				<button class="btn btn-dark" id="del" type="submit"
					onclick="javascript:form.action='<%=request.getContextPath()%>/mvInfo/mvList.do';">
					목록</button>
			</div>
		</div>
		</form>
</div>



<%@ include file="../inc/bottom.jsp"%>