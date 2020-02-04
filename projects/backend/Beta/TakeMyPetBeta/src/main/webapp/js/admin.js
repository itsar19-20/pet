<<<<<<< HEAD
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
	        
=======
$(() => {
		$('#homepage_admin').click(() => {
		document.getElementById('homepage_admin').style.color = '#14a302';
		document.getElementById('statistiche_admin').style.color = '#333';
		document.getElementById('gestioneUtenti_admin').style.color = '#333';
		document.getElementById('segnalazioni_admin').style.color = '#333';
		location.href = './index.html';
	})
	document.getElementById('statistiche_admin').style.color = '#14a302';
	$('#statistiche_admin').click(() => {
		document.getElementById('homepage_admin').style.color = '#333';
		document.getElementById('statistiche_admin').style.color = '#14a302';
		document.getElementById('gestioneUtenti_admin').style.color = '#333';
		document.getElementById('segnalazioni_admin').style.color = '#333';
	})
	$('#gestioneUtenti_admin').click(() => {
		document.getElementById('homepage_admin').style.color = '#333';
		document.getElementById('statistiche_admin').style.color = '#333';
		document.getElementById('gestioneUtenti_admin').style.color = '#14a302';
		document.getElementById('segnalazioni_admin').style.color = '#333';
	})
	$('#segnalazioni_admin').click(() => {
		document.getElementById('homepage_admin').style.color = '#333';
		document.getElementById('statistiche_admin').style.color = '#333';
		document.getElementById('gestioneUtenti_admin').style.color = '#333';
		document.getElementById('segnalazioni_admin').style.color = '#14a302';
	})
})
>>>>>>> c64a3017d58ff2ee8d1c2ee4b3945a2a5811c26c
