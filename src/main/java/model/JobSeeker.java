package model;

import java.util.ArrayList;

public class JobSeeker extends User{
	
	protected String username, address, phoneNumber, summary;
	protected Cv cv;
	protected Resume resume;
	protected ArrayList <WorkExperience> workExperience;
	
	public JobSeeker(String name, String email, String password) {
		super(name, email, password);
		workExperience = new ArrayList<WorkExperience>();
	}

	@Override
	public void add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public Cv getCv() {
		return cv;
	}
	
	public Resume getResume() {
		return resume;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public void setCv(Cv cv) {
		this.cv = cv;
	}
	
	public void setResume(Resume resume) {
		this.resume = resume;
	}
	
	public void addWorkExperience(WorkExperience workExperience) {
		this.workExperience.add(workExperience);
	}
}
