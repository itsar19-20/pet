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