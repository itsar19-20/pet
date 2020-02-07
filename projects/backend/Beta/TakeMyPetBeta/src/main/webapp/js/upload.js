$(() => {


        var readURL = function(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('.profile-pic').attr('src', e.target.result);
                }

                reader.readAsDataURL(input.files[0]);
            }
        }


        $(".file-upload").on('change', function(){
            readURL(this);
        });

        $(".upload-button").on('click', function() {
           $(".file-upload").click();
        });


    })










/*

    var path; //
    var file = new File(path);
    var fis = new FileInputStream(file);
    var byteImmagine = new byte[file.length()];
    fis.read(byteImmagine);
    var byteJson = JSON.stringify(byteImmagine);

    $.ajax({
        url: '/getImmagine',
        method: 'post',
        data:{
            username: utente.username,
            immagine: byteJson
        }
    })


})
*/