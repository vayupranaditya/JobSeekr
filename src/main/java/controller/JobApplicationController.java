package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import model.JobApplication;
import model.JobSeeker;
import model.Cv;
import model.Resume;

@RestController
@RequestMapping("jobApplication")
public class JobApplicationController {

    @PostMapping("add")
    public JobApplication add(@RequestParam Map<String, String> params) {
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker = jobSeeker.get(params.get("jobSeekerId"));
        Cv cv = new Cv();
        cv = cv.get(Long.parseLong(params.get("CvId")));
        Resume resume = new Resume();
        resume = resume.get(Long.parseLong(params.get("resumeId")));
    	JobApplication newJobApplication = new JobApplication(
                jobSeeker,
                Long.parseLong(params.get("job-id")),
                params.get("summary"),
                cv,
                resume
                );
        newJobApplication.save();
    	return newJobApplication;
    }

    @GetMapping("index")
    public ArrayList <JobApplication> index() {
        JobApplication jobApplication = new JobApplication();
        // ArrayList <JobApplication> jobApplicationList = jobApplication.getAll();
        ArrayList <JobApplication> jobApplicationList = new ArrayList <JobApplication>();
    	return jobApplicationList;
    }

    @GetMapping("view")
    public JobApplication view(@RequestParam long id) {
    // public ArrayList <String> view(@RequestParam String id) {
    	JobApplication jobApplication = new JobApplication();
        return jobApplication.get(id);
        // return jobApplication;
    }


    @PostMapping("update")
    public JobApplication update(@RequestParam Map<String, String> params) {
    	JobApplication jobApplication = new JobApplication();
        jobApplication = jobApplication.get(Long.parseLong(params.get("id")));
        jobApplication.save();
        return jobApplication.get(Long.parseLong(params.get("id")));
    }

    @PostMapping("delete")
    public String delete(@RequestParam long id) {
    	JobApplication jobApplication = new JobApplication();
    	jobApplication = jobApplication.get(id);
        jobApplication.delete();
        return "Success";
    }
}