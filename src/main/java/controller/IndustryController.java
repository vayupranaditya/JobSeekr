package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.*;
import model.Industry;

@CrossOrigin
@RestController
@RequestMapping("industry")
public class IndustryController {

    @PostMapping("add")
    public Industry add(@RequestParam Map<String, String> params) {
        if (params.get("name").equals(null) && params.get("id").equals(null)) return null;
    	Industry newIndustry = new Industry(
                params.get("name"), 
                params.get("id")
                );
        newIndustry.save();
    	return newIndustry.get(params.get("id"));
    }

    @GetMapping("all")
    public ArrayList <Industry> index() {
        Industry industry = new Industry();
        ArrayList <String> indices = industry.index();
        ArrayList <Industry> industryList = new ArrayList();
        for (String id : indices) {
            industryList.add(industry.get(id));
        }
        return industryList;
    }

    @GetMapping("view")
    public Industry view(@RequestParam String id) {
    // public ArrayList <String> view(@RequestParam String id) {
    	Industry industry = new Industry();
        return industry.get(id);
        // return industry;
    }


    @PostMapping("update")
    public Industry update(@RequestParam Map<String, String> params) {
    	Industry industry = new Industry();
        industry = industry.get(params.get("id"));
        industry.setName(params.get("name"));
        industry.update();
        return industry.get(params.get("id"));
    }

    @PostMapping("delete")
    public String delete(@RequestParam String id) {
    	Industry industry = new Industry();
    	industry = industry.get(id);
        industry.delete();
        return "Success";
    }
}