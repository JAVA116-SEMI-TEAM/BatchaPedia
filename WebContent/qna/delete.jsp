<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/qnaStyle.css?ver1.0"/>


<script type="text/javascript">
	$(function(){
		$('form[name=frmDelete]').submit(function(){
			if($('#pwd').val().length<1){
				alert('비밀번호를 입력하세요');
				$('#pwd').focus();
				event.preventDefault();
			}else if(!confirm('삭제하시겠습니까?')){
				event.preventDefault();
			}
		});
	});	
</script>


<%
	//detail.jsp에서 [삭제]링크 클릭하면 get방식으로 이동
	//=> http://localhost:9090/mystudy/board/delete.jsp?no=6
	//1
	String qnano=request.getParameter("qnano");
	String userid=request.getParameter("userid");
	
	//2	
	//3
%>
<div class="divForm">
<form name="frmDelete" method="post" action="delete_ok.do" >
		<input type="hidden" name="qnano" value="<%=qnano%>">
		
		<fieldset>
		<div>           
	            <label for="pwd" style="visibility: hidden">db비밀번호</label>
	            <input type="text" id="dbPwd" name="dbPwd" 
	            	value=<%=t_pwd %> style="visibility: hidden"/>   
	        </div>
	        <div>           
	            <label for="pwd" style="visibility: hidden">db아이디</label>
	            <input type="text" id="dbId" name="dbId" 
	            	value=<%=t_userid %> style="visibility: hidden"/>   
	        </div>
	        <div>           
	        	<span class="sp"><%=qnano %>번 글을 삭제하시겠습니까?</span>                        
	        </div>
	        <%if(Integer.parseInt(t_admincheck)==1){ %>
	        	<span class="sp">관리자는 모든 글을 삭제할 수 있습니다.</span>                        
		        <div>           
		            <label for="pwd" style="visibility: hidden">비밀번호</label>
		            <input type="text" id="pwd" name="pwd" style="visibility: hidden" value=0 />   
		        </div>
	        <%} else{%>
	        <div>           
	            <label for="pwd">비밀번호 </label>
	            <input type="password" id="pwd" name="pwd" />   
	        </div>
	        <%} %>
	        
	        <div>           
	            <label for="pwd" style="visibility: hidden">글 작성자</label>
	            <input type="text" id="userid" name="userid" 
	            	value=<%=userid %> style="visibility: hidden"/>   
	        </div>
	        <div class="center">
	            <input type ="submit"  value="삭제" />
	            <input type = "Button" value="목록" 
                	OnClick="location.href='list.do'" />
	        </div>
	        <div>           
	            <label for="pwd" style="visibility: hidden">관리자 여부</label>
	            <input type="text" id="admincheck" name="admincheck" 
	            	value=<%=t_admincheck %> style="visibility: hidden"/>   
	        </div>
	    </fieldset>
    </form>
</div>


<%@ include file="../inc/bottom.jsp"%>