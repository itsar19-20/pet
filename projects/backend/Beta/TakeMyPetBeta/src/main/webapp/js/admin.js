$(() => {
	$('#homepage_admin').click(() => {
		location.href = './index.html';

		document.getElementById('homepage_admin').style.color = '#eeeeee';
	})
	document.getElementById('homepage_admin').style.color = '14a302';
	$('#statistiche_admin').click(() => {
		document.getElementById('homepage_admin').style.color = '#eeeeee';
		document.getElementById('statistiche_admin').style.color = '#14a302';
		document.getElementById('gestioneUtenti_admin').style.color = '#eeeeee';
		document.getElementById('segnalazioni_admin').style.color = '#eeeeee';
	})
	$('#gestioneUtenti_admin').click(() => {
		document.getElementById('homepage_admin').style.color = '#eeeeee';
		document.getElementById('statistiche_admin').style.color = '#eeeeee';
		document.getElementById('gestioneUtenti_admin').style.color = '#14a302';
		document.getElementById('segnalazioni_admin').style.color = '#eeeeee';
	})
	$('#segnalazioni_admin').click(() => {
		document.getElementById('homepage_admin').style.color = '#eeeeee';
		document.getElementById('statistiche_admin').style.color = '#eeeeee';
		document.getElementById('gestioneUtenti_admin').style.color = '#eeeeee';
		document.getElementById('segnalazioni_admin').style.color = '#14a302';
	})
})

var listaUsername;
		  $.ajax({
	            url: '/admin',
	            method: 'get',
	        })
	        .done((listaUtenti) => {
	        	if (listaUtenti){
	        		listaUtenti.forEach(username => {
						username = listaUtenti.username;
					}); 
	        			
	        		}
	        	})
	        