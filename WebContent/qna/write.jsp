<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/qnaStyle.css?ver1.0"/>
<script type="text/javascript">
	$(function(){
		$('#title').focus();
		
		$('form[name=frmWrite]').find('input[type=button]').click(function(){
			location.href ='list.do';
		});
		
		$('form[name=frmWrite]').submit(function(){
			if($('#title').val().length<1){
				alert('제목을 입력하세요');
				$('#title').focus();
				event.preventDefault();
		};
		
	});
</script>

<div>
<form name="frmWrite" method="post" 
	action="write_ok.do" >
 <fieldset>
 		<div>
            <label for="name" style="visibility: hidden">작성자</label>
            <input type="text" id="name" name="name" value=<%=t_userName %> style="visibility: hidden;"/>
        </div>
        <div>
            <input type="text" id="title" name="title" placeholder=" 제목"  />
        </div><br>

        <div>  
 			<textarea id="content" name="content" rows="12" cols="40" placeholder=" 내용을 입력하세요" ></textarea>
        </div>
                		<div>
            <label for="name" style="visibility: hidden">아이디</label>
            <input type="text" id="id" name="id" 
            	value=<%=t_userid%>  style="visibility: hidden;"/>
        </div>
        <div>
            <input type = "submit" value="등록"/>
            <input type = "Button" value="목록" onclick="location.href	='list.do'" />         
        </div>

		<div>
            <label for="name" style="visibility: hidden">멤버번호</label>
            <input type="text" id="memno" name="memno" value=<%=t_memnumber %>  style="visibility: hidden;"/>
        </div>
            <label for="name" style="visibility: hidden">관리자여부</label>
            <input type="text" id="admincheck" name="admincheck" value=<%=t_admincheck %>  style="visibility: hidden;"/>
        </div>

    </fieldset>
</form>
</div>   

<%@ include file="../inc/bottom.jsp"%>