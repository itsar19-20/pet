
//aggiungere checkbox per doppio profilo (soloapp!!!)

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

            	if(utente.bloccato){
                    alert('Il tuo account è stato bloccato. Controlla la tua email per sbloccarlo.')
                    localStorage.removeItem('user');
                    sessionStorage.removeItem('user');
                    location.href = './index.html';
                  }
                if($('#rememberCheck').checked) {
                    localStorage.setItem('user',JSON.stringify(utente));
                }
                else {
                    sessionStorage.setItem('user',JSON.stringify(utente));
                }
                if(utente.tipoUtente == "admin"){
                	location.href = './admin.html';
                } else {
                	location.href = './index.html';
                	}


            }
            if(!utente) {
            	 $.ajax ( {
                     url:'/login',
                     method: 'get',
                     data: {
                         username: $('#inputUsername').val(),
                         password: $('#inputPassword').val()
                     }
                 })
                 .done((controlloBloccato) => {
                    if(controlloBloccato){
                     alert('Il tuo account è stato bloccato. Controlla la tua email per sbloccarlo.')
                     localStorage.removeItem('user');
                     sessionStorage.removeItem('user');
                     location.href = './index.html';
                    } 
                 });
            	 
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

  // Get the video
var video = document.getElementById("myVideo");


// Hiding the password

//alert("Try filling the password field and hit the eye button.");
var state = false;
function toggle(){
    if(state){
        document.getElementById("inputPassword").setAttribute("type","inputPassword");
        document.getElementById("eye-wrapper").style.boxShadow = '0 0 0 0px white';
        document.getElelementById("lock").style.fill = 'white';
        document.getElementById("open").style.display= 'none';
        document.getElementById("close").style.display= 'block';
        state = false;
    }
    else{
        document.getElementById("inputPassword").setAttribute("type","text");
        document.getElementById("eye-wrapper").style.boxShadow = '0 0 0 250px white';
        document.getElementById("lock").style.fill = '#121726';
        document.getElementById("open").style.display= 'block';
        document.getElementById("close").style.display= 'none';
        state = true;
    }
}