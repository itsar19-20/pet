$(() => {
        $('#logout').hide();

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


        $('#logout').click(() => {
                localStorage.removeItem('user');
                sessionStorage.removeItem('user')
                location.href('./index.html')
            })
	
})
    }
    
})


