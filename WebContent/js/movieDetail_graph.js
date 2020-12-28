/**
 * 
 */
var starsData = new Array();
<c:forEach items="${graphData}" var="item">
	starsData.push("${item}")
</c:forEach>
console.log("starsData[0]="+starsData[0]);

var ctx = document.getElementById('starsData').getContext('2d');
var myChart = new Chart(ctx, {
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