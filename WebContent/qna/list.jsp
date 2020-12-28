<%@page import="com.batcha.common.Utility"%>
<%@page import="com.batcha.qna.model.QnaService"%>
<%@page import="com.batcha.common.PagingVO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.List"%>
<%@page import="com.batcha.qna.model.QnaVO"%>
<%@page import="com.batcha.qna.model.QnaDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp"%>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/qnaStyle.css"/>
<%
	//뷰 페이지
	List<QnaVO> list = (List<QnaVO>)request.getAttribute("list");
	PagingVO pageVo = (PagingVO)request.getAttribute("pageVo");

	String condition=request.getParameter("searchCondition");
	String keyword=request.getParameter("searchKeyword");
	
	//3
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<script type="text/javascript">	
$(function(){
	$('.divList table.box2 tbody tr').hover(function(){
		$(this).css('background','lightblue');
	}, function(){
		$(this).css('background','');		
	});
});

</script>


<h2>Q&A</h2>
<button type="button" class="btn">
    <a href='<%=request.getContextPath() %>/qna/write.do' class="pink">글쓰기</a>
</button><br>
<%
	if(keyword !=null && !keyword.isEmpty()){ %>
		<p>검색어 : <%=keyword %>, <%=list.size() %>건 검색되었습니다.</p>
<%	}else{	
		keyword="";
	}%>
<div class="divList">
<table class="table table-hover"
	 	summary="기본 게시판에 관한 표로써, 번호, 제목, 작성자, 작성일, 조회수에 대한 정보를 제공합니다.">
	<colgroup>
		<col style="width:10%;" />
		<col style="width:50%;" />
		<col style="width:15%;" />
		<col style="width:15%;" />
		<col style="width:10%;" />		
	</colgroup>
	<thead>
	  <tr>
	    <th scope="col">번호</th>
	    <th scope="col">제목</th>
	    <th scope="col">작성자</th>
	    <th scope="col">작성일</th>
	    <th scope="col">조회수</th>
	  </tr>
	</thead> 
	<tbody> 
		<%if(list==null || list.isEmpty()){ %>
			<tr>
				<td colspan="5" class="align_center">데이터가 존재하지 않습니다.</td>
			</tr>
		<%}else{ %>		 
		  	<!--게시판 내용 반복문 시작  -->		
			<%
			int curPos = pageVo.getCurPos();
			int num = pageVo.getNum();
			
			for(int i=0;i<pageVo.getPageSize();i++){
				if(num<1) break;
				
				QnaVO vo=list.get(curPos++);
				num--;
			%>
				<tr>
					<td><%=vo.getQnaNo() %></td>
					<td style="text-align:left">
					<%if(vo.getDelFlag().equals("Y")){ %>
							<!-- 삭제된 글인 경우 -->
							<span style="color:gray">삭제된 글입니다.</span>
					<%}else{ %>
					<%=Utility.displayRe(vo.getStep()) %>
					<%if(vo.getStep()>0){ %>
						<i class="material-icons" style="color: #ff2f6e">subdirectory_arrow_right</i>
					<%} %>
					<a href
="<%=request.getContextPath() %>/qna/countUpdate.do?qnano=<%=vo.getQnaNo() %>">
						<%=vo.getTitle() %></a></td>
					<%}//if %>	
					<td><%=vo.getAuthor() %></td>
					<td><%=sdf.format(vo.getRegdate()) %></td>
					<td><%=vo.getReadCount() %></td>		
				</tr>
			<%}//for 
		  }//if%>
	  <!--반복처리 끝  -->
	  </tbody>
</table>	   
</div><br><br>
<div>
	<!-- 페이지 번호 추가 -->		
	<!-- 이전 블럭으로 이동 -->
	<nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
	<%if(pageVo.getFirstPage()>1){ %>
    <li class="page-item"> 
		<a class="page-link" aria-label="Previous" href="<%=request.getContextPath() %>/qna/list.do?currentPage=<%=pageVo.getFirstPage()-1%>&searchCondition=<%=condition%>&searchKeyword=<%=keyword%>">
			<span aria-hidden="true">&laquo;</span>
		</a></li>
	<%}//if %>
						
	<%
		for(int i=pageVo.getFirstPage();i<=pageVo.getLastPage();i++){
			if(i>pageVo.getTotalPage()) break; 
			
			if(i==pageVo.getCurrentPage()){	%>
			<li class="page-item">
				<span style="color:#FF2F6E;font-weight: bold" class="page-link">
					<%=i %></span>
					</li>
			<%}else{ %>
			<li class="page-item">
				<a class="page-link" style="color: #343a40" href 
="<%=request.getContextPath() %>/qna/list.do?currentPage=<%=i%>&searchCondition=<%=condition%>&searchKeyword=<%=keyword%>">
					<%=i %></a></li>
			<%}//if %>
	<%	}//for	%>
	
	<!-- 다음 블럭으로 이동 -->
	<%if(pageVo.getLastPage() < pageVo.getTotalPage()){ %>
	<li class="page-item">
		<a class="page-link" aria-label="Next" href="<%=request.getContextPath() %>/qna/list.do?currentPage=<%=pageVo.getLastPage()+1%>&searchCondition=<%=condition%>&searchKeyword=<%=keyword%>">
			<span aria-hidden="true">&raquo;</span>
		</a>
	<%}//if %>
	    </li>
  </ul>
</nav>
	<!--  페이지 번호 끝 -->
</div>
<div class="divSearch">
   	<form name="frmSearch" method="post" 
   		action='<%=request.getContextPath() %>/qna/list.do'>
        <select name="searchCondition">
            <option value="title" 
            	<%if("title".equals(condition)){ %>
            		selected="selected"
            	<%} %>
            >제목</option>
            <option value="content"
            	<%if("content".equals(condition)){ %>
            		selected="selected"
            	<%} %>
            >내용</option>
            <option value="author" 
            	<%if("author".equals(condition)){ %>
            		selected="selected"
            	<%} %>
            >작성자</option>
        </select>   
        <input type="text" name="searchKeyword" title="검색어 입력"
        	value="<%=keyword%>">   
		<input type="submit" value="검색">
    </form>
</div>



<%@ include file="../inc/bottom.jsp"%>