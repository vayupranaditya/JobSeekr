package model;

import java.util.*;
import database.Database;

public class Job implements Database{
	protected int id;
	protected String name, employmentType, jobSummary, minQualification, position;
	protected Date expireDate;
	protected long salary;
	protected Company company;
	protected Category category;
	protected Industry industry;
	protected ArrayList <JobApplication> JobApplication;

	public Job() {
	}
	
	public Job(String name, String employmentType, String jobSummary, String minQualification, String position, 
			Date expireDate, long salary, Company company, Category category, Industry industry) {
		 this.name = name;
		 this.employmentType = employmentType;
		 this.jobSummary = jobSummary;
		 this.minQualification = minQualification;
		 this.position = position;
		 this.expireDate = expireDate;
		 this.salary = salary;
		 this.company = company;
		 this.category = category;
		 this.industry = industry;
		 this.JobApplication = new ArrayList<JobApplication>();
	}
	
	public void save() {
		try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = "INSERT INTO job "
            	+ "(name, employment_types, job_summary, min_qualification, position, expire_date, salary, company_id, category_id, industry_id)"
                + " VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, name);
            preparedStmt.setString (2, employmentType);
            preparedStmt.setString (3, jobSummary);
            preparedStmt.setString (4, minQualification);
            preparedStmt.setString (5, position);
            preparedStmt.setDate (6, expireDate);
            preparedStmt.setLong (7, salary);
            preparedStmt.setString (8, company.getId());
            preparedStmt.setString (9, category.getId());
            preparedStmt.setString (10, industry.getId());
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {
          System.err.println("Error!");
          System.err.println(e.getMessage());
        }
	}
	
	public void edit(String name, String employmentType, String jobSummary, String minQualification, String position,
			Date expireDate, Company company, Category category, Industry industry) {
		if (name != "") this.setName(name);
		if (employmentType != "") this.setEmploymentType(employmentType);
		if (jobSummary != "") this.setJobSummary(jobSummary);
		if (minQualification != "") this.setMinQualification(minQualification);
		if (position != "") this.setPosition(position);
		if (expireDate != null) this.setExpireDate(expireDate);
		if (company != null) this.setCompany(company);
		if (category != null) this.setCategory(category);
		if (industry != null) this.setIndustry(industry);
	}
	
	public void delete() {
		try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = "DELETE FROM job WHERE id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, id);
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {
          	System.err.println("Error!");
          	System.err.println(e.getMessage());
        }
	}
	
	public Job read() {
		try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = "SELECT * FROM job WHERE id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, id);
            ResultSet result = preparedStmt.executeQuery();
			result.absolute(1);
        	int id;
        	String name, employmentType, jobSummary, minQualification, position, company_id, category_id, industry_id;
        	Date expireDate;
        	long salary;
        	id = result.getInt(1);
        	name = result.getString(2);
        	employmentType = result.getString(3);
        	jobSummary = result.getString(4);
        	minQualification = result.getString(5);
        	position = result.getString(6);
        	company_id = result.getString(9);
        	category_id = result.getString(10);
        	industry_id = result.getString(11);
        	expireDate = result.getDate(7);
        	salary = result.getLong(8);
        	Company company = new Company();
        	company = company.get(company_id);
        	Category category = new Category();
        	category = category.get(category_id);
        	Industry industry = new Industry();
        	industry = industry.get(industry_id);
        	Job job = new Job(name, employmentType, jobSummary, minQualification, position, 
				expireDate, salary, company, category, industry);
            conn.close();
            return job;
        } catch (Exception e) {
          	System.err.println("Error!");
          	System.err.println(e.getMessage());
          	return null;
        }
	}
	
	public void addApplication(JobSeeker jobSeeker, String summary, WorkExperience workExperience,
			Cv cv, Resume resume) {
		this.JobApplication.add(new JobApplication(jobSeeker, summary, workExperience, cv, resume));
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}
	
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	public void setIndustry(Industry industry) {
		this.industry = industry;
	}
	
	public void setJobSummary(String jobSummary) {
		this.jobSummary = jobSummary;
	}
	
	public void setMinQualification(String minQualification) {
		this.minQualification = minQualification;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public void setSalary(long salary) {
		this.salary = salary;
	}
	
	public JobApplication getApplication(int num) {
		return this.JobApplication.get(num);
	}
	
	public Category getCategory() {
		return category;
	}
	
	public Company getCompany() {
		return company;
	}
	
	public String getEmploymentType() {
		return employmentType;
	}
	
	public Date getExpireDate() {
		return expireDate;
	}
	
	public Industry getIndustry() {
		return industry;
	}
	
	public String getJobSummary() {
		return jobSummary;
	}
	
	public String getMinQualification() {
		return minQualification;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPosition() {
		return position;
	}
	
	public long getSalary() {
		return salary;
	}
}
