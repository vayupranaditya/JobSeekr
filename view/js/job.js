function makeJob(job) {
	var company = loadCompany(job.company_id);
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
                      ` + company.name + ` <br />
                      ` + company.city + `, Indonesia | IDR. ` + job.salary + `<br />
                      <small class="is-pulled-right">Until ` + job.expire_date + `</small>
                    </p>
                  </div>
                </div>
            </article>`;
    document.getElementById('job-list').innerHTML += template;
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
	xhr.send();
	console.log(xhr.response);
	return JSON.parse(xhr.response);
}

function loadCategory(categoryId) {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:3103/category/view?id=' + categoryId, false);
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
	xhr.open('GET', 'http://localhost:8000/industry/view' + params, false);
	xhr.send();
	console.log(xhr.response);
	return JSON.parse(xhr.response);
}

function loadJob(jobId) {
	var params = {
		id : jobId
	}
	var param = formatParams(params)
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8000/job/view' + param, false);
	xhr.send();
	console.log(xhr.response);
	return JSON.parse(xhr.response);
}

function loadJobs() {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8000/job/all', false);
	xhr.send();
	var allJobs = JSON.parse(xhr.response);
	document.getElementById('job-list').innerHTML = '';
	allJobs.forEach(makeJob);
}

function jobSearch() {
	var data = new FormData();
	data.append('name', document.getElementById("job-search-input" ).value);
	var params = {
		name : document.getElementById("job-search-input" ).value
	}
	var param = formatParams(params)
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8000/job/search' + param, false);
	xhr.send(data);
	var jobs = JSON.parse(xhr.response);
	document.getElementById('job-list').innerHTML = '';
	jobs.forEach(makeJob);
}

function seeJob(jobId) {
	sessionStorage.setItem('jobId', jobId);
	window.location = 'job-detail.html';
}

function formatJob() {
	var job = loadJob(sessionStorage.getItem('jobId'));
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
