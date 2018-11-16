
public class WorkExperience {
	protected String position, company;
	protected int duration; //in month
	
	public WorkExperience(String position, String company, int duration) {
		 this.position = position;
		 this.company = company;
		 this.duration = duration;
	}
	
	public String getPosition() {
		return position;
	}
	
	public String getCompany() {
		return company;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
}
