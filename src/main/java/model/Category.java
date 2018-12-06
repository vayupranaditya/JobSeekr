package model;

public class Category {
	protected String name;
	protected long id;
	//id from Database (using autoIncrement)
	
	public Category() {
	}
	public Category(String name) {
		 this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
