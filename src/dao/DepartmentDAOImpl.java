package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import models.Department;


public class DepartmentDAOImpl implements DepartmentDAO{
	private List<Department> departments;

	@Override
	public List<Department> getDepartments() {
		// TODO Auto-generated method stub
		DBConnection dBConnection = new DBConnection("carlos", "goroca");
		departments = new ArrayList<Department>();

		try {
			ResultSet rs = dBConnection.getDepartmentTable();
			
			while (rs.next()){
				departments.add(new Department(rs.getInt("idDepartment"), rs.getString("name")));//,Department.DepartmentEnum.valueOf(rs.getString("name"))));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return departments;
	}

	@Override
	public void save(Department department) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Department department) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department seachById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	


}
