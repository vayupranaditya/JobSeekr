function makeJob(job) {
	var expireDate = new Date(job.expireDate);
	console.log(job.id);
	var template = 
			`<article class="media" onclick="seeJob(` + job.id + `)">
                <figure class="media-left">
                  <p class="image is-64x64">
                    <img src="https://bulma.io/images/placeholders/128x128.png">
                  </p>
                </figure>
                <div class="media-content">
                  <div class="content">
                    <p>
                      <strong>` + job.position + ` (` + job.employment_type + `)</strong> <br />
                      ` + job.company.name + ` <br />
                      ` + job.company.city + `, Indonesia | IDR. ` + job.salary + `<br />
                      <small class="is-pulled-right">Until ` + expireDate.toDateString() + `</small>
                    </p>
                  </div>
                </div>
            </article>`;
    document.getElementById('job-list').innerHTML += template;
}

function viewAppliedPeople(job) {
	var appliedPeople = loadCounter(job.id);
	var expireDate = new Date(job.expireDate);
	var template = 
			`<article class="media" onclick="seeApplicant(` + job.id + `)">
                <figure class="media-left">
                  <p class="image is-64x64">
                    <img src="https://bulma.io/images/placeholders/128x128.png">
                  </p>
                </figure>
                <div class="media-content">
                  <div class="content">
                    <p>
                      <strong>` + job.position + ` (` + job.employment_type + `)</strong> <br />
                      ` + job.company.name + ` <br />
                      ` + appliedPeople + ` applicants<br />
                      <small class="is-pulled-right">Until ` + expireDate.toDateString() + `</small>
                    </p>
                  </div>
                </div>
            </article>`;
    document.getElementById('job-list').innerHTML += template;
}

function viewApplicantSummary(applicant) {
	var template = 
			`<div class="box">
              <div class="columns">
                <div class="column is-1">
                </div>
                <div class="column is-2">
                  <figure class="image is-128x128">
                    <img class="is-rounded" src="https://bulma.io/images/placeholders/128x128.png">
                  </figure>
                </div>
                <div class="column is-8">
                  <span class="title is-4 jobseeker-name">{{Name}}</span><br/>
                  <div class="columns">
                    <div class="column is-10">
                      <span class="subtitle is-5">{{Summary}}</span>
                    </div>
                  </div>
                </div>
                <div class="column is-1">
                </div>
              </div>
            </div>`;
    document.getElementById('applicant-list').innerHTML += template;
}

function editApplySummary() {
	var template = 
		`<div class="field" style="margin-bottom: 1vh">
		  	<div class="control">
		    	<textarea class="textarea is-medium" name="summary" id="summary-content" placeholder="Summary"></textarea>
		  	</div>
		</div>`;
	var applicationSummary = document.getElementById('application-summary');
	var summaryEditBtn = document.getElementById('summary-edit-btn');
	var summaryContent = document.getElementById('summary-content');
	var oldValue = summaryContent.innerHTML;
	summaryEditBtn.innerHTML = '&nbsp&nbspSave&nbsp&nbsp';
	summaryEditBtn.setAttribute('onclick', 'saveApplySummary()');
	applicationSummary.innerHTML = template;
	summaryContent = document.getElementById('summary-content');
	summaryContent.innerHTML = oldValue;
}

function saveApplySummary() {
	var template = 
		`<span class="subtitle is-5 jobseeker-summary" id="summary-content">{{Summary}}</span><br/>`;
	var applicationSummary = document.getElementById('application-summary');
	var summaryEditBtn = document.getElementById('summary-edit-btn');
	var summaryContent = document.getElementById('summary-content');
	var newSummary = summaryContent.value;
	summaryEditBtn.innerHTML = '&nbsp&nbspEdit&nbsp&nbsp';
	summaryEditBtn.setAttribute('onclick', 'editApplySummary()');
	applicationSummary.innerHTML = template;
	summaryContent = document.getElementById('summary-content');
	summaryContent.innerHTML = newSummary;
}

function formatParams(params){
  return "?" + Object
        .keys(params)
        .map(function(key){
          return key+"="+encodeURIComponent(params[key])
        })
        .join("&")
}

function loadCompany(companyId) {
	var params = {
		id : companyId
	}
	params = formatParams(params);
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:3103/company/view' + params, false);
	console.log(params);
	xhr.send();
	console.log(xhr.response);
	return JSON.parse(xhr.response);
}

function loadCategory(categoryId) {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:3103/category/view?id=' + parseFloat(categoryId), false);
	xhr.send();
	console.log(xhr.response);
	return JSON.parse(xhr.response);
}

