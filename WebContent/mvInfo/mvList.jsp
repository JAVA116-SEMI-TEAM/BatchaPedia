<%@page import="com.batcha.common.PagingVO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.batcha.mvInfo.model.MvInfoVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/mvInfo.css" />
<!-- Drowdown -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
<script src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/17119/tablesort.number.min.js"></script>
<script src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/17119/tablesort.min.js"></script>

<%
	//뷰페이지
	List<MvInfoVO> mvList = (List<MvInfoVO>) request.getAttribute("mvList");
	PagingVO pageVo = (PagingVO)request.getAttribute("pageVo");
	
	String option=request.getParameter("option");
	String keyword=request.getParameter("keyword");
	if(keyword==null || keyword.isEmpty()){
		keyword="";
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
%>
<script type="text/javascript" src="../js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">	
	$(function(){
		$('.table tbody tr a').hover(function(){
			$(this).css('color', '#FF2F6E');
		}, function(){
			$(this).css('color', 'white');
		});
	});
</script>
<style type="text/css">
input[type=text]:focus, #inlineFormCustomSelect:focus {
    border-color: rgba(153,153,153, 0.8);
    box-shadow: 0 1px 1px rgba(153,153,153, 0.075) inset, 
    			0 0 8px rgba(153,153,153, 0.6);
    outline: 0 none;
}

#currentP{
	color:#FF2F6E;
}

.mvSearch{
	/* margin-left:246px; */
}



</style>

