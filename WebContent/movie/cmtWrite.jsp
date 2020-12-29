<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>

$(function(){
	$('form[name=cmtFrm]').submit(function(){
		if($('#userid').val().length<1){
			alert("로그인 후 코멘트를 작성할 수 있습니다.");
			event.preventDefault();
			return false;
		}else if($('#cmtText').val().length<1){
			alert("코멘트를 작성해주세요.");
			event.preventDefault();
			return false;
		}
	});
});
</script>
<section id="cmtWriteSection" class="movieDeatil-commentWrite">
	<div class="movieDetail-comment__header"><h4>코멘트 쓰기</h4></div>
		<div class="movieDetail-info-inputComment">
			<form name="cmtFrm" action='<c:url value="/movie/cmtWrite.do?mvNo=${mvVo.mvNo}" />' method="post">
   			<textarea class="form-control" placeholder="코멘트를 입력해보세요." id="cmtText" name="cmtText" rows="5"></textarea>
   			<input type="hidden" id="userid" name="userid" value="${userid}">   			
   			<input type="hidden" id="memNo" name="memNo" value="${memNo}">   	
   			<input type="hidden" id="mvNo" name="mvNo" value="${mvNo}">   	
   			<div class="underCommentTa">
	  				<div class="input-group-prepend">
	  					<!-- <div class="input-group-text">
	     					<input type="checkbox" aria-label="spoiler check">&nbsp;<span>스포일러가 있습니다.</span>
	    				</div> -->
	  				</div>
				<input type="submit" id="cmtBtn" class="btn btn-sm btn-dark" onclick="loginConfirm()" value="코멘트 등록"></button>
			</div>
			</form>
		</form>
	</div>
</section>