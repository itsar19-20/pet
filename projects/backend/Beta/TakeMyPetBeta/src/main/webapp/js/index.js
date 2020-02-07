$(() => {
        $('#logout').hide();
        $('#admin').hide();

	if(localStorage.getItem('user')) {
            var utente = JSON.parse(localStorage.getItem('user'));
        }
        if(sessionStorage.getItem('user')) {
	    var utente = JSON.parse(sessionStorage.getItem('user'));
	}

        if(localStorage.getItem('user') || sessionStorage.getItem('user')){
            $('#ciaoUtente').text(` Ciao ${utente.username}`);
            $('#registrati').hide();
            $('#login').hide();
            $('#logout').show();

            $.ajax({
                url: '/getImmagine',
                method: 'get',
                data: {
                    username: utente.username
                }
            })
            .done((image) => {
                if(image){
                    var ima = new Image()
                    //var myCanvas = document.getElementById('my_canvas_id');
                    //var myCanvas = new Canvas()
                    //var ctx = myCanvas.getContext();
                    //var base64 =$.base64('encode', image);
                    //var blob = new Blob( [ image ], { type: "image/png" } );
                    //var urlCreator = window.URL || window.webkitURL;
                    // var imageUrl = urlCreator.createObjectURL(blob);
                    //$('#immagine').attr('src',base64);


                    //ima.onload = function(){
                     //   myCanvas.width = ima.width;
                     //   myCanvas.height = ima.height;
                     //   ctx.drawImage(ima,0,0); // Or at whatever offset you like
                    //};
                    ima.style = 'height: 70px; width: 80px'
                    ima.src = 'http://localhost:8080/getImmagine?username=' + utente.username //'data:image/png;base64,' + base64;
                    //ima.height = '70 px';
                    //ima.width = '80 px';
                     $('#immagineProfilo').append(ima);

                            //vedere per mettere multitype in input. non solo png
                } else {}  //mettere immagine di default

            })
        }

        if(utente.tipoUtente == "admin") {
            $('#admin').show();
        }


        $('#logout').click(() => {
                localStorage.removeItem('user');
                sessionStorage.removeItem('user')
                location.href('./index.html')
            })

})

