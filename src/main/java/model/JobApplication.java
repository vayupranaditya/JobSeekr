package model;

public class JobApplication {
	protected JobSeeker jobSeeker;
	protected String summary;
	protected WorkExperience workExperience;
	protected Cv cv;
	protected Resume resume;
	
	public JobApplication(JobSeeker jobSeeker, String summary, WorkExperience workExperience, Cv cv, Resume resume) {
		this.jobSeeker = jobSeeker;
		this.summary = summary;
		this.workExperience = workExperience;
		this.cv = cv;
		this.resume = resume;
	}
	
	public void add() {
		//add this object to database
	}
	
	public void delete() {
		//remove this object from database
	}
	
	public JobApplication read() {
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
	
	public void setWorkExperience(WorkExperience workExperience) {
		this.workExperience = workExperience;
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
	
	public WorkExperience getWorkExperience() {
		return workExperience;
	}
}
