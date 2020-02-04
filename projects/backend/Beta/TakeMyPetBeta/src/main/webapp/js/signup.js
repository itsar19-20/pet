$(() => {
   $('#haiSbagliato').hide();
   $('#esisteGia').hide();

   var tipo;

   $('#option3').change(tipo, () => {
        tipo = 'proprietario';
   });

   $('#option1').change(tipo, ()=> {
        tipo = 'petsitter';
   });

   $('#btnSignUp').click(() => {

    // SISTEMARE SE CAMPIVUOTI ATTENZIONE

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
               $('#esisteGia').show();
            } else {
                location.href = './login.html';
           }
        })
    }
    })
})