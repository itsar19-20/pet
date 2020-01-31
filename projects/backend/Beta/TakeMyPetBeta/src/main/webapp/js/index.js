$(() => {
	if(localStorage.getItem('user') || sessionStorage.getItem('user')){
        var utente_local = localStorage.getItem('user');
        var utente_session = sessionStorage.getItem('user');
       // ${utente_local.tipoUtente}
        if((utente_session.tipoUtente).contentEquals("proprietario")){
            $('#home').hide();
        }
    }
    
})