function loadIndustry(industryId) {
	var params = {
		id : industryId
	}
	params = formatParams(params);
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:3103/industry/view' + params, false);
	xhr.send();
	console.log(xhr.response);
	return JSON.parse(xhr.response);
}

function loadJob(jobId) {
	var params = {
		id : parseFloat(jobId)
	}
	var param = formatParams(params)
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:3103/job/view' + param, false);
	xhr.send();
	console.log(xhr.response);
	return JSON.parse(xhr.response);
}

function loadJobs() {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:3103/job/all', false);
	xhr.send();
	console.log(xhr.response);
	var allJobs = JSON.parse(xhr.response);
	document.getElementById('job-list').innerHTML = '';
	allJobs.forEach(makeJob);
}

function loadAppliedPeople() {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:3103/job/all', false);
	xhr.send();
	var allJobs = JSON.parse(xhr.response);
	document.getElementById('job-list').innerHTML = '';
	allJobs.forEach(viewAppliedPeople);
}

function jobSearch() {
	var data = new FormData();
	data.append('name', document.getElementById("job-search-input" ).value);
	var params = {
		name : document.getElementById("job-search-input" ).value
	}
	var param = formatParams(params)
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:3103/job/search' + param, false);
	xhr.send(data);
	var jobs = JSON.parse(xhr.response);
	document.getElementById('job-list').innerHTML = '';
	jobs.forEach(makeJob);
}

function recruiterJobSearch() {
	var data = new FormData();
	data.append('name', document.getElementById("job-search-input" ).value);
	var params = {
		name : document.getElementById("job-search-input" ).value
	}
	var param = formatParams(params)
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:3103/job/search' + param, false);
	xhr.send(data);
	var jobs = JSON.parse(xhr.response);
	document.getElementById('job-list').innerHTML = '';
	jobs.forEach(viewAppliedPeople);
}

function seeJob(jobId) {
	sessionStorage.setItem('jobId', parseFloat(jobId));
	window.location = 'job-detail.html';
}

function seeApplicant(jobId) {
	sessionStorage.setItem('jobId', jobId);
	window.location = 'applicant-summary.html';
}

function viewApplicant(applicationId) {
	sessionStorage.setItem('applicationId', applicationId);
	window.location = 'application-detail.html';
}

function formatJob() {
	var job = loadJob(parseFloat(sessionStorage.getItem('jobId')));
	console.log(sessionStorage.getItem('jobId'));
	var jobName = document.getElementsByClassName('job-name');
	for (i = 0; i < jobName.length; i++) {
		jobName[i].innerHTML = job.name;
		jobName[i].value = job.name;
	}
	var jobSalary = document.getElementsByClassName('job-salary');
	for (i = 0; i < jobSalary.length; i++) {
		jobSalary[i].innerHTML = job.salary;
		jobSalary[i].value = job.salary;
	}
	var jobEmployment = document.getElementsByClassName('job-employment');
	for (i = 0; i < jobEmployment.length; i++) {
		jobEmployment[i].innerHTML = job.employment_type;
		jobEmployment[i].value = job.employment_type;
	}
	var jobSummary = document.getElementsByClassName('job-summary-info');
	for (i = 0; i < jobSummary.length; i++) {
		jobSummary[i].innerHTML = job.job_summary;
		jobSummary[i].value = job.job_summary;
	}
	var jobMinimum = document.getElementsByClassName('job-minimum');
	for (i = 0; i < jobMinimum.length; i++) {
		jobMinimum[i].innerHTML = job.min_qualification;
		jobMinimum[i].value = job.min_qualification;
	}
	var jobExpire = document.getElementsByClassName('job-expire-date');
	var date = new Date(job.expire_date);
	for (i = 0; i < jobExpire.length; i++) {
		jobExpire[i].innerHTML = date.toDateString();
		jobExpire[i].value = date.toDateString();
	}
	var companyName = document.getElementsByClassName('company-name');
	for (i = 0; i < companyName.length; i++) {
		companyName[i].innerHTML = job.company.name;
		companyName[i].value = job.company.name;
	}
	var companyAddress = document.getElementsByClassName('company-address');
	for (i = 0; i < companyAddress.length; i++) {
		companyAddress[i].innerHTML = job.company.address;
		companyAddress[i].value = job.company.address;
	}
	var companyCity = document.getElementsByClassName('company-city');
	for (i = 0; i < companyCity.length; i++) {
		companyCity[i].innerHTML = job.company.city;
		companyCity[i].value = job.company.city;
	}
}

