$(() => {
	$('#homepage_admin').click(() => {
		location.href = './index.html';
		$('#homepage_admin').active();
	})
	$('#gestioneUtenti_admin').click(() => {
		$('#gestioneUtenti_admin').active();
	})
	$('#segnalazioni').click(() => {
		location.href = './index.html';
		$('#segnalazioni_admin').active();
	})
	$('#statistiche_admin').click(() => {
		location.href = './index.html';
		$('#statistiche_admin').active();
	})
	
})