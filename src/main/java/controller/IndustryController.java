package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import model.Industry;

@RestController
@RequestMapping("industry")
public class IndustryController {

	ArrayList <Industry> industryList = new ArrayList <Industry>();

    @RequestMapping("add")
    public Industry add(@RequestParam Map<String, String> params) {
        if (params.get("name").equals(null) && params.get("id").equals(null)) return null;
    	Industry newIndustry = new Industry(
                params.get("name"), 
                params.get("id")
                );
    	industryList.add(newIndustry);
    	return newIndustry;
    }

    @RequestMapping("index")
    public ArrayList <Industry> index() {
    	return industryList;
    }

    @RequestMapping("view")
    public Industry view(@RequestParam String id) {
    	for (Industry industry: industryList) {
    		if (industry.getId().equals(id)) {
				return industry;
    		}
    	}
    	return null;
    }


    @RequestMapping("update")
    public Industry update(@RequestParam Map<String, String> params) {
    	for (Industry industry: industryList) {
    		if (industry.getId().equals(params.get("id"))) {
				if (!params.get("name").equals("")) industry.setName(params.get("name"));
				return industry;
    		}
    	}
    	return null;
    }

    @RequestMapping("delete")
    public String delete(@RequestParam String id) {
    	for (Industry industry: industryList) {
    		if (industry.getId().equals(id)) {
				industryList.remove(industry);
				return "success";
    		}
    	}
    	return "not found";
    }
}