<div class="mvList" style="width:800px;">
	
	<!-- 검색 조건   -->
	<div class="mvSearch" style="float:left;">
	<form class="form-inline" name="mvInfoSearch" 
		action="<%=request.getContextPath() %>/mvInfo/mvList.do" >
	      <label class="mr-sm-2 sr-only" for="inlineFormCustomSelect"></label>
	      <select class="custom-select mb-2 mr-sm-4" 
	      		id="inlineFormCustomSelect" name="option">
	        <option selected>검색어</option>
	        <option value="mvCode"
		        <%if("mvCode".equals(option)){%>
		        	selected="selected"
		        <%}%>
	        >영화코드</option>
	        <option value="mvTitle"
		        <%if("mvTitle".equals(option)){%>
		        	selected="selected"
		        <%}%>
	        >영화명</option>
	        <option value="actors"
		        <%if("actors".equals(option)){%>
		        	selected="selected"
		        <%}%>
	        >주연배우</option>
	        <option value="director"
		        <%if("director".equals(option)){%>
		        	selected="selected"
		        <%}%>
	        >감독명</option>
	      </select>
	  <label class="sr-only" for="inlineFormInputName2"></label>
	  <input type="text" class="form-control mb-2 mr-sm-4" 
	  id="inlineFormInputName2" placeholder="검색어를 입력하세요"
	  name="keyword" value="<%=keyword %>" >
	  <button type="submit" class="btn btn-secondary mb-2 mr-sm-2" >검색</button>
	</form>
	</div>
	
	<!-- 정렬  -->
	<div class="btn-group sortCondition" style="float:right;">
	  <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" 
	  data-display="static" aria-haspopup="true" aria-expanded="false" id="status" name="b">
	   검색 조건
	  </button>
	  <div class="dropdown-menu dropdown-menu-lg-right">
	    <button class="dropdown-item" type="button" id="titleSort">영화명순(ㄱ~z)</button>
	    <button class="dropdown-item" type="button">제작연도순</button>
	  </div>
	</div>
	
		
	<!-- 영화 목록  -->
	<table class="table caption-top table-dark table-striped" id="mvListTable">
		<thead>
			<tr>
				<th id="listCode" scope="col">영화코드</th>
				<th id="listTitle" scope="col">영화명</th>
				<th id="listGenre" scope="col">장르</th>
				<th id="listMakeYear" scope="col">제작연도</th>
			</tr>
		</thead>
		<tbody>
			
		<%if (mvList == null || mvList.isEmpty()) {%>
			<tr>
				<td colspan="4">데이터가 존재하지 않습니다.</td>
			</tr>
		
		<%} else {%>
			<%
				int num=pageVo.getNum();
				int curPos=pageVo.getCurPos();
			
				for (int i=0;i<pageVo.getPageSize();i++) {
					if(num<1) break;
					
					MvInfoVO mVo = mvList.get(curPos++);
					num--;%>
					<tr class="list">
						<td id="listNo"><%=mVo.getMvCode()%></td>
						<td id="listTitle">
						<a href="<%=request.getContextPath()%>/mvInfo/mvEdit.do?mvNo=<%=mVo.getMvNo()%>">
								<%=mVo.getMvTitle()%></a></td>
						<td id="listGenre"><%=mVo.getGenre()%></td>
						<td id="listMakeYear"><%=mVo.getMakeYear()%></td>
					</tr>
			<%	}%>
		<%	}%>
			
		</tbody>
	</table>
	
	
	<!-- 페이지 번호 -->
	<!-- 이전 페이지 -->
	<div class="pageNum" style="float:left;margin-left:300px">
	<nav aria-label="Page navigation example" > 
		<ul class="pagination" >
			<%if(pageVo.getFirstPage()>1) { %>
				<li class="page-item">
				<a class="page-link text-dark" aria-label="Previous"
				href="<%=request.getContextPath()%>/mvInfo/mvList.do?currentPage=<%=pageVo.getFirstPage()-1%>&option=<%=option%>&keyword=<%=keyword %>"> 
				<span aria-hidden="true">&laquo;</span>
				</a></li>
			<%}// %>
		<!-- 페이지번호 반복 -->
		<%
			for(int i=pageVo.getFirstPage();i<=pageVo.getLastPage();i++){
				if(i>pageVo.getTotalPage()) break;
				
				if(i==pageVo.getCurrentPage()){%>
					<li class="page-item"><a class="page-link" 
						id="currentP" href="#"><%=i %></a></li>
			  <%}else{%>
					<li class="page-item">
					<a class="page-link text-dark"
					href="<%=request.getContextPath()%>/mvInfo/mvList.do?currentPage=<%=i%>&option=<%=option%>&keyword=<%=keyword %>">
				<%=i %></a></li>
			  <%}//if%>
		  <%}//for%>
		
		<!-- 다음 블럭 -->
		<%if(pageVo.getLastPage() < pageVo.getTotalPage()){ %>
			<li class="page-item"><a class="page-link text-dark" 
		href="<%=request.getContextPath() %>/mvInfo/mvList.do?currentPage=<%=pageVo.getLastPage()+1%>&option=<%=option %>&keyword=<%=keyword %>"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		<%} %>
		</ul>
		</nav>
		</div>
		<!-- 영화 등록 버튼 -->
		<div class="writeBtn"  style="float:right">
			<button class="btn btn-secondary btn-sm" id="writeBtn"
				type="button"
				onclick="javascript:location.href='<%=request.getContextPath()%>/mvInfo/mvWrite.do';">
				영화 등록</button>
		</div>
		<div style="clear:both;">
		</div>
	
</div>

<script type="text/javascript">
new Tablesort(document.getElementById('mvListTable'));
</script>

<style type="text/css">
th[role=columnheader]:not(.no-sort) {
	cursor: pointer;
}

th[role=columnheader]:not(.no-sort):after {
	content: '';
	float: right;
	margin-top: 7px;
	border-width: 0 4px 4px;
	border-style: solid;
	border-color: #404040 transparent;
	visibility: hidden;
	opacity: 0;
	-ms-user-select: none;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

th[aria-sort=ascending]:not(.no-sort):after {
	border-bottom: none;
	border-width: 4px 4px 0;
}

th[aria-sort]:not(.no-sort):after {
	visibility: visible;
	opacity: 0.4;
}

th[role=columnheader]:not(.no-sort):hover:after {
	visibility: visible;
	opacity: 1;
}
</style>
<%@ include file="../inc/bottom.jsp"%>
