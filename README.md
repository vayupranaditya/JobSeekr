# JobSeekr

An online job portal made with Java Spring.

In order to build this application, you need to [install Maven first](https://maven.apache.org/install.html).

Once you've done with your installation, head up to application directory and type `mvn install` to build this application.

Then you need to run this application by typing `java -jar target/jobseekr-rest-api-0.1.0.jar`

Routes:
- Post: /industry/add {string name, id}
- Get: /industry/view {string id}
- Post: /industry/update {string name, id}
- Post: industry/delete {string id}
- Post: /job/add {string name, employment-type, job-summary, min-qualification, position, industry-id}
- Get: /job/view {(long)int id}
- Post: /company/add {string name, address, city, website, about, industry}
- Get: /company/view {string id}

Keep updated on [this repository](https://github.com/vayupranaditya/jobseekr)
