$(() => {
 	$('#haiSbagliato').hide();
    $('#btnLogin').click(() => {
        $.ajax({
            url: '/login',
            method: 'post',
            data: {
                username: $('#inputUsername').val(),
                password: $('#inputPassword').val()
            }
        })
        .done((utente) => {
       
            if (utente) {
           
            	if(utente.contatoreAccessiSbagliati >= 10){
                    alert('Il tuo account è stato bloccato. Controlla la tua email per sbloccarlo.')
                    localStorage.removeItem('user');
                    sessionStorage.removeItem('user');
                    location.href = './index.html'
                  }
                if($('#rememberCheck').checked) {
                    localStorage.setItem('user',JSON.stringify(utente));
                   
                    if(utente.tipoUtente == "Admin"){
                    	location.href = './admin.html';
                    } else{location.href = './index.html';
                    	}
                }
                else {
                    sessionStorage.setItem('user',JSON.stringify(utente));
                  
                    if(utente.tipoUtente == "Admin"){
                    	location.href = './admin.html';
                    } else{location.href = './index.html';
                    }
                }

            }
            if(!utente) {
            	localStorage.removeItem('user');
                sessionStorage.removeItem('user');
                $('#haiSbagliato').show();
                //location.href = './no.html'
            }
        })
              
         // .fail(() => {
             //   localStorage.removeItem('user');
              //  sessionStorage.removeItem('user');
              //  $('#haiSbagiato').show();
         // })
    })
})
            
        
