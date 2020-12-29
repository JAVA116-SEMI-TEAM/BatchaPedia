<%@page import="com.batcha.mvInfo.model.MvInfoVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp"%>
<style type="text/css">
	.mvSearchRes{
		margin:0 auto;
		width:700px;
	}
	
	#keyword{
		margin-bottom:25px;
		font-size:18px;
		font-weight:bold;
		color:#343a40;
	}
	
	span{
		color: gray;
		display:block;
	}
	
	.title{
		color:#343a40;
		font-weight:bold;
	}
</style>
<%

%>
<div class="mvSearchRes">
	<div class="SearchRes" id="SearchRes">
			<div>
			<%if(searchKeyword!=null || searchKeyword.isEmpty()){ %>	
				
				<p id="keyword">검색어 : <%=searchKeyword %>, 검색결과 : <%=searchList.size()%>건</p>			
			<%} %>	
			</div>
			<hr style="margin-top:10px;margin-bottom:0px">
			<br>
	</div>
	<!-- 상세 검색 결과-->
	<div class="ResList" style="padding-left:10px">
		<ul class="list-unstyled">
			<%for(int i=0;i<searchList.size();i++){
				MvInfoVO mVo=searchList.get(i);
				%>
			<li class="media" style="width:100%" >
			<img src="<%=mVo.getThumbnail() %>" class=""
				style="width: 4rem; height: 4rem;" alt="...">
				<div class="" style="padding: 10px;width:100%;">
					<span class="title"><%=mVo.getMvTitle() %></span> 
					<span style="font-size:14px;"><%=mVo.getMakeYear() %> · <%=mVo.getNation() %></span>
					<hr style="margin-top:20px;margin-bottom:5px">
				</div>
			</li>
			<%} %>
		</ul>
		<br><br>			
	</div>

</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<%@ include file="../inc/bottom.jsp"%>
