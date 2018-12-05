function jobseekerSignup() {
	var data = new FormData();
	data.append('name', document.getElementById("jobseeker-signup-name-input" ).value);
	data.append('email', document.getElementById("jobseeker-signup-email-input" ).value);
	data.append('password', document.getElementById("jobseeker-signup-password-input" ).value);
	data.append('password_confirmation', document.getElementById("jobseeker-signup-password-confirm-input" ).value);
	data.append('_token', document.getElementById("jobseeker-signup").getElementsByTagName("input")[5].value);
	// var data = "";
	// data += 'name=' + document.getElementById("jobseeker-signup-name-input" ).value;
	// data += '&email=' + document.getElementById("jobseeker-signup-email-input" ).value;
	// data += '&password=' + document.getElementById("jobseeker-signup-password-input" ).value;
	// data += '&password_confirmation=' + document.getElementById("jobseeker-signup-password-confirm-input" ).value;
	var xhr = new XMLHttpRequest();
	xhr.open('POST', '', false);
	xhr.send(data);
	console.log(xhr.response);
	console.log(data);
	// var form = document.getElementById('jobseeker-signup');
	// form.submit();
}

function getCsrf() {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', '', false);
	xhr.send();
	return xhr.response;
}

function addCsrf() {
	var forms = document.getElementsByClassName("form");
	for (i=0; i < forms.length; i++) {
		var csrf = getCsrf()
		forms[i].innerHTML += csrf;
	}
}