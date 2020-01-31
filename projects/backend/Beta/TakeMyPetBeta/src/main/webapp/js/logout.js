$(() => {
	$('#btnLogout').click(() => {
			  location.href = './index.html';
			  localStorage.clear();
			  sessionStorage.clear();
	})
})

              
         // .fail(() => {
             //   localStorage.removeItem('user');
              //  sessionStorage.removeItem('user');
              //  $('#haiSbagiato').show();
         // })
        
