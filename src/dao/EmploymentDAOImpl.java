package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.*;

public class EmploymentDAOImpl implements EmploymentDAO{

	@Override
	public List<Employment> list() {
		// TODO Auto-generated method stub
		DBConnection dBConnection = new DBConnection("carlos", "goroca");
		List<Employment> employments = new ArrayList<Employment>();

		try {
			ResultSet rs = dBConnection.getEmploymentTable();
			
			while (rs.next()){
				employments.add(new Employment(rs.getInt("id"), rs.getString("name"), rs.getInt("departmentId"),Employment.Gender.valueOf(rs.getString("gender"))));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return employments;
	}

	@Override
	public void save(Employment student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Employment student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employment seachById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
