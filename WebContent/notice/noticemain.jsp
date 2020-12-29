<%@page import="com.batcha.mynotice.model.NoticeVO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.batcha.common.Utility"%>
<%@page import="com.batcha.common.PagingVO"%>
<%@page import="com.batcha.mycmt.model.MyCmtVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../inc/top.jsp"%>
<style type="text/css">

.notice-warp{
	width: 1200px;
	margin: 50px auto;
	padding-left:100px;
}

.ntTable{
	clear: both;
}

.nttitle-warp {
    width: 950px;
}

.nttitle {
    margin-bottom: 34px;
}

.go-ntWhite {
    float: right;
    margin-bottom: 10px;
    padding-right: 45px;
}

.notitable th:nth-child(1) {
	width: 10%;
}
.notitable th:nth-child(2) {
	width: 50%;
	color: #343a40;
}
.notitable th:nth-child(3) {
	width: 15%;
}
.notitable th:nth-child(4) {
	width: 15%;
}
.notitable th:nth-child(5) {
	width: 10%;
}

</style>
<%
	List<NoticeVO> list=(List<NoticeVO>)request.getAttribute("ntlist");
	PagingVO pageVo = (PagingVO)request.getAttribute("pageVo");
	
	NoticeVO ntVo = new NoticeVO();
	
	String condition = request.getParameter("searchCondition");
	String keyword = request.getParameter("searchKeyword");
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	//HttpSession session=request.getSession();
	String t_admincheck_nt=(String)session.getAttribute("t_admincheck");
%>

<%if (keyword != null && !keyword.isEmpty()) {%>
   <p style="display: none;">검색어 : <%=keyword%>, <%=list.size()%>건 검색되었습니다.</p>
   <%} else {
   keyword = "";
}%>

<div class="notice-warp">
	<!-- 공지테이블 -->
				<div class="table-responsive row notitable">
					<div class="nttitle-warp">
						<h3 class="sub-header nttitle">공지사항</h3>
<%-- 						<%if(t_admincheck_nt.equals(1)){%> --%>
							<div class="go-ntWhite">
								<a style="color:#FF2F6E" href="<%=request.getContextPath() %>/notice/noticeWrite.do">글쓰기</a>
							</div>
<%-- 						<%} %> --%>
					</div>
					<table class="table table-hover col-md-10 ntTable">
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>조회수</th>
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
								
								ntVo=list.get(curPos++);
								num--;
							%>
								<tr>
									<td><%=ntVo.getNoticeNo() %></td>
									<td>
										<a style="color: #343a40;" 
										href="<%=request.getContextPath() %>/notice/NoticeCountOk.do?no=<%=ntVo.getNoticeNo() %>">
										<%=ntVo.getTitle() %>
										</a>
									</td>
									<td>관리자</td>
									<td><%=sdf.format(ntVo.getRegdate()) %></td>
									<td><%=ntVo.getReadcount() %></td>
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
								      	href="<%=request.getContextPath() %>/notice/noticemain.do?currentPage=<%=pageVo.getFirstPage()-1%>&searchCondition=<%=condition%>&searchKeyword=<%=keyword %>" 
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
						    			href="<%=request.getContextPath() %>/notice/noticemain.do?currentPage=<%=i%>&searchCondition=<%=condition%>&searchKeyword=<%=keyword %>">
						    			<%=i %></a></li>
								<%}
							} %>						   
						    
							  <%if(pageVo.getLastPage()<pageVo.getTotalPage()){%>
							  	<li class="page-item">
							      <a class="page-link text-dark" 
							      	href="<%=request.getContextPath() %>/notice/noticemain.do?currentPage=<%=pageVo.getLastPage()+1%>&searchCondition=<%=condition%>&searchKeyword=<%=keyword %>" 
							      	aria-label="Next">
							        <span aria-hidden="true">&raquo;</span>
							      </a>
							     </li>
							 <% } %>
						    
						    
						  </ul>
						</nav>
					</div>
					
					<div class="sherchbox">
						<form  name="frmsherchbox" method="post" 
							action="<%=request.getContextPath() %>/notice/noticemain.do">
							<select name="searchCondition">
								<option value="title"
									<%if("title".equals(condition)){%>
										selected="selected"
									<%} %>	
								>제목</option>
								<option value="content"
									<%if("content".equals(condition)){%>
										selected="selected"
									<%} %>	
								>내용</option>
							</select>
							<input type="text" name="searchKeyword" value="<%=keyword %>" title="검색어 입력"/>
							<input type="submit" value="검색">
						</form>
					</div>
				</div>
</div>


<div style="clear: both;"></div>

<%@ include file="../inc/bottom.jsp" %>