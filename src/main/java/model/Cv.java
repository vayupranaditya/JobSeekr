package model;

public class Cv extends Document{
	public Cv(){
	}
	public Cv(String name) {
		 super(name);
	}
	 
	public String getName() {
		return name;
	}
	 
	public void setName(String name) {
		this.name = name;
	}

	public Cv get(long id) {
		return null;
	}
}
