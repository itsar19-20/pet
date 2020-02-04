$(() =>{
	$.ajax({
		url: '/AdminController',
		method: 'get'
	})
	.done((listaUtenti) => {
		if(listaUtenti){
			var dataObject = listaUtenti;
			
			$(document).ready(function() {
			    var table = $('#example').DataTable( {
			        "ajax": dataObject,
			        "columnDefs": [ {
			            "targets": -1,
			            "data": null,
			            "defaultContent": "<button>Click!</button>"
			        } ]
			    } );
			 
			    $('#example tbody').on( 'click', 'button', function () {
			        var data = table.row( $(this).parents('tr') ).data();
			        alert( data[0] +"'s salary is: "+ data[ 5 ] );
			    } );
			} );
		}
	})
})
		