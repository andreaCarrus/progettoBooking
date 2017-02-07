package it.ariadne.bookingresources;

public class Car extends Resource {

	private int numberOfSpace;
	private String type;

	public Car(String id, String description, int numberOfSpace, String type) {
		super(id, description);
		this.numberOfSpace = numberOfSpace;
		this.type = type;
	}

	public Car() {
		super();
	}

	public int getNumberOfSpace() {
		return numberOfSpace;
	}

	public void setNumberOfSpace(int numberOfSpace) {
		this.numberOfSpace = numberOfSpace;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
