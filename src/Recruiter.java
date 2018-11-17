
public class Recruiter extends User{
	protected String userName, address, email, phoneNumber;
	protected Company company;
	
	public Recruiter(String name, String email, String password) {
		 super(name, email, password);
	}
	
	
	@Override
	public void add() {
		// add this object to database
		
	}

	public void update(String userName, String address, String email, String phoneNumber, Company company) {
		 if (userName != "") this.setUserName(userName);
		 if (address != "") this.setAddress(address);
		 if (email != "") this.setEmail(email);
		 if (phoneNumber != "") this.setPhoneNumber(phoneNumber);
		 if (company != null) this.setCompany(company);
	}

	@Override
	public void delete() {
		// remove this object from database
		
	}
	
	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		super.setEmail(email);
	}
	
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName(name);
	}
	
	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		super.setPassword(password);
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return super.getEmail();
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return super.getPassword();
	}
	
	public String getAddress() {
		return address;
	}
	
	public Company getCompany() {
		return company;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getUserName() {
		return userName;
	}
}
