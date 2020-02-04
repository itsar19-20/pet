$(() =>{
	$.ajax({
		url: '/statistica',
		method: 'get'
	})
	.done((stat) => {
		if(stat){
            var ctx = document.getElementById("myChart");
            var myChart = new Chart(ctx, {
              type: 'line',
              data: {
                labels: data.dataRegistrazione,
                datasets: [{
                  data: data.dataReg,
                  lineTension: 0,
                  backgroundColor: 'transparent',
                  borderColor: '#14a302',
                  borderWidth: 4,
                  pointBackgroundColor: '#14a302'
                }]
              },
              options: {
                scales: {
                  yAxes: [{
                    ticks: {
                      beginAtZero: false
                    }
                  }]
                },
                legend: {
                  display: false,
                }
              }
            });
		}
	})
})
