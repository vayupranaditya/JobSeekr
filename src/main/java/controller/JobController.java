package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.*;
import java.text.SimpleDateFormat;
import model.*;

@CrossOrigin
@RestController
@RequestMapping("job")
public class JobController {

    @PostMapping("add")
    public Job add(@RequestParam Map<String, String> params, @RequestParam Date expireDate, @RequestParam long salary) {
        if (params.get("name").equals(null)) return null;
        Company company = new Company();
//        company = company.get(params.get("company"));
        Category category = new Category();
//        category = category.get(params.get("category"));
        Industry industry = new Industry();
        // industry = industry.get(params.get("industry-id"));
    	Job newJob = new Job(
                params.get("name"),
                params.get("employment-type"),
                params.get("job-summary"),
                params.get("min-qualification"),
                params.get("position"),
                expireDate,
                salary,
                company,
                category,
                industry
                );
        newJob.save();
    	return newJob;
    }

    @GetMapping("all")
    public ArrayList <Job> index() {
    	Job job = new Job();
        ArrayList <Long> indices = job.index();
        ArrayList <Job> jobList = new ArrayList();
        for (Long id : indices) {
            jobList.add(job.get(id));
        }
        return jobList;
    }

    @GetMapping("search")
    public ArrayList <Job> search(String name) {
        Job job = new Job();
        ArrayList <Long> indices = job.search(name);
        ArrayList <Job> jobList = new ArrayList();
        for (Long id : indices) {
            jobList.add(job.get(id));
        }
        return jobList;
    }

    @GetMapping("view")
    public Job view(@RequestParam long id) {
    	Job job = new Job();
        return job.get(id);
    }

    @PostMapping("update")
    public Job update(@RequestParam Map<String, String> params) {
//    	for (Job job: jobList) {
//    		if (job.getId().equals(params.get("id"))) {
//				if (!params.get("name").equals("")) job.setName(params.get("name"));
//				return job;
//    		}
//    	}
    	return null;
    }

    @PostMapping("delete")
    public String delete(@RequestParam long id) {
        Job job = new Job();
        job = job.get(id);
        if (job != null) {
            job.delete();
            return "{\"Message\": 200}";
        } else {
            return "{\"Code\": 404}";
        }
    }
}