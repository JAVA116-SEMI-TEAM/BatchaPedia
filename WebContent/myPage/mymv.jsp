<%@page import="com.batcha.mymv.model.MyMvVO"%>
<%@page import="com.batcha.mycmt.model.MyCmtVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../inc/top.jsp"%>

<style type="text/css">
.mymvbox-warp {
	width: 1200px;
	margin: 50px auto;
}

.mvStitle {
	margin: 7px;
}

.mymvbox-warp2 {
	margin: 0 auto;
}

.mymvbox {
	margin: 12px;
	width: 209px;
	float: left;
}

.mymvbox img{
width: 100%;
height: 100%;
}

</style>
<%
	List<MyMvVO> mvlist= (List<MyMvVO>)request.getAttribute("mvlist");
	
	MyMvVO mmvo = new MyMvVO();
	
	
%>
<div class="mymvbox-warp">
	<div class="mvStitle">
		<h2>#찜목록</h2>
	</div>
	<br>
	<div class="mymvbox-warp2">
		
			<%if(mvlist==null && mvlist.isEmpty()){%>
				<div class="nomvlist">찜한 영화가 없습니다</div>
			<%}else{%>
			<%for(int i=0;i<mvlist.size();i++){
					mmvo=mvlist.get(i); %>
			<div class="mymvbox">
				<a href="#" class="image fit"><img src="<%=mmvo.getThumbnail() %>" alt="영화 사진"></a> 
					<span><%=mmvo.getMvTitle() %></span>
			</div>
			<%}
			} %>
		
	</div>

</div>
<div style="clear: both; margin-bottom: 100px;"></div>

<%@ include file="../inc/bottom.jsp"%>