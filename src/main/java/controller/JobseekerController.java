package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import model.User;
import model.JobSeeker;

@RestController
@RequestMapping("jobseeker")
class JobSeekerController {

	ArrayList <JobSeeker> jobseekerList = new ArrayList <JobSeeker>();

    @PostMapping("signup")
    public JobSeeker signup(@RequestParam Map<String, String> params) {
    	JobSeeker newJobSeeker = new JobSeeker(params.get("name"), params.get("email"), params.get("password"));
    	jobseekerList.add(newJobSeeker);
        newJobSeeker.add();
    	return newJobSeeker.get(params.get("email"));
    }
    
    @PostMapping("signin")
    public String signin(@RequestParam Map<String, String> params) {
        JobSeeker jobseeker = new JobSeeker();
        jobseeker = jobseeker.get(params.get("email"));
        if (jobseeker != null) {
            if (jobseeker.getPassword().equals(params.get("password"))) {
                return "{\"email\": \"" + jobseeker.getEmail() + "\","
                    + "\"code\": 200}";
            } else {
                return "{\"Code\": 403}";
            }
        } else {
            return "{\"Code\": 404}";
        }
    }

    @GetMapping("index")
    public ArrayList <JobSeeker> index() {
    	return jobseekerList;
    }

    @GetMapping("view")
    public JobSeeker view(@RequestParam String email) {
        JobSeeker jobseeker = new JobSeeker();
    	jobseeker = jobseeker.get(email);
        if (jobseeker != null) {
            return jobseeker;
        } else {
            return null;
        }
    }


    @PostMapping("update")
    public JobSeeker update(@RequestParam Map<String, String> params) {
        JobSeeker jobseeker = new JobSeeker();
        jobseeker = jobseeker.get(params.get("email"));
		if (!params.get("name").equals("")) jobseeker.setName(params.get("name"));
		if (!params.get("password").equals("")) jobseeker.setPassword(params.get("password"));
		if (!params.get("username").equals("")) jobseeker.setUsername(params.get("username"));
		if (!params.get("address").equals("")) jobseeker.setAddress(params.get("address"));
		if (!params.get("email").equals("")) jobseeker.setEmail(params.get("email"));
		if (!params.get("phoneNumber").equals("")) jobseeker.setPhoneNumber(params.get("phoneNumber"));
        // jobseeker.save();
        // return jobseeker.get(params.get("email"));
        return jobseeker;
    }

    @PostMapping("delete")
    public String delete(@RequestParam String email) {
        JobSeeker jobseeker = new JobSeeker();
        jobseeker = jobseeker.get(email);
        if (jobseeker != null) {
            jobseeker.delete();
            return "{\"Message\": 200}";
        } else {
            return "{\"Code\": 404}";
        }
    }
}