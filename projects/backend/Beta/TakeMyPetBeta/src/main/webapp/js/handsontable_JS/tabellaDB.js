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
				
				$('#tabella2').DataTable( {
					data: listaUtenti,
					columns: [
						{title: 'Username', data: 'username'},
						{title: 'Nome', data: 'nome' },
						{title: 'Cognome', data: 'cognome' },
						{title: 'Bloccato', data: 'bloccato' },
						{title: 'Doppio profilo', data: 'doppioProfilo' },
						{data: null,
						render: function (data, type, row) {
							return '<button	id="btnElimina" class="btnEdit btn btn-primary btn-sm">Elimina</button>','<button id="btnBlocca" class="btnEdit btn btn-primary btn-sm style:"center">Blocca</button>';;
                            

						}
					]
			    } );
		}
	})
})

