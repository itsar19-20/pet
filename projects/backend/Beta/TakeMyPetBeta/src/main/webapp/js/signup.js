$(() => {
   $('#haiSbagliato').hide();
   $('#esisteGia').hide();
   $('#riempireCampi').hide();

   var tipo = 'petsitter';

   $('#option3').change(tipo, () => {
        tipo = 'proprietario';
   });

   $('#option1').change(tipo, ()=> {
        tipo = 'petsitter';
   });

   
   
   $('#btnSignUp').click(() => {


    if(($('#inputPassword').val()) != ($('#inputConfermaPassword').val())) {
        $('#haiSbagliato').show();
    } 
    else if($('#inputName').val()==("")|| $('#inputSurname').val()==("") || $('#inputEmail').val()==("") || $('#inputUsername').val()==("")  || $('#inputPassword').val()==("") || $('#inputConfermaPassword').val()==("") ){
    	$('#riempireCampi').show();
    }
    else {
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
                //funzione per convertire base64 to uint8!!!!!
                /*
                function base64ToArrayBuffer(base64) {
                    var binary_string =  window.atob(base64);
                    var len = binary_string.length;
                    var bytes = new Uint8Array( len );
                    for (var i = 0; i < len; i++)        {
                        bytes[i] = binary_string.charCodeAt(i);
                    }
                    return bytes.buffer;
                }
                */
            	
                var base64Immagine = $('.profile-pic').attr('src').substring(23);
                //taglio via le ultime 4 cifre per la decodifica sul server
                var base64Cut = base64Immagine.substring(0,(base64Immagine.length - 4));

               // var bytes = base64ToArrayBuffer(base64Immagine)
               // var bytesJson = JSON.stringify(Array.from(bytes));

                //passo al server il base64 e lo converto li
                $.ajax({
                    url: '/getImmagine',
                    method: 'post',
                    data: {
                        username: $('#inputUsername').val(),
                        immagine: base64Cut
                    }
                })
                .done(() => {location.href = './login.html';})
           }
        })
    }
    })
})


