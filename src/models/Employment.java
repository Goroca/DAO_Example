package models;


public class Employment {
	private int id;
	private String name;
	private Department department;
	private Gender gender;


	public Employment(int id, String name, Department department, Gender gender) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.gender = gender;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Employment [id=" + id + ", name=" + name + ", department=" + department + ", gender=" + gender + "]";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}


	public Gender getGender() {
		return gender;
	}


	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
}
