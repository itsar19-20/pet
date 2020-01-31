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


// Get the video
var video = document.getElementById("myVideo");

// Get the button
var btn = document.getElementById("myBtn");

// Pause and play the video, and change the button text
function myFunction() {
  if (video.paused) {
    video.play();
    btn.innerHTML = "Pause";
  } else {
    video.pause();
    btn.innerHTML = "Play";
  }
}
