package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import model.User;
import model.Recruiter;

@RestController
@RequestMapping("recruiter")
public class RecruiterController {

	ArrayList <Recruiter> recruiterList = new ArrayList <Recruiter>();

    @PostMapping("signup")
    public Recruiter signup(@RequestParam Map<String, String> params) {
    	Recruiter newRecruiter = new Recruiter(params.get("name"), params.get("email"), params.get("password"));
    	recruiterList.add(newRecruiter);
        newRecruiter.add();
    	return newRecruiter.get(params.get("email"));
    }
    
    @PostMapping("signin")
    public String signin(@RequestParam Map<String, String> params) {
    	Recruiter recruiter = new Recruiter();
        recruiter = recruiter.get(params.get("email"));
        if (recruiter != null) {
            if (recruiter.getPassword().equals(params.get("password"))) {
                return "{\"email\": \"" + recruiter.getEmail() + "\","
                    + "\"code\": 200}";
            } else {
                return "{\"Code\": 403}";
            }
        } else {
            return "{\"Code\": 404}";
        }
    }

    @GetMapping("index")
    public ArrayList <Recruiter> index() {
    	return recruiterList;
    }

    @GetMapping("view")
    public Recruiter view(@RequestParam String email) {
    	for (Recruiter recruiter: recruiterList) {
    		if (recruiter.getEmail().equals(email)) {
				return recruiter;
    		}
    	}
    	return null;
    }


    @PostMapping("update")
    public Recruiter update(@RequestParam Map<String, String> params) {
    	for (Recruiter recruiter: recruiterList) {
    		if (recruiter.getEmail().equals(params.get("email"))) {
				if (!params.get("name").equals("")) recruiter.setName(params.get("name"));
				if (!params.get("email").equals("")) recruiter.setEmail(params.get("email"));
				if (!params.get("password").equals("")) recruiter.setPassword(params.get("password"));
				if (!params.get("username").equals("")) recruiter.setUsername(params.get("username"));
				if (!params.get("address").equals("")) recruiter.setAddress(params.get("address"));
				if (!params.get("email").equals("")) recruiter.setEmail(params.get("email"));
				if (!params.get("phoneNumber").equals("")) recruiter.setPhoneNumber(params.get("phoneNumber"));
				// if (!params.get("company").equals("")) recruiter.setCompany(params.get("name"));
				return recruiter;
    		}
    	}
    	return null;
    }

    @PostMapping("delete")
    public String delete(@RequestParam String email) {
        Recruiter recruiter = new Recruiter();
        recruiter = recruiter.get(email);
        if (recruiter != null) {
            recruiter.delete();
            return "{\"Message\": 200}";
        } else {
            return "{\"Code\": 404}";
        }
    }
}