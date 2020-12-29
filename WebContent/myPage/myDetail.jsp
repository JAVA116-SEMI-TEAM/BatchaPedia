<%@page import="com.batcha.memInfo.model.MemInfoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/regStyle.css?ver2.0"/>
<style type="text/css">
	@charset "UTF-8";

*{
   font-family: 'Noto Sans KR', sans-serif;
}

article.regForm {
	width: 100%;
}

.regForm-content{
	width: 620px;
	margin: 0 auto 150px;
}

input.regInput {
    width: 400px;
    height: 40px;
}

input#userid {
    width: 295px;
    height: 40px;
}

input#btnChkId{
    height: 40px;
    width: 100px;
    background: #FCFCFC;
    border: 0.5px solid gray;
}

select#hp1 {
    background: #FCFCFC;
    border: 0.5px solid gray;
    height: 40px;
    width: 110px;
}

input#hp2 {
    height: 40px;
    width: 130px;
}

input#hp3 {
    height: 40px;
    width: 130px;
}

input#email1 {
    height: 40px;
    width: 217px;
}

select#email2 {
    height: 40px;
    width: 160px;
}

input#wr_submit {background: #ff2f6e;border: 0.5px solid;width: 400px;height: 50px;    border-radius: 16px; color: #fff;}

input#email1 {}

input#email3 {
    height: 40px;
    width: 200px;
}

/* checkId */
.checkId2{
   width: 100px;
    height: 40px;
    background: #FCFCFC;
    border: 0.5px solid gray;
}

p.pink {
    color: #ff2f6e;
}
</style>

<script type="text/javascript" src="../js/member.js"></script>

<script type="text/javascript">
$(function(){
	
	$('form[name=frm1]').submit(function(){
		if($('#mypwd1').val().length<1){
			alert('비밀번호를 입력하세요');
			$('#mypwd1').focus();
			event.preventDefault();
		}else if($('#mypwd1').val()!=$('#mypwd2').val()){
			alert('비밀번호가 일치하지 않습니다');
			$('#mypwd2').focus();
			event.preventDefault();
		}else if(!($('#myhp2').val()) || !($('#myhp3').val())){
			alert('전화번호는 숫자만 가능합니다');
			$('#myhp2').focus();
			event.preventDefault();
		}
	});
});
</script>

<%
	String userid=(String)session.getAttribute("userid");
	MemInfoVO memVo = (MemInfoVO)request.getAttribute("memVo");
	
	String myname = (String)request.getAttribute("myname");
	
	String mybirth= (String)request.getAttribute("mybirth");
	
	String myhp1= (String)request.getAttribute("myhp1");
	String myhp2= (String)request.getAttribute("myhp2");
	String myhp3= (String)request.getAttribute("myhp3");
	
	Boolean isEtc=(Boolean)request.getAttribute("isEtc");
	String email1= (String)request.getAttribute("email1");
	String email2= (String)request.getAttribute("email2");
	
%>
<article class="regForm">
<div class="regForm-content">
	<form name="frm1" method="post" action="<%=request.getContextPath() %>/myPage/myEditOk.do">
	<fieldset>
		<input type="hidden" value="<%=memVo.getMemNo()%>" name="mynum">
	    <div>        
	        <label for="myname">이름</label><br>
	        <input type="text" name="myname" id="myname" class="regInput" readonly
	        	style="background: rgba(192, 192, 195, 0.8);" value="<%=myname%>">
	    </div><br>
	    <div>
	        <label for="userid">아이디</label><br>
	        <input type="text" name="userid" id="userid" class="regInput" readonly
	        	style="background: rgba(192, 192, 195, 0.8);" value="<%=userid%>">
	    </div><br>
	    <div>
	        <label for="mypwd">현재 비밀번호</label><br>
	        <input type="Password" name="mypwd" id="mypwd" class="regInput" value="<%=memVo.getPwd()%>">
	    </div><br>
	    <div>
	        <label for="mypwd1">비밀번호</label><br>
	        <input type="Password" name="mypwd1" id="mypwd1" class="regInput">
	    </div><br>
	    <div>
	        <label for="mypwd2">비밀번호 확인</label><br>
	        <input type="Password" name="mypwd2" id="mypwd2" class="regInput" >
	    </div><br>
	    <div>        
	        <label for="mybirth">생년월일</label><br>
	        <input type="text" name="mybirth" id="mybirth" class="regInput" readonly
	        	style="background: rgba(192, 192, 195, 0.8);" value="<%=mybirth%>">
	    </div><br>
	    <div>
	        <label for="myhp1">휴대폰</label><br>
	        <select name="myhp1" id="myhp1" title="휴대폰 앞자리">
	            <option value="010"
	            	<%if(myhp1.equals("010")) {%>
	            		selected="selected"
	            	<%}%>
	            >010</option>
	            <option value="011"
	            	<%if(myhp1.equals("011")) {%>
	            		selected="selected"
	            	<%}%>
	            >011</option>
	            <option value="016"
	            	<%if(myhp1.equals("016")) {%>
	            		selected="selected"
	            	<%}%>
	            >016</option>
	            <option value="017"
	            	<%if(myhp1.equals("017")) {%>
	            		selected="selected"
	            	<%}%>
	            >017</option>
	            <option value="018"
	            	<%if(myhp1.equals("018")) {%>
	            		selected="selected"
	            	<%}%>
	            >018</option>
	            <option value="019"
	            	<%if(myhp1.equals("019")) {%>
	            		selected="selected"
	            	<%}%>
	            >019</option>
	          </select>
	        -
	        <input type="text" name="myhp2" id="myhp2" maxlength="4" title="휴대폰 가운데자리"
	            class="hp" value="<%=myhp2%>"> -
	        <input type="text" name="myhp3" id="myhp3" maxlength="4" title="휴대폰 뒷자리"
	            class="hp" value="<%=myhp3%>">
	    </div><br>
	    <div>
	        <label for="email1">이메일 주소</label><br>
	        <input type="text" name="email1"  id="email1" 
	        	title="이메일주소 앞자리" value="<%=email1 %>"> @
	        <select name="email2" id="email2"  title="이메일주소 뒷자리">
	            <option value="naver.com"
					<%if(email2.equals("naver.com")) {%>
	            		selected="selected"
	            	<%}%>           
	            >naver.com</option>
	            <option value="hanmail.net"
	            	<%if(email2.equals("hanmail.net")) {%>
	            		selected="selected"
	            	<%}%>
	            >hanmail.net</option>
	            <option value="nate.com"
	            	<%if(email2.equals("nate.com")) {%>
	            		selected="selected"
	            	<%}%>
	            >nate.com</option>
	            <option value="gmail.com"
	            	<%if(email2.equals("gmail.com")) {%>
	            		selected="selected"
	            	<%}%>
	            >gmail.com</option>
	            <option value="etc"
	            	<%if(email2.equals("etc")) {%>
	            		selected="selected"
	            	<%}%>
	            >직접입력</option>
	        </select>
	        <input type="text" name="email3" id="email3" title="직접입력인 경우 이메일주소 뒷자리"
	           <%if(isEtc) {%>
		           style="visibility:hidden"
		           value="<%=email2%>"
	           <%}else{ %>
        		style="visibility:hidden"
        	   <%} %>
	           >
	    </div><br><br>
	    <div>
	         <input type="submit" value="회원수정">
	    </div>
	</fieldset>
	
	</form>
</div>
</article>

<%@ include file="../inc/bottom.jsp"%>