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
	signin(document.getElementById("jobseeker-signup-email-input" ).value, document.getElementById("jobseeker-signup-password-input" ).value);
}

function recruiterSignup() {
	var data = new FormData();
	data.append('name', document.getElementById("recruiter-signup-name-input" ).value);
	data.append('email', document.getElementById("recruiter-signup-email-input" ).value);
	data.append('password', document.getElementById("recruiter-signup-password-input" ).value);
	data.append('password_confirmation', document.getElementById("recruiter-signup-password-confirm-input" ).value);
	// data.append('_token', document.getElementById("jobseeker-signup").getElementsByTagName("input")[5].value);
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'http://localhost:3103/recruiter/signup', false);
	xhr.send(data);
	signinRecruiter(document.getElementById("recruiter-signup-email-input" ).value, document.getElementById("recruiter-signup-password-input" ).value);
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

function signinRecruiter(email, password) {
	var data = new FormData();
	data.append('email', email);
	data.append('password', password);
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'http://localhost:3103/recruiter/signin', false);
	xhr.send(data);
	var signinStatus = JSON.parse(xhr.response);
	if (signinStatus.code === 200) {
		console.log('signin success!');
		sessionStorage.setItem("email", signinStatus.email);
		window.location = 'recruiter-dashboard.html';
	} else {
		console.log(signinStatus);
	}
}

function jobseekerSignin() {
	var email  = document.getElementById("jobseeker-signin-email-input" ).value;
	var password = document.getElementById("jobseeker-signin-password-input" ).value;
	signin(email, password);
}

function recruiterSignin() {
	var email  = document.getElementById("recruiter-signin-email-input" ).value;
	var password = document.getElementById("recruiter-signin-password-input" ).value;
	signinRecruiter(email, password);
}

function loadJobseeker(userEmail) {
	var params = {
		email : userEmail
	}
	var param = formatParams(params)
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:3103/jobseeker/view' + param, false);
	xhr.send();
	if (xhr.response == '') {
		xhr.open('GET', 'http://localhost:3103/recruiter/view' + param, false);
		xhr.send();
	}
	console.log(xhr.response);
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
	var jobseekerAddress = document.getElementsByClassName('jobseeker-address');
	for (i = 0; i < jobseekerAddress.length; i++) {
		jobseekerAddress[i].innerHTML = user.address;
		jobseekerAddress[i].value = user.address;
	}
	var jobseekerEmail = document.getElementsByClassName('jobseeker-email');
	for (i = 0; i < jobseekerEmail.length; i++) {
		jobseekerEmail[i].innerHTML = user.email;
		jobseekerEmail[i].value = user.email;
	}
	var jobseekerPhoneNumber = document.getElementsByClassName('jobseeker-phone-number');
	for (i = 0; i < jobseekerPhoneNumber.length; i++) {
		jobseekerPhoneNumber[i].innerHTML = user.phoneNumber;
		jobseekerPhoneNumber[i].value = user.phoneNumber;
	}
	var jobseekerSummary = document.getElementsByClassName('jobseeker-summary');
	for (i = 0; i < jobseekerSummary.length; i++) {
		jobseekerSummary[i].innerHTML = user.summary;
		jobseekerSummary[i].value = user.summary;
	}
}

function signout() {
	sessionStorage.clear();
	window.location = 'index.html';
}