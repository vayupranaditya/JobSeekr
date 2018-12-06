package model;

import java.util.ArrayList;
import java.sql.*;
import database.Database;

public class JobSeeker extends User implements Database{
	
	protected String username, address, phoneNumber, summary;
	protected Cv cv;
	protected Resume resume;
	protected ArrayList <WorkExperience> workExperience;

	public JobSeeker(){
	}

	public JobSeeker(String name, String email, String password) {
		super(name, email, password);
		workExperience = new ArrayList<WorkExperience>();
	}

	public JobSeeker(String name, String email, String password, String username, String address, String phoneNumber, String summary, Cv cv, Resume resume) {
		super(name, email, password);
		this.username = username;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.summary = summary;
		this.cv = cv;
		this.resume = resume;
		workExperience = new ArrayList<WorkExperience>();
	}

	public void JobSeeker() {
		workExperience = new ArrayList<WorkExperience>();
	}

	@Override
	public void add() {
		try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = "INSERT INTO jobseeker "
            	+ "(name, email, password)"
                + " VALUE (?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, email);
            preparedStmt.setString (3, password);
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }		
	}

	public JobSeeker get(String email) {
		try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = "SELECT * FROM jobseeker WHERE email = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, email);
            ResultSet result = preparedStmt.executeQuery();
			result.absolute(1);
        	String resultName, resultEmail, resultPassword, resultUsername, resultAddress, resultPhoneNumber, resultSummary;
        	Long resultCvId, resultResumeId;
			Cv resultCv;
			Resume resultResume;
			resultName = result.getString("name");
			resultEmail = result.getString("email");
			resultPassword = result.getString("password");
			resultUsername = result.getString("username");
			resultAddress = result.getString("address");
			resultPhoneNumber = result.getString("phoneNumber");
			resultSummary = result.getString("summary");
			resultCvId = result.getLong("cv_id");
			resultResumeId = result.getLong("resume_id");
			resultCv = new Cv();
			resultResume = new Resume();
			resultCv = resultCv.get(resultCvId);
			resultResume = resultResume.get(resultResumeId);
        	JobSeeker jobseeker = new JobSeeker(
        		resultName, 
        		resultEmail, 
        		resultPassword, 
        		resultUsername,
        		resultAddress,
        		resultPhoneNumber,
        		resultSummary,
        		resultCv,
        		resultResume);
            conn.close();
            return jobseeker;
        } catch (Exception e) {
          	System.err.println("Industry error!");
          	System.err.println(e.getMessage());
          	return null;
        }
	}

	public void save() {
		try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = 
            	"UPDATE jobseeker SET"
		            	+ " name = ?, "
		            	+ " password = ?, "
		            	+ " username = ?, "
		            	+ " address = ?, "
		            	+ " phoneNumber = ?, "
		            	+ " summary = ?, "
		            	+ " cv_id = ?, "
		            	+ " resume_id = ? "
	                + " WHERE email = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, password);
            preparedStmt.setString (3, username);
            preparedStmt.setString (4, address);
            preparedStmt.setString (5, phoneNumber);
            preparedStmt.setString (6, summary);
            preparedStmt.setLong (7, cv.getId());
            preparedStmt.setLong (8, resume.getId());
            preparedStmt.setString (9, email);
            System.out.println(preparedStmt.toString());
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {

          	System.err.println(e.getMessage());
        }
	}

	@Override
	public void delete() {
		try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = 
            	"DELETE FROM jobseeker " 
	                + " WHERE email = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, email);
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
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
