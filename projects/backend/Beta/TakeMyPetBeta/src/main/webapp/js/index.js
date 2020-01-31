$(() => {
<<<<<<< HEAD
	if(localStorage.getItem('user') || sessionStorage.getItem('user')){
        var utente_local = localStorage.getItem('user');
        var utente_session = sessionStorage.getItem('user');
       // ${utente_local.tipoUtente}
        if((utente_session.tipoUtente).contentEquals("proprietario")){
            $('#home').hide();
        }
    }
    
=======

        $('#logout').hide();
        $('#admin').hide();

	if(localStorage.getItem('user')) {
            var utente = JSON.parse(localStorage.getItem('user'));
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

        if(utente.tipoUtente == "admin") {
            $('#admin').show();
        }


        $('#logout').click(() => {
                localStorage.removeItem('user');
                sessionStorage.removeItem('user')
                location.href('./index.html')
            })
	
>>>>>>> b7f8f718ae165ffc98ef3ce8c4cad93c56b9835c
})
