<%@page import="com.batcha.mvInfo.model.MvInfoVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp"%>
<style type="text/css">
.mvSearch {
	margin: 30px;
}

.result {
	margin: 0px;
	width: 1500px;
	height: 400px;
}


.card {
	width: 200px;
	height: 300px;
	border: 0px;
}

.card-body {
	padding: 5px;
}

#highRank {
	width: 100%;
	height: 100%;
}

.column {
	margin-right: 12px;
}

span {
	color: gray;
	display: block;
}

h5{
	
}
.title {
	color: #343a40;
	font-weight: bold;
}

#more {
	color: #FF2F6E;
	text-decoration: none;
	font-weight: bold;
}

img:not([alt=logo]){
	border:0.5px solid silver;

}

#searchD{
	display:flex;
	align-content:flex-start;
}

#res{
	margin:15px 0 25px 10px;
	font-weight:bold;
}
</style>
<%
List<MvInfoVO> mvList = (List<MvInfoVO>) request.getAttribute("mvList");

%>
<div class="mvSearch">
	<div class="result" id="highRanking">
		<h5 style="margin:25px;font-weight:bold;color: #343a40;">상위 검색 결과</h5>
		<div class="row" style="width: 100%; margin-left: 20px;">
		
		<%
			for(int i=0;i<searchList.size();i++){ 
			MvInfoVO mVo=searchList.get(i);
				if(i==6){
					break;
				}
		%>
			<!-- 첫번째 -->
			<div class="column left-box">
				<div class="card" id="card">
					<a href="<%=request.getContextPath()%>/faqMgr/faqList.do"> <img
						src="<%=mVo.getThumbnail() %>" id="highRank" class="card-img-top"
						alt="..." style="width: 100%; height: 100%"></a>
					<div class="card-body">
						<span class="card-text title"><%=mVo.getMvTitle() %></span> 
						<span class="card-text" style="font-size:14px;"><%=mVo.getMakeYear() %> · <%=mVo.getNation() %></span>
						<span class="card-text" style="font-size:14px;"><%=mVo.getGenre()%></span>
					</div>
				</div>
			</div>
		<%} %>
		</div>
	</div> 
	
	
	<!-- 상세 검색 -->
	<div class="result" id="detail">
		<hr class="searchHr">
		<header class="detailHeader" style="width: 100%; margin-left: 20px;">
			<div style="width: 1300px; float: left">
				<h5 id="res" style="color: #343a40;">영화</h5>
			</div>
			<div style="float: right">
				<a id="more" style="margin-right:30px;"
			href="<%=request.getContextPath()%>/mvSearch/mvSearchRes.do?mvmvSearchk=<%=mvSearchk %>">
				더보기</a>
			</div>
			<br>
		</header>
		
		<div class="row" style="clear: both;margin-left: 20px;width:1000px">
				<%for(int i=0;i<searchList.size();i++){ 
					MvInfoVO mVo = searchList.get(i); 
					if(i==9){
						break;
					}%>
					<div class="record" style="float:left;">
						<div class="media" style="height: 70px;">
							<img src="<%=mVo.getThumbnail() %>" class=""
							style="width: 4rem; height: 4rem;" alt="...">
							<div class="" style="padding: 10px; width: 250px">
								<span class="title"><%=mVo.getMvTitle() %></span> 
								<span style="font-size:14px;"><%=mVo.getMakeYear() %> · <%=mVo.getNation() %></span>
								<hr style="width: 180px; margin-top: 15px">
							</div>
						</div>
					</div>
				<%} %>
		</div>
		
	</div>

</div>


<%@ include file="../inc/bottom.jsp"%>
