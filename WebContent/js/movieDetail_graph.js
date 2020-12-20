/**
 * 
 */
var ctx = document.getElementById('starsGraph').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['1','2','3','4','5','6','7','8','9','10'],
        datasets: [{
            data: [1,1,3,5,16,29,30,5,2,1],
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
