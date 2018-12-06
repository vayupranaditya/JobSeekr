package model;

public class Document {
	protected String name, owner;
	protected long id;

	public Document() {		
	}

	public Document(String name, String owner) {
		 this.name = name;
		 this.owner = owner;
	}
	 
	public String getName() {
		return name;
	}
	 
	public long getId() {
		return id;
	}

	public String getOwner() {
		return owner;
	}
	 
	public void setName(String name) {
		this.name = name;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
}
