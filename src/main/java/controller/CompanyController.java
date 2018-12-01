package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.Image;
import java.util.*;
import model.Company;
import model.Industry;

@RestController
@RequestMapping("company")
public class CompanyController {

	ArrayList <Company> companyList = new ArrayList <Company>();

     @PostMapping("add")
     public Company add(@RequestParam Map<String, String> params) {
    	Industry industry = new Industry();
    	industry = industry.get(params.get("industry"));
    	Image image = null;
     	Company newCompany = new Company(
                 params.get("name"), 
                 params.get("address"), 
                 params.get("city"),
                 params.get("website"),
                 params.get("about"),
                 industry,
                 image
                 );
     	companyList.add(newCompany);
     	newCompany.save();
     	return newCompany;
     }

    @GetMapping("index")
    public ArrayList <Company> index() {
    	return companyList;
    }

    @GetMapping("view")
    public Company view(@RequestParam long id) {
    	Company company = new Company();
    	company = company.get(id);
    	return company;
    }


    @PostMapping("update")
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

    @PostMapping("delete")
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