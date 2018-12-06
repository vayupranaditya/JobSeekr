package model;

public class Document {
	protected String name;
	protected long id;

	public Document() {
		
	}
	public Document(String name) {
		 this.name = name;
	}
	 
	public String getName() {
		return name;
	}
	 
	public long getId() {
		return id;
	}
	 
	public void setName(String name) {
		this.name = name;
	}
}
