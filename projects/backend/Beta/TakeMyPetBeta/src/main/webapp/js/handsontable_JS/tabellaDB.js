$(() =>{
	$.ajax({
		url: '/AdminController',
		method: 'get'
	})
	.done((listaUtenti) => {
		if(listaUtenti){
				$('#tabella').DataTable( {
					data: listaUtenti,
					columns: [
						{title: 'Username', data: 'username'},
						{title: 'Nome', data: 'nome' },
						{title: 'Cognome', data: 'cognome' },
						{title: 'Bloccato', data: 'bloccato' },
						{title: 'Doppio profilo', data: 'doppioProfilo' },
					]
			    } );
		}
	})
})
