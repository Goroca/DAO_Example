package models;


public class Employment {
	private int id;
	private String name;
	private int departmentId;
	private Gender gender;
	
	public enum Gender {
		MALE,FEMALE;
		}

	public Employment(int id, String name, int departmentId, Gender gender) {
		super();
		this.id = id;
		this.name = name;
		this.departmentId = departmentId;
		this.gender = gender;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Employment [id=" + id + ", name=" + name + ", departmentId=" + departmentId + ", gender=" + gender + "]";
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


	public int getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}


	public Gender getGender() {
		return gender;
	}


	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
}
