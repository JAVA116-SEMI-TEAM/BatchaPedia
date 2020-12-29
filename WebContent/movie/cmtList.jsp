<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
function pageFunc(curPage){
	$('input[name=currentPage]').val(curPage);
	$('form[name=frmPage]').submit();
}
</script>
<%
	String userid=request.getParameter("userid");
%>
<section class="movieDeatil-commentList">
<form name="frmPage" action="<c:url value='/movie/movieDetail.do?mvNo=${cmt.mvNo}'/>" method="post" >
	<input type="hidden" name="currentPage" value="${pageVo.currentPage}">
	<input type="hidden" name="mvNo" value="${cmt.mvNo}">
</form>
	<h4>코멘트 ${fn:length(cmtList)}건</h4>
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
				<c:forEach var="i" begin="1" end="${pageVo.pageSize}"> <!-- 페이지사이즈만큼 돌릴거야 포문을 -->
				<c:if test="${num>=1}"> <!-- 하나라도 출력할 코멘트가 남아있다면 -->
					<c:set var="cmt" value="${cmtList[curPos]}"/> <!-- 코멘트 객체를 만들어 -->
					<c:set var="userid" value="${fn:substring(cmt.userid,0,4)}"/>
					<c:set var="curPos" value="${curPos+1}"/>
					<c:set var="num" value="${num-1}"/>
						<div class="cmtList-eachCmt">
							<div class="cmtList-eachCmt__cmtTop">
								<span class="cmtList-eachCmt__userid">${userid}****</span>
								<span class="cmtList-eachCmt__cmtRegDate">
									<fmt:formatDate value="${cmt.cmtRegdate}" pattern="yyyy-MM-dd" /></span>
							</div>
							<div class="cmtList-eachCmt__contents">
								<p class="cmtList-eachCmt__contents__text">${cmt.cmtText}</p>
							</div>
							<%-- <div class="cmtList-eachCmt__cmtButtons">
								공감<a href="#" class="btn btn-group-xs"><i class="fas fa-thumbs-up"></i></a>
							    <a href="#" class="btn btn-group-xs"><i class="far fa-thumbs-up"></i></a>${cmt.agrCnt}
							       비공감<a href="#" class="btn btn-group-xs"><i class="fas fa-thumbs-down"></i></a>
							    <a href="#" class="btn btn-group-xs"><i class="far fa-thumbs-down"></i></a>${cmt.dagrCnt}
							</div> --%>
						</div>
				</c:if>
			</c:forEach>
		</c:if>
	</div> <!-- 코멘트리스트 끝 -->
	<div class="cmtList-Paging">
		<!-- 이전 블록으로 이동 -->
		<c:if test="${pageVo.firstPage>1}">
			<a href="<c:url value='/movie/movieDetail.do?mvNo=${mvNo}&currentPage=${pageVo.firstPage-1}'/>" >
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
					<a href="<c:url value='/movie/movieDetail.do?mvNo=${mvNo}&currentPage=${i}'/>" style="color:#343a40;" onclick="pageFunc(${i})">[${i}]</a>
				</c:if>
			</c:if>
		</c:forEach>
		<!-- 다음 블록으로 이동 -->
		<c:if test="${pageVo.lastPage<pageVo.totalPage}">
			<a href="<c:url value='/movie/movieDetail.do?mvNo=${mvNo}&currentPage=${pageVo.lastPage+1}'/>">
				<i class="fas fa-caret-right"></i>
			</a>
		</c:if>
	</div>
</section>