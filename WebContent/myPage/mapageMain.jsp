<%@page import="com.batcha.memInfo.model.MemInfoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<%
	MemInfoVO memVo = new MemInfoVO();
%>
	<div id="wrap">
		<div id="top-wrap">
			<div><h2 class="page-header"><%=memVo.getName() %>님의 페이지입니다</h2></div>
			<div><a href="#">화원정보수정</a></div>
		</div><br><br>
		<div id="com-wrap">
			<div><h3>#내 평점</h3></div>
			<div><a>더보기</a></div>
			<div>
				<div>
					<div> < </div>
					<!-- for문 시작 -->
					<div>
						<div>
							<div><img alt="" src="" class="img-rounded"></div>
							<div style="display: none">
								<h4>평점</h4>
								<span class="text-muted">Something else</span>
							</div>
						</div>
						<a href="#"></a>
					</div>
					<!-- for문 끝 -->
					<div> > </div>
				</div>
			</div>
		</div>
	</div>
	