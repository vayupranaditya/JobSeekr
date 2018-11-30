package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import model.User;
import model.Recruiter;

@RestController
@RequestMapping("recruiter")
public class RecruiterController {

	ArrayList <Recruiter> recruiterList = new ArrayList <Recruiter>();

    @RequestMapping("signup")
    public Recruiter signup(@RequestParam Map<String, String> params) {
    	Recruiter newRecruiter = new Recruiter(params.get("name"), params.get("email"), params.get("password"));
    	recruiterList.add(newRecruiter);
    	return newRecruiter;
    }
    
    @RequestMapping("signin")
    public String signin(@RequestParam Map<String, String> params) {
    	for (Recruiter recruiter: recruiterList) {
    		if (recruiter.getEmail().equals(params.get("email"))) {
    			if (recruiter.getPassword().equals(params.get("password"))) {
    				return "Logged in!";
    			} else {
    				return "Wrong password specified!";
    			}
    		}
    	}
    	return "User not found!";
    }

    @RequestMapping("index")
    public ArrayList <Recruiter> index() {
    	return recruiterList;
    }

    @RequestMapping("view")
    public Recruiter view(@RequestParam String email) {
    	for (Recruiter recruiter: recruiterList) {
    		if (recruiter.getEmail().equals(email)) {
				return recruiter;
    		}
    	}
    	return null;
    }


    @RequestMapping("update")
    public Recruiter update(@RequestParam Map<String, String> params) {
    	for (Recruiter recruiter: recruiterList) {
    		if (recruiter.getEmail().equals(params.get("email"))) {
				if (!params.get("name").equals("")) recruiter.setName(params.get("name"));
				if (!params.get("email").equals("")) recruiter.setEmail(params.get("email"));
				if (!params.get("password").equals("")) recruiter.setPassword(params.get("password"));
				if (!params.get("userName").equals("")) recruiter.setUserName(params.get("userName"));
				if (!params.get("address").equals("")) recruiter.setAddress(params.get("address"));
				if (!params.get("email").equals("")) recruiter.setEmail(params.get("email"));
				if (!params.get("phoneNumber").equals("")) recruiter.setPhoneNumber(params.get("phoneNumber"));
				// if (!params.get("company").equals("")) recruiter.setCompany(params.get("name"));
				return recruiter;
    		}
    	}
    	return null;
    }

    @RequestMapping("delete")
    public String delete(@RequestParam String email) {
    	for (Recruiter recruiter: recruiterList) {
    		if (recruiter.getEmail().equals(email)) {
				recruiterList.remove(recruiter);
				return "success";
    		}
    	}
    	return "not found";
    }
}