function formatCompany() {
	var job = loadJob(sessionStorage.getItem('jobId'));
	console.log(job.company_id);
	var company = loadCompany(job.company_id);
	var companyName = document.getElementsByClassName('company-name');
	for (i = 0; i < companyName.length; i++) {
		companyName[i].innerHTML = company.name;
		companyName[i].value = company.name;
	}
	var companyAddress = document.getElementsByClassName('company-address');
	for (i = 0; i < companyAddress.length; i++) {
		companyAddress[i].innerHTML = company.address;
		companyAddress[i].value = company.address;
	}
	var companyCity = document.getElementsByClassName('company-city');
	for (i = 0; i < companyCity.length; i++) {
		companyCity[i].innerHTML = company.city;
		companyCity[i].value = company.city;
	}
}

function formatCategory() {
	var job = loadJob(sessionStorage.getItem('jobId'));
	var category = loadCategory(job.category_id);
	var categoryName = document.getElementsByClassName('category-name');
	for (i = 0; i < categoryName.length; i++) {
		categoryName[i].innerHTML = category.name;
		categoryName[i].value = category.name;
	}
}

function formatIndustry() {
	var job = loadJob(sessionStorage.getItem('jobId'));
	var company = loadCompany(job.company_id);
	console.log(company.industry.name);
	var industryName = document.getElementsByClassName('industry-name');
	for (i = 0; i < industryName.length; i++) {
		industryName[i].innerHTML = company.industry.name;
		industryName[i].value = company.industry.name;
	}
}


function apply() {
	var data = new FormData();
	data.append('owner', sessionStorage.getItem('email'));
	data.append('job_id', sessionStorage.getItem('jobId'));
	data.append('summary', document.getElementById('summary-content').innerHTML);
	data.append('cv_id', 0);
	data.append('resume_id', 0);
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'http://localhost:8000/job-application/create', false);
	xhr.send(data);
	if (xhr.status === 201) {
		window.location = 'job-application-done.html';
	} else {
		alert(xhr.status);
	}
}

function loadCounter(jobId) {
	var params = {
		job_id : jobId
	}
	params = formatParams(params);
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8000/job-application/count' + params, false);
	xhr.send();
	return xhr.response;
}

function loadApplication(applicationId) {
	var params = {
		id : applicationId
	}
	params = formatParams(params);
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8000/job-application/show' + params, false);
	xhr.send();
	console.log(xhr.response);
	return JSON.parse(xhr.response);
}

function loadApplicant(applicant) {
	var owner = loadJobseeker(applicant.owner);
	console.log(applicant.id);
	var template = 
			`
			<div class="box" onclick="viewApplicant(` + applicant.id + `)">
              <div class="columns">
                <div class="column is-1">
                </div>
                <div class="column is-2">
                  <figure class="image is-128x128">
                    <img class="is-rounded" src="https://bulma.io/images/placeholders/128x128.png">
                  </figure>
                </div>
                <div class="column is-8">
                  <span class="title is-4 jobseeker-name">` + owner.name + `</span><br/>
                  <div class="columns">
                    <div class="column is-10">
                      <span class="subtitle is-5">` + applicant.summary + `</span>
                    </div>
                  </div>
                </div>
                <div class="column is-1">
                </div>
              </div>
            </div>
			`;
	document.getElementById('applicants').innerHTML += template;
}

function loadApplicantOfJob(jobId) {
	var params = {
		job_id : sessionStorage.getItem('jobId')
	}
	params = formatParams(params);
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8000/job-application/pending' + params, false);
	xhr.send();
	var allApplicants = JSON.parse(xhr.response);
	allApplicants.forEach(loadApplicant);
}

function formatApplicant() {
	var application = loadApplication(sessionStorage.getItem('applicationId'));
	var owner = loadJobseeker(application.owner);
	console.log(application.owner);
	// var company = loadCompany(job.company_id);
	var ownerName = document.getElementsByClassName('application-name');
	for (i = 0; i < ownerName.length; i++) {
		ownerName[i].innerHTML = owner.name;
		ownerName[i].value = owner.name;
	}
	var applicantSummary = document.getElementsByClassName('application-summary');
	for (i = 0; i < applicantSummary.length; i++) {
		applicantSummary[i].innerHTML = application.summary;
		applicantSummary[i].value = application.summary;
	}
}

function accept() {
	var data = new FormData();
	data.append('id', sessionStorage.getItem('applicationId'));
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'http://localhost:8000/job-application/accept', false);
	xhr.send(data);
	var response = JSON.parse(xhr.response);
	window.location = 'application-respond.html';
}

function decline() {
	var data = new FormData();
	data.append('id', sessionStorage.getItem('applicationId'));
	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'http://localhost:8000/job-application/decline', false);
	xhr.send(data);
	var response = JSON.parse(xhr.response);
	window.location = 'application-respond.html';
}