
$(() =>{
    var dataPoints=[];

var options =  {
	animationEnabled: true,
	theme: "light2",
	title: {
		text: "Daily New Users"
	},
	axisX: {
		valueFormatString: "DD MMM YYYY",
	},
	axisY: {
		title: "Users",
		titleFontSize: 24,
		includeZero: false
	},
	data: [{
		type: "spline",
		yValueFormatString: "$#,###.##",
		dataPoints: dataPoints
	}]
};

	$.ajax({
		url: '/statistica',
		method: 'get'
	})
	.done((stat) => {
		if(stat){

            for (var i = 0; i < stat.length; i++) {


                dataPoints.push({
                    x: new Date(stat[i][0]),
                    y: stat[i][1]
                });
            }

        }
        $("#chartContainer").CanvasJSChart(options);
	})
	$('#tabella2').DataTable( {
		data: listaUtenti,
		columns: [
			{title: 'Username', data: 'username', name: "username"},
			{title: 'Nome', data: 'nome' },
			{title: 'Cognome', data: 'cognome' },
			{title: 'Bloccato', data: 'bloccato' },
			{title: 'Doppio profilo', data: 'doppioProfilo' },
		]
	} );
})


$(() =>{
    var dataPoints=[];

var options =  {
	animationEnabled: true,
	theme: "light2",
	title: {
		text: "Daily New Users"
	},
	axisX: {
		valueFormatString: "DD MMM YYYY",
	},
	axisY: {
		title: "Users",
		titleFontSize: 24,
		includeZero: false
	},
	data: [{
		type: "spline",
		yValueFormatString: "$#,###.##",
		dataPoints: dataPoints
	}]
};

	$.ajax({
		url: '/statisticheAdmin',
		method: 'get'
	})
	.done((stat) => {
		if(stat){

            for (var i = 0; i < stat.length; i++) {


                dataPoints.push({
                    x: new Date(stat[i][0]),
                    y: stat[i][1]
                });
            }

        }
        $("#chartContainer").CanvasJSChart(options);
	})
})
