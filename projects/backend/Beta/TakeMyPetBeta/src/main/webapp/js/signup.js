$(() => {
   $('#haiSbagliato').hide();
   $('#esisteGia').hide();

   $('#btnSignUp').click(() => {
       if(($('#btnPetSitter').focus())){
           var tipo = 'petsitter'
       } 
       else {
           var tipo = 'proprietario'
       }

    if(($('#inputPassword').val()) != ($('#inputConfermaPassword').val())) {
        $('#haiSbagliato').show();
    } else {
       $.ajax({
           url: '/signUp',
           method: 'post',
           data: {
               name: $('#inputName').val(),
               surname: $('#inputSurname').val(),
               email: $('#inputEmail').val(),
               username: $('#inputUsername').val(),
               password: $('#inputPassword').val(),
               type: tipo

           }
        })
        .done((controllo) => {
           if(controllo) {
               $('esisteGia').show();
            } else {
                location.href = './login.html';
           }
        })
    }
    })
})