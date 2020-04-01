$(() => {
	if(localStorage.getItem('user')){
		
	var utente = JSON.parse(localStorage.getItem('user'));
	}
	else if(sessionStorage.getItem('user')) {
		var utente = JSON.parse(sessionStorage.getItem('user'));
	}
	else {
		location.href = './index.html';
	}
    
/*	if(localStorage.getItem('user')) {
        
    } else {
        location.href = 'login.html';
    }
    setInterval(() => { //controlla che nel localstorage ci sia un utente salvato, in caso contrario riporta alla pagina di login
        if(localStorage.getItem('user')) {
        
        } else {
            alert('Sessione scaduta!')
            location.href = 'login.html';
        }
	}, 10000);
*/
	if(utente.tipoUtente != "admin"){
		location.href = './index.html';
	}
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
	
	//prova
	$('.sorting').click(() => {
				document.getElementById("mySidenav_segnalazioni").style.width = "100%";
	})
})



