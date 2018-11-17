import java.util.*;

public class Job {
	protected String name, employmentType, jobSummary, minQualification, position;
	protected Date expireDate;
	protected long salary;
	protected Company company;
	protected Category category;
	protected Industry industry;
	protected ArrayList <Application> application;
	
	public Job(String name, String employmentType, Company company) {
		 this.name = name;
		 this.employmentType = employmentType;
		 this.company = company;
		 this.application = new ArrayList<Application>();
	}
	
	public void add() {
		//add this object to database
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
		//delete this object from database
	}
	
	public Job read() {
		//read this object from database
		return this;
	}
	
	public void addApplication(JobSeeker jobSeeker, String summary, WorkExperience workExperience,
			Cv cv, Resume resume) {
		this.application.add(new Application(jobSeeker, summary, workExperience, cv, resume));
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
	
	public Application getApplication(int num) {
		return this.application.get(num);
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
