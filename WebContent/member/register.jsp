<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/top.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/regStyle.css?ver3.0"/>
<script type="text/javascript" src="../js/member.js"></script>

<script type="text/javascript">
	$(function(){
		$('#wr_submit').click(function(){
			if($('#name').val().length<1){
				alert('이름을 입력하세요');
				$('#name').focus();
				event.preventDefault();
			}else if(!validate_userid($('#userid').val())){
				alert('아이디는 영문, 숫자, _만 가능합니다.');
				$('#userid').focus();
				event.preventDefault();				
			}else if($('#pwd').val().length<1){
				alert('비밀번호를 입력하세요');
				$('#pwd').focus();
				event.preventDefault();
			}else if($('#pwd').val()!=$('#pwd2').val()){
				alert('비밀번호가 일치하지 않습니다.');
				$('#pwd2').focus();
				event.preventDefault();
			}else if(!validate_phone($('#hp2').val()) ||
					!validate_phone($('#hp3').val())){
				alert('전화번호는 숫자만 가능합니다.');
				$('#hp2').focus();
				event.preventDefault();				
			}else if($('#chkId').val() !='Y'){
				alert('아이디 중복확인하세요');
				$('#btnChkId').focus();
				event.preventDefault();
			}
		});
		
		
		
		
	});
	
	
</script>

<article class="regForm-content">
<div>
<form name="frm1" method="post" action="register_ok.do">
<fieldset>
    <div>        
        <label for="name">이름</label><br>
        <input type="text" name="name" id="name" class="regInput">
    </div><br>
    <div>
        <label for="userid">아이디</label><br>
        <input type="text" name="userid" id="userid"
        		style="ime-mode:inactive">&nbsp;
        <input type="button" value="중복확인" id="btnChkId" title="새창열림" class="whiteB">
    </div><br>
    <div>
        <label for="pwd">비밀번호</label><br>
        <input type="Password" name="pwd" id="pwd" class="regInput">
    </div><br>
    <div>
        <label for="pwd2">비밀번호 확인</label><br>
        <input type="Password" name="pwd2" id="pwd2" class="regInput">
    </div><br>
    <div>        
        <label for="birthday">생년월일(ex:19990101)</label><br>
        <input type="text" name="birthday" id="birthday" class="regInput">
    </div><br>
    <div>
        <label for="hp1">휴대폰</label><br>
        <select name="hp1" id="hp1" title="휴대폰 앞자리">
            <option value="010">010</option>
            <option value="011">011</option>
            <option value="016">016</option>
            <option value="017">017</option>
            <option value="018">018</option>
            <option value="019">019</option>
       	</select>
        -
        <input type="text" name="hp2" id="hp2" maxlength="4" title="휴대폰 가운데자리"
        	 class="hp"> -
        <input type="text" name="hp3" id="hp3" maxlength="4" title="휴대폰 뒷자리"
        	 class="hp">
    </div><br>
    <div>
        <label for="email1">이메일 주소</label><br>
        <input type="text" name="email1"  id="email1" title="이메일주소 앞자리"> @
        <select name="email2" id="email2"  title="이메일주소 뒷자리">
            <option value="naver.com">naver.com</option>
            <option value="hanmail.net">hanmail.net</option>
            <option value="nate.com">nate.com</option>
            <option value="gmail.com">gmail.com</option>
            <option value="etc">직접입력</option>
        </select>
        <input type="text" name="email3" id="email3" title="직접입력인 경우 이메일주소 뒷자리"
        	style="visibility:hidden">
    </div><br><br>
    <div>
         <input type="submit" id="wr_submit" value="회원가입" class="pinkB regSubmit">
    </div>
</fieldset>

 <input type ="text" name="chkId" id="chkId" style="visibility:hidden">
        
</form>
</div>
</article>

<%@ include file="../inc/bottom.jsp"%>