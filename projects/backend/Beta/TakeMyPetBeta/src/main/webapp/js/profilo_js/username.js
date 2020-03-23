$(() =>{
	$('#nomeUtente').click(() => {
	$('#nomeUtente').text(" Ciao");
	location.href('./gestioneUtenti.html')});
	/*$.ajax({
		url: '/listaUtentiAdmin',
		method: 'get'
	})
	.done((listaUtenti) => {
		
		if(listaUtenti){
//=================================CREA TABELLA 1======================================================
				data: listaUtenti,
					ciccio =  data.username
		}
	});*/
} )