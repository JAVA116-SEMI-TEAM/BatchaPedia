<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/mvInfo.css" />
<!-- ckEditor 추가-->
<script src="../resource/ckeditor/ckeditor.js"></script>

<style type="text/css">

input[type=text]:focus,  .boxOffice:focus,
input[type=text]:hover, select:hover
	{
	border-color: rgba(153, 153, 153, 0.8);
	box-shadow: 0 1px 1px rgba(153, 153, 153, 0.075) inset, 0 0 8px
		rgba(153, 153, 153, 0.6);
	outline: 0 none;
}


label{
	color:#343a40;
}

#label{
	margin-right:0px;
	padding-right:0px;
}


.card {
	width: 200px;
	height: 250px;
	border: 0.8px solid silver;
}


#textbox{
	margin-left:0;
 	padding-left:0;
}

.btn-group{
	margin-bottom:15px;
	margin-left:550px;
	width:300px;
}


.mvWrite{
	margin-bottom:50px;
}

input:required {
    box-shadow:none;
    
}
input:invalid {
    box-shadow:0 0 3px rgba(255,0,0,0.8);
    border:1.2px solid rgb(255,0,0);
}
input:invalid::placeholder {
color:rgba(255,0,0,0.4);
font-style: italic;
}

/* 
input[type=text]:hover, select:hover{
	background-color:gray;
	color:white;
} */
</style>
<div class="mvInfo mvWrite">
	
	<form name="mvWriteFrm" id="mvWriteFrm" method="post">
	<!-- 상단 (타이틀, 상영상태) -->
	<div>
		<p style="font-size:25px;color:#343a40;">영화 정보 등록</p>
		<hr style="margin-top:10px;margin-left:-20px;width:655px;">
	</div>
	<!-- 등록/목록 버튼  -->
	<div class="btn-group">
		<div class="col-sm-10">
			<button class="btn btn-outline-secondary" id="writeBtn" type="submit"
				onclick="javascript:form.action='<%=request.getContextPath()%>/mvInfo/mvWrite_ok.do';">
				등록</button>
		</div>
	</div>
	
	<!-- 썸네일(왼쪽) -->
	<div style="float:left;clear:both;">
		<div class="column left-box">
				<div class="card" id="card">
					<a style="color:gray;text-align:center;margin-top:100px">영화 이미지</a>
					<div class="card-body">
						<span class="card-text title"></span> 
						<span class="card-text" style="font-size:14px;"></span>
						<span class="card-text" style="font-size:14px;"></span>
					</div>
				</div>
			</div>
	</div>
	<!-- 썸네일 오른쪽 -->
	<div style="margin-left:15px;float:left;width:495px">
		
		<!-- 영화명 -->
		<div class="row mb-3 success" >
			<label for="mvTitle" id="label" class="col-sm-2">영화명</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="mvTitle" name="mvTitle">
			</div>
		</div>
		<!-- 영문명 -->
		<div class="row mb-3 error">
			<label for="mvTitleEn" id="label" class="col-sm-2">영문명</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="mvTitleEn" name="mvTitleEn">
			</div>
		</div>
		<!-- 영화코드-->
		<div class="row mb-3">
			<label for="mvCode" id="label" class="col-sm-2" style="margin-right:0;">영화코드</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="mvCode" name="mvCode">
			</div>
		</div>
		<!-- 장르 -->
		<div class="row mb-3">
			<label for="genre" id="label" class="col-sm-2">장르</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="genre" name="genre">
			</div>
		</div>
		<!-- 제작연도 -->
		<div class="row mb-3">
			<label for="makeYear" id="label" class="col-sm-2">제작연도</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="makeYear" name="makeYear">
			</div>
		</div>
	</div>
	
	<!-- 썸네일 하단  -->
	<div style="clear:both;width:754px;margin-top:5px">
		<!-- 감독 -->
		<div class="row mb-3">
			<label for="director" id="label" class="col-sm-2">감독</label>
			<div class="col-sm-8" id="textbox">
				<input type="text" class="form-control" id="director" name="director">
			</div>
		</div>
		<!-- 주연배우 -->
		<div class="row mb-3">
			<label for="actors" id="label" class="col-sm-2">주연배우</label>
			<div class="col-sm-8" id="textbox">
				<input type="text" class="form-control" id="actors" name="actors">
			</div>
		</div>
		
		
		<!-- 줄거리 -->
		<div class="row mb-3" >
				<div class="col-sm-10">
					<textarea class="form-control" placeholder="줄거리 입력" id="story"
						name="story">(영화 줄거리를 입력하세요)</textarea>
					<script>
					CKEDITOR.replace('story',{height:'400px'});
					CKEDITOR.ENTER_BR;
					CKEDITOR.editorConfig = function( config ) {
		                  config.enterMode = CKEDITOR.ENTER_BR;
		        	};
					</script>
				</div>
		</div>
		<div>
			<button class="btn btn-outline-secondary" id="listBtn" type="button"
			onclick="location.href='<%=request.getContextPath()%>/mvInfo/mvList.do';">
			목록보기</button>
		</div>
	</div>
	</form>
</div>
<script type="text/javascript" src="jquery-3.5.1.min.js"></script>
<script type="text/javascript">

$(function(){
	$('#writeBtn').click(function(){
		if($('#mvTitle').val().length<1){
			$('#mvTitle').attr('required',true)
			.attr('placeholder','영화명을 입력하세요');
			event.preventDefault();
		}else if($('#mvCode').val().length<1){
			$('#mvCode').attr('required',true)
			.attr('placeholder','영화코드를 입력하세요');
			event.preventDefault();
		}else if($('#makeYear').val().length<1){
			$('#makeYear').attr('required',true)
			.attr('placeholder','제작연도를 입력하세요');
			event.preventDefault();
		}else if($('#director').val().length<1){
			$('#director').attr('required',true)
			.attr('placeholder','감독명을 입력하세요');
			event.preventDefault();
		}	
	});
});
	
</script>


<%@ include file="../inc/bottom.jsp"%>