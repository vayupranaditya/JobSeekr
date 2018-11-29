package model;

import java.awt.Image;

public class Company {
	protected String name, address, city, website, about;
	protected Industry industry;
	protected Image logo;
	
	public void add() {
		//add this object to database
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
	
	public void delete() {
		//delete this object from database
	}
	
	public Company read() {
		//read object of this from database
		return this;
	}
	
	public Company(String name, String address, String city, String website, Industry industry) {
		 this.name = name;
		 this.address = address;
		 this.city = city;
		 this.website = website;
		 this.industry = industry;
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
