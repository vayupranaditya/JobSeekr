package model;

import java.sql.*;
import database.Database;

public class JobApplication implements Database{
	protected long id, jobId;
	protected JobSeeker jobSeeker;
	protected String summary;
	protected Cv cv;
	protected Resume resume;
	protected int isAccepted;

	public JobApplication() {
	}
	
	public JobApplication(JobSeeker jobSeeker, long jobId, String summary, Cv cv, Resume resume) {
		this.jobSeeker = jobSeeker;
		this.jobId = jobId;
		this.summary = summary;
		this.cv = cv;
		this.resume = resume;
	}
	
	public void add() {
		try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = "INSERT INTO job_application "
            	+ "(jobseeker_id, job_id, summary, cv_id, resume_id)"
                + " VALUE (?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, jobSeeker.getEmail());
            preparedStmt.setString (2, summary);
            preparedStmt.setLong (3, jobId);
            preparedStmt.setLong (4, cv.getId());
            preparedStmt.setLong (5, resume.getId());
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {
          	System.err.println("JobApplication error!");
          	System.err.println(e.getMessage());
        }
	}
	
	public void delete() {
		//remove this object from database
	}

	public void save() {
		//update to database
	}
	
	public JobApplication get(long id) {
		//read this object from database
		return this;
	}
	
	public void setCv(Cv cv) {
		this.cv = cv;
	}
	
	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}
	
	public void setResume(Resume resume) {
		this.resume = resume;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setIsAccepted(int isAccepted) {
		this.isAccepted = isAccepted;
	}
	
	public long getId() {
		return id;
	}

	public long getJobId() {
		return jobId;
	}

	public Cv getCv() {
		return cv;
	}
	
	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}
	
	public Resume getResume() {
		return resume;
	}
	
	public String getSummary() {
		return summary;
	}

	public int getIsAccepted() {
		return isAccepted;
	}
}
