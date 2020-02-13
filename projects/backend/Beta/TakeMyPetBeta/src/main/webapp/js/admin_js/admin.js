
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
		document.getElementById("mySidenav_statistiche").style.width = "100%";
	})
	$('#gestioneUtenti_admin').click(() => {
		document.getElementById('homepage_admin').style.color = '#333';
		document.getElementById('statistiche_admin').style.color = '#333';
		document.getElementById('gestioneUtenti_admin').style.color = '#14a302';
		document.getElementById('segnalazioni_admin').style.color = '#333';
		document.getElementById("mySidenav_gestioneUtenti").style.width = "100%";
		document.getElementById("col-md-2 d-none d-md-block bg-light sidebar").style.width = "0%";
	})
	$('#segnalazioni_admin').click(() => {
		document.getElementById('homepage_admin').style.color = '#333';
		document.getElementById('statistiche_admin').style.color = '#333';
		document.getElementById('gestioneUtenti_admin').style.color = '#333';
		document.getElementById('segnalazioni_admin').style.color = '#14a302';
		document.getElementById("mySidenav_segnalazioni").style.width = "100%";
	})
	
	$('#btnclose_statistiche').click(() => {
		 document.getElementById("mySidenav_statistiche").style.width = "0"
	})
	$('#btnclose_gestioneUtenti').click(() => {
		document.getElementById("mySidenav_gestioneUtenti").style.width = "0";	
	})
	$('#btnclose_segnalazioni').click(() => {
		document.getElementById("mySidenav_segnalazioni").style.width = "0";	
	})

})


