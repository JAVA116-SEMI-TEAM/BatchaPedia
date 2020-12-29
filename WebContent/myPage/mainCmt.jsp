<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.batcha.mycmt.model.MyCmtService"%>
<%@page import="com.batcha.mycmt.model.MyCmtVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
<%
	HttpSession sessionmc=request.getSession();
	int memNoMc = (int)sessionmc.getAttribute("memberno");
	
	MyCmtService mcService = new MyCmtService();
	List<MyCmtVO> mclist = new ArrayList<MyCmtVO>();
	
	System.out.println("mclist="+mclist);
	
	try {
		mclist= mcService.selectMainMyCmt(memNoMc);
	} catch (SQLException e) {
		e.printStackTrace();
	}

%>	
	
<div class="go-cmt">
	<a class="go-cmta" href="<%=request.getContextPath()%>/myPage/mycmt.do">더보기 »</a>
	<div style="clear: both;"></div>
</div>
<!-- <div class="mainCmt"> -->
	<div class="row mycmtRd">
		<!-- for -->
		<%if(mclist==null && mclist.isEmpty()){%>
		<div>내가 쓴 코멘트가 없습니다</div>
		<%}else {
 					for(int i=0; i<mclist.size();i++){%> 
		<%MyCmtVO mcvo=mclist.get(i);%>
		<div class="card mycmtsbox">
			 <div class="card-body">
        	    <div>
					<p><%=mcvo.getMvTitle() %></p>
					<div style="word-break:normal;"><%=mcvo.getCmtText() %></div>
				</div>
			</div>
		</div>
		<%} 
		}%> 
</div>