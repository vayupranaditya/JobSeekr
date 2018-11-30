package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import model.Company;

@RestController
@RequestMapping("company")
public class CompanyController {

	ArrayList <Company> companyList = new ArrayList <Company>();

    // @RequestMapping("add")
    // public Company add(@RequestParam Map<String, String> params) {
    // 	Company newCompany = new Company(
    //             params.get("name"), 
    //             params.get("address"), 
    //             params.get("city"),
    //             params.get("website")
    //             // params.get("industry") search for industry first!
    //             );
    // 	companyList.add(newCompany);
    // 	return newCompany;
    // }

    @RequestMapping("index")
    public ArrayList <Company> index() {
    	return companyList;
    }

    @RequestMapping("view")
    public Company view(@RequestParam String name) {
    	for (Company company: companyList) {
    		if (company.getName().equals(name)) {
				return company;
    		}
    	}
    	return null;
    }


    @RequestMapping("update")
    public Company update(@RequestParam Map<String, String> params) {
    	for (Company company: companyList) {
    		if (company.getName().equals(params.get("name"))) {
				if (!params.get("name").equals("")) company.setName(params.get("name"));
				if (!params.get("address").equals("")) company.setAddress(params.get("address"));
				if (!params.get("city").equals("")) company.setCity(params.get("city"));
				if (!params.get("website").equals("")) company.setWebsite(params.get("website"));
				if (!params.get("about").equals("")) company.setAbout(params.get("about"));
                // if (!params.get("industry").equals("")) company.setIndustry(params.get("industry"));         search for industry first!
				// if (!params.get("logo").equals("")) company.setLogo(params.get("logo"));     file handling!
				return company;
    		}
    	}
    	return null;
    }

    @RequestMapping("delete")
    public String delete(@RequestParam String name) {
    	for (Company company: companyList) {
    		if (company.getName().equals(name)) {
				companyList.remove(company);
				return "success";
    		}
    	}
    	return "not found";
    }
}