<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <section class="movieDetail-starsGraph">
		<div class="movieDetail-starsGraph-header">
			<!-- todo 실제 더미데이터 입력 후 평점 끌어와서 그래프 그리기 -->
			<h4>평점분포</h4>
			<span>${avgStars}점 (${memCntOfMv} 명 참여)</span>
		</div>
		<canvas id="starsGraph">
		<!-- <script src="../js/movieDetail_graph.js"></script> -->
		</canvas> 
		<canvas>
			<script>
			var starsData = new Array();
			<c:forEach items="${graphData}" var="item">
			starsData.push("${item}")
			</c:forEach>
			
			var graphData = document.getElementById('starsGraph').getContext('2d');
			var myChart = new Chart(graphData, {
			    type: 'bar',
			    data: {
			        labels: ['1','2','3','4','5','6','7','8','9','10'],
			        datasets: [{
			            data: [starsData[0],starsData[1],starsData[2],starsData[3],starsData[4],
							   starsData[5],starsData[6],starsData[7],starsData[8],starsData[9]],
			            backgroundColor: 'gold',
			        }]
			    },
			    options: {
			    	legend: {
			        	display: false
			        },
			        title: {
			        	display: false
			        },
			    	scales: {
			    	  xAxes: [{
			              display: true
			            }],
			          yAxes: [{
			              display: false
			            }],
			        },
			        responsive: true,
			        maintainAspectRatio: false,
			    }
			});
			</script>
		</canvas>
	</section>