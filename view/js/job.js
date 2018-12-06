function getAllJobs() {
	var xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8000/job/all', false);
	xhr.send();
	var allJobs = JSON.parse(xhr.response);
	allJobs.forEach(makeJob);
}

function makeJob(job) {
	var template = `<article class="media">
                <figure class="media-left">
                  <p class="image is-64x64">
                    <img src="https://bulma.io/images/placeholders/128x128.png">
                  </p>
                </figure>
                <div class="media-content">
                  <div class="content">
                    <p>
                      <strong>` + job.position + ` (` + job.employment_type + `)</strong> <br />
                      Proofn Indonesia <br />
                      Bandung, Indonesia | IDR. 4.000.000 - 6.000.000<br />
                      <small class="is-pulled-right">Posted on Nov 1 2018, until Dec 20 2018</small>
                    </p>
                  </div>
                </div>
              </article>`;
    console.log(template);
    document.getElementById('job-list').innerHTML += template;
}