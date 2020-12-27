<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
	.movieDetail-comment__list{
		border: 1px solid #e5e5e5;
		border-radius: 10px;
		min-height: 100px;
		width: 900px;
	}
	
	.cmtList-eachCmt__empty{
		height: 100px;
		width: 900px;
		display: table;
		text-align: center;
	}
	
	.cmtList-eachCmt__empty p{
		text-align:center;
		display:table-cell;
		vertical-align:middle;
		color=#7f7f7f;
	}
	
	.cmtList-eachCmt {
		font-color:#343a40;
	}
	
	.cmtList-eachCmt__cmtTop{
		display: flex;
		justify-content: space-between;
		padding: 10px;
	}
	
	.cmtList-eachCmt__userid{
	    font-weight: bold;
	    font-size: 1.2em;
	}
	
	.cmtList-eachCmt__contents{
		border: 1px solid #a5a5a7;
		padding: 10px;
		width: 100%;
		border-radius: 5px;
		margin-bottom: 10px;
	}
	
	.cmtList-eachCmt__cmtButtons{
		
	}
	
	.cmtList-warningMsg{
		font-size: 1.2em;
	}
	
	.cmtList-eachCmt__cmtButtons a {
		margin-left: 5px;
	}
</style>
<script>
function pageFunc(curPage){
	$('input[name=currentPage]').val(curPage);
	$('form[name=frmPage]').submit();
}
</script>
<section class="movieDeatil-commentList">
<form name="frmPage" action="<c:url value='/movie/movieDetail.do?mvNo=${mvNo}'/>" method="post" >
	<input type="hidden" name="currentPage" value="${pageVo.currentPage}">
	<input type="hidden" name="mvNo" value="${mvNo}">
</form>
	<h3>코멘트 ${cmtListSize}건</h3>
	<div class="movieDetail-comment__list">
			<!-- 코멘트가 없을 때 -->
			<c:if test="${empty cmtList}">
				<div class="cmtList-eachCmt__empty">
					<p class="cmtList-warningMsg" >등록된 코멘트가 없습니다.</p>
				</div>
			</c:if>
			<!-- 코멘트가 있을 때 -->
			<c:if test="${!empty cmtList}">  <!-- 코멘트리스트가 비어있지 않다면 -->
				<c:set var="num" value="${pageVo.num}"/> <!-- 전체 코멘트 개수를 세팅하고 -->
				<c:set var="curPos" value="${pageVo.curPos}"/> <!-- 현재 출력된 코멘트의 위치를 세팅한다 -->
				<c:forEach var="i" begin="0" end="${pageVo.pageSize}"> <!-- 페이지사이즈만큼 돌릴거야 포문을 -->
				<c:if test="${num>=1}"> <!-- 하나라도 출력할 코멘트가 남아있다면 -->
					<c:set var="cmt" value="${cmtList[curPos]}"/> <!-- 코멘트 객체를 만들어 -->
					<c:set var="userid" value="${cmt.userid}"/> 
					<c:set var="curPos" value="${curPos+1}"/>
					<c:set var="num" value="${num-1}"/>
					<div class="cmtList-eachCmt">
						<div class="cmtList-eachCmt__cmtTop">
							<span class="cmtList-eachCmt__userid">${cmt} ${cmt.userid}</span>
							<span class="cmtList-eachCmt__cmtRegDate">
								<fmt:formatDate value="${cmt.cmtRegdate}" pattern="yyyy-MM-dd" /></span>
						</div>
						<div class="cmtList-eachCmt__contents">
							<p class="cmtList-eachCmt__contents__text">${cmt.cmtText}</p>
						</div>
						<div class="cmtList-eachCmt__cmtButtons">
							공감<a href="#" class="btn btn-group-xs"><i class="fas fa-thumbs-up"></i></a>
						    <a href="#" class="btn btn-group-xs"><i class="far fa-thumbs-up"></i></a>${cmt.agrCnt}
						       비공감<a href="#" class="btn btn-group-xs"><i class="fas fa-thumbs-down"></i></a>
						    <a href="#" class="btn btn-group-xs"><i class="far fa-thumbs-down"></i></a>${cmt.dagrCnt}
						</div>
					</div>
				</c:if>
			</c:forEach>
		</c:if>
	</div> <!-- 코멘트리스트 끝 -->
	<div class="cmtList-Paging">
		<!-- 이전 블록으로 이동 -->
		<c:if test="${pageVo.firstPage>1}">
			<a href="#" onclick="pageFunc(${pageVo.firstPage-1})">
				<i class="fas fa-caret-left"></i>
			</a>
		</c:if>
		<!-- [1][2][3][4][5] 페이징-->
		<c:forEach var="i" begin="${pageVo.firstPage}" end="${pageVo.lastPage}">
			<c:if test="${i<=pageVo.totalPage}">
				<c:if test="${pageVo.currentPage==i}">
					<span style="font-weight: bold; color:#FF2F6E;">${i}</span>
				</c:if>
				<c:if test="${pageVo.currentPage!=i}">
					<a href="#" style="color:#343a40;" onclick="pageFunc(${i})></a>
				</c:if>
			</c:if>
		</c:forEach>
		
		<!-- 다음 블록으로 이동 -->
		<c:if test="${pageVo.lastPage<pageVo.totalPage}">
			<a href="#" onclick="pageFunc(${pageVo.firstPage-1})">
				<i class="fas fa-caret-right"></i>
			</a>
		</c:if>
	</div>
</section>