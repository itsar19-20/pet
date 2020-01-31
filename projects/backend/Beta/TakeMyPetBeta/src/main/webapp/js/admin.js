$(() => {
	$('#homepage_admin').click(() => {
		location.href = './index.html';
		$($('nav-link')).active();
	})
	$('#gestioneUtenti_admin').click(() => {
		$('nav-link').active();
	})
	$('#segnalazioni_admin').click(() => {
		document.getElementById('segnalazioni_admin').style.background = '#14a302';
	})
	$('#statistiche_admin').click(() => {
		$('nav-link').active();
	})
	
})