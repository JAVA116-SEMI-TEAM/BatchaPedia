<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- todo 버튼 선택 시 킵리스트에 넣어주기 데이터 연동 -->
			<div class="keepBtns">
				<label class="toggleBtn">
					<input type="checkbox" id="toggleCheckBox" onclick="toggle(this)"/>
				</label>
				<!-- <form action="" method="get" name="keepbtnFrm">
					<button id="keepBtn" name="keepBtn" value="f" style="display:none"
						class="btn btn-sm btn-default">
					<i class="fas fa-plus-circle"></i> 나중에 볼 영화
					</button>
					
					<button id="unKeepBtn" name="keepBtn" value="t" style="display:inline"
					class="btn btn-sm btn-default">
					<i class="fas fa-minus-circle"></i> 볼 영화에서 제외
					</button>
				 -->
			<script type="text/javascript">
				function toggle(element){
					console.log(element.checked);
					if(element.checked){
						keptCheck=true;
						document.getElementById('toggleCheckBox').setAttribute("keptCheck", true);
						location.reload();
					}else{
						keptCheck=false;
						document.getElementById('toggleCheckBox').setAttribute("keptCheck", false);
						location.reload();
					}
					console.log(keptCheck);
				}
				
				//로그인 안했을 경우 얼럿 띄워줘야 함
			//	alert('로그인 하셔야 합니다.');
			</script>
			
			</div>