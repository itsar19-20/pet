$(() => {
	if(localStorage.getItem('user')){
        var utente = localStorage.getItem('user');
	} else {
        var utente = sessionStorage.getItem('user');
	}
        $(utenteText).text('utente.username');
        
       
	
})