package models;

public class Department {
	private int departmentId;
	private DepartmentEnum departmentEnum;
	public Department(int departmentId, DepartmentEnum departmentEnum) {
		super();
		this.departmentId = departmentId;
		this.departmentEnum = departmentEnum;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public DepartmentEnum getDepartmentEnum() {
		return departmentEnum;
	}
	public void setDepartmentEnum(DepartmentEnum departmentEnum) {
		this.departmentEnum = departmentEnum;
	}
	
	
}
