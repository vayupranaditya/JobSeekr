package model;

import java.awt.Image;
import java.sql.*;
import database.Database;

public class Company implements Database{
	protected String name, address, city, website, about;
	protected Industry industry;
	protected Image logo;
	protected long id;
	
	public Company() {
	}
	
	 public void save() {
	 	try {
             Class.forName(dbDriver);
             Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
             String query = "INSERT INTO company "
             	+ "(name, address, city, website, about, industry_id, logo)"
                 + " VALUE (?, ?, ?, ?, ?, ?, ?)";
             PreparedStatement preparedStmt = conn.prepareStatement(query);
             preparedStmt.setString (1, name);
             preparedStmt.setString (2, address);
             preparedStmt.setString (3, city);
             preparedStmt.setString (4, website);
             preparedStmt.setString (5, about);
             preparedStmt.setString (6, industry.getId());
             //until file handling implemented
             preparedStmt.setString (7, "null");
             preparedStmt.execute();
             conn.close();
         } catch (Exception e) {
           System.err.println("Company error!");
           System.err.println(e.getMessage());
         }
	 }
	 
	 public Company get(long id) {
			try {
	            Class.forName(dbDriver);
	            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
	            String query = "SELECT * FROM company WHERE id = ?";
	            PreparedStatement preparedStmt = conn.prepareStatement(query);
	            preparedStmt.setLong (1, id);
	            ResultSet result = preparedStmt.executeQuery();
				result.absolute(1);
				long resultId = result.getLong(1);
	        	String resultName = result.getString(2);
	        	String resultAddress = result.getString(3);
	        	String resultCity = result.getString(4);
	        	String resultWebsite = result.getString(5);
	        	String resultAbout = result.getString(6);
	        	String resultIndustryId = result.getString(7);
	        	Industry industry = new Industry();
	        	industry = industry.get(resultIndustryId);
	        	String resultLogo = result.getString(8);
	        	Image image = null;
	        	Company company = new Company(
	        			resultId, resultName, resultAddress, resultCity, resultWebsite, resultAbout, industry, image
	        			);
	            conn.close();
	            return company;
	        } catch (Exception e) {
	          	System.err.println("Company error!");
	          	System.err.println(e.getMessage());
	          	return null;
	        }
		}
	
	public void edit(String name, String address, String city, 
			String website, String about, Industry industry, Image logo) {
		if (name != "") this.setName(name);
		if (city != "") this.setCity(city);
		if (about != "") this.setAbout(about);
		if (address != "") this.setAddress(address);
		if (website != "") this.setWebsite(website);
		if (industry != null) this.setIndustry(industry);
		if (logo != null) this.setLogo(logo);
	}
	
	public long getId() {
		return id;
	}
	
	public void delete() {
		//delete this object from database
	}
	
	public Company read() {
		//read object of this from database
		return this;
	}
	
	public Company(String name, String address, String city, String website, String about, Industry industry, Image logo) {
		 this.name = name;
		 this.address = address;
		 this.city = city;
		 this.website = website;
		 this.about = about;
		 this.industry = industry;
		 this.logo = logo;
	}
	
	public Company( long id, String name, String address, String city, String website, String about, Industry industry, Image logo) {
		 this.id = id;
		 this.name = name;
		 this.address = address;
		 this.city = city;
		 this.website = website;
		 this.about = about;
		 this.industry = industry;
		 this.logo = logo;
	}
	
	public String getAbout() {
		return about;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getCity() {
		return city;
	}
	
	public Industry getIndustry() {
		return industry;
	}
	
	public Image getLogo() {
		return logo;
	}
	
	public String getName() {
		return name;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public void setAbout(String about) {
		this.about = about;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setIndustry(Industry industry) {
		this.industry = industry;
	}
	
	public void setLogo(Image logo) {
		this.logo = logo;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
}
