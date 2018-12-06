package model;

import java.util.ArrayList;
import java.sql.*;
import database.Database;

public class Recruiter extends User implements Database{
	protected String username, address, phoneNumber;
	protected Company company;

	public Recruiter(){
	}
	
	public Recruiter(String name, String email, String password) {
		 super(name, email, password);
	}

	public Recruiter(String name, String email, String password, String username, String address, String phoneNumber, Company company) {
		 super(name, email, password);
		 this.username = username;
		 this.address = address;
		 this.phoneNumber = phoneNumber;
		 this.company = company;
	}
	
	
	@Override
	public void add() {
		try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = "INSERT INTO recruiter "
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

	public Recruiter get(String email) {
		try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = "SELECT * FROM recruiter WHERE email = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, email);
            ResultSet result = preparedStmt.executeQuery();
			result.absolute(1);
        	String resultName, resultEmail, resultPassword, resultUsername, resultAddress, resultPhoneNumber;
        	Long resultCompanyId;
			Company resultCompany;
			resultCompany = new Company();
			resultName = result.getString("name");
			resultEmail = result.getString("email");
			resultPassword = result.getString("password");
			resultUsername = result.getString("username");
			resultAddress = result.getString("address");
			resultPhoneNumber = result.getString("phoneNumber");
			resultCompanyId = result.getLong("company_id");
			resultCompany = resultCompany.get(resultCompanyId);
        	Recruiter recruiter = new Recruiter(
        		resultName, 
        		resultEmail, 
        		resultPassword, 
        		resultUsername,
        		resultAddress,
        		resultPhoneNumber,
        		resultCompany);
            conn.close();
            return recruiter;
        } catch (Exception e) {
          	System.err.println("Industry error!");
          	System.err.println(e.getMessage());
          	return null;
        }
	}

	// public void update(String username, String address, String email, String phoneNumber, Company company) {
	// 	 if (username != "") this.setusername(username);
	// 	 if (address != "") this.setAddress(address);
	// 	 if (email != "") this.setEmail(email);
	// 	 if (phoneNumber != "") this.setPhoneNumber(phoneNumber);
	// 	 if (company != null) this.setCompany(company);
	// }

	@Override
	public void delete() {
		try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            String query = 
            	"DELETE FROM recruiter " 
	                + " WHERE email = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, email);
            preparedStmt.execute();
            conn.close();
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
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
	
	public void setUsername(String username) {
		this.username = username;
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
	
	public String getusername() {
		return username;
	}
}
