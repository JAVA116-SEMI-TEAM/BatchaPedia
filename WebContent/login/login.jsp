<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>

<script type="text/javascript">
$(function(){
	$('#userid').focus();
	
	$('form[name=frmLogin]').submit(function(){
		$('.infobox').each(function(idx, item){
			if($('#userid').val().length<1){
				alert("아이디를 입력하세요");
				$('#userid').focus();
				event.preventDefault();
				return false;
			}else if($('#pwd').val().length<1){
				alert("비밀번호를 입력하세요");
				$('#pwd').focus();
				event.preventDefault();
				return false;
			}
		});
	});
});
</script>
<%
	//쿠키 읽어오기(아이디 기억)
	String ckValue="";
	Cookie[] ckArr=request.getCookies();
	if(ckArr!=null){
		for(int i=0;i<ckArr.length;i++){
			if(ckArr[i].getName().equals("ck_userid")){
				ckValue=ckArr[i].getValue();
				break;
			}
		}//for
	}
%>
<article class="loginForm">
	<form name="frmLogin" method="post"	action="login_ok.do" class="center">
		<fieldset>
			<div>
				<input type="text" placeholder=" 아이디" name="userid" id="userid" 
					 class="loginInput infobox" value="<%=ckValue%>">
			</div><br>
			<div>
				<input type="password" placeholder=" 비밀번호" class="loginInput infobox"
					 name="pwd" id="pwd">
			</div><br>
			<div>
				<input type="submit" id="lg_submit" class="loginInput pinkB" value="로그인"><br><br>
				<input type="checkbox" id="chkSave" class="form-check-input"
					<%if(ckValue!=null && !ckValue.isEmpty()){ %>
						checked="checked";
					<%} %>
				>
				<label for="chkSave">아이디 저장하기</label><br><br>
				<p class="gray">계정이 없으신가요? <a href="<%=request.getContextPath()%>/member/register.do" class="pink">회원가입</a>
			</div>
		</fieldset>
	</form>
</article>			

<%@ include file="../inc/bottom.jsp" %>