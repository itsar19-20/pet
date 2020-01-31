$(() => {
<<<<<<< HEAD
	if(localStorage.getItem('user') || sessionStorage.getItem('user')){
        var utente_local = localStorage.getItem('user');
        var utente_session = sessionStorage.getItem('user');
       // ${utente_local.tipoUtente}
        if((utente_session.tipoUtente == "proprietario") || (utente_local == "proprietario")){
            $('#home').hide();
=======
        $('#logout').hide();

	if(localStorage.getItem('user')) {
            var utente = JSON.parse(localStorage.getItem('user'));
>>>>>>> branch 'master' of https://github.com/itsar19-20/pet.git
        }
        if(sessionStorage.getItem('user')) {
	    var utente = JSON.parse(sessionStorage.getItem('user'));
	}
      
        if(localStorage.getItem('user') || sessionStorage.getItem('user')){
            $('#ciaoUtente').text(` Ciao ${utente.username}`);
            $('#registrati').hide();
            $('#login').hide();
            $('#logout').show();
        }


        $('#logout').click(() => {
                localStorage.removeItem('user');
                sessionStorage.removeItem('user')
                location.href('./index.html')
            })
	
})
    }
    
})


