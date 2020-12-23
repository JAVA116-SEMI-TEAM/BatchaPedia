<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
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

<div class="divForm">
<form name="frmWrite" method="post" 
	action="write_ok.do" >
 <fieldset>
	<legend>Q&A</legend>
        <div class="firstDiv">
            <label for="title">제목</label><br>
            <input type="text" id="title" name="title"  />
        </div>
		<div>
            <label for="name" style="visibility: hidden">작성자</label>
            <input type="text" id="name" name="name" value=<%=t_userName %> />
        </div>
		<div>
            <label for="name" style="visibility: hidden">멤버번호</label>
            <input type="text" id="memno" name="memno" value=<%=t_memno %> />
        </div>
            <label for="name" style="visibility: hidden">관리자여부</label>
            <input type="text" id="admincheck" name="admincheck" value=<%=t_admincheck %> />
        </div>
		<div>
            <label for="name" style="visibility: hidden">아이디</label>
            <input type="text" id="id" name="id" 
            	value=<%=t_userid%> />
        </div>
        <div>  
        	<label for="content">내용</label><br>     
 			<textarea id="content" name="content" rows="12" cols="40"></textarea>
        </div>
 <!--        <div>
            <label for="upfile">첨부파일</label><br>
            <input type="file" id="upfile" name="upfile" />
        </div> -->
        <div class="center">
            <input type = "submit" value="등록"/>
            <input type = "Button" value="글목록" />         
        </div>
    </fieldset>
</form>
</div>   

<%@ include file="../inc/bottom.jsp"%>