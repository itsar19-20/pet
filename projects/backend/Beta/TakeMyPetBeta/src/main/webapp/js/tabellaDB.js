$(() =>{
	$.ajax({
		url: '/listaUtentiAdmin',
		method: 'get'
	})
	.done((listaUtenti) => {
		
		if(listaUtenti){

//=================================CREA TABELLA 1======================================================
			
			$('#tabella').DataTable( {
				data: listaUtenti,
				columns: [
					{title: 'Username', data: 'username'},
					{title: 'Nome', data: 'nome' },
					{title: 'Cognome', data: 'cognome' },
					{title: 'Attivo', data: 'attivo' },
					{title: 'Doppio profilo', data: 'doppioProfilo' },
					{title: 'Stato',data: null,
						render: function (data, type, row) {
							return '<button	id="btnElimina" class="btnEdit btn btn-primary btn-lg" style="height: 32px; padding-bottom:0px; padding-top:0px">Elimina</button><button id="btnBlocca" class="btnEdit btn btn-primary btn-lg" style="height: 32px; padding-bottom:0px; padding-top:0px">Blocca</button><button id="btnSblocca" class="btnEdit btn btn-primary btn-lg" style="height: 32px; padding-bottom:0px; padding-top:0px">Sblocca</button><button id="btnProfilo" class="btnEdit btn btn-primary btn-lg" style="height: 32px; padding-bottom:0px; padding-top:0px">Profilo</button>';

						}
					},
					]
			} );
	
//==========================Serve perche la tabella da errore quando viene chiamata==========================

			table = $('#tabella').DataTable({
				retrieve: true,
				paging: false
			});
			
//=================================BOTTONE ELIMINA TABELLA 1================================================	

			$('#tabella tbody').on('click', '#btnElimina', function () {
				var data_row = table.row($(this).closest('tr')).data();

				$.ajax({
					url: '/listaUtentiAdmin',
					method: 'post',
					data: {
						username: data_row.username,
						controllo: 'elimina'
					},
				}).done(() => {
					location.href = './admin.html';
				}).fail(() => {
					alert("Qualcosa e' andato storto!")
				})
			});
			
//=================================BOTTONE BLOCCA TABELLA 1================================================	

			$('#tabella tbody').on('click', '#btnBlocca', function () {
				var data_row = tableDue.row($(this).closest('tr')).data();

				$.ajax({
					url: '/listaUtentiAdmin',
					method: 'post',
					data: {
						username: data_row.username,
						controllo: 'blocca'
					},
				}).done(() => {
					location.href = './admin.html';
				}).fail(() => {
					alert("Qualcosa e' andato storto!")
				})
			});	

//=================================BOTTONE ATTIVA TABELLA 1================================================	

			$('#tabella tbody').on('click', '#btnSblocca', function () {
				var data_row = tableDue.row($(this).closest('tr')).data();

				$.ajax({
					url: '/listaUtentiAdmin',
					method: 'post',
					data: {
						username: data_row.username,
						controllo: 'attiva'
					},
				}).done(() => {
					location.href = './admin.html';
				}).fail(() => {
					alert("Qualcosa e' andato storto!")
				})
			});	
			
//=================================BOTTONE PROFILO TABELLA 1================================================	

			$('#tabella tbody').on('click', '#btnProfilo', function () {
				var data_row = tableDue.row($(this).closest('tr')).data();

				$.ajax({
					url: '/listaUtentiAdmin',
					method: 'post',
					data: {
						username: data_row.username,
						//prendi utente
					},
				}).done(() => {
					location.href = './admin.html';
				}).fail(() => {
					alert("Qualcosa e' andato storto!")
				})
			});	

//=================================CREA TABELLA 2================================================			

			var rows = $('#tabella2').DataTable( {
				data: listaUtenti,
				columns: [
					{title: 'Username', data: 'username'},
					{title: 'Nome', data: 'nome' },
					{title: 'Cognome', data: 'cognome' },
					{title: 'Attivoo', data: 'attivo' },
					{title: 'Doppio profilo', data: 'doppioProfilo' },
					{data: null,
						render: function (data, type, row) {
							return '<button	id="btnElimina" class="btnEdit btn btn-primary btn-lg" style="height: 32px; padding-bottom:0px; padding-top:0px">Elimina</button><button id="btnBlocca" class="btnEdit btn btn-primary btn-lg" style="height: 32px; padding-bottom:0px; padding-top:0px">Blocca</button><button id="btnSblocca" class="btnEdit btn btn-primary btn-lg" style="height: 32px; padding-bottom:0px; padding-top:0px">Sblocca</button>';


						}
					},
					]
			} );

//==========================Serve perche la tabella da errore quando viene chiamata==========================
			
			tableDue = $('#tabella2').DataTable({
				retrieve: true,
				paging: false
			});
			
//=================================BOTTONE ELIMINA TABELLA 2================================================	
			
			$('#tabella2 tbody').on('click', '#btnElimina', function () {
				var data_row = tableDue.row($(this).closest('tr')).data();

				$.ajax({
					url: '/listaUtentiAdmin',
					method: 'post',
					data: {
						username: data_row.username,
						controllo: 'elimina'
					},
				}).done(() => {
					location.href = './admin.html';
				}).fail(() => {
					alert("Qualcosa e' andato storto!")
				})
			});	

//=================================BOTTONE BLOCCA TABELLA 2================================================			

			$('#tabella2 tbody').on('click', '#btnBlocca', function () {
				var data_row = tableDue.row($(this).closest('tr')).data();

				$.ajax({
					url: '/listaUtentiAdmin',
					method: 'post',
					data: {
						username: data_row.username,
						controllo: 'blocca'
					},
				}).done(() => {
					location.href = './admin.html';
				}).fail(() => {
					alert("Qualcosa e' andato storto!")
				})
			});	

//=================================BOTTONE ATTIVA TABELLA 2================================================			

			$('#tabella2 tbody').on('click', '#btnSblocca', function () {
				var data_row = tableDue.row($(this).closest('tr')).data();

				$.ajax({
					url: '/listaUtentiAdmin',
					method: 'post',
					data: {
						username: data_row.username,
						controllo: 'attiva'
					},
				}).done(() => {
					location.href = './admin.html';
				}).fail(() => {
					alert("Qualcosa e' andato storto!")
				})
			});	
		}
	})
})

