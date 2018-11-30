package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.sql.*;
import java.util.*;
import model.Job;

@RestController
@RequestMapping("job")
public class JobController {
    
    String myDriver = "org.gjt.mm.mysql.Driver";
    String myUrl = "jdbc:mysql://localhost/preparedstatement";
	ArrayList <Job> jobList = new ArrayList <Job>();

    @GetMapping("add")
    public void add() {
        try {
            // create a mysql database connection
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
        
            // create a sql date object so we can use it in our INSERT statement
            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

            // the mysql insert statement
            String query = " insert into users (first_name, last_name, date_created, is_admin, num_points)"
                + " values (?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, "Barney");
            preparedStmt.setString (2, "Rubble");
            preparedStmt.setDate   (3, startDate);
            preparedStmt.setBoolean(4, false);
            preparedStmt.setInt    (5, 5000);

            // execute the preparedstatement
            preparedStmt.execute();
          
            conn.close();
        } catch (Exception e) {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }
    }

    // @PostMapping("add")
    // public Job add(@RequestParam Map<String, String> params) {
    //     if (params.get("name").equals(null) && params.get("id").equals(null)) return null;
    // 	Job newJob = new Job(
    //             params.get("name"), 
    //             params.get("id")
    //             );
    // 	jobList.add(newJob);
    // 	return newJob;
    // }

    // @GetMapping("index")
    // public ArrayList <Job> index() {
    // 	return jobList;
    // }

    // @GetMapping("view")
    // public Job view(@RequestParam String id) {
    // 	for (Job job: jobList) {
    // 		if (job.getId().equals(id)) {
				// return job;
    // 		}
    // 	}
    // 	return null;
    // }

    // @PostMapping("update")
    // public Job update(@RequestParam Map<String, String> params) {
    // 	for (Job job: jobList) {
    // 		if (job.getId().equals(params.get("id"))) {
				// if (!params.get("name").equals("")) job.setName(params.get("name"));
				// return job;
    // 		}
    // 	}
    // 	return null;
    // }

    // @PostMapping("delete")
    // public String delete(@RequestParam String id) {
    // 	for (Job job: jobList) {
    // 		if (job.getId().equals(id)) {
				// jobList.remove(job);
				// return "success";
    // 		}
    // 	}
    // 	return "not found";
    // }
}