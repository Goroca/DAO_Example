package dao;

import java.util.List;

import models.Department;

public interface DepartmentDAO {
	public List<Department> getDepartments();

	public void save(Department department);

	public void update(Department department);

	public Department seachById(int id);

	public void delete(int id);
}
