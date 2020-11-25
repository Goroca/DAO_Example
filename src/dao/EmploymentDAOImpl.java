package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.*;

public class EmploymentDAOImpl implements EmploymentDAO{
	private List<Employment> employments;
	private Employment employer;
	
	@Override
	public List<Employment> getEmployments() throws Exception {
		// TODO Auto-generated method stub
		DBConnection dBConnection = new DBConnection("carlos", "goroca");
		employments = new ArrayList<Employment>();
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
	public void save(Employment employer) {
		// TODO Auto-generated method stub
		DBConnection dBConnection = new DBConnection("carlos", "goroca");

		try {
			dBConnection.addEmployment(employer);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Employment employer) {
		// TODO Auto-generated method stub
		DBConnection dBConnection = new DBConnection("carlos", "goroca");

		try {
			dBConnection.editEmployment(employer);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Employment seachById(int id) throws Exception {
		DBConnection dBConnection = new DBConnection("carlos", "goroca");
		try {
			ResultSet rs = dBConnection.getById(id);			
			
			if(rs.next()){
				List<String> phoneNumbers = getPhoneNumbes(rs.getInt("id"));
				employer = new Employment(rs.getInt("id"), rs.getString("name"), rs.getInt("departmentId"),Employment.Gender.valueOf(rs.getString("gender")),phoneNumbers);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return employer;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		DBConnection dBConnection = new DBConnection("carlos", "goroca");
		try {
			dBConnection.deleteEmployer(id);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public List<String> getPhoneNumbes(int idEmployer) throws Exception {
		// TODO Auto-generated method stub
		DBConnection dBConnection = new DBConnection("carlos", "goroca");
		List<String> phoneNumbers = new ArrayList<String>();
		try {
			ResultSet rs = dBConnection.getPhoneNumbers(idEmployer);
						
			while (rs.next()){
				phoneNumbers.add(rs.getString("number"));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return phoneNumbers;
	}
	

}
