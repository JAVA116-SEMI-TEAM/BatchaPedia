/**
 * 
 */


var graphData = '${graphData}';
console.log(graphData[0]);
var ctx = document.getElementById('starsGraph').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['1','2','3','4','5','6','7','8','9','10'],
        datasets: [{
            data: [graphData[0],graphData[1],graphData[2],graphData[3],graphData[4],
				   graphData[5],graphData[6],graphData[7],graphData[8],graphData[9]],
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
