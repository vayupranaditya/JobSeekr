
public class Category {
	protected String id, name;
	//id from Database (using autoIncrement)
	
	public Category(String name) {
		 this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
