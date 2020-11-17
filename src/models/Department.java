package models;

public class Department {
	private int departmentId;
	private String departmentName;
//	private DepartmentEnum departmentEnum;
//	
//	public enum DepartmentEnum {
//		RRHH,LOGISTICA, CONTABILIDAD, INFORMATICA;
//		}

	public Department(int departmentId, String departmentName) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
	
	
