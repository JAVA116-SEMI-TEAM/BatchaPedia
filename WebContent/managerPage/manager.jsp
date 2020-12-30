<%@page import="java.sql.SQLException"%>
<%@page import="com.batcha.memInfo.model.MemInfoService"%>
<%@page import="com.batcha.common.Utility"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.batcha.common.PagingVO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.batcha.memInfo.model.MemInfoVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@ include file="../inc/top.jsp"%>

<style type="text/css">
.admin-sidenav {
	width: auto;
	height: auto;
	margin-left: 0px;
}

.admin-sidenav a {
	text-decoration: none;
}

.admin-sidenav li {
	text-align: justify;
	padding: .5rem;
	padding-left: 1rem;
	-webkit-transition: all .2s linear;
	transition: all .2s linear;
	background-color: #000;
	border: 1px solid #333;
}

.admin-sidenav li a {
	color: #fff;
}

.admin-sidenav li a:active {
	border-color: #02d3f5;
}

.admin-sidenav li:hover {
	border-radius: 0 .5rem .5rem 0;
	border-color: #02d3f5;
	-webkit-transform: translate(30px, 0px);
	transform: translate(30px, 0px);
	background: -webkit-linear-gradient(left, #006a7b, #002340);
	background: linear-gradient(to right, #006a7b, #002340);
}

.admin-sidenav li:active {
	border-color: #02d3f5;
}

h2.page-header {
    color: #FF0558;
/*     font-size: 36px; */
    margin: -13px 5px 39px 0;
}

.row.placeholders.total-warp {
    margin-bottom: 85px;
}

.mid-contant{
	 margin-bottom: 42px;
}

.content-warp{
	padding: 0 50px;
}

.nav-warp{
	padding-left: 0;
}

.total-warp{
	position: absolute;
}

.total1 {
    width: 30%;
    float: left;
    margin-left: 20px;
}

a.quicklink.link1 {
    background: #fc6719;
}
a.quicklink {
    display: inline-block;
    width: 220px;
    height: 145px;
    position: relative;
    margin: 40px 125px 120px 0;
}
a.quicklink .ql_caption {
    display: block;
    height: 100%;
    width: 100%;
    position: relative;
}
.outer {
    display: table;
    position: relative;
    vertical-align: middle;
    text-align: center;
    height: 100%;
    width: 100%;
    border-spacing: 0px;
    padding: 0px;
}
.inner {
    display: table-cell;
    position: relative;
    vertical-align: middle;
    text-align: center;
    height: 100%;
    width: 100%;
    border-spacing: 0px;
    padding: 0px;
}
a.quicklink .ql_caption h4, 
a.quicklink .ql_caption h5 {
    margin: 0px;
    padding: 0px;
    text-transform: uppercase;
    line-height: 1.46em;
    color: #fff;
}
a.quicklink.link1 .ql_top {
    border-bottom-color: #fc6719;
}
a.quicklink.link1 .ql_bottom {
    border-top-color: #fc6719;
}


.ql_top {
    bottom: 145px;
    border-bottom: 40px solid #ccc;
}
.ql_bottom {
    top: 145px;
    border-top: 40px solid #ccc;
}
.ql_top, .ql_bottom {
    position: absolute;
    left: 0px;
    width: 0px;
    border-left: 110px solid transparent;
    border-right: 110px solid transparent;
}

a.quicklink.link2 {
    background: #fcf4e7;
}
a.quicklink.link2 .ql_top {
    border-bottom-color: #fcf4e7;
}
a.quicklink.link2 .ql_bottom {
    border-top-color: #fcf4e7;
}


a.quicklink.link3 .ql_top {
    border-bottom-color: #bcbdc0;
}
a.quicklink.link3 .ql_bottom {
    border-top-color: #bcbdc0;
}
a.quicklink.link3 {
    background: #bcbdc0;
}
a.quicklink {
    font-size: 36px;
    font-weight: 500;
    text-decoration:none;
}

.sherchbox {
    margin: 0 auto;
    width: 800px;
}

.page-warp{
	margin: 0 auto;
	width: 800px;
}

#contents {
    margin-top: 130px;
    margin-left: 0px;
    margin-right: 0px;
    margin-bottom: 166px;
}

</style>


</head>
<%
	List<MemInfoVO> list=(List<MemInfoVO>)request.getAttribute("mnglist");
	PagingVO pageVo = (PagingVO)request.getAttribute("pageVo");

	MemInfoVO meminfoVo = new MemInfoVO();
	
	String condition = request.getParameter("searchCondition");
	String keyword = request.getParameter("searchKeyword");

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<body>
	<%if (keyword != null && !keyword.isEmpty()) {%>
   <p style="display: none;">검색어 : <%=keyword%>, <%=list.size()%>건 검색되었습니다.</p>
   <%} else {
   keyword = "";
   }%>
   	
	<div class="container-fluid">
		<div class="row">
			<div id="admin-sidebar" class="col-md-2 p-x-0 p-y-3 nav-warp">
			<ul class="sidenav admin-sidenav list-unstyled">
				<li><a href="<%=request.getContextPath()%>/managerPage/manager.do">매니저페이지</a></li>
				<li><a href="<%=request.getContextPath() %>/mvInfo/mvList.do">영화목록관리</a></li>
				<li><a href="<%=request.getContextPath()%>/managerPage/notice.do">공지사항</a></li>
			</ul>
		</div>
			<div class="col-md-10 content-warp">
				<h2 class="page-header">매니저 페이지</h2>

			<!-- 회원테이블 -->
				<div class="table-responsive row memtable">
					<h3 class="sub-header">회원테이블</h3>
					<table class="table table-hover col-md-10">
						<thead>
							<tr>
								<th>회원번호</th>
								<th>아이디</th>
								<th>이름</th>
								<th>가입일</th>
								<th>탈퇴일</th>
							</tr>
						</thead>
						<tbody>
						<%if(list==null || list.isEmpty()){%>
							<tr>
								<td colspan="5">데이터가 없습니다</td>
							</tr>
						<%} else{
							int curPos = pageVo.getCurPos();
							int num = pageVo.getNum();
							for(int i=0; i<pageVo.getPageSize();i++){
								if(num<1) break;
								
								meminfoVo=list.get(curPos++);
								num--;
							%>
								<tr>
									<td><%=meminfoVo.getMemNo() %></td>
									<td>
										<a href="<%=request.getContextPath() %>/managerPage/managerDetail.do?no=<%=meminfoVo.getMemNo() %>">
										<%=meminfoVo.getId() %>
										</a>
									</td>
									<td><%=meminfoVo.getName() %></td>
									<td><%=sdf.format(meminfoVo.getRegdate())%></td>
									<td>
									<%if(meminfoVo.getOutdate()==null){
										System.out.print("-");		
									} else{%>
									<%=sdf.format(meminfoVo.getOutdate()) %>
									<%}%>
									
									</td>
								</tr>
							<%}%>
						<%}%>
						</tbody>
					</table>
					
					<div class="page-warp">
						<nav aria-label="Page navigation example">
						  <ul class="pagination">
							  <%if(pageVo.getFirstPage()>1){ %>
							  	<li class="page-item">
								      <a class="page-link text-dark" 
								      	href="<%=request.getContextPath() %>/managerPage/manager.do?currentPage=<%=pageVo.getFirstPage()-1%>&searchCondition=<%=condition%>&searchKeyword=<%=keyword %>" 
								      	aria-label="Previous">
								        <span aria-hidden="true">&laquo;</span>
								      </a>
							      </li>
							 <% } %>
						    
						    
							<%for(int i=pageVo.getFirstPage();i<=pageVo.getLastPage(); i++ ){
								if(i>pageVo.getTotalPage()) break;
								
								if(i==pageVo.getCurrentPage()){%>
						    		<li class="page-item"><a class="page-link" href="#"><%=i %></a></li>
<%-- 						    		<li class="page-item"><%=i %></li> --%>
								<%}else{%>
						    		<li class="page-item text-dark">
						    		<a class="page-link" 
						    			href="<%=request.getContextPath() %>/managerPage/manager.do?currentPage=<%=i%>&searchCondition=<%=condition%>&searchKeyword=<%=keyword %>">
						    			<%=i %></a></li>
								<%}
							} %>						   
						    
							  <%if(pageVo.getLastPage()<pageVo.getTotalPage()){%>
							  	<li class="page-item">
							      <a class="page-link text-dark" 
							      	href="<%=request.getContextPath() %>/managerPage/manager.do?currentPage=<%=pageVo.getLastPage()+1%>&searchCondition=<%=condition%>&searchKeyword=<%=keyword %>" 
							      	aria-label="Next">
							        <span aria-hidden="true">&raquo;</span>
							      </a>
							     </li>
							 <% } %>
						    
						    
						  </ul>
						</nav>
					</div>
					
					<div class="sherchbox">
						<form  name="frmsherchbox" method="post" action="<%=request.getContextPath() %>/managerPage/manager.do">
							<select name="searchCondition">
								<option value="id"
									<%if("id".equals(condition)){%>
										selected="selected"
									<%} %>	
								>아이디</option>
								<option value="name"
									<%if("name".equals(condition)){%>
										selected="selected"
									<%} %>	
								>이름</option>
							</select>
							<input type="text" name="searchKeyword" value="<%=keyword %>" title="검색어 입력"/>
							<input type="submit" value="검색">
						</form>
					</div>
				</div>
				
			</div>
		</div>
		
	</div>
	
	<div>
		
	</div>



<div style="clear: both;"></div>

<%@ include file="../inc/bottom.jsp" %>