<%@page import="com.batcha.common.Utility"%>
<%@page import="com.batcha.common.PagingVO"%>
<%@page import="com.batcha.mycmt.model.MyCmtVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../inc/top.jsp"%>
<style type="text/css">

.mycmy-warp{
	width: 1200px;
	height: 950px;
	margin: 50px auto;
}

.cmtStitle{
	margin: 7px;
}

.myTable th:nth-child(1) {
	width: 10%;
}
.myTable th:nth-child(2) {
	width: 20%;
}
.myTable th:nth-child(3) {
	width: 70%;
}

</style>
<%
	List<MyCmtVO> mclist = (List<MyCmtVO>)request.getAttribute("mcList");
	PagingVO mcpageVo = (PagingVO)request.getAttribute("mcpageVo");
	
	MyCmtVO mcVo = new MyCmtVO();
%>

<div class="mycmy-warp">
	<div class="cmtStitle">
		<h2>#내 코멘트</h2>
	</div><br>
	
	<div class="table-responsive ">
					<table class="table table-striped table-hover col-md-10 myTable">
						<thead>
							<tr>
								<th>번호</th>
								<th>영화 제목</th>
								<th>코멘트 내용</th>
							</tr>
						</thead>
						<tbody>
						<%if(mclist==null || mclist.isEmpty()){%>
							<tr>
								<td colspan="5">데이터가 없습니다</td>
							</tr>
						<%} else{
							int curPos = mcpageVo.getCurPos();
							int num = mcpageVo.getNum();
							for(int i=0; i<mcpageVo.getPageSize();i++){
								if(num<1) break;
								
								mcVo=mclist.get(curPos++);
								num--;
							%>
								<tr>
									<td><%=mcVo.getCmtNo() %></td>
									<td><a href="<%=request.getContextPath() %>/">
										<%=Utility.cutString(mcVo.getMvTitle(), 15)  %></a></td>
									<td><%=Utility.cutCmt(mcVo.getCmtText(), 80)%></td>
								</tr>
							<%}%>
						<%}%>
						</tbody>
					</table>
					
					<div class="page-warp">
						<nav aria-label="Page navigation example">
						  <ul class="pagination">
							  <%if(mcpageVo.getFirstPage()>1){ %>
							  	<li class="page-item">
								      <a class="page-link text-dark" 
								      	href="<%=request.getContextPath() %>/myPage/mycmt.do?currentPage=<%=mcpageVo.getFirstPage()-1%>" 
								      	aria-label="Previous">
								        <span aria-hidden="true">&laquo;</span>
								      </a>
							      </li>
							 <% } %>
						    
						    
							<%for(int i=mcpageVo.getFirstPage();i<=mcpageVo.getLastPage(); i++ ){
								if(i>mcpageVo.getTotalPage()) break;
								
								if(i==mcpageVo.getCurrentPage()){%>
						    		<li class="page-item"><a class="page-link" href="#"><%=i %></a></li>
<%-- 						    		<li class="page-item"><%=i %></li> --%>
								<%}else{%>
						    		<li class="page-item text-dark">
						    		<a class="page-link" 
						    			href="<%=request.getContextPath() %>/myPage/mycmt.do?currentPage=<%=i%>">
						    			<%=i %></a></li>
								<%}
							} %>						   
						    
							  <%if(mcpageVo.getLastPage()<mcpageVo.getTotalPage()){%>
							  	<li class="page-item">
							      <a class="page-link text-dark" 
							      	href="<%=request.getContextPath() %>/myPage/mycmt.do?currentPage=<%=mcpageVo.getLastPage()+1%>" 
							      	aria-label="Next">
							        <span aria-hidden="true">&raquo;</span>
							      </a>
							     </li>
							 <% } %>
						    
						    
						  </ul>
						</nav>
					</div>
	
	</div>
</div>



<div style="clear: both;"></div>

<%@ include file="../inc/bottom.jsp" %>