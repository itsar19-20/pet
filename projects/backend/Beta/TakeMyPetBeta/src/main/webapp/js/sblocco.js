//aggiungere checkbox per doppio profilo (soloapp!!!)

$(() => {
    $('#haiSbagliato').hide();
   $('#btnLogin').click(() => {
       $.ajax({
           url: '/SbloccoController',
           method: 'post',
           data: {
               username: $('#inputUsername').val(),
               codiceSblocco: $('#inputCodiceSblocco').val()
           }
       })
       .done((utente) => {

           if (utente) {
                    localStorage.setItem('user', JSON.stringify(utente));
                    sessionStorage.setItem('user', JSON.stringify(utente));
                    location.href = './index.html';
                   }
           
           if(!utente) {
               $('#haiSbagliato').show();
           }
       })
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