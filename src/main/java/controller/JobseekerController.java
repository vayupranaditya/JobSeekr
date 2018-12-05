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
    	return newJobSeeker;
    }
    
    @PostMapping("signin")
    public String signin(@RequestParam Map<String, String> params) {
    	for (JobSeeker jobseeker: jobseekerList) {
    		if (jobseeker.getEmail().equals(params.get("email"))) {
    			if (jobseeker.getPassword().equals(params.get("password"))) {
    				return "Logged in!";
    			} else {
    				return "Wrong password specified!";
    			}
    		}
    	}
    	return "User not found!";
    }

    @GetMapping("index")
    public ArrayList <JobSeeker> index() {
    	return jobseekerList;
    }

    @GetMapping("view")
    public JobSeeker view(@RequestParam String email) {
    	for (JobSeeker jobseeker: jobseekerList) {
    		if (jobseeker.getEmail().equals(email)) {
				return jobseeker;
    		}
    	}
    	return null;
    }


    @PostMapping("update")
    public JobSeeker update(@RequestParam Map<String, String> params) {
    	for (JobSeeker jobseeker: jobseekerList) {
    		if (jobseeker.getEmail().equals(params.get("email"))) {
				if (!params.get("name").equals("")) jobseeker.setName(params.get("name"));
				if (!params.get("email").equals("")) jobseeker.setEmail(params.get("email"));
				if (!params.get("password").equals("")) jobseeker.setPassword(params.get("password"));
				if (!params.get("userName").equals("")) jobseeker.setUsername(params.get("userName"));
				if (!params.get("address").equals("")) jobseeker.setAddress(params.get("address"));
				if (!params.get("email").equals("")) jobseeker.setEmail(params.get("email"));
				if (!params.get("phoneNumber").equals("")) jobseeker.setPhoneNumber(params.get("phoneNumber"));
				// if (!params.get("company").equals("")) jobseeker.setCompany(params.get("name"));
				return jobseeker;
    		}
    	}
    	return null;
    }

    @PostMapping("delete")
    public String delete(@RequestParam String email) {
    	for (JobSeeker jobseeker: jobseekerList) {
    		if (jobseeker.getEmail().equals(email)) {
				jobseekerList.remove(jobseeker);
				return "success";
    		}
    	}
    	return "not found";
    }
}