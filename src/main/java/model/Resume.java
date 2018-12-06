package model;

public class Resume extends Document{
	public Resume() {
	}
	public Resume(String name) {
		 super(name);
	}
	 
	public String getName() {
		return name;
	}
	 
	 public void setName(String name) {
		this.name = name;
	}

	public Resume get(long id) {
		return null;
	}
}
