<%@page import="com.batcha.mvInfo.model.MvInfoVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
   //top.jsp는 다른페이지에서도 공유하기 때문에 userid변수 중복을 막기위해 다른 이름 설정
   String t_memno=(String)session.getAttribute("memno");
   boolean t_isLogin=false;
   String t_userid=(String)session.getAttribute("userid");
   String t_userName=(String)session.getAttribute("userName");
   String t_memnumber=String.valueOf(session.getAttribute("memberno"));
   String t_pwd=(String)session.getAttribute("pwd");
   String t_admincheck=String.valueOf(session.getAttribute("adminCheck"));
   if(t_userid!=null && !t_userid.isEmpty()){
      t_isLogin=true; //로그인이 된 경우에만 true
   }
   
  List<MvInfoVO> searchList =(List<MvInfoVO>)request.getAttribute("searchList");
  String mvSearchk=request.getParameter("mvSearchk");
  if(mvSearchk==null || mvSearchk.isEmpty()){
      mvSearchk="";
  }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BatchaPedia</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/mainStyle.css?ver4.1"/>

<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/formLayout.css?ver3.0"/> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
</head>
<body>
<header id="header">
<nav class="navbar navbar-expand-sm navbar-light fixed-top" id="top">
  <!-- logo -->
  <a class="navbar-brand" href="<%=request.getContextPath() %>/main.do" id="logo">
    <img src="<%=request.getContextPath()%>/images/logo.png" alt="logo" style="width:180px;">
  </a>
  
  <!-- Links -->
  <ul class="navbar-nav">
    <form class="form-inline" action="<%=request.getContextPath()%>/mvSearch/mvSearch.do" method="post">
    <div class="input-group">
      <div class="input-group-prepend">
        <span class="input-group-text"><i class='fas fa-search'></i></span>
      </div>
      <input type="text" class="form-control" placeholder="영화를 검색하세요." 
      id="search" name="mvSearchKeyword" value="<%=mvSearchk %>">
    </div>
  </form>
	<li class="nav-item">
     <a class="nav-link" href="<%=request.getContextPath()%>/mvSearch/mvSearch.do?mvSearchKeyword=<%=mvSearchk %>">영화검색</a>
    </li>
    <%if(!t_isLogin){ //로그인 안 된 경우 %>
    <li class="nav-item">
      <a class="nav-link" href="<%=request.getContextPath()%>/login/login.do">로그인</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="<%=request.getContextPath()%>/member/register.do">회원가입</a>
    </li>
    <%}else{ //로그인 된 경우%>
     <li class="nav-item">
      <a class="nav-link" href="<%=request.getContextPath()%>/login/logout.do">로그아웃</a>
    </li>
       <%if(Integer.parseInt(t_admincheck)==1){ %>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath() %>/mvInfo/mvList.do">관리자</a>
          </li>
       <%}else{ %>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath() %>/myPage/myPage.do">마이페이지</a>
          </li>
       <%} %>
    <%} %>   
      <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">고객센터</a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="<%=request.getContextPath() %>/notice/noticemain.do">공지사항</a>
         <a class="dropdown-item"href="<%=request.getContextPath() %>/faqMgr/faqList.do">FAQ</a>
        <a class="dropdown-item" href="<%=request.getContextPath()%>/qna/list.do">Q&A</a>
      </div>
    </li>
    
  </ul>
  <hr class="fixed-top">
</nav>
</header>

<section id="contents">

<!-- main -->