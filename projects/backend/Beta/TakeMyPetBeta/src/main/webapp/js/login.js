$(() => {
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
                if($('#rememberCheck').checked) {
                    localStorage.setItem('user',JSON.stringify(utente));
                    location.href = './index.html';
                }
                else {
                    sessionStorage.setItem('user',JSON.stringify(utente));
                    location.href = './index.html'
                }
            if (utente == null) {
                localStorage.removeItem('user');
                sessionStorage.removeItem('user');
                $('#haiSbagiato').show;
                if($(utente.contatoreAccessiSbagliati >= 10)){
                    alert('Il tuo account è stato bloccato. Controlla la tua email per sbloccarlo.')
                }
            }
            }
        })
    })

});