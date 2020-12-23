/**
 * 
 */

//좌표값의 최소 최대를 구하고
//그걸 10등분 한걸 좌표값 x+a, x+a~2a, 그런 식으로 나누면... 되지 않을까...

var starsRect=document.querySelector('#stars');
var rect=starsRect.getBoundingClientRect();
var starSelectWidth=new Array();
var starSelectHeight=33;
for (var key in rect) {
	if(typeof rect[key] !== 'function') {
    let para = document.createElement('p');
    para.textContent  = `${ key } : ${ rect[key] }`;
    document.body.appendChild(para);
  }
} //x y width height top right bottom left 확인하는 코드
for(var i=1; i<=10; i++){
	starSelectWidth[i-1]=380+17.5*i;
	console.log(starSelectWidth[i-1]);
}//가로 한계값을 10개 각각 구한다 

var cursorW=0, cursorH=0;
$(document).ready(function(){
   $(document).mousemove(function(e){
	   cursorW=e.pageX;
	   cursorH=e.pageY;
	   changeColor(cursorW, cursorH);
   });
});//마우스 위치값을 구한다

var mg=1;

function changeStarHalfGold(star){ //별 반개
	document.querySelector(star).classList.add('fa-star-half-alt');
	document.querySelector(star).classList.add('star-gold');
	document.querySelector(star).classList.remove('fa-star');
	document.querySelector(star).classList.remove('star-mono');
}

function changeStarFullGold(star){ //별 한개
	document.querySelector(star).classList.add('fa-star');
	document.querySelector(star).classList.add('star-gold');
	document.querySelector(star).classList.remove('fa-star-half-alt');
	document.querySelector(star).classList.remove('star-mono');
}

function changeStarFullMono(star){ //색 빼기
	document.querySelector(star).classList.add('fa-star');
	document.querySelector(star).classList.add('star-mono');
	document.querySelector(star).classList.remove('fa-star-half-alt');
	document.querySelector(star).classList.remove('star-gold');
}

function changeColor(cursorW, cursorH){ //순서대로 별 0.5개, 1개, 1.5개 ... 변경되는 함수
	if(cursorW >= 380 && cursorW <= rect.x+(starSelectHeight/2)*1 && cursorH >= 252 && cursorH<=285){
		//색깔과 아이콘을 바꾸고
		//별 0.5개
		changeStarHalfGold('#firstStar');
		//클릭하면 해당 평점값을 저장해
	}else if(cursorW >= 380 && cursorW <= rect.x+(starSelectHeight/2)*2+(mg*1) && cursorH >= 252 && cursorH<=285){
		//1.0개
		changeStarFullGold('#firstStar');
		changeStarFullMono('#secondStar');
	}else if(cursorW >= 380 && cursorW <= rect.x+(starSelectHeight/2)*3+(mg*2) && cursorH >= 252 && cursorH<=285){
		//1.5개
		changeStarFullGold('#firstStar');
		changeStarHalfGold('#secondStar');
	}else if(cursorW >= 380 && cursorW <= rect.x+(starSelectHeight/2)*4+(mg*3) && cursorH >= 252 && cursorH<=285){
		//2개
		changeStarFullGold('#firstStar');
		changeStarFullGold('#secondStar');
		changeStarFullMono('#thirdStar');
	}else if(cursorW >= 380 && cursorW <= rect.x+(starSelectHeight/2)*5+(mg*4) && cursorH >= 252 && cursorH<=285){
		//2.5개
		changeStarFullGold('#firstStar');
		changeStarFullGold('#secondStar');
		changeStarHalfGold('#thirdStar');
	}else if(cursorW >= 380 && cursorW <= rect.x+(starSelectHeight/2)*6+(mg*5) && cursorH >= 252 && cursorH<=285){
		//3개
		changeStarFullGold('#firstStar');
		changeStarFullGold('#secondStar');
		changeStarFullGold('#thirdStar');
		changeStarFullMono('#fourthStar');
	}else if(cursorW >= 380 && cursorW <= rect.x+(starSelectHeight/2)*7+(mg*6) && cursorH >= 252 && cursorH<=285){
		//3.5개
		changeStarFullGold('#firstStar');
		changeStarFullGold('#secondStar');
		changeStarFullGold('#thirdStar');
		changeStarHalfGold('#fourthStar');
	}else if(cursorW >= 380 && cursorW <= rect.x+(starSelectHeight/2)*8+(mg*7) && cursorH >= 252 && cursorH<=285){
		//4개
		changeStarFullGold('#firstStar');
		changeStarFullGold('#secondStar');
		changeStarFullGold('#thirdStar');
		changeStarFullGold('#fourthStar');
		changeStarFullMono('#fifthStar');
	}else if(cursorW >= 380 && cursorW <= rect.x+(starSelectHeight/2)*9+(mg*9) && cursorH >= 252 && cursorH<=285){
		//4.5개
		changeStarFullGold('#firstStar');
		changeStarFullGold('#secondStar');
		changeStarFullGold('#thirdStar');
		changeStarFullGold('#fourthStar');
		changeStarHalfGold('#fifthStar');
	}else if(cursorW >= 380 && cursorW <= rect.x+(starSelectHeight/2)*10+(mg*10) && cursorH >= 252 && cursorH<=285){
		//5개
		changeStarFullGold('#firstStar');
		changeStarFullGold('#secondStar');
		changeStarFullGold('#thirdStar');
		changeStarFullGold('#fourthStar');
		changeStarFullGold('#fifthStar');
	}else {
		changeStarFullMono('#firstStar');
		changeStarFullMono('#secondStar');
		changeStarFullMono('#thirdStar');
		changeStarFullMono('#fourthStar');
		changeStarFullMono('#fifthStar');
	}
}