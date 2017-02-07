package it.ariadne.bookingresources;

public abstract class Resource {
	
	private String id;
	private String description;
	
	public Resource(String id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	
	
	public Resource() {
		super();
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
