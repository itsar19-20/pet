$(() => {
	$('nav').click(() => {
		 $('nav-link').active();
	})
	$('#homepage_admin').click(() => {
		location.href = './index.html';
	})
})
