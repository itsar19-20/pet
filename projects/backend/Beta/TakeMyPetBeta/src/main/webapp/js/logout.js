$(() => {
	$('#btnLogout').click(() => {
		localStorage.clear();
		sessionStorage.clear();
		location.href = './index.html';
	})
})
