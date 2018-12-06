function jobseekerSignup() {
	var data = new FormData();
	data.append('name', document.getElementById("jobseeker-signup-name-input" ).value);
	data.append('email', document.getElementById("jobseeker-signup-email-input" ).value);
	data.append('password', document.getElementById("jobseeker-signup-password-input" ).value);
	data.append('password_confirmation', document.getElementById("jobseeker-signup-password-confirm-input" ).value);
	// data.append('_token', document.getElementById("jobseeker-signup").getElementsByTagName("input")[5].value);
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'http://localhost:3103/jobseeker/signup', false);
	xhr.send(data);
	xhr.open('POST', 'http://localhost:3103/jobseeker/signin', false);
	xhr.send(data);
}

function signin(email, password) {
	var data = new FormData();
	data.append('email', email);
	data.append('password', password);
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'http://localhost:3103/jobseeker/signin', false);
	xhr.send(data);
	var signinStatus = JSON.parse(xhr.response);
	if (signinStatus.code === 200) {
		console.log('signin success!');
		sessionStorage.setItem("email", signinStatus.email);
		window.location = 'jobseeker-dashboard.html';
	} else {
		console.log(signinStatus);
	}
}

function jobseekerSignin() {
	var email  = document.getElementById("jobseeker-signin-email-input" ).value;
	var password = document.getElementById("jobseeker-signin-password-input" ).value;
	signin(email, password);
}

function loadJobseeker(userEmail) {
	var params = {
		email : userEmail
	}
	var param = formatParams(params)
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:3103/jobseeker/view' + param, false);
	xhr.send();
	return JSON.parse(xhr.response);
}

function checkAuth() {
	if (sessionStorage.getItem('email')) {
		//do something
	} else {
		window.location = 'index.html';
	}
}

function formatForUser() {
	var user = loadJobseeker(sessionStorage.getItem('email'));
	var jobseekerName = document.getElementsByClassName('jobseeker-name');
	for (i = 0; i < jobseekerName.length; i++) {
		jobseekerName[i].innerHTML = user.name;
		jobseekerName[i].value = user.name;
	}
}

function signout() {
	sessionStorage.clear();
	window.location = 'index.html';
}