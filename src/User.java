
public abstract class User {
	protected String name, email, password;
	
	public User(String name, String email, String password) {
		 this.name = name;
		 this.email = email;
		 this.password = password;
	}
	
	public User(String email, String password) {
		 this.email = email;
		 this.password = password;
	}
	
	public abstract void create();
	public abstract void add();
	public abstract void update();
	public abstract void delete();
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
