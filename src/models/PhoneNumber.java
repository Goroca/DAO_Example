package models;

public class PhoneNumber {

	private int id;
	private int employerId;
	private String number;
	public PhoneNumber() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PhoneNumber(int id, int employerId, String number) {
		super();
		this.id = id;
		this.employerId = employerId;
		this.number = number;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmployerId() {
		return employerId;
	}
	public void setEmployerId(int employerId) {
		this.employerId = employerId;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
}
